package cs3500.tripletriad.controller.config;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import cs3500.tripletriad.gamecomponents.AttackValue;
import cs3500.tripletriad.gamecomponents.Card;
import cs3500.tripletriad.gamecomponents.Deck;
import cs3500.tripletriad.gamecomponents.Direction;

/**
 * This class reads a file containing information about the cards in the deck.
 * It then initializes a deck given the file and cards.
 */
public class DeckInitializer {
  Deck deck;
  private final Map<String, AttackValue> attackValuesMap = new HashMap<>(Map.of(
          "1", AttackValue.ONE,
          "2", AttackValue.TWO,
          "3", AttackValue.THREE,
          "4", AttackValue.FOUR,
          "5", AttackValue.FIVE,
          "6", AttackValue.SIX,
          "7", AttackValue.SEVEN,
          "8", AttackValue.EIGHT,
          "9", AttackValue.NINE,
          "A", AttackValue.TEN));

  /**
   * Constructor for DeckInitializer.
   * Constructs a deck with the given cards and map values.
   * @param filePath the file that contains information about the cards.
   */
  public DeckInitializer(String filePath) {
    Scanner scanner = new FileScanner(filePath).getScanner();
    List<Card> deck = new ArrayList<>();
    while (scanner.hasNext()) {
      String cardName = scanner.next();
      Map<Direction, AttackValue> values = new HashMap<>();
      values.put(Direction.NORTH, translateIntoAV(scanner.next()));
      values.put(Direction.SOUTH, translateIntoAV(scanner.next()));
      values.put(Direction.EAST, translateIntoAV(scanner.next()));
      values.put(Direction.WEST, translateIntoAV(scanner.next()));
      deck.add(new Card(cardName, values, Color.blue));
    }
    this.deck = new Deck(deck);
  }

  private AttackValue translateIntoAV(String s) {
    if(attackValuesMap.containsKey(s)) {
      return attackValuesMap.get(s);
    }
    throw new IllegalArgumentException("Deck config file has invalid Attack Value: " + s);
  }

  /**
   * Returns the deck.
   * @return the deck.
   */
  public Deck getDeck() {
    return this.deck;
  }
}
