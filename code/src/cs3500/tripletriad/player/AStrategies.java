package cs3500.tripletriad.player;

import java.util.List;
import java.util.ArrayList;

import cs3500.tripletriad.gamecomponents.Card;
import cs3500.tripletriad.model.TTModel;

/**
 * Represents an abstract class for the Strategies.
 * Contains the constructor and tieBreaker method.
 */
public abstract class AStrategies implements Strategies {
  protected TTModel model;
  public int row;
  public int col;
  public int card;

  /**
   * Constructor for AStrategies.
   * @param model the model that will be assessed for best strategies.
   */
  public AStrategies(TTModel model) {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }
    this.model = model;
    this.row = 0;
    this.col = 0;
    this.card = 0;
  }

  @Override
  public List<Integer> tieBreaker(List<Integer> posn1, List<Integer> posn2) {
    return new ArrayList<>();
  }

  @Override
  public void strategyOne() {
    throw new IllegalStateException("Wrong strategy");
  }

  @Override
  public int getRow() {
    return this.row;
  }

  @Override
  public int getCard() {
    return this.card;
  }

  @Override
  public int getCol() {
    return this.col;
  }

  @Override
  public void strategyTwo() {
    throw new IllegalArgumentException("Wrong Strategy");
  }

}
