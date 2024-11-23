package cs3500.tripletriad.modeltests;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.awt.Color;
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
import cs3500.tripletriad.gamecomponents.Hole;
import cs3500.tripletriad.model.TTModel;
import cs3500.tripletriad.model.TripleTriadModel;
import cs3500.tripletriad.player.HumanPlayer;
import cs3500.tripletriad.player.Player;
import cs3500.tripletriad.gamecomponents.PlaceHolder;

/**
 * The test class for the model implementation.
 */
public class ModelTests {
  private TripleTriadModel model;
  private Player redPlayer;
  private Grid grid;
  private Deck deck;
  private Map<Direction, AttackValue> values;


  @Before
  public void init() {
    Player bluePlayer = new HumanPlayer(Color.BLUE);
    this.redPlayer = new HumanPlayer(Color.RED);
    values =
            new HashMap<>(Map.of(Direction.NORTH, AttackValue.ONE,
                    Direction.EAST, AttackValue.THREE,
                    Direction.SOUTH, AttackValue.FIVE,
                    Direction.WEST, AttackValue.NINE));
    Map<Direction, AttackValue> values2 =
            new HashMap<>(Map.of(Direction.NORTH, AttackValue.FOUR,
                    Direction.EAST, AttackValue.THREE,
                    Direction.SOUTH, AttackValue.THREE,
                    Direction.WEST, AttackValue.EIGHT));
    Card blueCard = new Card("Blue Card", values, Color.BLUE);
    Card redCard = new Card("Red Card", values2, Color.RED);
    Card blueCard2 = new Card("Blue Card 2", values2, Color.BLUE);
    Card redCard2 = new Card("Red Card 2", values, Color.RED);
    Card blueCard3 = new Card("Blue Card", values2, Color.BLUE);
    Card redCard3 = new Card("Red Card", values, Color.RED);
    Card blueCard4 = new Card("Blue Card 4", values2, Color.BLUE);
    Card redCard4 = new Card("Red Card 4", values, Color.RED);
    Card blueCard5 = new Card("Blue Card 5", values2, Color.BLUE);
    Card redCard5 = new Card("Red Card 5", values, Color.RED);
    List<Card> listCards = new ArrayList<>();
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
    List<BoardCell> cellList1 = new ArrayList<>();
    List<BoardCell> cellList2 = new ArrayList<>();
    cellList1.add(new PlaceHolder());
    cellList1.add(new PlaceHolder());
    cellList1.add(new PlaceHolder());
    cellList2.add(new PlaceHolder());
    cellList2.add(new PlaceHolder());
    cellList2.add(new PlaceHolder());
    this.deck = new Deck(listCards);
    List<List<BoardCell>> listOfListCards = new ArrayList<>();
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

  //tests if the winning player is determined correctly
  //when it is a tie.
  @Test
  public void testGameEndsInWinningPlayer() {
    model.startGame(deck, grid);
    model.playToGrid(0,0, 0);
    model.playToGrid(0,0, 1);
    model.playToGrid(0, 0, 2);
    model.playToGrid(0, 1, 0);
    model.playToGrid(0, 1, 1);
    model.playToGrid(0, 1, 2);
    Assert.assertTrue(model.isGameOver());
    Assert.assertThrows(IllegalStateException.class, () -> model.winningPlayer());
  }


  //tests if the game is over.
  //and determines the winner correctly.
  @Test
  public void endGame() {
    List<List<BoardCell>> listOfListCards = new ArrayList<>();
    List<BoardCell> list1 = new ArrayList<>();
    List<BoardCell> list2 = new ArrayList<>();
    list1.add(new Card("Card 1", values, Color.BLUE));
    list2.add(new Card("Card 1", values, Color.BLUE));
    listOfListCards.add(list1);
    listOfListCards.add(list2);
    Grid grid1 = new Grid(listOfListCards);
    TTModel model1 = new TripleTriadModel();
    model1.startGame(deck, grid1);
    Assert.assertTrue(model1.isGameOver());
    Assert.assertEquals(model1.winningPlayer().getColor(), Color.BLUE);
  }

  //tests when played to another card
  @Test (expected = IllegalArgumentException.class)
  public void testPlayToSameCard() {
    model.startGame(deck, grid);
    model.playToGrid(0,0, 0);
    model.playToGrid(0,0, 0);

  }

  //test when played to a hole
  @Test (expected = IllegalArgumentException.class)
  public void testPlayToHole() {
    List<List<BoardCell>> listOfListCards = new ArrayList<>();
    List<BoardCell> list1 = new ArrayList<>();
    List<BoardCell> list2 = new ArrayList<>();
    list1.add(new PlaceHolder());
    list2.add(new Hole());
    listOfListCards.add(list1);
    listOfListCards.add(list2);
    Grid grid1 = new Grid(listOfListCards);
    TTModel model2 = new TripleTriadModel();
    model2.startGame(deck, grid1);
    model2.playToGrid(0, 1, 0);
  }

  // tests if the index given is out of bounds.
  @Test (expected = IllegalArgumentException.class)
  public void testOutOfBoundsGrid() {
    model.startGame(deck, grid);
    model.playToGrid(10, 50, 50);
  }

  //tests if the index given is out of bounds from players hand.
  @Test (expected = IllegalArgumentException.class)
  public void testOfBoundsDeck() {
    model.startGame(deck, grid);
    model.playToGrid(50, 0, 0);
  }


}
