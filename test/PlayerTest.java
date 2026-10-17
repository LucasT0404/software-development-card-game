import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import static org.junit.jupiter.api.Assertions.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.concurrent.atomic.AtomicInteger;

public class PlayerTest {
    @Test
    void turnKeepsFourCardsAndDiscardsProperly() {
        CardDeck draw = new CardDeck(1);
        CardDeck discard = new CardDeck(2);
        StringWriter writer = new StringWriter();
        PrintWriter output = new PrintWriter(writer);
        Player player = new Player(1, draw, discard, new AtomicInteger(0), output);
        player.receiveCard(new Card(6));
        player.receiveCard(new Card(7));
        player.receiveCard(new Card(4));
        player.receiveCard(new Card(2));
        draw.addCard(new Card(9));

        assertTrue(player.playTurn());
        String log = writer.toString();
        assertEquals("7 4 2 9", player.handContents());
        assertEquals(0, draw.size());
        assertEquals("deck2 contents: 6", discard.contents());
        assertTrue(log.contains("player 1 draws a 9 from deck 1"));
        assertTrue(log.contains("player 1 discards a 6 to deck 2"));
        assertTrue(log.contains("player 1 current hand is 7 4 2 9"));
    }

    @Test
    void emptyDrawDeckReturnsFalseTurn() {
        CardDeck draw = new CardDeck(1);
        CardDeck discard = new CardDeck(2);
        Player player = new Player(1, draw, discard, new AtomicInteger(0), new PrintWriter(new StringWriter()));
        player.receiveCard(new Card(6));
        player.receiveCard(new Card(7));
        player.receiveCard(new Card(4));
        player.receiveCard(new Card(2));

        assertFalse(player.playTurn());
        assertEquals("6 7 4 2", player.handContents());
    }

    @Test
    void totalCardsConserved() {
        CardDeck draw = new CardDeck(1);
        CardDeck discard = new CardDeck(2);
        Player player = new Player(1, draw, discard, new AtomicInteger(0), new PrintWriter(new StringWriter()));
        player.receiveCard(new Card(6));
        player.receiveCard(new Card(7));
        player.receiveCard(new Card(4));
        player.receiveCard(new Card(2));
        for (int i = 0; i < 4; i++) {
            draw.addCard(new Card(i));
        }
        discard.addCard(new Card(2));
        int totalCards = player.getHandSize() + draw.size() + discard.size();

        for (int i = 0; i < 3; i++) {
            assertTrue(player.playTurn());
            assertEquals(totalCards, player.getHandSize() + discard.size() + draw.size());
        }
    }

    @Test
    @Timeout(5)
    void oneDeckAsBothDrawAndDiscard() {
        CardDeck draw = new CardDeck(1);
        CardDeck discard = draw;
        Player player = new Player(1, draw, discard, new AtomicInteger(0), new PrintWriter(new StringWriter()));
        player.receiveCard(new Card(6));
        player.receiveCard(new Card(7));
        player.receiveCard(new Card(4));
        player.receiveCard(new Card(2));
        for (int i = 0; i < 4; i++) {
            draw.addCard(new Card(i));
        }

        assertTrue(player.playTurn());
        assertEquals("7 4 2 0", player.handContents());
        assertEquals("deck1 contents: 1 2 3 6", draw.contents());
    }

    @Test
    void constructorThrowsOnPlayerZero() {
        CardDeck draw = new CardDeck(1);
        CardDeck discard = new CardDeck(2);
        assertThrows(IllegalArgumentException.class,
                () -> new Player(0, draw, discard, new AtomicInteger(0), new PrintWriter(new StringWriter())));
    }

    @Test
    void constructorThrowsOnNullDeck() {
        CardDeck draw = new CardDeck(1);
        CardDeck discardNull = null;
        CardDeck drawNull = null;
        CardDeck discard = new CardDeck(2);
        assertThrows(IllegalArgumentException.class,
                () -> new Player(1, draw, discardNull, new AtomicInteger(0), new PrintWriter(new StringWriter())));
        assertThrows(IllegalArgumentException.class,
                () -> new Player(1, drawNull, discard, new AtomicInteger(0), new PrintWriter(new StringWriter())));
    }
}
