import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.NoSuchElementException;

public class CardDeckTest {
    @Test
    void fifoTest() {
        CardDeck deck = new CardDeck(1);
        deck.addCard(new Card(1));
        deck.addCard(new Card(2));
        deck.addCard(new Card(3));
        assertEquals(1, deck.drawCard().getValue());
        assertEquals(2, deck.drawCard().getValue());
        assertEquals(3, deck.drawCard().getValue());
    }

    @Test
    void throwsEmptyDeckException() {
        CardDeck deck = new CardDeck(1);
        assertThrows(NoSuchElementException.class, () -> deck.drawCard());
    }

    @Test
    void testIsEmptyUntilAddCard() {
        CardDeck deck = new CardDeck(1);
        assertTrue(deck.isEmpty());
        deck.addCard(new Card(1));
        assertFalse(deck.isEmpty());
    }

    @Test
    void testSizeTracking() {
        CardDeck deck = new CardDeck(1);
        assertEquals(0, deck.size());
        deck.addCard(new Card(1));
        deck.addCard(new Card(2));
        deck.addCard(new Card(3));
        assertEquals(3, deck.size());
        deck.drawCard();
        assertEquals(2, deck.size());
    }

    @Test
    void testConstructorDeckNumberValidation() {
        assertThrows(IllegalArgumentException.class, () -> new CardDeck(0));
        assertDoesNotThrow(() -> new CardDeck(1));
    }

    @Test
    void testContentLineWithEmptyDeck() {
        CardDeck deck = new CardDeck(1);
        assertEquals("deck1 contents: ", deck.contents());
    }

    @Test
    void testContentLineWithFullDeck() {
        CardDeck deck = new CardDeck(2);
        deck.addCard(new Card(1));
        deck.addCard(new Card(2));
        deck.addCard(new Card(3));
        deck.addCard(new Card(3));
        assertEquals("deck2 contents: 1 2 3 3", deck.contents());
    }

    @Test
    void testConcurrencyNotLosingCards() throws InterruptedException {
        CardDeck deck = new CardDeck(1);
        Runnable addCards = () -> {
            for (int i = 0; i < 200000; i++) {
                deck.addCard(new Card(i));
            }
        };

        Thread thread1 = new Thread(addCards);
        Thread thread2 = new Thread(addCards);

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        assertEquals(400000, deck.size());
    }

}
