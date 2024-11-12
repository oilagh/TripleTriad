package cs3500.tripletriad.configurationTests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import cs3500.tripletriad.controller.config.DeckInitializer;
import cs3500.tripletriad.controller.config.GridInitializer;

/**
 * Test setup for configuration Files
 */
public class ConfigurationTests {
  private GridInitializer grid;
  private DeckInitializer deck;

  @Before
  public void init() {
    String pathGrid = "docs" + File.separator + "board.config";
    String pathDeck = "docs" + File.separator + "cards.config";
    grid = new GridInitializer(pathGrid);
    deck = new DeckInitializer(pathDeck);
  }

  @Test
  public void testGridInitialization() {
    Assert.assertEquals(grid.getGrid().toString(),
            "__    _\n" +
                    "_ _   _\n" +
                    "_  _  _\n" +
                    "_   _ _\n" +
                    "_    __\n");
  }

}

