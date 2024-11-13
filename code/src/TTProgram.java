import java.io.File;
import java.io.StringReader;

import cs3500.tripletriad.controller.TTController;
import cs3500.tripletriad.controller.config.DeckInitializer;
import cs3500.tripletriad.controller.config.GridInitializer;
import cs3500.tripletriad.model.TTModel;
import cs3500.tripletriad.model.TripleTriadModel;
import cs3500.tripletriad.view.TTGraphicsView;

/**
 * Main method for the program.
 * Allows the user to interact with the game.
 */
public class TTProgram {

  /**
   * Static class for the main program.
   * @param args the argument that will be input for the program to run.
   */
  public static void main(String[] args) {
    String pathGrid = "code" + File.separator + "docs" + File.separator + "boardNoDirectPath.config";
    String pathDeck = "code" + File.separator + "docs" + File.separator + "cardsEnoughForAll.config";
    GridInitializer grid = new GridInitializer(pathGrid);
    DeckInitializer deck = new DeckInitializer(pathDeck);
    TTModel model = new TripleTriadModel();
    model.startGame(deck.getDeck(), grid.getGrid());
    TTGraphicsView view = new TTGraphicsView(model);
    view.addClickListener(new TTController(new StringReader(""), new StringBuilder()));
    view.setVisible(true);
  }
}
