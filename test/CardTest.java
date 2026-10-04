import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CardTest {

    @Test
    void testCardValue() {
        Card testCard = new Card(4);
        assertEquals(4, testCard.getValue());
    }

    @Test
    void negativeCardValue() {
        assertThrows(IllegalArgumentException.class, () -> new Card(-1));
    }

    @Test
    void cardWithValueZero() {
        Card card = new Card(0);
        assertEquals(0, card.getValue());
    }

    @Test
    void cardWithLargeValue() {
        Card card = new Card(10000);
        assertEquals(10000, card.getValue());
    }

    // Outputs like CardDeck.content() and player hand depend on this output.
    @Test
    void testToStringReturnsNumber() {
        Card card = new Card(67);
        assertEquals("67", card.toString());
    }

}
