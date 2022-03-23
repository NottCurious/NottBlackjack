import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

public class Deck {
    private LinkedList<Card> cards;
    private Function<LinkedList<Card>, LinkedList<Card>> shuffleAlgorithm;

    /**
     * Init the game with a given number of decks.
     *
     * {@code new Decks(2)} = Init with 2 decks
     * {@code new Decks(3)} = Init with 3 decks
     *
     * @param shuffleAlgorithm A func that takes the initial sorted card list and returns a shuffled list.
     */
    public Deck(Function<LinkedList<Card>, LinkedList<Card>> shuffleAlgorithm) {
        this.shuffleAlgorithm = shuffleAlgorithm;
    }

    /**
     * Deals one card from the deck, removing it from the object's state. If the deck is empty. It is reshuffled before
     * dealing a new card.
     *
     * @return the card that was dealed.
     */
    public Card deal() {
        if(cards == null || cards.isEmpty()) {
            reshuffle();
        }
        return cards.pollFirst();
    }

    /**
     * Shuffle the cards in the deck.
     */
    public void reshuffle() {
        LinkedList<Card> newCards = new LinkedList<>();

        for(Card.Suit suit : Card.Suit.values()) {
            for(int val = 1; val < 14; val++) {
                newCards.add(new Card(val, suit));
            }
        }
        this.cards = this.shuffleAlgorithm.apply(newCards);
    }

    /**
     * Get the number of cards in this deck
     * @return the number of cards in the deck.
     */
    public int size() {
        return cards.size();
    }

    /**
     * Returns the cards in this deck
     * @returns An immutable list of the cards in the deck.
     */
    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
