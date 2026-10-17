import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicInteger;

public class Player implements Runnable {
    private final CardDeck drawDeck;
    private final CardDeck discardDeck;
    private final int playerNum;
    private final Hand hand;
    private final AtomicInteger winner;
    private final PrintWriter output;
    private final CardDeck firstLock;
    private final CardDeck secondLock;

    public Player(int playerNum, CardDeck drawDeck, CardDeck discardDeck, AtomicInteger winner, PrintWriter output) {
        if (playerNum < 1) {
            throw new IllegalArgumentException("Minimum number for player must be 1");
        }
        if (drawDeck == null || discardDeck == null) {
            throw new IllegalArgumentException("Decks are null");
        }
        if (discardDeck.getDeckNum() < drawDeck.getDeckNum()) {
            firstLock = discardDeck;
            secondLock = drawDeck;
        } else {
            firstLock = drawDeck;
            secondLock = discardDeck;
        }

        this.playerNum = playerNum;
        this.drawDeck = drawDeck;
        this.discardDeck = discardDeck;
        this.winner = winner;
        this.output = output;
        this.hand = new Hand(playerNum);
    }

    boolean playTurn() {
        synchronized (firstLock) {
            synchronized (secondLock) {
                if (drawDeck.isEmpty()) {
                    return false;
                }
                Card drawnCard = drawDeck.drawCard();
                hand.addCard(drawnCard);
                Card discardedCard = hand.discard();
                discardDeck.addCard(discardedCard);
                return true;
            }
        }
    }

    public void receiveCard(Card c) {
        hand.addCard(c);
    }

    public String handContents() {
        return hand.contents();
    }

    public int getHandSize() {
        return hand.size();
    }

    public void run() {

    }
}
