package cs3500.tripletriad.model;

import java.awt.Color;
import java.util.List;

import cs3500.tripletriad.gamecomponents.BoardCell;
import cs3500.tripletriad.gamecomponents.GridTT;
import cs3500.tripletriad.player.TTPlayer;

/**
 * Represents an immutable model. It works by taking in and composing with a mutable model,
 * but all the relevant mutating methods are not included.
 */
public class TTImmutableModel implements ReadOnlyTripleTriadModel {
  private TripleTriadModel model;

  TTImmutableModel(TripleTriadModel model) {
    super();
    this.model = model;
  }


  @Override
  public TTPlayer winningPlayer() {
    return model.winningPlayer();
  }

  @Override
  public boolean isGameOver() {
    return model.isGameOver();
  }

  @Override
  public TTPlayer getPlayersTurn() {
    return model.getPlayersTurn();
  }

  @Override
  public TTPlayer getSpecificPlayer(Color color) {
    return model.getSpecificPlayer(color);
  }

  @Override
  public GridTT getGrid() {
    return model.getGrid();
  }

  @Override
  public List<Integer> getGridDimensions() {
    return model.getGridDimensions();
  }

  @Override
  public BoardCell contentsAt(int row, int col) {
    return model.contentsAt(row, col);
  }

  @Override
  public TTPlayer whoOwns(int row, int col) {
    return model.whoOwns(row, col);
  }

  @Override
  public int howManyPoints(TTPlayer player) {
    return model.howManyPoints(player);
  }

  @Override
  public TTModel getMutableModel() {
    return model;
  }
}
