package cs3500.tripletriad.gamecomponents;


import java.awt.Color;
import java.util.Map;

/**
 * This class represents a card that will be used in the TripleTriad game.
 */
public class Card implements BoardCell{
  private String name;
  private Map<Direction, AttackValue> attackValues;
  // doesn't need to know who owns it??
  private Color color;

  /**
   * Constructor for Card.
   * @param name the name of the card.
   * @param values the map that represents the values and directions of the card.
   * @param player the player that the card belongs to.
   */
  public Card(String name, Map<Direction, AttackValue> values, Color player) {
    if (name == null || player == null || values == null) {
      throw new IllegalArgumentException("None of the arguments can be null.");
    }
    this.name = name;
    if (!player.equals(Color.BLUE) && !player.equals(Color.RED) && !player.equals(Color.WHITE)) {
      throw new IllegalArgumentException("Color should be red or blue");
    }
    this.color = player;
    this.attackValues = values;
  }

  /**
   * Returns the attack values associated to that card.
   * @return the map that represents the attack values for the card.
   */
  public Map<Direction, AttackValue> getAttackValues() {
    return this.attackValues;
  }

  /**
   * Returns the color associated to that card.
   * @return the color that is assigned to the card.
   */
  public Color getColor() {
    return this.color;
  }

  /**
   * Returns the name associated to that card.
   * @return the name that is assigned to the card.
   */
  public String getName() {
    return this.name;
  }

  /**
   * Determines if the two cards are equivalent.
   * @param card the card that will be compared against.
   * @return true if the cards are the same, and false otherwise.
   */
  @Override
  public boolean equals(Object card) {
    if (card instanceof Card) {
      Card card2 = (Card) card;
      Map<Direction, AttackValue> cardValues = card2.getAttackValues();
      return cardValues.equals(this.attackValues) && this.color.equals(card2.getColor())
              && this.name.equals(card2.getName());
    }
    return false;
  }


  /**
   * Returns the card.
   * @return the card itself.
   */
  @Override
  public Card getCard() {
    return this;
  }

  /**
   * Returns the String representation of the Card.
   * @return A string representation of the card.
   */
  @Override
  public String toString() {
    return this.name + " " +
            attackValues.get(Direction.NORTH) + " " +
            attackValues.get(Direction.SOUTH) + " " +
            attackValues.get(Direction.EAST) + " " +
            attackValues.get(Direction.WEST);
  }

  @Override
  public String toStringForGrid() {
    if (this.color.equals(Color.BLUE)) {
      return "B";
    }
    if (this.color.equals(Color.RED)) {
      return "R";
    }
    throw new IllegalArgumentException("Invalid card");
  }

  /**
   * Changes the color of the card to the given color.
   * @param color the color the card will be changed to.
   */
  public void changeColor(Color color) {
    this.color = color;
  }

}
