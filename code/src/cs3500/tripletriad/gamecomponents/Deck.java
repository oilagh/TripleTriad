package cs3500.tripletriad.gamecomponents;

import java.util.ArrayList;
import java.util.List;

/**
 * This class reads the card input from the files.
 * Creates a deck of cards to be used in the game.
 */
public class Deck implements DeckTT {
  private List<Card> deck;

  /**
   * Constructor for Deck.
   * @param deck the list of cards used for the deck.
   */
  public Deck(List<Card> deck) {
    this.deck = deck;
  }

  /**
   * Constructor for Deck.
   */
  public Deck() {
    this.deck = new ArrayList<>();
  }

  @Override
  public int deckSize() {
    return this.deck.size();
  }

  @Override
  public List<Card> getCards() {
    return this.deck;
  }

  @Override
  public String toString() {
    StringBuilder deckString = new StringBuilder();
    for (Card card : this.deck) {
      deckString.append(card.toString());
      deckString.append(System.lineSeparator());
    }
    return deckString.toString();
  }

}
