package cs3500.tripletriad.player;

/**
 * This represents the player's actions in the Red Solo Model game.
 */
public interface PlayerActions {

  /**
   * This allows the player to play to grid.
   * @param row the row of the grid that the card will be played.
   * @param col the column of the grid that the card will be played.
   * @param card the index of the card in the player's hand that will be played to grid.
   */
  void playerPlayToGrid(int row, int col, int card);


}
