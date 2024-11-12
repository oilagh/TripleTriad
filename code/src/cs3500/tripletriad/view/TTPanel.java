package cs3500.tripletriad.view;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.util.List;
import javax.swing.*;
import javax.swing.border.Border;

import cs3500.tripletriad.controller.TTController;
import cs3500.tripletriad.gamecomponents.BoardCell;
import cs3500.tripletriad.gamecomponents.Card;
import cs3500.tripletriad.gamecomponents.Direction;
import cs3500.tripletriad.gamecomponents.Hole;
import cs3500.tripletriad.gamecomponents.PlaceHolder;
import cs3500.tripletriad.model.TTModel;
import cs3500.tripletriad.player.Player;

/**
 * Represents a panel that represents a cell in a grid.
 */
public class TTPanel extends JPanel {

  private TTModel model;

  private Graphics2D g2d;

  TTGraphicsView view;

  int selectedCardIndex = -1;

  public TTPanel(TTModel model) {
    this.model = model;
  }


  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    this.g2d = (Graphics2D) g.create();

    String title = model.getPlayersTurn().toString();
    Border border = BorderFactory.createTitledBorder(title);
    this.setBorder(border);

    drawGrid(g2d);
  }

  public void setView(TTGraphicsView view) {
    this.view = view;
  }

  private void drawGrid(Graphics2D g2d) {
    List<Card> hand =   model.getSpecificPlayer(Color.RED).getHand();
    int CARD_WIDTH = this.getWidth() / (2 + model.getGrid().gridList().get(0).size());
    int CARD_HEIGHT = this.getHeight() / model.getGrid().gridList().size();
    int HAND_HEIGHT;
    HAND_HEIGHT = getHandHeight(hand);

    drawHand(g2d, hand, HAND_HEIGHT, CARD_WIDTH, 0, Color.red);

    List<List<BoardCell>> grid = model.getGrid().gridList();
    for(int i = 0; i < grid.size(); i++) {
      for(int j = 0; j < grid.get(i).size(); j++) {
        if(grid.get(i).get(j) instanceof PlaceHolder) {
          g2d.setColor(Color.yellow);
          g2d.fillRect((j + 1) * CARD_WIDTH, i * CARD_HEIGHT, CARD_WIDTH, CARD_HEIGHT);
          g2d.setColor(Color.black);
          g2d.drawRect((j + 1) * CARD_WIDTH, i * CARD_HEIGHT, CARD_WIDTH, CARD_HEIGHT);
        }
        else if(grid.get(i).get(j) instanceof Hole) {
          g2d.setColor(Color.gray);
          g2d.fillRect((j + 1) * CARD_WIDTH, i * CARD_HEIGHT, CARD_WIDTH, CARD_HEIGHT);
          g2d.setColor(Color.black);
          g2d.drawRect((j + 1) * CARD_WIDTH, i * CARD_HEIGHT, CARD_WIDTH, CARD_HEIGHT);
        }
        else if(grid.get(i).get(j) instanceof Card) {
          Card c = (Card) grid.get(i).get(j);
          g2d.setColor(c.getColor());
          String north = c.getAttackValues().get(Direction.NORTH).toString();
          String south = c.getAttackValues().get(Direction.SOUTH).toString();
          String east = c.getAttackValues().get(Direction.EAST).toString();
          String west = c.getAttackValues().get(Direction.WEST).toString();

          g2d.fillRect((j + 1) * CARD_WIDTH, i * CARD_HEIGHT, CARD_WIDTH, CARD_HEIGHT);
          g2d.setColor(Color.black);
          g2d.drawRect((j + 1) * CARD_WIDTH, i * CARD_HEIGHT, CARD_WIDTH, CARD_HEIGHT);
          g2d.setFont(new Font("Arial", Font.PLAIN, 24));
          g2d.drawString(north, CARD_WIDTH/ 2 + (j + 1) * CARD_WIDTH, CARD_HEIGHT /4 + i * CARD_HEIGHT);
          g2d.drawString(south, CARD_WIDTH/ 2 + (j + 1) * CARD_WIDTH, CARD_HEIGHT /4 * 3 + i * CARD_HEIGHT);
          g2d.drawString(east, CARD_WIDTH/ 4 + (j + 1) * CARD_WIDTH, CARD_HEIGHT /2 + i * CARD_HEIGHT);
          g2d.drawString(west, CARD_WIDTH/ 4 * 3 + (j + 1) * CARD_WIDTH, CARD_HEIGHT /2 + i * CARD_HEIGHT);
        }
      }
    }
    hand = model.getSpecificPlayer(Color.blue).getHand();
    HAND_HEIGHT = getHandHeight(hand);
    drawHand(g2d, hand, HAND_HEIGHT, CARD_WIDTH, grid.get(0).size() + 1, Color.blue);
  }

  private int getHandHeight(List<Card> hand) {
    int HAND_HEIGHT;
    if(!hand.isEmpty()) {
      HAND_HEIGHT = this.getHeight() / hand.size();
    }
    else {
      HAND_HEIGHT = -1;
    }
    return HAND_HEIGHT;
  }

  private void drawHand(Graphics2D g2d, List<Card> hand, int HAND_HEIGHT, int CARD_WIDTH, int pos, Color turn) {
    if(HAND_HEIGHT >= 0) {
      for (int i = 0; i < hand.size(); i++) {
        Card c = hand.get(i);
        g2d.setColor(c.getColor());
        String north = c.getAttackValues().get(Direction.NORTH).toString();
        String south = c.getAttackValues().get(Direction.SOUTH).toString();
        String east = c.getAttackValues().get(Direction.EAST).toString();
        String west = c.getAttackValues().get(Direction.WEST).toString();

        g2d.fillRect(pos * CARD_WIDTH, i * HAND_HEIGHT, CARD_WIDTH, HAND_HEIGHT);
        g2d.setColor(Color.black);
        g2d.drawRect(pos * CARD_WIDTH, i * HAND_HEIGHT, CARD_WIDTH, HAND_HEIGHT);
        if(this.model.getPlayersTurn().getColor().equals(turn) && i == this.selectedCardIndex) {
          Stroke defaultStroke = g2d.getStroke();
          g2d.setStroke(new BasicStroke(10));
          g2d.drawRoundRect(pos * CARD_WIDTH, i * HAND_HEIGHT,
                  CARD_WIDTH, HAND_HEIGHT, 10, 20);
          g2d.setStroke(defaultStroke);
        }
        g2d.setFont(new Font("Arial", Font.PLAIN, 24));
        g2d.drawString(north, CARD_WIDTH / 2 + pos * CARD_WIDTH, HAND_HEIGHT / 4 + i * HAND_HEIGHT);
        g2d.drawString(south, CARD_WIDTH / 2 + pos * CARD_WIDTH, HAND_HEIGHT / 4 * 3 + i * HAND_HEIGHT);
        g2d.drawString(east, CARD_WIDTH / 4 + pos * CARD_WIDTH, HAND_HEIGHT / 2 + i * HAND_HEIGHT);
        g2d.drawString(west, CARD_WIDTH / 4 * 3 + pos * CARD_WIDTH, HAND_HEIGHT / 2 + i * HAND_HEIGHT);
      }
    }
  }
  public void addClickListener(TTController listener) {
    this.addMouseListener(new TTTClickListener(listener));
  }


  class TTTClickListener implements MouseListener {

    private final TTController controller;


    public TTTClickListener(TTController controller) {
      this.controller = controller;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
      System.err.println(e.getX() + "," + e.getY());
      int widthCards = (2 + model.getGrid().gridList().get(0).size());
      int x = e.getX() / (TTPanel.this.getWidth() / widthCards);
      int y = e.getY() / (TTPanel.this.getHeight() / model.getGrid().gridList().size());
      if(x == 0 && model.getPlayersTurn().getColor().equals(Color.red)) {
        Player player = model.getSpecificPlayer(Color.RED);
        this.selectCard(x, e.getY() / (TTPanel.this.getHeight() / player.getHand().size()));
      }
      else if(x == widthCards - 1 && model.getPlayersTurn().getColor().equals(Color.blue)) {
        Player player = model.getSpecificPlayer(Color.BLUE);
        this.selectCard(x, e.getY() / (TTPanel.this.getHeight() / player.getHand().size()));
      }
      else if(selectedCardIndex >= 0){
        try {
          model.playToGrid(selectedCardIndex, y, x - 1);
          System.out.print("Player: "  + model.getPlayersTurn().getColor());
          System.out.print(y + " "  + (x - 1));
        } catch(IllegalArgumentException ex) {
          // do nothing
        }
        selectedCardIndex = -1;
      }
      view.repaint();
    }

    private void selectCard(int x, int y) {
      selectedCardIndex = y;
      int CARD_WIDTH = TTPanel.this.getWidth() / (2 + model.getGrid().gridList().get(0).size());
      int HAND_HEIGHT = TTPanel.this.getHeight() / model.getPlayersTurn().getHand().size();
      g2d.drawRect(x * CARD_WIDTH, y * HAND_HEIGHT, CARD_WIDTH - 10, HAND_HEIGHT - 10);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
  }
}
