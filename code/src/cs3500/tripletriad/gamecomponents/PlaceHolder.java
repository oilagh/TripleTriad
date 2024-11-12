package cs3500.tripletriad.gamecomponents;

/**
 * Represents an empty card cell that could contain a card.
 */
public class PlaceHolder implements BoardCell{
  @Override
  public Card getCard() {
    throw new IllegalArgumentException("Cannot get card from a PlaceHolder");
  }

  @Override
  public String toString() {
    return "_";
  }

  @Override
  public String toStringForGrid() {
    return "_";
  }
}
