package cs3500.tripletriad.player;

import java.util.List;
import java.util.ArrayList;

import cs3500.tripletriad.model.TTModel;

/**
 * Represents an abstract class for the Strategies.
 * Contains the constructor and tieBreaker method.
 */
public abstract class AStrategies implements Strategies {
  protected TTModel model;

  /**
   * Constructor for AStrategies.
   * @param model the model that will be assessed for best strategies.
   */
  public AStrategies(TTModel model) {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }
    this.model = model;
  }

  @Override
  public List<Integer> tieBreaker(List<Integer> posn1, List<Integer> posn2) {
    return new ArrayList<>();
  }

  @Override
  public List<Integer> strategyOne() {
    return new ArrayList<>();
  }

  @Override
  public List<Integer> strategyTwo() {
    return new ArrayList<>();
  }

}
