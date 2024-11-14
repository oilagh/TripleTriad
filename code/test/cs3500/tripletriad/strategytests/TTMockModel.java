package cs3500.tripletriad.strategytests;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import cs3500.tripletriad.gamecomponents.BoardCell;
import cs3500.tripletriad.gamecomponents.DeckTT;
import cs3500.tripletriad.gamecomponents.GridTT;
import cs3500.tripletriad.model.ReadOnlyTripleTriadModel;
import cs3500.tripletriad.model.TTModel;
import cs3500.tripletriad.player.AStrategies;
import cs3500.tripletriad.player.StrategyOne;
import cs3500.tripletriad.player.TTPlayer;

/**
 * Represents the mock model.
 */
public class TTMockModel implements TTModel {
  private final Appendable out;

  /**
   * Represents the constructor for the mock model.
   * @param out represents the appendable to push outs to.
   */
  public TTMockModel(Appendable out) {
    this.out = out;
  }


  @Override
  public void startGame(DeckTT deck, GridTT grid) {
    try {
      out.append("Game Started" + System.lineSeparator());
    } catch (IOException e) {
      throw new IllegalArgumentException("Could not append string");
    }
  }

  @Override
  public void playToGrid(int cardInHand, int row, int col) {
    try {
      out.append("Strategy played card " + cardInHand + " to: "
              + row + " | " + col + System.lineSeparator());
    } catch (IOException e) {
      throw new IllegalArgumentException("Could not append string");
    }
  }

  @Override
  public ReadOnlyTripleTriadModel getImmutableModel() {
    try {
      out.append("Changed to immutable" + System.lineSeparator());
    } catch (IOException e) {
      throw new IllegalArgumentException("Could not append string");
    }
    return null;
  }

  @Override
  public TTPlayer winningPlayer() {
    try {
      out.append("Got winning player" + System.lineSeparator());
    } catch (IOException e) {
      throw new IllegalArgumentException("Could not append string");
    }
    return null;
  }

  @Override
  public boolean isGameOver() {
    try {
      out.append("Checked if game was over" + System.lineSeparator());
    } catch (IOException e) {
      throw new IllegalArgumentException("Could not append string");
    }
    return false;
  }

  @Override
  public TTPlayer getPlayersTurn() {
    try {
      out.append("Got players turn." + System.lineSeparator());
    } catch (IOException e) {
      throw new IllegalArgumentException("Could not append string");
    }
    return null;
  }

  @Override
  public TTPlayer getSpecificPlayer(Color color) {
    try {
      out.append("Got player color." + System.lineSeparator());
    } catch (IOException e) {
      throw new IllegalArgumentException("Could not append string");
    }
    return null;
  }

  @Override
  public GridTT getGrid() {
    try {
      out.append("Got grid" + System.lineSeparator());
    } catch (IOException e) {
      throw new IllegalArgumentException("Could not append string");
    }
    return null;
  }

  @Override
  public List<Integer> getGridDimensions() {
    try {
      out.append("got dimensions" + System.lineSeparator());
    } catch (IOException e) {
      throw new IllegalArgumentException("Could not append string");
    }
    return List.of();
  }

  @Override
  public BoardCell contentsAt(int row, int col) {
    try {
      out.append("Got contents at: " + row + ", " + col + System.lineSeparator());
    } catch (IOException e) {
      throw new IllegalArgumentException("Could not append string");
    }
    return null;
  }

  @Override
  public TTPlayer whoOwns(int row, int col) {
    try {
      out.append("checked who owns"  + System.lineSeparator());
    } catch (IOException e) {
      throw new IllegalArgumentException("Could not append string");
    }
    return null;
  }

  @Override
  public int howManyPoints(TTPlayer player) {
    try {
      out.append("checked how many points");
    } catch (IOException e) {
      throw new IllegalArgumentException("Could not append string");
    }
    return 0;
  }

  @Override
  public TTModel getMutableModel() {
    try {
      out.append("Mutable model");
    } catch (IOException e) {
      throw new IllegalArgumentException("Could not append string");
    }
    return null;
  }
}
