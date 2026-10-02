package test;

import org.junit.Assert;
import org.junit.Test;

public class CardTest {

    @Test
    void testCardValue() {
        Card testCard = new Card(4);
        int value = testCard.getValue();
        Assert.assertEquals(4, value);
    }
}
