import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class PackTest {
    @Test
    void loadValidPackTwoPlayers() {
        List<Card> cards = Pack.loadPack("packs/valid_n2.txt", 2);

        assertEquals(16, cards.size());
        assertEquals(2, cards.get(0).getValue());
        assertEquals(0, cards.get(15).getValue());
    }

    @Test
    void throwOnTooFewCardsInPack() {
        assertThrows(IllegalArgumentException.class, () -> Pack.loadPack("packs/too_few_n2.txt", 2));
    }

    @Test
    void throwOnTooManyCardsInPack() {
        assertThrows(IllegalArgumentException.class, () -> Pack.loadPack("packs/too_many_n2.txt", 2));

    }

    @Test
    void throwOnNegativeIntegerInPack() {
        assertThrows(IllegalArgumentException.class, () -> Pack.loadPack("packs/negative_value_n2.txt", 2));
    }

    @Test
    void throwOnInvalidCharsInPack() {
        assertThrows(IllegalArgumentException.class, () -> Pack.loadPack("packs/invalid_chars_n2.txt", 2));
    }

    @Test
    void throwOnEmptyFile() {
        assertThrows(IllegalArgumentException.class, () -> Pack.loadPack("packs/empty.txt", 2));
    }

    @Test
    void throwOnInvalidPackSize() {
        assertThrows(IllegalArgumentException.class, () -> Pack.loadPack("packs/valid_n2.txt", 3));
    }

    @Test
    void throwOnNonExistentPath() {
        assertThrows(IllegalArgumentException.class, () -> Pack.loadPack("packs/goated_pack_n67.txt", 2));
    }
}
