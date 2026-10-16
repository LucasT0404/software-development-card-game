<<<<<<< HEAD
import java.io.FileWriter;
import java.io.IOException;



//start individual player threads in CardGame class.

class Player implements Runnable{
    private final int playerNum;







}


/*ensures thread safe class
private volatile boolean exit
=======
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
            throw new IllegalStateException("Decks are null");
        }
        if (discardDeck.getDeckNum() < drawDeck.getDeckNum()) {
            firstLock = discardDeck;
            secondLock = drawDeck;
        } else {
            firstLock = drawDeck;
            secondLock = discardDeck;
        }
        Hand hand = new Hand(playerNum);
>>>>>>> dc8fc13 (Started Player class runnable logic and constructor)

        this.playerNum = playerNum;
        this.drawDeck = drawDeck;
        this.discardDeck = discardDeck;
        this.winner = winner;
        this.output = output;
        this.hand = hand;
    }

    boolean playTurn() {
        CardDeck first = drawDeck;
        CardDeck second = discardDeck;

        synchronized (first) {
            synchronized (second) {
                if (first.isEmpty()) {
                    return false;
                }
            }
        }
        Card drawnCard = first.drawCard();
        hand.addCard(drawnCard);
        Card discardedCard = hand.discard();
        discardDeck.addCard(discardedCard);
        return true;

    }

    public void run() {
        Thread player = new Thread();

    }
}

/*
 * start individual player threads in CardGame class.
 *
 * class Player implements Runnable
 * private playerNum;
 *
 * //ensures thread safe class
 * private volatile boolean exit
 *
 * //list of all players, numbered 1 to n
 * private List<Int> players = new ArrayList<>();
 * for (int i = 0; i < n; i++){
 * playerNum = i+1;
 * }
 *
 * public Player(){
 * }
 *
 * public void stopThread() {
 * exit = true;
 * }
 *
 * getPlayerIndex()
 * return playerIndex;
 *
 */