package cs3500.tripletriad.strategytests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cs3500.tripletriad.gamecomponents.AttackValue;
import cs3500.tripletriad.gamecomponents.BoardCell;
import cs3500.tripletriad.gamecomponents.Card;
import cs3500.tripletriad.gamecomponents.Deck;
import cs3500.tripletriad.gamecomponents.Direction;
import cs3500.tripletriad.gamecomponents.Grid;
import cs3500.tripletriad.gamecomponents.GridTT;
import cs3500.tripletriad.gamecomponents.PlaceHolder;
import cs3500.tripletriad.model.TTModel;
import cs3500.tripletriad.model.TripleTriadModel;
import cs3500.tripletriad.player.Player;
import cs3500.tripletriad.player.Strategies;
import cs3500.tripletriad.player.StrategyOne;
import cs3500.tripletriad.player.StrategyTwo;
import cs3500.tripletriad.view.TTStringView;
import cs3500.tripletriad.view.TTView;


/**
 * Represents the test class for the Strategy implementation.
 */
public class StrategyTests {

  private Deck deck;
  private Map<Direction, AttackValue> values;
  private TTModel model;


  @Before
  public void init() {
    Player bluePlayer = new Player(Color.BLUE);
    Player redPlayer = new Player(Color.RED);
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
    Card blueCard2 = new Card("Blue Card2", values2, Color.BLUE);
    Card redCard2 = new Card("Red Card2", values, Color.RED);
    Card blueCard3 = new Card("Blue Card", values2, Color.BLUE);
    Card redCard3 = new Card("Red Card", values, Color.RED);
    Card blueCard4 = new Card("Blue Card4", values2, Color.BLUE);
    Card redCard4 = new Card("Red Card4", values, Color.RED);
    Card blueCard5 = new Card("Blue Card5", values2, Color.BLUE);
    Card redCard5 = new Card("Red Card5", values, Color.RED);
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
    GridTT grid = new Grid(listOfListCards);
    model = new TripleTriadModel(redPlayer, bluePlayer, grid, deck);
    model.startGame(deck, grid);
  }


  @Test
  public void testStrategyTwo() {
    Strategies strategyTwo = new StrategyTwo(model);
    strategyTwo.strategyTwo();
    model.playToGrid(strategyTwo.getCard(), strategyTwo.getRow(),strategyTwo.getCol());
    TTView view = new TTStringView(model);
    //checks to see if the card was placed in the right spot.
    Assert.assertTrue(view.toString().contains("Player: BLUE\n"
    + "R__\n"
    + "___\n"));
    //checks to see if the right card was placed on the grid.
    Assert.assertEquals(model.getSpecificPlayer(Color.RED).handToString(), "Red Card 4 3 3 8\n"
    + "Blue Card2 4 3 3 8\n");
    strategyTwo.strategyTwo();
    model.playToGrid(strategyTwo.getCard(), strategyTwo.getRow(), strategyTwo.getCol());
    TTView view2 = new TTStringView(model);
    //checks to see if the card was placed in the right spot.
    Assert.assertTrue(view2.toString().contains("Player: RED\n"
    + "R__\n"
    + "B__\n"));
    //checks to see if the right card was placed on the grid.
    Assert.assertEquals(model.getSpecificPlayer(Color.BLUE).handToString(), "Red Card2 1 5 3 9\n"
    + "Red Card 1 5 3 9\n");
    strategyTwo.strategyTwo();
    model.playToGrid(strategyTwo.getCard(), strategyTwo.getRow(), strategyTwo.getCol());
    TTView view3 = new TTStringView(model);
    //checks to see if the card was placed in the right spot.
    Assert.assertTrue(view3.toString().contains("Player: BLUE\n"
    + "R_R\n"
    + "B__\n"));
    //checks to see if the right card was placed on the grid.
    Assert.assertEquals(model.getSpecificPlayer(Color.RED).handToString(), "Blue Card2 4 3 3 8\n");
    strategyTwo.strategyTwo();
    model.playToGrid(strategyTwo.getCard(), strategyTwo.getRow(), strategyTwo.getCol());
    TTView view4 = new TTStringView(model);
    //checks to see if the card was placed in the right spot.
    Assert.assertTrue(view4.toString().contains("Player: RED\n"
    + "R_R\n"
    + "B_B\n"));
    //checks to see if the right card was placed on the grid.
    Assert.assertEquals(model.getSpecificPlayer(Color.BLUE).handToString(), "Red Card 1 5 3 9\n");
  }

  //tests to see if strategy one throws an exception
  //when the game is already over.
  @Test (expected = IllegalStateException.class)
  public void testStrategyTwoWhenGameOver() {
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
    Strategies strategy2 = new StrategyTwo(model1);
    strategy2.strategyTwo();
  }


  //tests to see if strategy one throws an exception
  //when the game is already over.
  @Test (expected = IllegalStateException.class)
  public void testStrategyOneWhenGameOver() {
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
    Strategies strategy1 = new StrategyOne(model1);
    strategy1.strategyOne();
  }




}
