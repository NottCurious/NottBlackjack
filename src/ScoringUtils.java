import java.util.List;

public class ScoringUtils {
    /**
     * Calculates the value of a hand. When the hand contains aces, it will count one of them as a '11' if it does not
     * result in a bust.
     *
     * @param hand the hand to evaluate
     * @return the numeric value of that hand.
     */
    public static final int scoreHand(List<Card> hand) {
        int nAce = (int) hand.stream().filter(c -> c.value() == 1).count();
        int val = hand.stream()
                .mapToInt(Card::value)
                .filter(v -> v != 1)
                .map(v -> Math.min(v, 10))
                .sum();

        val += nAce;

        if(nAce > 0 && val <= 11) {
            val += 10;
        }

        return val;
    }

    /**
     * Compares two hands accounting for natural blackjacks and busting.
     *
     * @param handA hand to compare
     * @param handB other hand to compare
     * @return a neg integer, zero or a pos integer if handA is less, equal or more than handB.
     */
    public static final int compareHands(List<Card> handA, List<Card> handB) {
        int scoreA = scoreHand(handA);
        int scoreB = scoreHand(handB);

        if(scoreA == 21 && scoreB == 21) {
            if(handA.size() == 2 && handB.size() != 2) {
                return 1;
            } else if (handA.size() != 2 && handB.size() == 2) {
                return -1;
            } else {
                return 0;
            }
        } else if (scoreA > 21 || scoreB > 21) {
            if(scoreA > 21 && scoreB > 21) {
                return 0;
            } else if (scoreA > 21) {
                return -1; // B wins
            } else {
                return 1; // A wins
            }
        } else {
            return Integer.compare(scoreA, scoreB);
        }
    }
}
