package test;

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
        boolean thrown = false;
        try {
            Card negCard = new Card(-1);
        } catch (IllegalArgumentException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }
}
