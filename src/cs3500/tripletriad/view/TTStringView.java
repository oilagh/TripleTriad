package cs3500.tripletriad.view;

import cs3500.tripletriad.model.ReadOnlyTripleTriadModel;
import cs3500.tripletriad.model.TTModel;
import cs3500.tripletriad.player.TTPlayer;

/**
 * Represents the String view for the game.
 */
public class TTStringView implements TTView {
  private final ReadOnlyTripleTriadModel model;
  private final Appendable output;

  /**
   * Constructor for TTStringView.
   * @param model the model that will be represented in a string.
   */
  public TTStringView(ReadOnlyTripleTriadModel model) {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }
    this.model = model;
    this.output = null;
  }

  /**
   * Constructor for TTStringView.
   * @param model the model that will be represented.
   * @param output the output for the user.
   */
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
    TTPlayer player = model.getPlayersTurn();
    sb.append("Player: ");
    sb.append(player.toString());
    sb.append("\n");
    sb.append(model.getGrid().toStringForView());
    sb.append(player.handToString());
    return sb.toString();
  }
}
