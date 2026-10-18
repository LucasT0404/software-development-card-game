public final class Card {
    private final int value;

    public Card(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("Value must be a non-negative integer");
        }
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}