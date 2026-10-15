import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HandTest {
    @Test
    void preferedFourOfAKindWin() {
        Hand hand = new Hand(1);
        hand.addCard(new Card(1));
        hand.addCard(new Card(1));
        hand.addCard(new Card(1));
        hand.addCard(new Card(1));
        assertTrue(hand.isWinning());
    }

    @Test
    void nonPreferedFourOfAKindWin() {
        Hand hand = new Hand(6);
        hand.addCard(new Card(7));
        hand.addCard(new Card(7));
        hand.addCard(new Card(7));
        hand.addCard(new Card(7));
        assertTrue(hand.isWinning());
    }

    @Test
    void threeOfAKindOnly() {
        Hand hand = new Hand(6);
        hand.addCard(new Card(6));
        hand.addCard(new Card(7));
        hand.addCard(new Card(7));
        hand.addCard(new Card(7));
        assertFalse(hand.isWinning());
    }

    @Test
    void lessThanFourCards() {
        Hand hand = new Hand(6);
        hand.addCard(new Card(7));
        hand.addCard(new Card(7));
        hand.addCard(new Card(7));
        assertFalse(hand.isWinning());
    }

    @Test
    void fiveOfAKind() {
        Hand hand = new Hand(6);
        hand.addCard(new Card(7));
        hand.addCard(new Card(7));
        hand.addCard(new Card(7));
        hand.addCard(new Card(7));
        hand.addCard(new Card(7));
        assertFalse(hand.isWinning());
    }

    @Test
    void discardFirstNonPreferredCard() {
        Hand hand = new Hand(3);

        hand.addCard(new Card(1));
        hand.addCard(new Card(3));
        hand.addCard(new Card(4));
        hand.addCard(new Card(2));
        hand.addCard(new Card(5));
        Card discardedCard = hand.discard();

        assertEquals(1, discardedCard.getValue());
        assertEquals("3 4 2 5", hand.contents());
    }

    @Test
    void discardReducesSize() {
        Hand hand = new Hand(3);
        hand.addCard(new Card(1));
        hand.addCard(new Card(3));
        hand.addCard(new Card(4));
        hand.addCard(new Card(2));
        hand.addCard(new Card(5));
        hand.discard();

        assertEquals(4, hand.size());
    }

    @Test
    void discardSkipsPreferredCard() {
        Hand hand = new Hand(3);
        hand.addCard(new Card(3));
        hand.addCard(new Card(3));
        hand.addCard(new Card(3));
        hand.addCard(new Card(2));
        hand.addCard(new Card(3));
        Card discardedCard = hand.discard();

        assertEquals(2, discardedCard.getValue());
        assertEquals("3 3 3 3", hand.contents());
    }

    @Test
    void preferredValueHandOnly() {
        Hand hand = new Hand(3);
        hand.addCard(new Card(3));
        hand.addCard(new Card(3));
        hand.addCard(new Card(3));
        hand.addCard(new Card(3));
        hand.addCard(new Card(3));

        assertThrows(IllegalStateException.class, () -> hand.discard());
    }

}
