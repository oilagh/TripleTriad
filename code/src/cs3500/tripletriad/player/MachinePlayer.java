package cs3500.tripletriad.player;

import java.awt.*;
import java.util.List;

import cs3500.tripletriad.gamecomponents.Card;
import cs3500.tripletriad.model.TTModel;
import cs3500.tripletriad.strategies.Strategies;

public class MachinePlayer extends Player {
  private Strategies strategy;


  public MachinePlayer(List<Card> hand, Color color, TTModel model) {
    super(hand, color, model);
    this.strategy = null;
  }

  public MachinePlayer(List<Card> hand, Color color, Strategies strategy, TTModel model) {
    super(hand, color, model);
    if (strategy == null) {
      throw new IllegalArgumentException("Your strategy cannot be null.");
    }
    this.strategy = strategy;
  }

  public MachinePlayer(TTPlayer player) {
    super(player);
  }

  public void playToGrid() {
    model.playToGrid(this.strategy.getCard(), this.strategy.getRow(), this.strategy.getCol());
  }

}
