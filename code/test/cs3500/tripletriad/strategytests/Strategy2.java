package cs3500.tripletriad.strategytests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import cs3500.tripletriad.controller.config.DeckInitializer;
import cs3500.tripletriad.controller.config.GridInitializer;
import cs3500.tripletriad.model.TTModel;
import cs3500.tripletriad.model.TripleTriadModel;
import cs3500.tripletriad.player.StrategyOne;

/**
 * Represents tests for Strategy 2 that makes decisions for the player.
 */
public class Strategy2 {
  private TTModel model;
  private TTModel model2;
  private GridInitializer grid;
  private DeckInitializer deck;

  @Before
  public void setUp() {
    String pathGrid = "code" + File.separator + "docs" + File.separator + "strategyBoard.config";
    String pathDeck = "code" + File.separator +
            "docs" + File.separator + "cardsEnoughForAll.config";
    grid = new GridInitializer(pathGrid);
    deck = new DeckInitializer(pathDeck);
    model = new TripleTriadModel();
    model.startGame(deck.getDeck(), grid.getGrid());
  }

  @Test
  public void testSimpleStrategyLog() {
    Appendable out = new StringBuilder();
    TTMockModel mock = new TTMockModel(out);
    mock.startGame(deck.getDeck(), grid.getGrid());
    StrategyOne one = new StrategyOne(model);
    int hand = one.card;
    int row = one.row;
    int col = one.col;
    // should play to top left corner because corner closest to 0,0
    mock.playToGrid(hand, row, col);
    Assert.assertTrue(out.toString().contains("played card 0 to: 0 | 0"));

    model.playToGrid(0, 0, 0);
    // should play to top right corner
    hand = one.card;
    row = one.row;
    col = one.col;
    mock.playToGrid(hand, row, col);

    System.out.print(out.toString());
  }


}
