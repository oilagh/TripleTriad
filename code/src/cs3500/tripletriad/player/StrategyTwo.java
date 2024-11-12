package cs3500.tripletriad.player;

import java.util.ArrayList;
import java.util.List;

import cs3500.tripletriad.gamecomponents.BoardCell;
import cs3500.tripletriad.gamecomponents.Grid;
import cs3500.tripletriad.model.TTModel;

/**
 * Represents the second strategy that analyzes the corner.
 */
public class StrategyTwo extends AStrategies {

  /**
   * Constructor for Strategy Two.
   * @param model the model that will be used the strategy.
   */
  public StrategyTwo(TTModel model) {
    super(model);
  }

  //TODO: what does it mean to find the card hardest to be flipped.
  @Override
  public List<Integer> strategyTwo() {
    Grid grid = model.getGrid();
    List<Integer> posn = model.getGridDimensions();
    //gets the bottom right corner.
    int bottomRightCornerRow = posn.get(0);
    int bottomRightCornerColumn = posn.get(1);
    BoardCell cellBottomRight = grid.getCard(bottomRightCornerRow, bottomRightCornerColumn);
    //gets the bottom left corner.
    int bottomLeftCornerRow = posn.get(0);
    int bottomLeftCornerColumn = 0;
    BoardCell cellBottomLeft = grid.getCard(bottomLeftCornerRow, bottomLeftCornerColumn);
    //gets the top right corner.
    int topRightCornerRow = 0;
    int topRightCornerColumn = posn.get(1);
    BoardCell cellTopRight = grid.getCard(topRightCornerRow, topRightCornerColumn);
    //gets top left corner.
    int topLeftCornerRow = 0;
    int topLeftCornerColumn = 0;
    BoardCell cellTopLeft = grid.getCard(topLeftCornerRow, topLeftCornerColumn);
    List<List<BoardCell>> list = grid.gridList();
    return new ArrayList<>();
  }
}
