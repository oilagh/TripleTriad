package cs3500.tripletriad.gamecomponents;

/**
 * Represents a cell inside the board.
 * A cell can either be a card, hole, or placeholder.
 */
public interface BoardCell {

  /**
   * Gets the card from a board Cell if it can.
   * Otherwise it throws an exception if empty cell or hole.
   * @return Card.
   */
  public Card getCard();

  /**
   * Returns the string representation to be used in grid view.
   * @return the string version of the cell.
   */
  String toStringForGrid();

  /**
   * Returns the string of the cell.
   * @return String of the cell "[Card]", " ", "_".
   */
  public String toString();
}
