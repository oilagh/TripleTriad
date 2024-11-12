import java.io.File;
import java.io.StringReader;

import cs3500.tripletriad.controller.TTController;
import cs3500.tripletriad.controller.config.DeckInitializer;
import cs3500.tripletriad.controller.config.GridInitializer;
import cs3500.tripletriad.model.TTModel;
import cs3500.tripletriad.model.TripleTriadModel;
import cs3500.tripletriad.view.TTGraphicsView;

public class TTProgram {

  public static void main(String args[]) {
    String pathGrid = "docs" + File.separator + "boardDirectPath.config";
    String pathDeck = "docs" + File.separator + "cardsEnoughForAll.config";
    GridInitializer grid = new GridInitializer(pathGrid);
    DeckInitializer deck = new DeckInitializer(pathDeck);
    TTModel model = new TripleTriadModel();
    model.startGame(deck.getDeck(), grid.getGrid());
    TTGraphicsView view = new TTGraphicsView(model);
    view.addClickListener(new TTController(new StringReader(""), new StringBuilder()));
    view.setVisible(true);
  }
}
