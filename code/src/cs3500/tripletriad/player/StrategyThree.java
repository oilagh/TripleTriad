package cs3500.tripletriad.player;

import java.awt.*;
import java.util.List;
import java.util.Map;

import cs3500.tripletriad.gamecomponents.AttackValue;
import cs3500.tripletriad.gamecomponents.Card;
import cs3500.tripletriad.gamecomponents.Deck;
import cs3500.tripletriad.gamecomponents.Direction;
import cs3500.tripletriad.model.TTModel;

public class StrategyThree extends AStrategies {

  public StrategyThree(TTModel model) {
    super(model);
  }

  public void strategyThree() {
    Map<Point, List<Direction>> points = model.getGrid().leastFlippablePositions();
    List<Card> deck = model.getPlayersTurn().getHand();
    List<Card> otherDeck;
    if(model.getPlayersTurn().getColor().equals(Color.BLUE)) {
      otherDeck = model.getSpecificPlayer(Color.RED).getHand();;
    }
    else {
      otherDeck = model.getSpecificPlayer(Color.BLUE).getHand();;
    }
    for(Point point : points.keySet()) {
        for(Direction direction : points.get(point)) {
          for(Card card : deck) {
            AttackValue value = card.getAttackValues().get(direction);
            beatableCards(otherDeck, value);
          }
      }
    }
  }

  private int beatableCards(List<Card> otherHand, AttackValue value) {
    List<Card> deck = model.getPlayersTurn().getHand();
    return 0;
  }
}
