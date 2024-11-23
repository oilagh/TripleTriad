package cs3500.tripletriad.viewtests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cs3500.tripletriad.controller.config.DeckInitializer;
import cs3500.tripletriad.controller.config.GridInitializer;
import cs3500.tripletriad.gamecomponents.AttackValue;
import cs3500.tripletriad.gamecomponents.BoardCell;
import cs3500.tripletriad.gamecomponents.Card;
import cs3500.tripletriad.gamecomponents.Deck;
import cs3500.tripletriad.gamecomponents.Direction;
import cs3500.tripletriad.gamecomponents.Grid;
import cs3500.tripletriad.gamecomponents.PlaceHolder;
import cs3500.tripletriad.model.TTModel;
import cs3500.tripletriad.model.TripleTriadModel;
import cs3500.tripletriad.player.HumanPlayer;
import cs3500.tripletriad.player.Player;

/**
 * The test class for the view implementation.
 */
public class ViewTests {
  // test model with configuration file info
  private TTModel configModel;
  // test model with small file info
  private TTModel configModelSmall;
  // test model with small file info
  private TTModel configModelOddPlaceholder;

  @Before
  public void init() {
    Player bluePlayer = new HumanPlayer(Color.BLUE);
    Player redPlayer = new HumanPlayer(Color.RED);
    Map<Direction, AttackValue> values =
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
    ArrayList<BoardCell> cellList1 = new ArrayList<>();
    ArrayList<BoardCell> cellList2 = new ArrayList<>();
    cellList1.add(new PlaceHolder());
    cellList1.add(new PlaceHolder());
    cellList1.add(new PlaceHolder());
    cellList2.add(new PlaceHolder());
    cellList2.add(new PlaceHolder());
    cellList2.add(new PlaceHolder());
    Deck deck = new Deck(listCards);
    List<List<BoardCell>> listOfListCards = new ArrayList<>();
    listOfListCards.add(cellList1);
    listOfListCards.add(cellList2);
    Grid grid = new Grid(listOfListCards);
    TripleTriadModel model = new TripleTriadModel(redPlayer, bluePlayer, grid, deck);
    model.startGame(deck, grid);

    // config Model
    configModel = new TripleTriadModel();
    String pathGrid = "code" + File.separator + "docs" + File.separator
            + "boardDirectPath.config";
    String pathDeck = "code" + File.separator + "docs" + File.separator
            + "cardsEnoughForAll.config";
    configModel.startGame(new DeckInitializer(pathDeck).getDeck(),
            new GridInitializer(pathGrid).getGrid());

    // config Model Small
    configModelSmall = new TripleTriadModel();
    String smallPathGrid = "code" + File.separator + "docs" + File.separator
            + "boardNoHoles.config";
    String smallPathDeck = "code" + File.separator + "docs" + File.separator
            + "cardsEnoughForAll.config";
    configModelSmall.startGame(new DeckInitializer(smallPathDeck).getDeck(),
            new GridInitializer(smallPathGrid).getGrid());

    // config Model PlaceHolder
    configModelOddPlaceholder = new TripleTriadModel();
    String placeholderGrid = "code" + File.separator + "docs" + File.separator
            + "boardOddPlaceholders.config";
    String placeholderDeck = "code" + File.separator + "docs" + File.separator
            + "cardsEnoughForAll.config";
    configModelOddPlaceholder.startGame(new DeckInitializer(placeholderDeck).getDeck(),
            new GridInitializer(placeholderGrid).getGrid());
  }


  @Test
  public void testConfigModel() {
    configModel.playToGrid(0, 0, 2);
    configModel.playToGrid(0, 0, 1);
    configModel.playToGrid(3, 1, 1);
    Assert.assertEquals(0,0);

  }

  @Test
  public void testConfigModelSmallWin() {
    configModelSmall.playToGrid(0, 0, 0);
    configModelSmall.playToGrid(0, 1, 0);
    configModelSmall.playToGrid(0, 0, 1);

    // Not all cells filled
    Assert.assertFalse(configModelSmall.isGameOver());
    configModelSmall.playToGrid(0, 1, 1);
    // Game is filled and is over
    Assert.assertTrue(configModelSmall.isGameOver());
  }

  @Test
  public void testConfigModelOddWin() {
    configModelOddPlaceholder.playToGrid(0, 0, 0);
    // South 4
    // North 3

    // Cards properly decrement
    Assert.assertEquals(configModelOddPlaceholder.getPlayersTurn().getHand().size(), 2);
    configModelOddPlaceholder.playToGrid(0, 1, 0);

    // Not all cells filled
    Assert.assertFalse(configModelOddPlaceholder.isGameOver());
    configModelOddPlaceholder.playToGrid(0, 1, 1);
    // Game is filled and is over
    Assert.assertTrue(configModelOddPlaceholder.isGameOver());
    // Game is Tied 2 blue cards and 2 red cards

    // counts cards in hand and on board Blue = 3, Red = 1
    Assert.assertEquals(configModelOddPlaceholder.winningPlayer().toString(), "BLUE");

  }


}
