package cs3500.tripletriad.cardTests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import cs3500.tripletriad.gamecomponents.AttackValue;
import cs3500.tripletriad.gamecomponents.Card;
import cs3500.tripletriad.gamecomponents.Direction;


/**
 * Test cases for the tic tac toe model. Verifying that game state is properly managed, and
 * all game actions are properly validated.
 */
public class CardsTests {
  private Card redCard;
  private Map<Direction, AttackValue> values;
  private Map<Direction, AttackValue> values2;

  @Before
  public void setup() {
    values =
            new HashMap<>(Map.of(Direction.NORTH, AttackValue.ONE, Direction.EAST,
                    AttackValue.THREE, Direction.SOUTH, AttackValue.FIVE, Direction.WEST, AttackValue.NINE));
    values2 =
            new HashMap<>(Map.of(Direction.NORTH, AttackValue.FOUR, Direction.EAST,
                    AttackValue.THREE, Direction.SOUTH, AttackValue.THREE, Direction.WEST, AttackValue.EIGHT));
    redCard = new Card("Red Card", values, Color.BLUE);
  }

  //Tests if the toString() method for card outputs the correct string.
  @Test
  public void testCardString() {
    Assert.assertEquals(redCard.toString(), "Red Card 1 5 3 9");
  }

  //Tests if the constructor for card throws an IllegalArgumentException if the
  //name is null.
  @Test (expected = IllegalArgumentException.class)
  public void testCardNullName(){
    Card card = new Card(null, values, Color.PINK);
  }

  //Tests if the constructor for card throws an IllegalArgumentException if the
  //AttackValues are null.
  @Test (expected = IllegalArgumentException.class)
  public void testCardNullValues() {
    Card card = new Card("Card 1", null, Color.BLUE);
  }

  //Tests if the constructor for the card throws an IllegalArgumentException if the
  //color is null.
  @Test (expected = IllegalArgumentException.class)
  public void testCardNullColor() {
    Card card = new Card("Card 2", values, null);
  }

  //Tests if the constructor for card throws an IllegalArgumentException if the
  //color given is not a valid color.
  @Test (expected = IllegalArgumentException.class)
  public void testCardWrongColor() {
    Card card = new Card("Card 3", values, Color.GREEN);
  }

  //Tests if the getAttackValues returns the correct attack values for the card.
  @Test
  public void testGetAttackValues() {
    Assert.assertEquals("Attack values should be the same", redCard.getAttackValues(), values);
  }

  //Tests if the getColor returns the correct color for the card.
  @Test
  public void testGetColor() {
    Assert.assertEquals("Colors should be equal.", redCard.getColor(), Color.BLUE);
  }

  //Tests if the equals method for card is correct when true.
  @Test
  public void testCardEqualsTrue() {
    Assert.assertEquals(redCard, redCard);
  }

  //Tests if the equals method for card is correct when false.
  @Test
  public void testCardEqualsFalse() {
    Card newCard = new Card("New Card", values2, Color.BLUE);
    Assert.assertNotEquals(redCard, newCard);
  }

  @Test
  public void testToStringCard() {
    String expected = "Red Card 1 5 3 9";
    Assert.assertEquals("Should be the same.", redCard.toString(), expected);
  }

}

