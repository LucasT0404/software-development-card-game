import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import static org.junit.jupiter.api.Assertions.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

public class CardGameTest {
    @Test
    @Timeout(10)
    void immediateWinWithTwoPlayersRunsProperly() throws Exception {
        int numPlayers = 2;
        CardGame game = new CardGame(numPlayers, Pack.loadPack("packs/immediate_win_n2.txt", numPlayers));
        game.play();

        assertEquals(1, game.getWinner());
    }

    @Test
    @Timeout(10)
    void validPackWithTwoPlayersRunsProperly() throws Exception {
        int numPlayers = 2;
        CardGame game = new CardGame(numPlayers, Pack.loadPack("packs/valid_n2.txt", numPlayers));
        game.play();

        assertNotEquals(0, game.getWinner());
    }

    @Test
    @Timeout(10)
    void testCardConservation() throws Exception {
        int numPlayers = 2;
        CardGame game = new CardGame(numPlayers, Pack.loadPack("packs/valid_n2.txt", numPlayers));
        game.play();

        assertEquals(8 * numPlayers, game.totalCards());
    }

    @Test
    @Timeout(10)
    void allHandsHaveFourCardsAtEnd() throws Exception {
        int numPlayers = 2;
        CardGame game = new CardGame(numPlayers, Pack.loadPack("packs/valid_n2.txt", numPlayers));
        game.play();

        assertTrue(game.handSizeIsFour());
    }

    @BeforeEach
    void deleteOutputFilesForNewTests() throws Exception {
        Files.deleteIfExists(Path.of("player1_output.txt"));
        Files.deleteIfExists(Path.of("player2_output.txt"));
        Files.deleteIfExists(Path.of("deck1_output.txt"));
        Files.deleteIfExists(Path.of("deck2_output.txt"));
    }

    @Test
    @Timeout(10)
    void allOutputFilesWritten() throws Exception {
        int numPlayers = 2;
        CardGame game = new CardGame(numPlayers, Pack.loadPack("packs/valid_n2.txt", numPlayers));
        game.play();

        assertTrue(Files.exists(Path.of("player1_output.txt")));
        assertTrue(Files.exists(Path.of("player2_output.txt")));
        assertTrue(Files.exists(Path.of("deck1_output.txt")));
        assertTrue(Files.exists(Path.of("deck2_output.txt")));
    }

    @Test
    @Timeout(10)
    void immediateWinOutput() throws Exception {
        int numPlayers = 2;
        CardGame game = new CardGame(numPlayers, Pack.loadPack("packs/immediate_win_n2.txt", numPlayers));
        game.play();
        List<String> lines = Files.readAllLines(Path.of("player1_output.txt"));

        assertEquals(4, lines.size());
        assertEquals("player 1 initial hand 1 1 1 1", lines.get(0));
        assertEquals("player 1 final hand: 1 1 1 1", lines.get(3));
    }

    @RepeatedTest(10)
    @Timeout(30)
    void validPackWithFourPlayersRepeated() throws Exception {
        int numPlayers = 4;
        CardGame game = new CardGame(numPlayers, Pack.loadPack("packs/valid_n4.txt", numPlayers));
        game.play();

        assertTrue(game.getWinner() > 0);
        assertTrue(game.getWinner() <= 4);
        assertTrue(game.handSizeIsFour());
        assertEquals(8 * numPlayers, game.totalCards());
    }
}
