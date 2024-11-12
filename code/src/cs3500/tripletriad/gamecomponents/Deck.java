package cs3500.tripletriad.gamecomponents;

import java.util.ArrayList;
import java.util.List;

/**
 * This class reads the card input from the files.
 * Creates a deck of cards to be used in the game.
 */
public class Deck {
  private List<Card> deck;

  /**
   * Constructor for Deck.
   */
  public Deck(List<Card> deck) {
    this.deck = deck;
  }

  public Deck() {
    this.deck = new ArrayList<>();
  }

  /**
   * This function initializes the deck.
   */
  public void initDeck() {

  }

  /**
   * returns the size of the deck.
   * @return the size of the deck.
   */
  public int deckSize() {
    return this.deck.size();
  }

  /**
   * returns the list of cards in a deck.
   * @return the list of cards in a deck.
   */
  public List<Card> getCards() {
    return this.deck;
  }

  /**
   * Returns a string representation of the deck.
   * @return a string representing the deck of cards.
   */
  public String toString() {
    StringBuilder deckString = new StringBuilder();
    for(Card card : this.deck) {
      deckString.append(card.toString());
      deckString.append(System.lineSeparator());
    }
    return deckString.toString();
  }

}
