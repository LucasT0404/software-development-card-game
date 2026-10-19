import java.io.BufferedReader;
import java.io.IOException;
import java.lang.NumberFormatException;
import java.util.ArrayList;
import java.util.List;
import java.io.FileReader;

public class Pack {
    private Pack() {
    }

    public static List<Card> loadPack(String filename, int numPlayers) {
        // Each line of pack as a value in a list
        List<Card> cards = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            // Until the last line is read
            while ((line = reader.readLine()) != null) {
                int value = Integer.parseInt(line.trim());
                cards.add(new Card(value));
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("Could not read pack: " + filename, e);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Line is not an integer", e);
        }
        // check if pack size is valid (8*n)
        if (cards.size() != 8 * numPlayers) {
            throw new IllegalArgumentException("Pack does not have a valid number of cards | Expected: "
                    + (8 * numPlayers) + " Found: " + cards.size());
        }
        return cards;
    }
}
