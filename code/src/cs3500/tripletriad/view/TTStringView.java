package cs3500.tripletriad.view;

import cs3500.tripletriad.model.TTModel;
import cs3500.tripletriad.player.Player;

public class TTStringView implements TTView {
  private final TTModel model;
  private final Appendable output;

  public TTStringView(TTModel model) {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }
    this.model = model;
    this.output = null;
  }

  public TTStringView(TTModel model, Appendable output) {
    if (model == null || output == null) {
      throw new IllegalArgumentException("Arguments cannot be null.");
    }
    this.model = model;
    this.output = output;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    Player player = model.getPlayersTurn();
    sb.append("Player: ");
    sb.append(player.toString());
    sb.append("\n");
    sb.append(model.getGrid().toStringForView());
    sb.append(player.handToString());
    return sb.toString();
  }
}
