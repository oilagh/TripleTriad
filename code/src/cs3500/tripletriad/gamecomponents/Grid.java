package cs3500.tripletriad.gamecomponents;

import java.util.ArrayList;
import java.util.List;

/**
 * This class reads the grid input from the file.
 * It creates the grid to be used in the game.
 */
public class Grid {
  private List<List<BoardCell>> grid;

  /**
   * Constructor for Grid.
   * @param grid represents the grid that will be assigned to the Grid class.
   */
  public Grid(List<List<BoardCell>> grid) {
    this.grid = grid;
  }

  public Grid() {
    this.grid = new ArrayList<>();
  }
  /**
   * Gets the card at the given index.
   *
   * @param row the index of the row.
   * @param col the index of the column.
   * @return the card at the given index.
   */
  public Card getCard(int row, int col) {
    return grid.get(row).get(col).getCard();
  }

  public List<List<BoardCell>> gridList() {
    return this.grid;
  }

  public void setGrid(int row, int col, Card card) {
    grid.get(row).set(col, card);
  }


  @Override
  public String toString() {
    StringBuilder result = new StringBuilder();
    int row = grid.size();
    int col = grid.get(0).size();
    for (int i = 0; i < row; i++) {
      for (int k = 0; k < col; k++) {
        result.append(grid.get(i).get(k).toString());
      }
      result.append(System.lineSeparator());
    }
    return result.toString();
  }

  /**
   * Determines how many card cells are in the grid.
   *
   * @return the number of card cells in the grid.
   */
  public int numCardCells() {
    int countCardCells = 0;
    for (List<BoardCell> column : grid) {
      for (BoardCell cell : column) {
        if (!(cell instanceof Hole)) {
          countCardCells++;
        }
      }
    }
    return countCardCells;
  }

  /**
   * Gets whether the given bounds are out of bounds of the grid.
   * @param row card row.
   * @param col card col.
   * @return true if the given indices are out of bounds.
   */
  public boolean checkOutOfBounds(int row, int col) {
    if(grid.size() < row) {
      return true;
    }
    else return grid.get(0).size() < col;
  }

  /**
   * Determines if the given cell has any adjacent cells.
   * @param row the cell row.
   * @param col the cell column.
   * @return a list of adjacent cells to the given cell, not including holes.
   */
  public void compareAdjacentCells(int row, int col) {
    List<Card> adjacent = new ArrayList<>();
    Card south;
    Card north;
    Card west;
    Card east;
    Card current = grid.get(row).get(col).getCard();
    if(grid.size() > row + 1) {
      if(grid.get(row + 1).get(col) instanceof Card) {
        south = grid.get(row + 1).get(col).getCard();
        if (current.getAttackValues().get(Direction.SOUTH)
                .greaterAttackValue(south.getAttackValues().get(Direction.NORTH))
        && !current.getColor().equals(south.getColor())) {
          south.changeColor(current.getColor());
          compareAdjacentCells(row + 1, col);
        }
      }
    }
    if(-1 != row - 1) {
      if (grid.get(row - 1).get(col) instanceof Card) {
        north = grid.get(row - 1).get(col).getCard();
        if (current.getAttackValues().get(Direction.NORTH)
                .greaterAttackValue(north.getAttackValues().get(Direction.SOUTH))
                && !current.getColor().equals(north.getColor())) {
          north.changeColor(current.getColor());
          compareAdjacentCells(row - 1, col);
        }
      }
    }
    if(grid.get(0).size() > col + 1) {
      if (grid.get(row).get(col + 1) instanceof Card) {
        west = grid.get(row).get(col + 1).getCard();
        if (current.getAttackValues().get(Direction.WEST)
                .greaterAttackValue(west.getAttackValues().get(Direction.EAST))
                && !current.getColor().equals(west.getColor())) {
          west.changeColor(current.getColor());
          compareAdjacentCells(row, col + 1);
        }
      }
    }
      if (-1 != col - 1) {
        if (grid.get(row).get(col - 1) instanceof Card) {
          east = grid.get(row).get(col - 1).getCard();
          if (current.getAttackValues().get(Direction.EAST)
                  .greaterAttackValue(east.getAttackValues().get(Direction.WEST))
                  && !current.getColor().equals(east.getColor())) {
            east.changeColor(current.getColor());
            compareAdjacentCells(row, col - 1);
          }
        }
      }
    }

  /**
   * determines if the grid contains any card cells that are empty.
   * @return true if it does not, and false if it does.
   */
  public boolean emptyCardCells() {
    for (List<BoardCell> column : grid) {
      for (BoardCell cell : column) {
        if (cell instanceof PlaceHolder) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * determines if the given coordinate is unoccupied in the grid.
   * @return true if it is, and false if it is not.
   */
  public boolean isCoordinateUnoccupied(int row, int col) {
      return grid.get(row).get(col) instanceof PlaceHolder;
  }

  private void cardGreater(Card card, Card adjacent) {

  }

  public String toStringForView() {
    StringBuilder sb = new StringBuilder();
    for (int row = 0; row < grid.size(); row++) {
      List<BoardCell> column = grid.get(row);
      for (int col = 0; col < column.size(); col++) {
        BoardCell cell = column.get(col);
        sb.append(cell.toStringForGrid());
      }
      sb.append("\n");
    }
    return sb.toString();
  }
}
