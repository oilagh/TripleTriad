package cs3500.tripletriad.gamecomponents;

/**
 * Represents a blue player or a red player.
 */
public enum Player {
  BLUE("B"), RED("R");
  private final String name;
  Player(String name) {
    this.name = name;
  }

  /**
   * Returns the name of the player.
   * @return the name of the player.
   */
  public String toString() {
    return this.name;
  }
}
