package cs3500.tripletriad.model;

import cs3500.tripletriad.gamecomponents.BoardCell;
import cs3500.tripletriad.gamecomponents.GridTT;
import cs3500.tripletriad.player.TTPlayer;

import java.awt.Color;
import java.util.List;

/**
 * Represents a version of the model that cannot be mutated.
 * Only allows the user to view the model, not mutate it.
 */
public interface ReadOnlyTripleTriadModel {

  /**
   * Determines which player is currently winning.
   * @return the player who is currently winning.
   */
  public TTPlayer winningPlayer();

  /**
   * Determines if the game is Over.
   * @return true if the game is over, false otherwise.
   */
  public boolean isGameOver();

  /**
   * Returns the player whos turn it is.
   * @return the player whos turn it is to play to the grid.
   */
  TTPlayer getPlayersTurn();

  /**
   * Returns the specified player.
   */
  TTPlayer getSpecificPlayer(Color color);

  /**
   * Returns the grid of the model.
   * @return grid of the model.
   */
  GridTT getGrid();

  /**
   * Returns a list of integers of the size of the grid.
   * The first number in the list is the number of rows, and the second is the number of columns.
   * @return a list of integers that represents the dimensions of the grid.
   */
  List<Integer> getGridDimensions();

  /**
   * Returns the contents of the given cell.
   * @param row the row of the cell.
   * @param col the column of the cell
   * @return the cell at the given indices.
   */
  BoardCell contentsAt(int row, int col);

  /**
   * Returns the player who owns the contents of the given cell.
   * @param row the row of the cell.
   * @param col the column of the cell.
   * @return the player who owns the card (if there is one).
   * @throws IllegalArgumentException if there is no such player or card.
   */
  TTPlayer whoOwns(int row, int col);


  /**
   * Determines how many points a player has in a game.
   *
   * @param player the player whos points will be counted.
   * @return the number of points the player currently has.
   */
  int howManyPoints(TTPlayer player);

  ReadOnlyTripleTriadModel getMutableModel();
}
