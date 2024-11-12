package cs3500.tripletriad.viewTests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
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
import cs3500.tripletriad.player.Player;
import cs3500.tripletriad.view.TTStringView;
import cs3500.tripletriad.view.TTView;

public class ViewTests {

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
  private TTView view;

  // test model with configuration file info
  private TTModel configModel;
  private TTStringView viewConfigModel;

  // test model with small file info
  private TTModel configModelSmall;
  private TTStringView viewConfigModelSmall;

  // test model with small file info
  private TTModel configModelOddPlaceholder;
  private TTStringView viewConfigModelOddPlaceholder;

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
    blueCard2 = new Card("Blue Card2", values2, Color.BLUE);
    redCard2 = new Card("Red Card2", values, Color.RED);
    blueCard3 = new Card("Blue Card", values2, Color.BLUE);
    redCard3 = new Card("Red Card", values, Color.RED);
    blueCard4 = new Card("Blue Card4", values2, Color.BLUE);
    redCard4 = new Card("Red Card4", values, Color.RED);
    blueCard5 = new Card("Blue Card5", values2, Color.BLUE);
    redCard5 = new Card("Red Card5", values, Color.RED);
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
    view = new TTStringView(model);
    model.startGame(deck, grid);

    // config Model
    configModel = new TripleTriadModel();
    String pathGrid = "docs" + File.separator + "boardDirectPath.config";
    String pathDeck = "docs" + File.separator + "cardsEnoughForAll.config";
    configModel.startGame(new DeckInitializer(pathDeck).getDeck(),
            new GridInitializer(pathGrid).getGrid());
    viewConfigModel = new TTStringView(configModel);

    // config Model Small
    configModelSmall = new TripleTriadModel();
    String smallPathGrid = "docs" + File.separator + "boardNoHoles.config";
    String smallPathDeck = "docs" + File.separator + "cardsEnoughForAll.config";
    configModelSmall.startGame(new DeckInitializer(smallPathDeck).getDeck(),
            new GridInitializer(smallPathGrid).getGrid());
    viewConfigModelSmall = new TTStringView(configModelSmall);

    // config Model PlaceHolder
    configModelOddPlaceholder = new TripleTriadModel();
    String placeholderGrid= "docs" + File.separator + "boardOddPlaceholders.config";
    String placeholderDeck = "docs" + File.separator + "cardsEnoughForAll.config";
    configModelOddPlaceholder.startGame(new DeckInitializer(placeholderDeck).getDeck(),
            new GridInitializer(placeholderGrid).getGrid());
    viewConfigModelOddPlaceholder = new TTStringView(configModelOddPlaceholder);
  }


  @Test
  public void testConfigModel() {
    configModel.playToGrid(0, 0, 2);
    configModel.playToGrid(0, 0, 1);
    configModel.playToGrid(3, 1, 1);

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
    // Game is Tied 2 blue cards and 2 red cards
    Assert.assertThrows(IllegalStateException.class, () -> configModelSmall.winningPlayer());
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
