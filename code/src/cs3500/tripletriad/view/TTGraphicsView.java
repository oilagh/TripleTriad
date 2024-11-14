package cs3500.tripletriad.view;



import javax.swing.JFrame;

import cs3500.tripletriad.model.ReadOnlyTripleTriadModel;

/**
 * Represents the graphics view class that extends JFrame and implements TTView.
 * Allows the viewer to view the model.
 */
public class TTGraphicsView extends JFrame implements TTFrame {

  /**
   * Constructor for TTGraphicsView.
   * @param model the model for which the view will be created.
   */
  public TTGraphicsView(ReadOnlyTripleTriadModel model) {
    TTPanelView p = new TTPanelView(model);
    p.setView(this);
    this.setSize(1000, 800);
    this.add(p);
    this.setTitle("Player: " + model.getPlayersTurn().toString());
  }

}
