package cs3500.tripletriad.gamecomponents;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class reads the grid input from the file.
 * It creates the grid to be used in the game.
 */
public class Grid implements GridTT {
  private List<List<BoardCell>> grid;

  /**
   * Constructor for Grid.
   * @param grid represents the grid that will be assigned to the Grid class.
   */
  public Grid(List<List<BoardCell>> grid) {
    this.grid = grid;
  }

  /**
   * Constructor for Grid.
   */
  public Grid() {
    this.grid = new ArrayList<>();
  }

  @Override
  public Card getCard(int row, int col) {
    return grid.get(row).get(col).getCard();
  }

  @Override
  public List<List<BoardCell>> gridList() {
    return this.grid;
  }

  @Override
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

  @Override
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

  @Override
  public boolean checkOutOfBounds(int row, int col) {
    if (grid.size() < row) {
      return true;
    }
    else {
      return (grid.get(0).size() < col);
    }
  }

  @Override
  public void compareAdjacentCells(int row, int col) {
    List<Card> adjacent = new ArrayList<>();
    Card south;
    Card north;
    Card west;
    Card east;
    Card current = grid.get(row).get(col).getCard();
    if (grid.size() > row + 1) {
      if (grid.get(row + 1).get(col) instanceof Card) {
        south = grid.get(row + 1).get(col).getCard();
        if (current.getAttackValues().get(Direction.SOUTH)
                .greaterAttackValue(south.getAttackValues().get(Direction.NORTH))
                && !current.getColor().equals(south.getColor())) {
          south.changeColor(current.getColor());
          compareAdjacentCells(row + 1, col);
        }
      }
    }
    if (-1 != row - 1) {
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
    if (grid.get(0).size() > col + 1) {
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

  @Override
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


  @Override
  public boolean isCoordinateUnoccupied(int row, int col) {
    return grid.get(row).get(col) instanceof PlaceHolder;
  }


  @Override
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

  @Override
  public BoardCell getCell(int row, int col) {
    return grid.get(row).get(col).getBoardCell();
  }

  @Override
  public GridTT copy() {
    List<List<BoardCell>> newGrid = new ArrayList<>();
    for (List<BoardCell> row : this.grid) {
      List<BoardCell> copyRow = new ArrayList<>();
      for (BoardCell cell : row) {
        if (cell instanceof Card) {
          copyRow.add(new Card((Card) cell));
        }
        else if (cell instanceof Hole) {
          copyRow.add(new Hole());
        }
        else if (cell instanceof PlaceHolder) {
          copyRow.add(new PlaceHolder());
        }
        else {
          throw new IllegalArgumentException("Not a valid cell.");
        }
      }
      newGrid.add(copyRow);
    }
    return new Grid(newGrid);
  }

  /**
   * Gets the least flippable points in the board.
   * @return the list of points with their associated directions.
   */
  public Map<Point, List<Direction>> leastFlippablePositions() {
    Map<Point, List<Direction>> mapPositions = new HashMap<>();
    int maxNeighbors = 5;
    for (int row = 0; row < grid.size(); row++) {
      List<BoardCell> column = grid.get(row);
      for (int col = 0; col < column.size(); col++) {
        if (howManyPlaceHolderNeighbors(row, col).size() < maxNeighbors) {
          maxNeighbors = howManyPlaceHolderNeighbors(row, col).size();
          mapPositions = new HashMap<>();
          mapPositions.put(new Point(row, col), howManyPlaceHolderNeighbors(row, col));
        }
        else if (howManyPlaceHolderNeighbors(row, col).size() == maxNeighbors) {
          mapPositions.put(new Point(row, col), howManyPlaceHolderNeighbors(row, col));
        }
      }
    }
    return mapPositions;
  }

  // works with leastFlippablePositions and returns how many placeholders are next to it
  private List<Direction> howManyPlaceHolderNeighbors(int row, int col) {
    List<Direction> directions = new ArrayList<>();
    if (!(grid.get(row).get(col) instanceof PlaceHolder)) {
      return new ArrayList<>(List.of(Direction.NORTH,
              Direction.NORTH,Direction.NORTH,Direction.NORTH, Direction.NORTH
              ));
    }
    try {
      if (grid.get(row + 1).get(col) instanceof PlaceHolder) {
        directions.add(Direction.NORTH);
      }
    } catch (IndexOutOfBoundsException e) {
      // card is on the edge of the grid
    }
    try {
      if (grid.get(row - 1).get(col) instanceof PlaceHolder) {
        directions.add(Direction.SOUTH);
      }
    } catch (IndexOutOfBoundsException e) {
      // card is on the edge of the grid
    }
    try {
      if (grid.get(row).get(col + 1) instanceof PlaceHolder) {
        directions.add(Direction.EAST);
      }
    } catch (IndexOutOfBoundsException e) {
      // card is on the edge of the grid
    }
    try {
      if (grid.get(row).get(col - 1) instanceof PlaceHolder) {
        directions.add(Direction.WEST);
      }
    } catch (IndexOutOfBoundsException e) {
      // card is on the edge of the grid
    }
    return directions;
  }
}
