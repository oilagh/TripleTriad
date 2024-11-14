package cs3500.tripletriad.player;

import java.awt.Point;
import java.awt.Color;
import java.util.List;
import java.util.Map;

import cs3500.tripletriad.gamecomponents.AttackValue;
import cs3500.tripletriad.gamecomponents.Card;
import cs3500.tripletriad.gamecomponents.Direction;
import cs3500.tripletriad.model.TTModel;

/**
 * Represents a Strategy Three for our Players, but was unfinished due to a lack of time.
 */
public class StrategyThree extends AStrategies {

  public StrategyThree(TTModel model) {
    super(model);
  }

  /**
   * Stores the card hand and index of the row and col to play to for the current strategy.
   */
  public void strategyThree() {
    Map<Point, List<Direction>> points = model.getGrid().leastFlippablePositions();
    List<Card> deck = model.getPlayersTurn().getHand();
    List<Card> otherDeck;
    if (model.getPlayersTurn().getColor().equals(Color.BLUE)) {
      otherDeck = model.getSpecificPlayer(Color.RED).getHand();
    }
    else {
      otherDeck = model.getSpecificPlayer(Color.BLUE).getHand();
    }
    for (Point point : points.keySet()) {
      for (Direction direction : points.get(point)) {
        for (Card card : deck) {
          AttackValue value = card.getAttackValues().get(direction);
          beatableCards(otherDeck, value);
        }
      }
    }
  }

  // supposed to tell you how many cards in the opposing hand can beat a specified Attack Value
  private int beatableCards(List<Card> otherHand, AttackValue value) {
    List<Card> deck = model.getPlayersTurn().getHand();
    return 0;
  }
}
