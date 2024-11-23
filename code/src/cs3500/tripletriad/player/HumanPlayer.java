package cs3500.tripletriad.player;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import cs3500.tripletriad.gamecomponents.Card;
import cs3500.tripletriad.model.TTModel;

public class HumanPlayer extends Player {

  public HumanPlayer(List<Card> hand, Color color, TTModel model) {
    super(hand, color, model);
  }

  public HumanPlayer(TTPlayer player) {
    super(player);
  }

  public HumanPlayer(Color color) {
    super(color);
  }
}
