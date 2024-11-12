package cs3500.tripletriad.view;

import java.awt.*;

import javax.swing.*;

import cs3500.tripletriad.controller.TTController;
import cs3500.tripletriad.model.TTModel;

public class TTGraphicsView extends JFrame implements TTView {
  private TTPanel panel;

  private TTModel model;

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
