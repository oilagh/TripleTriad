package cs3500.tripletriad.model;

import cs3500.tripletriad.gamecomponents.DeckTT;
import cs3500.tripletriad.gamecomponents.GridTT;


/**
 * Represents the interface for the game model for triple triad.
 */
public interface TTModel extends ReadOnlyTripleTriadModel {

  /**
   * Assigns cards to players.
   */
  void startGame(DeckTT deck, GridTT grid);


  /**
   * This allows the player to play any card to the grid.
   * @param cardInHand the card to be played to the grid.
   * @param row the row of the cell to be played to.
   * @param col the column of the cell to be played to.
   */
  public void playToGrid(int cardInHand, int row, int col);

}
