import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Hand {
    private final List<Card> cards = new ArrayList<>();
    private final int preferedNum;

    public Hand(int preferedNum) {
        if (preferedNum < 1) {
            throw new IllegalArgumentException("Prefered value must be at least 1");
        }
        this.preferedNum = preferedNum;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int size() {
        return cards.size();
    }

    public boolean isWinning() {
        if (cards.size() != 4) {
            return false;
        }
        int firstCard = cards.get(0).getValue();
        for (Card card : cards) {
            if (card.getValue() != firstCard) {
                return false;
            }
        }
        return true;
    }

    // Stops as soon as the first non-preferred card is found in the hand
    public Card discard() {
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getValue() != preferedNum) {
                return cards.remove(i);
            }
        }
        throw new IllegalStateException("No cards of non-preferred value to discard");
    }

    public String contents() {
        return cards.stream().map(Card::toString).collect(Collectors.joining(" "));
    }

}