package cs3500.tripletriad.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import cs3500.tripletriad.gamecomponents.BoardCell;
import cs3500.tripletriad.gamecomponents.Card;
import cs3500.tripletriad.gamecomponents.Deck;
import cs3500.tripletriad.gamecomponents.DeckTT;
import cs3500.tripletriad.gamecomponents.GridTT;
import cs3500.tripletriad.gamecomponents.Grid;
import cs3500.tripletriad.player.Player;
import cs3500.tripletriad.player.TTPlayer;

/**
 * Represents the game model for the game Triple Triad. Assume that all indexes are 0-based
 * for rows, columns, or for card in hand.
 */
public class TripleTriadModel implements TTModel {
  private final TTPlayer playerRed;
  private final TTPlayer playerBlue;
  private TTPlayer playerTurn;
  private GridTT grid;
  private DeckTT deck;
  private boolean gameStarted;

  /**
   * Constructor for TripleTriadModel.
   * @param grid the grid for the model.
   * @param deck the deck of cards that will be used in the game.
   */
  public TripleTriadModel(GridTT grid, DeckTT deck) {
    if (grid == null || deck == null) {
      throw new IllegalArgumentException("No Arguments can be null.");
    }
    this.playerRed = new Player(new ArrayList<>(), Color.RED);
    this.playerBlue = new Player(new ArrayList<>(), Color.BLUE);
    this.playerTurn = playerRed;
    this.grid = grid;
    this.deck = deck;
    gameStarted = false;
  }

