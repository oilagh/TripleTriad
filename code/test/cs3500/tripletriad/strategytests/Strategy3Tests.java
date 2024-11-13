package cs3500.tripletriad.strategytests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.List;
import java.io.File;
import java.util.ArrayList;

import cs3500.tripletriad.controller.config.DeckInitializer;
import cs3500.tripletriad.controller.config.GridInitializer;
import cs3500.tripletriad.gamecomponents.Direction;
import cs3500.tripletriad.model.TTModel;
import cs3500.tripletriad.model.TripleTriadModel;
import cs3500.tripletriad.view.TTGraphicsView;

public class Strategy3Tests {
  private TTModel model;
  private TTModel model2;

  @Before
  public void setUp() {
    String pathGrid = "code" + File.separator + "docs" + File.separator + "board.config";
    String pathGrid2 = "code" + File.separator + "docs" + File.separator + "boardOddPlaceholders.config";
    String pathDeck = "code" + File.separator + "docs" + File.separator + "cardsEnoughForAll.config";
    GridInitializer grid = new GridInitializer(pathGrid);
    GridInitializer grid2 = new GridInitializer(pathGrid2);
    DeckInitializer deck = new DeckInitializer(pathDeck);
    model = new TripleTriadModel();
    model2 = new TripleTriadModel();
    model.startGame(deck.getDeck(), grid.getGrid());
    model2.startGame(deck.getDeck(), grid2.getGrid());
  }

  @Test
  public void testGridLeastPlaceholders() {
    // board has 3 positions that are surrounded by holes
    Assert.assertEquals(model.getGrid().leastFlippablePositions().size(), 3);
    // empty list because surrounded by holes
    Assert.assertEquals(model.getGrid().leastFlippablePositions().get(new Point(1, 2)),
            new ArrayList<>());

  }

  @Test
  public void testGridLeastPlaceholders2() {
    // board has 2 positions that are surrounded by 1 placeholders
    Assert.assertEquals(model2.getGrid().leastFlippablePositions().size(), 2);
    // also prints out the associated location that can compare to a hole.
    Assert.assertEquals(model2.getGrid().leastFlippablePositions()
                    .get(new Point(1, 1)), new ArrayList<>(List.of(Direction.WEST)));
  }
}
