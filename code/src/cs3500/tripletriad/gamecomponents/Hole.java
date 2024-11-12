package cs3500.tripletriad.gamecomponents;

/**
 * This represents a hole in the grid where a card cannot be placed.
 */
public class Hole implements BoardCell {
  @Override
  public Card getCard() {
    throw new IllegalArgumentException("Called getCard() on a Hole.");
  }

  @Override
  public String toString() {
    return " ";
  }

  @Override
  public String toStringForGrid() {
    return " ";
  }
}
