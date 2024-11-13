package cs3500.tripletriad.player;

import java.awt.Color;
import java.util.List;

import cs3500.tripletriad.gamecomponents.Card;

/**
 * Interface for the player class.
 */
public interface TTPlayer {

  /**
   * Adds the card to the players hand.
   * @param card the card to be added.
   */
  void addCard(Card card);

  /**
   * Gets the color assigned to the player.
   * @return the color assigned to the player.
   */
  Color getColor();

  /**
   * Gets the hand of the player.
   * @return the hand of cards of the player.
   */
  List<Card> getHand();

  /**
   * Creates a string representation of the player.
   * @return string representation of the player.
   */
  String toString();

  /**
   * Creates a string representation of the player's hand.
   * @return string representation of the player's hand.
   */
  String handToString();


}
