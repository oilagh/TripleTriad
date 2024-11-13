package cs3500.tripletriad.view;



import javax.swing.JFrame;

import cs3500.tripletriad.controller.TTController;
import cs3500.tripletriad.model.TTModel;

/**
 * Represents the graphics view class that extends JFrame and implements TTView.
 * Allows the viewer to view the model.
 */
public class TTGraphicsView extends JFrame implements TTView {
  private TTPanel panel;
  private TTModel model;

  /**
   * Constructor for TTGraphicsView.
   * @param model the model for which the view will be created.
   */
  public TTGraphicsView(TTModel model) {
    this.model = model;
    TTPanel p = new TTPanel(model);
    p.setView(this);
    this.panel = p;
    this.setSize(1000, 800);
    this.add(this.panel);
  }

  public void addClickListener(TTController listener) {
    this.panel.addClickListener(listener);
  }

  public void refresh() {
    this.repaint();
  }

  public void makeVisible() {
    this.setVisible(true);
  }

}
