package cs3500.tripletriad.controller;

import java.io.File;
import java.util.Scanner;

import cs3500.tripletriad.model.TripleTriadModel;

/**
 * This is the class for the controller for the game Triple Triad.
 * It allows the player to interact with the model.
 */
public class TTController implements TripleTriadController {
  private final Appendable output;
  private Scanner scanner;

  /**
   * Constructor for the TTController.
   * @param rd the readable that is taken in for input.
   * @param ap the appendable that is the output.
   */
  public TTController(Readable rd, Appendable ap) {
    if (rd == null || ap == null) {
      throw new IllegalArgumentException("One of your objects is null");
    }
    output = ap;
    Readable input = rd;
    this.scanner = new Scanner(rd);
  }

  @Override
  public void playGame(TripleTriadModel model, File fileGrid, File fileDeck) {
    //TODO: implement method.
  }



}
