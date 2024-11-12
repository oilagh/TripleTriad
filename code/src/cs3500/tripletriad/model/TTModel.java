package cs3500.tripletriad.model;

import cs3500.tripletriad.gamecomponents.Deck;
import cs3500.tripletriad.gamecomponents.Grid;
import cs3500.tripletriad.player.Player;

/**
 * Represents the interface for the game model for triple triad.
 */
public interface TTModel extends ReadOnlyTripleTriadModel {

  /**
   * Assigns cards to players.
   */
  void startGame(Deck deck, Grid grid);


   //The model should assign the cards to each player.
   //The model should compare cards and allow the players to place cards.

  /**
   * This allows the player to play any card to the grid.
   * @param cardInHand the card to be played to the grid.
   * @param row the row of the cell to be played to.
   * @param col the column of the cell to be played to.
   */
  public void playToGrid(int cardInHand, int row, int col);


}