  /**
   * Copy constructor for the TripleTriadModel.
   * @param model the model that will be created a copy of.
   */
  public TripleTriadModel(TTModel model) {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }
    TripleTriadModel other = (TripleTriadModel) model;
    this.playerRed = other.playerRed;
    this.playerBlue = other.playerBlue;
    this.playerTurn = other.playerTurn;
    this.grid = other.grid.copy();
    this.deck = other.deck;
    this.gameStarted = other.gameStarted;
  }

  /**
   * Constructor for TripleTriadModel. Used primarily for testing.
   * @param redPlayer the red player that will play first.
   * @param bluePlayer the blue player who will play second.
   * @param grid the grid to use in the game.
   * @param deck represents a list of cards that can be passed.
   */
  public TripleTriadModel(TTPlayer redPlayer, TTPlayer bluePlayer, GridTT grid, DeckTT deck) {
    if (grid == null || deck == null) {
      throw new IllegalArgumentException("No Arguments can be null.");
    }
    this.playerRed = redPlayer;
    this.playerBlue = bluePlayer;
    this.playerTurn = playerRed;
    this.grid = grid;
    this.deck = deck;
    gameStarted = false;
  }

  /**
   * Constructor for TripleTriadModel represents an empty constructor.
   */
  public TripleTriadModel() {
    this.grid = new Grid();
    this.deck = new Deck();
    this.playerRed = new Player(new ArrayList<>(), Color.RED);
    this.playerBlue = new Player(new ArrayList<>(), Color.BLUE);
    this.playerTurn = playerRed;
    gameStarted = false;
  }

  @Override
  public void startGame(DeckTT deck, GridTT grid) {
    //checks to see if the game is already started of if it has ended.
    if (gameStarted) {
      throw new IllegalStateException("Game has already started.");
    }
    //checks to see if the deck or grid is null.
    if (deck == null || grid == null) {
      throw new IllegalArgumentException("Deck or Grid cannot be null.");
    }
    //check to see if the number of cards is correct
    if (deck.deckSize() < grid.numCardCells() + 1) {
      throw new IllegalArgumentException("Invalid number of cards.");
    }
    //assign the grid to the grid and deck to the deck.
    this.deck = deck;
    this.grid = grid;
    //sets gameStarted to true.
    gameStarted = true;
    //assign n+1/2 cards to each player
    List<Card> copyDeck = this.deck.getCards();
    for (int cards = 0; cards < ((grid.numCardCells() + 1) / 2); cards++) {
      Card card = copyDeck.get(0);
      card.changeColor(Color.RED);
      //the cards inside the player only represent the cards
      //that are in the player's hand and available to play to the grid.
      this.playerRed.addCard(card);
      copyDeck.remove(0);
    }

    for (int cards = 0; cards < ((grid.numCardCells() + 1) / 2); cards++) {
      Card card = copyDeck.get(0);
      card.changeColor(Color.BLUE);
      //the cards inside the player only represent the cards
      //that are in the player's hand and available to play to the grid.
      this.playerBlue.addCard(card);
      copyDeck.remove(0);
    }
    //assigns the turn to the redPlayer.
    this.playerTurn = playerRed;
  }


  @Override
  public void playToGrid(int idxHand, int row, int col) {
    //determines if the game is already over.
    if (isGameOver()) {
      //throws an exception if the game is already over.
      throw new IllegalStateException("Game is already over.");
    }
    if (grid.checkOutOfBounds(row, col)) {
      throw new IllegalArgumentException("row or col out of bounds: " + row + "|" + col);
    }
    //checks if the cell is not a hole and does not already contain a card.
    if (!grid.isCoordinateUnoccupied(row, col)) {
      throw new IllegalArgumentException("Coordinate is occupied or is a hole"  + row + "|" + col);
    }
    grid.setGrid(row, col, playerTurn.getHand().get(idxHand));
    playerTurn.getHand().remove(idxHand);
    if (playerTurn == playerRed) {
      playerTurn = playerBlue;
    }
    else {
      playerTurn = playerRed;
    }

    grid.compareAdjacentCells(row, col);
  }


  @Override
  public TTPlayer winningPlayer() {
    if (!isGameOver()) {
      throw new IllegalStateException("Game is not over, winner cannot be determined.");
    }
    //returns the red player if they have more cards than the blue player.
    if (playerRed.getHand().size() + this.howManyCards(playerRed)
            > playerBlue.getHand().size() + this.howManyCards(playerBlue)) {
      return this.playerRed;
    }
    //returns the blue player if they have more cards than the red player.
    else if (playerRed.getHand().size() + this.howManyCards(playerRed)
            < playerBlue.getHand().size() + this.howManyCards(playerBlue)) {
      return this.playerBlue;
    }
    //throws an exception if the game is a tie and there is no winner.
    throw new IllegalStateException("The game is a tie.");
  }

  @Override
  public int howManyPoints(TTPlayer player) {
    return player.getHand().size() + howManyCards(player);
  }

  private int howManyCards(TTPlayer player) {
    List<List<BoardCell>> grid = this.grid.gridList();
    int count = 0;
    for (int index = 0; index < grid.size(); index++) {
      List<BoardCell> list = grid.get(index);
      for (int boardCell = 0; boardCell < list.size(); boardCell++) {
        BoardCell cell = list.get(boardCell);
        if (cell instanceof Card) {
          Card card = (Card) cell;
          if (card.getColor().equals(player.getColor())) {
            count++;
          }
        }
      }
    }
    return count;
  }

  @Override
  public boolean isGameOver() {
    return !this.grid.emptyCardCells();
  }

  @Override
  public TTPlayer getPlayersTurn() {
    return this.playerTurn;
  }

  @Override
  public TTPlayer getSpecificPlayer(Color color) {
    if (color.equals(Color.RED)) {
      return this.playerRed;
    }

    if (color.equals(Color.BLUE)) {
      return this.playerBlue;
    }
    throw new IllegalArgumentException("Invalid color for player: " + color);
  }

  @Override
  public GridTT getGrid() {
    return this.grid;
  }

  @Override
  public List<Integer> getGridDimensions() {
    List<Integer> dimensions = new ArrayList<>();
    dimensions.add(grid.gridList().size() - 1);
    dimensions.add(grid.gridList().get(0).size() - 1);
    return dimensions;
  }


  @Override
  public BoardCell contentsAt(int row, int col) {
    List<BoardCell> list = this.grid.gridList().get(row);
    BoardCell cell = list.get(col);
    return cell;
  }

  @Override
  public TTPlayer whoOwns(int row, int col) {
    List<BoardCell> list = this.grid.gridList().get(row);
    BoardCell cell = list.get(col);
    if (cell instanceof Card) {
      Card card = (Card) cell;
      if (card.getColor().equals(Color.BLUE)) {
        return this.playerBlue;
      }
      else if (card.getColor().equals(Color.RED)) {
        return this.playerRed;
      }
    }
    throw new IllegalArgumentException("No such player.");
  }

}
