import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.ArrayList;

public class CardGame {
    private final List<Player> players = new ArrayList<>();
    private final List<CardDeck> decks = new ArrayList<>();
    private final int n;
    private final AtomicInteger winner = new AtomicInteger(0);

    public CardGame(final int n, List<Card> cards) throws IOException {
        this.n = n;

        for (int i = 0; i < n; i++) {
            decks.add(new CardDeck(i + 1));
        }

        for (int i = 0; i < n; i++) {
            players.add(new Player(i + 1, decks.get(i), decks.get((i + 1) % n), winner,
                    new PrintWriter(new FileWriter("player" + (i + 1) + "_output.txt"))));
        }

        // Counter to loop through pack (increments each time after use) --> ensures
        // --> same card in pack is not dealt twice.
        int packIncrementer = 0;
        for (int round = 0; round < 4; round++) {
            for (int p = 0; p < n; p++) {
                Card cardToAddToHand = cards.get(packIncrementer++);
                players.get(p).receiveCard(cardToAddToHand);
            }
        }

        // fill decks with remaining cards same as players loop.
        for (int round = 0; round < 4; round++) {
            for (int i = 0; i < n; i++) {
                Card cardToAddToDeck = cards.get(packIncrementer++);
                decks.get(i).addCard(cardToAddToDeck);
            }
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {

        // reading in the number n of players in the game
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        boolean invalid = true;
        int numPlayers = 0;
        while (invalid) {
            System.out.println("Please enter the number of players: ");
            try {
                numPlayers = Integer.parseInt(input.readLine().trim());
                if (numPlayers < 1) {
                    throw new IllegalArgumentException();
                }
                invalid = false;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid Input: amount of players must be at least 1");
            }
        }

        boolean invalidPack = true;
        List<Card> cards = new ArrayList<>();
        while (invalidPack) {
            System.out.println("Please enter location of pack to load: ");
            try {
                String path = input.readLine().trim();
                cards = Pack.loadPack(path, numPlayers);
                invalidPack = false;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        CardGame game = new CardGame(numPlayers, cards);
        game.play();
    }

    public void play() throws InterruptedException, IOException {
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String name = "player" + (i + 1);
            Player player = players.get(i);
            Thread playerThread = new Thread(player, name);
            playerThread.start();
            threads.add(playerThread);
        }
        for (Thread thread : threads) {
            thread.join();
        }
        for (CardDeck deck : decks) {
            deck.writeToDeckFile();
        }
    }

    public int getWinner() {
        return winner.get();
    }

    public int totalCards() {
        int totalCards = 0;
        for (int i = 0; i < n; i++) {
            totalCards += players.get(i).getHandSize();
            totalCards += decks.get(i).size();
        }
        return totalCards;
    }

    public boolean handSizeIsFour() {
        for (Player player : players) {
            if (player.getHandSize() != 4) {
                return false;
            }
        }
        return true;
    }

}