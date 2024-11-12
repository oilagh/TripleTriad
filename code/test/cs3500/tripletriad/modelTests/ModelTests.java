package cs3500.tripletriad.modelTests;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import cs3500.tripletriad.gamecomponents.AttackValue;
import cs3500.tripletriad.gamecomponents.Card;
import cs3500.tripletriad.gamecomponents.Deck;
import cs3500.tripletriad.gamecomponents.BoardCell;
import cs3500.tripletriad.gamecomponents.Direction;
import cs3500.tripletriad.gamecomponents.Grid;
import cs3500.tripletriad.model.TTModel;
import cs3500.tripletriad.model.TripleTriadModel;
import cs3500.tripletriad.player.Player;
import cs3500.tripletriad.gamecomponents.PlaceHolder;

public class ModelTests {
  private TripleTriadModel model;
  private Player bluePlayer;
  private Player redPlayer;
  private Grid grid;
  private Deck deck;
  private List<Card> listCards;
  private List<List<BoardCell>> listOfListCards;
  private Card blueCard;
  private Card blueCard2;
  private Card blueCard3;
  private Card blueCard4;
  private Card blueCard5;
  private Card redCard;
  private Card redCard2;
  private Card redCard3;
  private Card redCard4;
  private Card redCard5;
  private List<BoardCell> cellList1;
  private List<BoardCell> cellList2;
  private BoardCell cardCell;
  private Map<Direction, AttackValue> values;
  private Map<Direction, AttackValue> values2;


  @Before
  public void init() {
    this.bluePlayer = new Player(Color.BLUE);
    this.redPlayer = new Player(Color.RED);
    values =
            new HashMap<>(Map.of(Direction.NORTH, AttackValue.ONE, Direction.EAST,
                    AttackValue.THREE, Direction.SOUTH, AttackValue.FIVE, Direction.WEST, AttackValue.NINE));
    values2 =
            new HashMap<>(Map.of(Direction.NORTH, AttackValue.FOUR, Direction.EAST,
                    AttackValue.THREE, Direction.SOUTH, AttackValue.THREE, Direction.WEST, AttackValue.EIGHT));
    blueCard = new Card("Blue Card", values, Color.BLUE);
    redCard = new Card("Red Card", values2, Color.RED);
    blueCard2 = new Card("Blue Card 2", values2, Color.BLUE);
    redCard2 = new Card("Red Card 2", values, Color.RED);
    blueCard3 = new Card("Blue Card", values2, Color.BLUE);
    redCard3 = new Card("Red Card", values, Color.RED);
    blueCard4 = new Card("Blue Card 4", values2, Color.BLUE);
    redCard4 = new Card("Red Card 4", values, Color.RED);
    blueCard5 = new Card("Blue Card 5", values2, Color.BLUE);
    redCard5 = new Card("Red Card 5", values, Color.RED);
    listCards = new ArrayList<>();
    listCards.add(blueCard);
    listCards.add(redCard);
    listCards.add(blueCard2);
    listCards.add(redCard2);
    listCards.add(blueCard3);
    listCards.add(redCard3);
    listCards.add(blueCard4);
    listCards.add(redCard4);
    listCards.add(blueCard5);
    listCards.add(redCard5);
    cellList1 = new ArrayList<>();
    cellList2 = new ArrayList<>();
    cellList1.add(new PlaceHolder());
    cellList1.add(new PlaceHolder());
    cellList1.add(new PlaceHolder());
    cellList2.add(new PlaceHolder());
    cellList2.add(new PlaceHolder());
    cellList2.add(new PlaceHolder());
    this.deck = new Deck(listCards);
    listOfListCards = new ArrayList<>();
    listOfListCards.add(cellList1);
    listOfListCards.add(cellList2);
    grid = new Grid(listOfListCards);
    this.model = new TripleTriadModel(redPlayer, bluePlayer, grid, deck);
  }

  //tests if the constructor throws an exception when the grid is null.
  @Test (expected = IllegalArgumentException.class)
  public void testNullGrid() {
    TTModel model = new TripleTriadModel(null, deck);
  }

  //tests if the constructor throws an exception when the deck is null.
  @Test (expected = IllegalArgumentException.class)
  public void testNullDeck() {
    TTModel model = new TripleTriadModel(grid, null);
  }

  //tests if the startGame method works correctly
  @Test
  public void testStartGameAssignsToPlayers() {
    List<Card> before = redPlayer.getHand();
    Assert.assertTrue(before.isEmpty());
    model.startGame(deck, grid);
    Assert.assertFalse(redPlayer.getHand().isEmpty());
  }

  //tests if the startGame method throws an exception if the deck of
  //cards is too small
  @Test
  public void testSmallDeckException() {
    List<Card> listOfCards = new ArrayList<>();
    Deck deckSmall = new Deck(listOfCards);
    Assert.assertThrows(IllegalArgumentException.class, () -> model.startGame(deckSmall, grid));
  }


}
