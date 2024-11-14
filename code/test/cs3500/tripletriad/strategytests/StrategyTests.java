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
import cs3500.tripletriad.gamecomponents.DeckTT;
import cs3500.tripletriad.gamecomponents.Direction;
import cs3500.tripletriad.gamecomponents.Grid;
import cs3500.tripletriad.gamecomponents.GridTT;
import cs3500.tripletriad.gamecomponents.Hole;
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
  private TTModel model2;
  private Deck deck2;


  @Before
  public void init() {
    Player bluePlayer = new Player(Color.BLUE);
    Player redPlayer = new Player(Color.RED);
    Player bluePlayer2 = new Player(Color.BLUE);
    Player redPlayer2 = new Player(Color.RED);
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

    Map<Direction, AttackValue> values3 =
            new HashMap<>(Map.of(Direction.NORTH, AttackValue.TEN,
            Direction.EAST, AttackValue.TEN,
            Direction.SOUTH, AttackValue.TEN,
            Direction.WEST, AttackValue.TEN));
    Map<Direction, AttackValue> values4 =
            new HashMap<>(Map.of(Direction.NORTH, AttackValue.FIVE,
                    Direction.EAST, AttackValue.THREE,
                    Direction.SOUTH, AttackValue.EIGHT,
                    Direction.WEST, AttackValue.ONE));

    Map<Direction, AttackValue> values5 =
            new HashMap<>(Map.of(Direction.NORTH, AttackValue.NINE,
                    Direction.EAST, AttackValue.TWO,
                    Direction.SOUTH, AttackValue.FOUR,
                    Direction.WEST, AttackValue.SEVEN));
    Map<Direction, AttackValue> values6 =
            new HashMap<>(Map.of(Direction.NORTH, AttackValue.ONE,
                    Direction.EAST, AttackValue.ONE,
                    Direction.SOUTH, AttackValue.ONE,
                    Direction.WEST, AttackValue.ONE));
    Map<Direction, AttackValue> values7 =
            new HashMap<>(Map.of(Direction.NORTH, AttackValue.THREE,
                    Direction.EAST, AttackValue.TWO,
                    Direction.SOUTH, AttackValue.FOUR,
                    Direction.WEST, AttackValue.FIVE));
    Map<Direction, AttackValue> values8 =
            new HashMap<>(Map.of(Direction.NORTH, AttackValue.NINE,
                    Direction.EAST, AttackValue.TWO,
                    Direction.SOUTH, AttackValue.SEVEN,
                    Direction.WEST, AttackValue.SEVEN));
    Map<Direction, AttackValue> values9 =
            new HashMap<>(Map.of(Direction.NORTH, AttackValue.TWO,
                    Direction.EAST, AttackValue.NINE,
                    Direction.SOUTH, AttackValue.FIVE,
                    Direction.WEST, AttackValue.EIGHT));
    Map<Direction, AttackValue> values10 =
            new HashMap<>(Map.of(Direction.NORTH, AttackValue.EIGHT,
                    Direction.EAST, AttackValue.SIX,
                    Direction.SOUTH, AttackValue.FIVE,
                    Direction.WEST, AttackValue.FOUR));
    Map<Direction, AttackValue> values11 =
            new HashMap<>(Map.of(Direction.NORTH, AttackValue.FOUR,
                    Direction.EAST, AttackValue.SIX,
                    Direction.SOUTH, AttackValue.EIGHT,
                    Direction.WEST, AttackValue.SEVEN));
    Map<Direction, AttackValue> values12 =
            new HashMap<>(Map.of(Direction.NORTH, AttackValue.ONE,
                    Direction.EAST, AttackValue.TWO,
                    Direction.SOUTH, AttackValue.THREE,
                    Direction.WEST, AttackValue.FOUR));
    Map<Direction, AttackValue> values13 =
            new HashMap<>(Map.of(Direction.NORTH, AttackValue.NINE,
                    Direction.EAST, AttackValue.SIX,
                    Direction.SOUTH, AttackValue.EIGHT,
                    Direction.WEST, AttackValue.TEN));
    Map<Direction, AttackValue> values14 =
            new HashMap<>(Map.of(Direction.NORTH, AttackValue.ONE,
                    Direction.EAST, AttackValue.SEVEN,
                    Direction.SOUTH, AttackValue.FIVE,
                    Direction.WEST, AttackValue.SIX));
    Map<Direction, AttackValue> values15 =
            new HashMap<>(Map.of(Direction.NORTH, AttackValue.FIVE,
                    Direction.EAST, AttackValue.TEN,
                    Direction.SOUTH, AttackValue.EIGHT,
                    Direction.WEST, AttackValue.THREE));
    Map<Direction, AttackValue> values16 =
            new HashMap<>(Map.of(Direction.NORTH, AttackValue.FOUR,
                    Direction.EAST, AttackValue.NINE,
                    Direction.SOUTH, AttackValue.FIVE,
                    Direction.WEST, AttackValue.FOUR));
    Map<Direction, AttackValue> values17 =
            new HashMap<>(Map.of(Direction.NORTH, AttackValue.SIX,
                    Direction.EAST, AttackValue.SIX,
                    Direction.SOUTH, AttackValue.FIVE,
                    Direction.WEST, AttackValue.TWO));
    Map<Direction, AttackValue> values18 =
            new HashMap<>(Map.of(Direction.NORTH, AttackValue.EIGHT,
                    Direction.EAST, AttackValue.ONE,
                    Direction.SOUTH, AttackValue.THREE,
                    Direction.WEST, AttackValue.TWO));
    Map<Direction, AttackValue> values19 =
            new HashMap<>(Map.of(Direction.NORTH, AttackValue.SIX,
                    Direction.EAST, AttackValue.SIX,
                    Direction.SOUTH, AttackValue.SIX,
                    Direction.WEST, AttackValue.THREE));
    Map<Direction, AttackValue> values20 =
            new HashMap<>(Map.of(Direction.NORTH, AttackValue.EIGHT,
                    Direction.EAST, AttackValue.ONE,
                    Direction.SOUTH, AttackValue.EIGHT,
                    Direction.WEST, AttackValue.ONE));


    Card card1 = new Card ("Card1", values4, Color.RED);
    Card card2 = new Card("Card2", values3, Color.BLUE);
    Card card3 = new Card("Card3", values, Color.RED);
    Card card4 = new Card("Card4", values2, Color.BLUE);
    Card card5 = new Card("Card5", values5, Color.RED);
    Card card6 = new Card("Card6", values6, Color.BLUE);
    Card card7 = new Card("Card7", values7, Color.RED);
    Card card8 = new Card("Card8", values8, Color.BLUE);
    Card card9 = new Card("Card9", values9, Color.RED);
    Card card10 = new Card("Card10", values10, Color.BLUE);
    Card card11 = new Card("Card11", values11, Color.RED);
    Card card12 = new Card("Card12", values12, Color.BLUE);
    Card card13 = new Card("Card13", values13, Color.RED);
    Card card14 = new Card("Card14", values14, Color.BLUE);
    Card card15 = new Card("Card15", values15, Color.RED);
    Card card16 = new Card("Card16", values16, Color.BLUE);
    Card card17 = new Card("Card17", values17, Color.RED);
    Card card18 = new Card("Card18", values18, Color.BLUE);
    Card card19 = new Card("Card19", values19, Color.RED);
    Card card20 = new Card("Card20", values20, Color.BLUE);



    List<Card> card = new ArrayList<>();
    card.add(card1);
    card.add(card2);
    card.add(card3);
    card.add(card4);
    card.add(card5);
    card.add(card6);
    card.add(card7);
    card.add(card8);
    card.add(card9);
    card.add(card10);
    card.add(card11);
    card.add(card12);
    card.add(card13);
    card.add(card14);
    card.add(card15);
    card.add(card16);
    card.add(card17);
    card.add(card18);
    card.add(card19);
    card.add(card20);
    this.deck2 = new Deck(card);

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


    List<BoardCell> cellList3 = new ArrayList<>();
    List<BoardCell> cellList4 = new ArrayList<>();
    List<BoardCell> cellList5 = new ArrayList<>();
    cellList3.add(new PlaceHolder());
    cellList3.add(new PlaceHolder());
    cellList3.add(new PlaceHolder());
    cellList3.add(new PlaceHolder());

    cellList4.add(new PlaceHolder());
    cellList4.add(new PlaceHolder());
    cellList4.add(new PlaceHolder());
    cellList4.add(new Hole());

    cellList5.add(new Hole());
    cellList5.add(new PlaceHolder());
    cellList5.add(new PlaceHolder());
    cellList5.add(new PlaceHolder());

    List<List<BoardCell>> listCells = new ArrayList<>();
    listCells.add(cellList3);
    listCells.add(cellList4);
    listCells.add(cellList5);
    Grid grid2 = new Grid(listCells);

    model = new TripleTriadModel(redPlayer, bluePlayer, grid, deck);
    model.startGame(deck, grid);

    model2 = new TripleTriadModel(redPlayer2, bluePlayer2, grid2, deck2);
    model2.startGame(deck2, grid2);
  }


  @Test
  public void testStrategyOne() {
    TTView view = new TTStringView(model2);
    Strategies strategyOne = new StrategyOne(model2);
    Strategies strategyTwo = new StrategyTwo(model2);
    model2.playToGrid(0, 1, 2);
    Assert.assertTrue(view.toString().contains( "Player: BLUE\n"
            + "____\n"
            + "__R \n"
            + " ___\n"));
    strategyOne.strategyOne();
    model2.playToGrid(strategyOne.getCard(), strategyOne.getRow(), strategyOne.getCol());
    Assert.assertTrue(view.toString().contains("Player: RED\n"
            + "__B_\n"
            + "__B \n"
            + " ___\n"));
    Assert.assertEquals(model2.getSpecificPlayer(Color.BLUE).handToString(),
            "Card6 1 1 1 1\n"
                    + "Card7 3 4 2 5\n"
                    + "Card9 2 5 9 8\n" + "Card10 8 5 6 4\n");
    strategyTwo.strategyTwo();
    model2.playToGrid(strategyTwo.getCard(), strategyTwo.getRow(), strategyTwo.getCol());
    Assert.assertTrue(view.toString().contains("Player: BLUE\n"
            + "R_B_\n" + "__B \n" + " ___\n"));
    strategyOne.strategyOne();
    model2.playToGrid(strategyOne.getCard(), strategyOne.getRow(), strategyOne.getCol());
    Assert.assertTrue(view.toString().contains("Player: RED\n" +"RBB_\n" + "__B \n"
            + " ___\n"));
    strategyTwo.strategyTwo();
    model2.playToGrid(strategyOne.getCard(), strategyTwo.getRow(), strategyTwo.getCol());
    Assert.assertTrue(view.toString().contains("Player: BLUE\n" + "RBBR\n"
                    + "__B \n" + " ___\n"));
    Assert.assertEquals(model2.getSpecificPlayer(Color.RED).handToString(), "Card4 4 3 3 8\n"
            + "Card5 9 4 2 7\n");
  }


  //Tests strategy One when no cards can be flipped.
  //tests if strategy one places cards in the index closest to zero.
  //And places the card at index zero.
  @Test
  public void testStrategyOneNoFlips() {
    Strategies strategyOne = new StrategyOne(model);
    strategyOne.strategyOne();
    model.playToGrid(strategyOne.getCard(), strategyOne.getRow(), strategyOne.getCol());
    TTView view = new TTStringView(model);
    //checks to see if the strategy one places the card in the correct place.
    Assert.assertTrue(view.toString().contains("Player: BLUE\n"
            + "R__\n"
            + "___\n"));
    //checks to see if the right card was placed.
    Assert.assertEquals(model.getSpecificPlayer(Color.RED).handToString(),
                    "Red Card 4 3 3 8\n" + "Blue Card2 4 3 3 8\n");
    strategyOne.strategyOne();
    model.playToGrid(strategyOne.getCard(), strategyOne.getRow(), strategyOne.getCol());
    //checks to see if the strategy one places the card in the correct place.
    Assert.assertTrue(view.toString().contains("Player: RED\n"
            + "RB_\n" + "___"));
    strategyOne.strategyOne();
    //checks to see if the right card was placed.
    Assert.assertEquals(model.getSpecificPlayer(Color.BLUE).handToString(),
            "Blue Card 4 3 3 8\n" + "Red Card 1 5 3 9\n");
    model.playToGrid(strategyOne.getCard(), strategyOne.getRow(), strategyOne.getCol());
    Assert.assertTrue(view.toString().contains("Player: BLUE\n"
            + "RB_\n" + "R__"));
    strategyOne.strategyOne();
    model.playToGrid(strategyOne.getCard(), strategyOne.getRow(), strategyOne.getCol());
    Assert.assertTrue(view.toString().contains("Player: RED\n"
            + "RB_\n" + "RB_"));
    strategyOne.strategyOne();
    model.playToGrid(strategyOne.getCard(), strategyOne.getRow(), strategyOne.getCol());
    Assert.assertTrue(view.toString().contains("Player: BLUE\n" + "RBR\n" + "RB_"));
    strategyOne.strategyOne();
    model.playToGrid(strategyOne.getCard(), strategyOne.getRow(), strategyOne.getCol());
    Assert.assertTrue(view.toString().contains("Player: RED\n" + "RBR\n" + "RBB"));
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
    Assert.assertEquals(model.getSpecificPlayer(Color.RED).handToString(),
            "Red Card 4 3 3 8\n" + "Blue Card2 4 3 3 8\n");
    strategyTwo.strategyTwo();
    model.playToGrid(strategyTwo.getCard(), strategyTwo.getRow(), strategyTwo.getCol());
    //checks to see if the card was placed in the right spot.
    Assert.assertTrue(view.toString().contains("Player: RED\n"
    + "R__\n"
    + "B__\n"));
    //checks to see if the right card was placed on the grid.
    Assert.assertEquals(model.getSpecificPlayer(Color.BLUE).handToString(),
            "Red Card2 1 5 3 9\n" + "Red Card 1 5 3 9\n");
    strategyTwo.strategyTwo();
    model.playToGrid(strategyTwo.getCard(), strategyTwo.getRow(), strategyTwo.getCol());
    //checks to see if the card was placed in the right spot.
    Assert.assertTrue(view.toString().contains("Player: BLUE\n"
    + "R_R\n"
    + "B__\n"));
    //checks to see if the right card was placed on the grid.
    Assert.assertEquals(model.getSpecificPlayer(Color.RED).handToString(),
            "Blue Card2 4 3 3 8\n");
    strategyTwo.strategyTwo();
    model.playToGrid(strategyTwo.getCard(), strategyTwo.getRow(), strategyTwo.getCol());
    //checks to see if the card was placed in the right spot.
    Assert.assertTrue(view.toString().contains("Player: RED\n"
    + "R_R\n"
    + "B_B\n"));
    //checks to see if the right card was placed on the grid.
    Assert.assertEquals(model.getSpecificPlayer(Color.BLUE).handToString(),
            "Red Card 1 5 3 9\n");
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
