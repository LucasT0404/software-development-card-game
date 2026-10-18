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
                printLog("player " + playerNum + " draws a " + drawnCard + " from deck " + drawDeck.getDeckNum());
                printLog("player " + playerNum + " discards a " + discardedCard + " to deck "
                        + discardDeck.getDeckNum());
                printLog("player " + playerNum + " current hand is " + handContents());
                return true;
            }
        }
    }

    private void printLog(String line) {
        output.println(line);
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
        try {
            printLog("player " + playerNum + " initial hand " + handContents());
            if (hand.winning()) {
                winner.compareAndSet(0, playerNum);
            }
            while (winner.get() == 0) {
                boolean turnTaken = playTurn();
                if (!turnTaken) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                } else {
                    if (hand.winning()) {
                        winner.compareAndSet(0, playerNum);
                    }
                }
            }
            int winnerNum = winner.get();
            if (winnerNum == playerNum) {
                System.out.println("player " + playerNum + " wins");
                printLog("player " + playerNum + " wins");
                printLog("player " + playerNum + " exits");
                printLog("player " + playerNum + " final hand: " + handContents());
            } else {
                printLog("player " + winnerNum + " has informed player " + playerNum + " that player " + winnerNum
                        + " has won");
                printLog("player " + playerNum + " exits");
                printLog("player " + playerNum + " hand: " + handContents());
            }
        } finally {
            output.close();
        }
    }
}
