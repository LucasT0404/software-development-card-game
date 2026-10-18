import java.util.Queue;
import java.util.stream.Collectors;
import java.util.ArrayDeque;
import java.io.FileWriter;
import java.io.IOException;

public class CardDeck {
    private final int deckNum;
    private final Queue<Card> deckCards = new ArrayDeque<>();

    public CardDeck(int deckNum) {
        if (deckNum < 1) {
            throw new IllegalArgumentException("Deck number must be at least 1");
        }
        this.deckNum = deckNum;
    }

    public synchronized void addCard(Card card) {
        deckCards.add(card);
    }

    public synchronized Card drawCard() {
        return deckCards.remove();
    }

    public synchronized boolean isEmpty() {
        return deckCards.isEmpty();
    }

    public synchronized int size() {
        return deckCards.size();
    }

    public int getDeckNum() {
        return deckNum;
    }

    public synchronized String contents() {
        return "deck" + deckNum + " contents: "
                + deckCards.stream().map(Card::toString).collect(Collectors.joining(" "));
    }

    // each deck's final contents written to deckX_output.txt file
    // each deck has a separate file.
    public void writeToDeckFile() throws IOException {
        String fileName = "deck" + deckNum + "_output.txt";
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(contents() + System.lineSeparator());
        }
    }

}