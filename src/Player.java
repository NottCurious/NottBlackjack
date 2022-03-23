import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents a player and data related to them (number, bets, cards).
 */
public class Player {
    private int playerNum;
    private double currBet;
    private double insuranceBet;
    private double splitBet;
    private double total;
    private LinkedList<Card> hand;
    private LinkedList<Card> splitHand;

    /**
     * Represents a player in the game with cards, bets, total and a playerNum.
     */
    public Player(int playerNum) {
        this.playerNum = playerNum;
        currBet = 0;
        insuranceBet = 0;
        splitBet = 0;
        total = 0;
        hand = new LinkedList<>();
        splitHand = null;
    }

    public int getPlayerNumber() {
        return this.playerNum;
    }

    public double getCurrentBet() {
        return this.currBet;
    }

    public void setCurrentBet(double currentBet) {
        this.currBet = currentBet;
    }

    public double getSplitBet() {
        return splitBet;
    }

    public double getInsuranceBet() {
        return insuranceBet;
    }

    public void setInsuranceBet(double insuranceBet) {
        this.insuranceBet = insuranceBet;
    }

    /**
     * RecordRound adds input parameter 'totalBet' to 'total' and then sets 'currentBet', 'splitBet', and 'insuranceBet'
     * to zero.
     */
    public void recordRound(double totalBet) {
        this.total = this.total + totalBet;
        this.currBet = 0;
        this.splitBet = 0;
        this.insuranceBet = 0;
    }

    /**
     * Returns the total of all bets won/lost
     * @return Total Value.
     */
    public double getTotal() {
        return this.total;
    }

    /**
     * Add the given card to the players main hand.
     *
     * @param card the Card to add.
     */
    public void dealCard(Card card) {
        dealCard(card, 1);
    }

    /**
     * Adds the given card to the players hand or split hand depending on the handNumber.
     *
     * @param card The card to add
     * @param handNumber 1 for the "first" hand and 2 for the "second" hand in a split hand scenario.
     */
    public void dealCard(Card card, int handNumber) {
        if(handNumber == 1) {
            hand.add(card);
        } else if (handNumber == 2) {
            splitHand.add(card);
        } else {
            throw new IllegalArgumentException("Invalid hand number " + handNumber);
        }
    }

    /**
     * Determines whether the player is eligible to split.
     * @return True if the player has not already split, and their hand is a pair. False otherwise.
     */
    public boolean canSplit() {
        if(isSplit()) {
            // Can't split twice
            return false;
        } else {
            return this.hand.get(0).value() == this.hand.get(1).value();
        }
    }

    /**
     * Determines whether the player has already split their hand.
     * @return false if splitHand is null, true otherwise.
     */
    public boolean isSplit() {
        return this.splitHand != null;
    }

    /**
     * Removes first card from hand to add it to new split hand
     */
    public void split() {
        this.splitBet = this.currBet;
        this.splitHand = new LinkedList<>();
        splitHand.add(hand.pop());
    }

    /**
     * Determines whether the player can double down.
     *
     * @param handNumber
     * @return
     */
    public boolean canDoubleDown(int handNumber) {
        if(handNumber == 1){
            return this.hand.size() == 2;
        } else if(handNumber == 2){
            return this.splitHand.size() == 2;
        } else {
            throw new IllegalArgumentException("Invalid hand number " + handNumber);
        }
    }

    /**
     * Doubles down on the given hand. Specifically, this method doubles the bet for the given hand and deals the given card.
     *
     * @param card The card to deal
     * @param handNumber The hand to deal to and double the bet for
     */
    public void doubleDown(Card card, int handNumber) {
        if(handNumber == 1){
            this.currBet = this.currBet * 2;
        } else if(handNumber == 2){
            this.splitBet = this.splitBet * 2;
        } else {
            throw new IllegalArgumentException("Invalid hand number " + handNumber);
        }
        this.dealCard(card, handNumber);
    }

    /**
     * Resets the hand to an empty list and the splitHand to null.
     */
    public void resetHand() {
        this.hand = new LinkedList<>();
        this.splitHand = null;
    }

    public List<Card> getHand() {
        return getHand(1);
    }

    /**
     * Returns the given hand
     * @param handNumber 1 for the "first" of a split hand (or the main hand when there is no split) or 2 for the "second" hand of a split hand.
     * @return The hand specified by handNumber
     */
    public List<Card> getHand(int handNumber) {
        if(handNumber == 1){
            return Collections.unmodifiableList(this.hand);
        } else if(handNumber == 2){
            return Collections.unmodifiableList(this.splitHand);
        } else {
            throw new IllegalArgumentException("Invalid hand number " + handNumber);
        }
    }
}
