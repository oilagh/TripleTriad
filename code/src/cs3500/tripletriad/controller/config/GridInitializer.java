package cs3500.tripletriad.controller.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import cs3500.tripletriad.gamecomponents.BoardCell;
import cs3500.tripletriad.gamecomponents.Grid;
import cs3500.tripletriad.gamecomponents.Hole;
import cs3500.tripletriad.gamecomponents.PlaceHolder;

/**
 * This class reads a file containing information about the cells in the grid.
 * It then initializes a grid given the file.
 */
public class GridInitializer {
  private Grid grid;

  /**
   * Constructor for GridInitializer.
   * @param filePath the file that will be read from.
   */
  public GridInitializer(String filePath) {
    Scanner scanner = new FileScanner(filePath).getScanner();
    List<List<BoardCell>> grid = new ArrayList<>();
    String s = "";
    int row = scanner.nextInt();
    int col = scanner.nextInt();
    List<BoardCell> rowList = new ArrayList<>();
    for (int i = 0; i < row; i++) {
      s = scanner.next();
      for (int k = 0; k < col; k++) {
        if (s.charAt(k) == 'C') {
          rowList.add(new PlaceHolder());
        } else if (s.charAt(k) == 'X') {
          rowList.add(new Hole());
        }
      }
      grid.add(rowList);
      rowList = new ArrayList<>();
    }
    this.grid = new Grid(grid);
  }

  public Grid getGrid() {
    return this.grid;
  }

}
