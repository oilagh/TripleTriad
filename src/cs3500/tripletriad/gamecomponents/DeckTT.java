package cs3500.tripletriad.gamecomponents;

import java.util.List;

/**
 * Represents the interface for the Deck.
 */
public interface DeckTT {

  /**
   * returns the size of the deck.
   * @return the size of the deck.
   */
  int deckSize();

  /**
   * returns the list of cards in a deck.
   * @return the list of cards in a deck.
   */
  List<Card> getCards();

  /**
   * Returns a string representation of the deck.
   * @return a string representing the deck of cards.
   */
  String toString();
}
