package cs3500.tripletriad.gamecomponents;

import java.awt.*;
import java.util.List;

/**
 * The interface for the grid.
 */
public interface GridTT {

  /**
   * Gets the card at the given index.
   *
   * @param row the index of the row.
   * @param col the index of the column.
   * @return the card at the given index.
   */
  Card getCard(int row, int col);

  /**
   * Gets the list of BoardCells from the grid.
   * @return the list of BoardCells from the Grid.
   */
  List<List<BoardCell>> gridList();

  /**
   * Sets the given card to the cell at the given index and column.
   * @param row the row where the card will be placed.
   * @param col the column where the card will be placed.
   * @param card the card that will be placed.
   */
  void setGrid(int row, int col, Card card);

  /**
   * Determines how many card cells are in the grid.
   *
   * @return the number of card cells in the grid.
   */
  int numCardCells();


  /**
   * Gets whether the given bounds are out of bounds of the grid.
   * @param row card row.
   * @param col card col.
   * @return true if the given indices are out of bounds.
   */
  boolean checkOutOfBounds(int row, int col);

  /**
   * Determines if the given cell has any adjacent cells.
   * @param row the cell row.
   * @param col the cell column.
   */
  void compareAdjacentCells(int row, int col);

  /**
   * determines if the grid contains any card cells that are empty.
   * @return true if it does not, and false if it does.
   */
  boolean emptyCardCells();


  /**
   * determines if the given coordinate is unoccupied in the grid.
   * @return true if it is, and false if it is not.
   */
  boolean isCoordinateUnoccupied(int row, int col);

  /**
   * Provides a string representation of the grid for the view.
   * @return a string representation of the grid.
   */
  String toStringForView();

  /**
   * Returns the BoardCEll at the given coordinates.
   * @param row the row of the board cell returned.
   * @param col the column of the board cell returned.
   * @return the BoardCell at the given coordinates.
   */
  BoardCell getCell(int row, int col);

  /**
   * Creates a copy of the grid.
   * @return copy of the grid.
   */
  GridTT copy();

  List<Point> leastFlippablePositions();
}
