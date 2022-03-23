/**
 * Record class does not allow the properties to change after the object has been created.
 *
 * It has no 'setter' methods and the properties are 'final'.
 */
record Card(int value, Suit suit) {
    public enum Suit {
        HEARTS, DIAMONDS, SPADES, CLUBS;
    }

    public Card {
        if(value < 1 || value > 13) {
            throw new IllegalArgumentException("Invalid card value " + value);
        }
        if(suit == null) {
            throw new IllegalArgumentException("Card suit must be non-null");
        }
    }

    public String toString() {
        StringBuilder result = new StringBuilder(2);

        if(value == 1) {
            result.append("A");
        } else if(value < 11) {
            result.append(value);
        } else if(value == 11) {
            result.append('J');
        } else if(value == 12) {
            result.append('Q');
        } else if(value == 13) {
            result.append('K');
        }

        return result.toString();
    }

    /**
     * Returns the value of {@link #toString()} preceded by either "AN " or "A " depending on which is gramatically correct.
     *
     * @return "AN [x]" when [x] is "an" ace or "an" 8, and "A [X]" otherwise.
     */
    public String toProseString() {
        if(value == 1 || value == 8) {
            return "AN " + toString();
        } else {
            return "A " + toString();
        }
    }
}
