package cs3500.tripletriad.player;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import cs3500.tripletriad.gamecomponents.Card;

/**
 * This class represents a player of triple triad.
 */
public class Player implements TTPlayer {
  private List<Card> hand;
  private Color color;

  /**
   * Constructor for Player.
   * @param hand the cards that belong to the player.
   */
  public Player(List<Card> hand, Color color) {
    if (hand == null || color == null) {
      throw new IllegalArgumentException("Hand cannot be null");
    }
    for (Card card : hand) {
      if (card == null) {
        throw new IllegalArgumentException("Card in hand cannot be null");
      }
    }
    this.color = color;
    this.hand = hand;
  }

  public Player(TTPlayer player) {
    Player other = (Player) player;
    this.color = other.color;
    this.hand = new ArrayList<>();
    for (int index = 0; index < other.hand.size(); index++) {
      Card card = other.hand.get(index);
      this.hand.add(new Card(card));
    }
  }

  /**
   * Constructor for Player.
   * @param color the color of the player.
   */
  public Player(Color color) {
    if (color == null) {
      throw new IllegalArgumentException("Color cannot be null");
    }
    this.color = color;
    this.hand = new ArrayList<>();
  }

  @Override
  public boolean equals(Object object) {
    if (object instanceof Player) {
      Player player = (Player) object;
      return this.hand.equals(player.hand);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(hand, color);
  }

  @Override
  public void addCard(Card card) {
    this.hand.add(card);
  }

  @Override
  public Color getColor() {
    return this.color;
  }

  @Override
  public List<Card> getHand() {
    return this.hand;
  }

  @Override
  public String toString() {
    if (this.color.equals(Color.BLUE)) {
      return "BLUE";
    }
    else if (this.color.equals(Color.RED)) {
      return "RED";
    }
    throw new IllegalArgumentException("Incorrect player");
  }

  @Override
  public String handToString() {
    StringBuilder sb = new StringBuilder();
    for (int card = 0; card < this.hand.size(); card++) {
      Card cards = this.hand.get(card);
      sb.append(cards.toString());
      sb.append("\n");
    }
    return sb.toString();
  }

}
