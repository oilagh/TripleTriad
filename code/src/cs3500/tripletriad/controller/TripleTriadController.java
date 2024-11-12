package cs3500.tripletriad.controller;

import java.io.File;

import cs3500.tripletriad.model.TripleTriadModel;

/**
 * this is the interface for the controller of the game Triple Triad.
 * It allows the player to interact with the model and play the game.
 */
public interface TripleTriadController {

  /**
   * Allows the player to play the game using the controller.
   * @throws IllegalArgumentException if any of the arguments are null.
   * @param model the model that will be used during the game.
   * @param fileGrid the file that is read from to get the grid.
   * @param fileDeck the file that is read from to get the deck.
   */
  void playGame(TripleTriadModel model, File fileGrid, File fileDeck);
}
