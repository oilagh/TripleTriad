package cs3500.tripletriad.player;

import java.util.ArrayList;
import java.util.List;

import cs3500.tripletriad.gamecomponents.BoardCell;
import cs3500.tripletriad.gamecomponents.PlaceHolder;
import cs3500.tripletriad.model.TTModel;
import cs3500.tripletriad.model.TripleTriadModel;

/**
 * Represents the first strategy for the game.
 * This strategy picks the card and position that flips the most cards.
 */
public class StrategyOne extends AStrategies {

  /**
   * Constructor for Strategy one.
   * @param model the model that the strategy will be applied to.
   */
  public StrategyOne(TTModel model) {
    super(model);
    this.row = 0;
    this.col = 0;
    this.card = 0;
  }

  @Override
  public void strategyOne() {
    if (model.isGameOver()) {
      throw new IllegalStateException("Game is already over");
    }
    int max = 0;
    List<Integer> cardsToBePlaced = new ArrayList<>();
    List<List<Integer>> listPositions = new ArrayList<>();
    List<List<BoardCell>> grid = model.getGrid().gridList();
    for (int row = 0; row < grid.size(); row++) {
      List<BoardCell> rows = grid.get(row);
      for (int col = 0; col < grid.size(); col++) {
        BoardCell cell = rows.get(col);
        if (cell instanceof PlaceHolder) {
          for (int cardIndex = 0;
               cardIndex < model.getPlayersTurn().getHand().size();
               cardIndex++) {
            TTModel modelCopy = new TripleTriadModel(this.model);
            int currentPlayersCards = modelCopy.howManyPoints(model.getPlayersTurn());
            modelCopy.playToGrid(cardIndex, row, col);
            int updatesPlayersCards = modelCopy.howManyPoints(model.getPlayersTurn());
            int difference = updatesPlayersCards - currentPlayersCards;
            if (difference > max) {
              max = difference;
              listPositions.clear();
              cardsToBePlaced.clear();
              List<Integer> position = new ArrayList<>();
              position.add(row);
              position.add(col);
              listPositions.add(position);
              cardsToBePlaced.add(cardIndex);
            }
            else if (difference == max) {
              List<Integer> position = new ArrayList<>();
              position.add(row);
              position.add(col);
              listPositions.add(position);
              cardsToBePlaced.add(cardIndex);
            }
          }
        }
      }
    }
    if (listPositions.size() > 1 && cardsToBePlaced.size() > 1) {
      int index = strategyOneTieBreaker(listPositions);
      this.card = cardsToBePlaced.get(index);
    }
    else if (max == 0) {
      row = positions().get(0);
      col = positions().get(1);
      card = 0;
    }
    else {
      row = listPositions.get(0).get(0);
      col = listPositions.get(0).get(1);
      card = cardsToBePlaced.get(0);
    }
  }

  private int strategyOneTieBreaker(List<List<Integer>> listPositions) {
    int minimum = model.getGridDimensions().get(0) + model.getGridDimensions().get(0) * 4;
    int index = 0;
    for (int list1 = 0; list1 < listPositions.size(); list1++) {
      List<Integer> position = listPositions.get(list1);
      int min = position.get(0) + position.get(1);
      if (min < minimum) {
        minimum = min;
        index = list1;
      }
    }
    this.row = listPositions.get(index).get(0);
    this.col = listPositions.get(index).get(1);
    return index;
  }

  private List<Integer> positions() {
    List<Integer> returnList = new ArrayList<>();
    for (int rows = 0; rows < model.getGridDimensions().get(0) + 1; rows++) {
      List<BoardCell> cols = model.getGrid().gridList().get(rows);
      for (int col = 0; col < cols.size(); col ++) {
        BoardCell cell = cols.get(col);
        if (cell instanceof PlaceHolder) {
          returnList.add(rows);
          returnList.add(col);
          return returnList;
        }
      }
    }
    throw new IllegalArgumentException("None left");
  }

}
