package cs3500.tripletriad.configurationtests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import cs3500.tripletriad.controller.config.GridInitializer;

/**
 * Test setup for configuration Files.
 */
public class ConfigurationTests {
  private GridInitializer grid;

  @Before
  public void init() {
    String pathGrid = "code" + File.separator + "docs" + File.separator + "board.config";
    String pathDeck = "code" + File.separator + "docs" + File.separator + "cards.config";
    grid = new GridInitializer(pathGrid);
  }

  @Test
  public void testGridInitialization() {
    Assert.assertEquals(grid.getGrid().toString(),
            "__    _\n"
                    + "_ _   _\n"
                    + "_  _  _\n"
                    + "_   _ _\n"
                    + "_    __\n");
  }

}

