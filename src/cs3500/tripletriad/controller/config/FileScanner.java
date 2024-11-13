package cs3500.tripletriad.controller.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class acts as a file scanner.
 * It allows scanning of a file to get the information inside the file.
 */
public class FileScanner {
  private Scanner scanner;

  /**
   * Constructor for FileScanner.
   * @param filePath the file that will be read from.
   */
  public FileScanner(String filePath) {
    File file = new File(filePath);
    try {
      scanner = new Scanner(file);
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File not found: " + filePath);
    }
  }

  /**
   * Gets the scanner of the file.
   * @return the scanner of the file.
   */
  public Scanner getScanner() {
    return scanner;
  }

}
