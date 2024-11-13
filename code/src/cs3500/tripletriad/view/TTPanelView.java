package cs3500.tripletriad.view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JPanel;

import cs3500.tripletriad.gamecomponents.BoardCell;
import cs3500.tripletriad.gamecomponents.Card;
import cs3500.tripletriad.gamecomponents.Direction;
import cs3500.tripletriad.gamecomponents.Hole;
import cs3500.tripletriad.gamecomponents.PlaceHolder;
import cs3500.tripletriad.model.ReadOnlyTripleTriadModel;
import cs3500.tripletriad.model.TTModel;
import cs3500.tripletriad.player.TTPlayer;

/**
 * Represents a panel that represents a cell in a grid.
 */
public class TTPanelView extends JPanel implements TTPanel {

  private ReadOnlyTripleTriadModel model;

  private Graphics2D g2d;

  TTGraphicsView view;

  int selectedCardIndex = -1;

  public TTPanelView(ReadOnlyTripleTriadModel model) {
    this.model = model;
    this.addMouseListener(new TTTClickListener());
  }


  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    this.g2d = (Graphics2D) g.create();

    view.setTitle("Player: " + model.getPlayersTurn().toString());
    drawGrid(g2d);
  }

  public void setView(TTGraphicsView view) {
    this.view = view;
  }

  private void drawGrid(Graphics2D g2d) {
    List<Card> hand =   model.getSpecificPlayer(Color.RED).getHand();
    int cardWidth = this.getWidth() / (2 + model.getGrid().gridList().get(0).size());
    int cardHeight = this.getHeight() / model.getGrid().gridList().size();
    int handHeight;
    handHeight = getHandHeight(hand);

    drawHand(g2d, hand, handHeight, cardWidth, 0, Color.red);

    List<List<BoardCell>> grid = model.getGrid().gridList();
    for (int i = 0; i < grid.size(); i++) {
      for (int j = 0; j < grid.get(i).size(); j++) {
        if (grid.get(i).get(j) instanceof PlaceHolder) {
          g2d.setColor(Color.yellow);
          g2d.fillRect((j + 1) * cardWidth, i * cardHeight, cardWidth, cardHeight);
          g2d.setColor(Color.black);
          g2d.drawRect((j + 1) * cardWidth, i * cardHeight, cardWidth, cardHeight);
        }
        else if (grid.get(i).get(j) instanceof Hole) {
          g2d.setColor(Color.gray);
          g2d.fillRect((j + 1) * cardWidth, i * cardHeight, cardWidth, cardHeight);
          g2d.setColor(Color.black);
          g2d.drawRect((j + 1) * cardWidth, i * cardHeight, cardWidth, cardHeight);
        }
        else if (grid.get(i).get(j) instanceof Card) {
          Card c = (Card) grid.get(i).get(j);
          g2d.setColor(c.getColor());
          String north = c.getAttackValues().get(Direction.NORTH).toString();
          String south = c.getAttackValues().get(Direction.SOUTH).toString();
          String east = c.getAttackValues().get(Direction.EAST).toString();
          String west = c.getAttackValues().get(Direction.WEST).toString();

          g2d.fillRect((j + 1) * cardWidth, i * cardHeight, cardWidth, cardHeight);
          g2d.setColor(Color.black);
          g2d.drawRect((j + 1) * cardWidth, i * cardHeight, cardWidth, cardHeight);
          int font = (this.getHeight() + this.getWidth()) /
                  (2 * this.model.getGrid().gridList().size() *
                          this.model.getGrid().gridList().get(0).size());
          g2d.setFont(new Font("Arial", Font.PLAIN, font));
          g2d.drawString(north, cardWidth / 2 + (j + 1) * cardWidth,
                  cardHeight / 4 + i * cardHeight);
          g2d.drawString(south, cardWidth / 2 + (j + 1) * cardWidth,
                  cardHeight / 4 * 3 + i * cardHeight);
          g2d.drawString(east, cardWidth / 4 + (j + 1) * cardWidth,
                  cardHeight / 2 + i * cardHeight);
          g2d.drawString(west, cardWidth / 4 * 3 + (j + 1) * cardWidth,
                  cardHeight / 2 + i * cardHeight);
        }
      }
    }
    hand = model.getSpecificPlayer(Color.blue).getHand();
    handHeight = getHandHeight(hand);
    drawHand(g2d, hand, handHeight, cardWidth, grid.get(0).size() + 1, Color.blue);
  }

  private int getHandHeight(List<Card> hand) {
    int handHeight;
    if (!hand.isEmpty()) {
      handHeight = this.getHeight() / hand.size();
    }
    else {
      handHeight = -1;
    }
    return handHeight;
  }

  private void drawHand(Graphics2D g2d, List<Card> hand,
                        int handHeight, int cardWidth,
                        int pos, Color turn) {
    if (handHeight >= 0) {
      for (int i = 0; i < hand.size(); i++) {
        Card c = hand.get(i);
        g2d.setColor(c.getColor());
        String north = c.getAttackValues().get(Direction.NORTH).toString();
        String south = c.getAttackValues().get(Direction.SOUTH).toString();
        String east = c.getAttackValues().get(Direction.EAST).toString();
        String west = c.getAttackValues().get(Direction.WEST).toString();

        g2d.fillRect(pos * cardWidth, i * handHeight, cardWidth, handHeight);
        g2d.setColor(Color.black);
        g2d.drawRect(pos * cardWidth, i * handHeight, cardWidth, handHeight);
        if (this.model.getPlayersTurn().getColor().equals(turn) && i == this.selectedCardIndex) {
          Stroke defaultStroke = g2d.getStroke();
          g2d.setStroke(new BasicStroke(10));
          g2d.drawRoundRect(pos * cardWidth, i * handHeight,
                  cardWidth, handHeight, 10, 20);
          g2d.setStroke(defaultStroke);
        }
        int font = 24;
        if(font - model.getPlayersTurn().getHand().size() > 10) {
          font -= model.getPlayersTurn().getHand().size();
        }
        else {
          font = 10;
        }
        g2d.setFont(new Font("Arial", Font.PLAIN, font));
        g2d.drawString(north, cardWidth / 2 + pos * cardWidth, handHeight / 4 + i * handHeight);
        g2d.drawString(south, cardWidth / 2 + pos * cardWidth, handHeight / 4 * 3 + i * handHeight);
        g2d.drawString(east, cardWidth / 4 + pos * cardWidth, handHeight / 2 + i * handHeight);
        g2d.drawString(west, cardWidth / 4 * 3 + pos * cardWidth, handHeight / 2 + i * handHeight);
      }
    }
  }

  public void addClickListener() {
    this.addMouseListener(new TTTClickListener());
  }


  class TTTClickListener implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {
      int widthCards = (2 + model.getGrid().gridList().get(0).size());
      int x = e.getX() / (TTPanelView.this.getWidth() / widthCards);
      int y = e.getY() / (TTPanelView.this.getHeight() / model.getGrid().gridList().size());
      if (x == 0 && model.getPlayersTurn().getColor().equals(Color.red)) {
        TTPlayer player = model.getSpecificPlayer(Color.RED);
        this.selectCard(x, e.getY() / (TTPanelView.this.getHeight() / player.getHand().size()));
      }
      else if (x == widthCards - 1 && model.getPlayersTurn().getColor().equals(Color.blue)) {
        TTPlayer player = model.getSpecificPlayer(Color.BLUE);
        this.selectCard(x, e.getY() / (TTPanelView.this.getHeight() / player.getHand().size()));
      }
      else if (selectedCardIndex >= 0) {
        try {
          TTModel mutableModel = model.getMutableModel();
          mutableModel.playToGrid(selectedCardIndex, y, x - 1);
          model = mutableModel.getMutableModel();
          System.out.println("Row: " + y + ". Col: " + x);
        } catch (IllegalArgumentException ex) {
          // do nothing
        }
        selectedCardIndex = -1;
      }
      view.repaint();
    }

    private void selectCard(int x, int y) {
      if(selectedCardIndex == y) {
        selectedCardIndex = -1;
      }
      else {
        selectedCardIndex = y;
        System.out.println("Player: " + model.getPlayersTurn().toString());
        System.out.println("Card in hand: " + y);
        int cardWidth = TTPanelView.this.getWidth() / (2 + model.getGrid().gridList().get(0).size());
        int handHeight = TTPanelView.this.getHeight() / model.getPlayersTurn().getHand().size();
        g2d.drawRect(x * cardWidth, y * handHeight, cardWidth - 10, handHeight - 10);
      }
    }

    @Override
    public void mousePressed(MouseEvent e) {
      //for when the mouse is pressed.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
      //for when the mouse is released.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
      //for the mouse event.
    }

    @Override
    public void mouseExited(MouseEvent e) {
      //for when the mouse is exited
    }
  }
}
