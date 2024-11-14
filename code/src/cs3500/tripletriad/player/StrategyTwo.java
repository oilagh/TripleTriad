package cs3500.tripletriad.player;

import java.util.ArrayList;
import java.util.List;

import cs3500.tripletriad.gamecomponents.AttackValue;
import cs3500.tripletriad.gamecomponents.BoardCell;
import cs3500.tripletriad.gamecomponents.Card;
import cs3500.tripletriad.gamecomponents.Direction;
import cs3500.tripletriad.gamecomponents.GridTT;
import cs3500.tripletriad.gamecomponents.PlaceHolder;
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
    this.row = 0;
    this.col = 0;
    this.card = 0;
  }

  @Override
  public void strategyTwo() {
    if (model.isGameOver()) {
      throw new IllegalStateException("Game is already over.");
    }
    GridTT grid = model.getGrid();
    List<Integer> dimensions = model.getGridDimensions();
    if (canBePlaced(grid.getCell(0, 0))) {
      this.row = 0;
      this.col = 0;
      this.card = whatCard(row, col);
    }
    else if (dimensions.get(0) > dimensions.get(1)) {
      if (canBePlaced(grid.getCell(0, dimensions.get(1)))) {
        this.row = 0;
        this.col = dimensions.get(1);
        this.card = whatCard(row, col);
      }
      else if (canBePlaced(grid.getCell(dimensions.get(0), 0))) {
        this.row = dimensions.get(0);
        this.col = 0;
        this.card = whatCard(row, col);
      }
      else if (canBePlaced(grid.getCell(dimensions.get(0), dimensions.get(1)))) {
        this.row = dimensions.get(0);
        this.col = dimensions.get(1);
        this.card = whatCard(row, col);
      }
    }
    else if (dimensions.get(0) <= dimensions.get(1)) {
      if (canBePlaced(grid.getCell(dimensions.get(0), 0))) {
        this.row = dimensions.get(0);
        this.col = 0;
        this.card = whatCard(row, col);
      }
      else if (canBePlaced(grid.getCell(0, dimensions.get(1)))) {
        this.row = 0;
        this.col = dimensions.get(1);
        this.card = whatCard(row, col);
      }
      else if (canBePlaced(grid.getCell(dimensions.get(0), dimensions.get(1)))) {
        this.row = dimensions.get(0);
        this.col = dimensions.get(1);
        this.card = whatCard(row, col);
      }
    }
    else {
      this.card = 0;
      List<Integer> positions = nextAvailablePosition();
      row = positions.get(0);
      col = positions.get(1);
    }
  }


  /**
   * Determines if the boardcell is a placeholder.
   * @param cell the cell to be determined if it is a placeholder or not.
   * @return true if the cell is a placeholder and false otherwise.
   */
  private boolean canBePlaced(BoardCell cell) {
    return (cell instanceof PlaceHolder);
  }

  //determines what card should be placed.
  private int whatCard(int row , int col) {
    List<Integer> dimensions = this.model.getGridDimensions();
    int card = 0;
    AttackValue value1 = AttackValue.ONE;
    AttackValue value2 = AttackValue.ONE;
    if (this.row == 0 && this.col == 0) {
      for (int cardIndex = 0;
           cardIndex < model.getPlayersTurn().getHand().size();
           cardIndex++) {
        Card cards = model.getPlayersTurn().getHand().get(cardIndex);
        AttackValue south = cards.getAttackValues().get(Direction.SOUTH);
        AttackValue east = cards.getAttackValues().get(Direction.EAST);
        if (south.greaterAttackValue(value1) && east.greaterAttackValue(value2)) {
          value1 = south;
          value2 = east;
          card = cardIndex;
        }
      }
      return card;
    }
    else if (this.row == 0 && this.col == dimensions.get(1)) {
      for (int cardIndex = 0;
           cardIndex < model.getPlayersTurn().getHand().size();
           cardIndex++) {
        Card cards = model.getPlayersTurn().getHand().get(cardIndex);
        AttackValue south = cards.getAttackValues().get(Direction.SOUTH);
        AttackValue west = cards.getAttackValues().get(Direction.WEST);
        if (south.greaterAttackValue(value1) && west.greaterAttackValue(value2)) {
          value1 = south;
          value2 = west;
          card = cardIndex;
        }
      }
      return card;
    }
    else if (this.row == dimensions.get(0) && this.col == 0) {
      for (int cardIndex = 0;
           cardIndex < model.getPlayersTurn().getHand().size();
           cardIndex++) {
        Card cards = model.getPlayersTurn().getHand().get(cardIndex);
        AttackValue north = cards.getAttackValues().get(Direction.NORTH);
        AttackValue east = cards.getAttackValues().get(Direction.EAST);
        if (north.greaterAttackValue(value1) && east.greaterAttackValue(value2)) {
          value1 = north;
          value2 = east;
          card = cardIndex;
        }
      }
      return card;
    }
    else if (this.row == dimensions.get(0) && this.col == dimensions.get(1)) {
      for (int cardIndex = 0;
           cardIndex < model.getPlayersTurn().getHand().size();
           cardIndex++) {
        Card cards = model.getPlayersTurn().getHand().get(cardIndex);
        AttackValue north = cards.getAttackValues().get(Direction.NORTH);
        AttackValue west = cards.getAttackValues().get(Direction.WEST);
        if (north.greaterAttackValue(value1) && west.greaterAttackValue(value2)) {
          value1 = north;
          value2 = west;
          card = cardIndex;
        }
      }
      return card;
    }
    throw new IllegalArgumentException("Cannot be used for non corner cell.");
  }


  //determines what is the next available position.
  private List<Integer> nextAvailablePosition() {
    List<List<BoardCell>> grid = this.model.getGrid().gridList();
    List<Integer> dimensions = model.getGridDimensions();
    List<Integer> returnList = new ArrayList<>();
    int minimum = dimensions.get(0) + dimensions.get(1) * 2;
    for (int row = 0; row < grid.size(); row++) {
      List<BoardCell> list = grid.get(row);
      for (int col = 0; col < list.size(); col++) {
        BoardCell cell = list.get(col);
        if (!isAnEdge(row, col) && cell instanceof PlaceHolder) {
          if (row + col < minimum) {
            returnList.clear();
            returnList.add(row);
            returnList.add(col);
          }
        }
      }
    }
    return returnList;
  }


  // checks edge cases where cards sit on the edge of the grid
  private boolean isAnEdge(int row, int col) {
    List<Integer> dimensions = this.model.getGridDimensions();
    if (row == 0 && col == 0 ) {
      return true;
    }
    else if (row == 0 && col == dimensions.get(1)) {
      return true;
    }
    else if (row == dimensions.get(0) && col == 0) {
      return true;
    }
    return row == dimensions.get(0) && col == dimensions.get(1);
  }


}
