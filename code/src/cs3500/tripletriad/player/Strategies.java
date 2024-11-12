package cs3500.tripletriad.player;

import java.util.List;
/**
 * This represents the strategies for the computer players.
 */
public interface Strategies {
  /**
   * Breaks the ties when two cells are the same for a strategy.
   * @param posn1 the first list of integers.
   * @param posn2 the second list of integers.
   * @return the cell that is closer to index 0.
   */
  List<Integer> tieBreaker(List<Integer> posn1, List<Integer> posn2);

  /**
   * Handles the first strategy that places the card in the position.
   * that flips the most cards.
   * @return a list of integers that represents the position of the cell.
   */
  List<Integer> strategyOne();

  /**
   * Handles the second strategy that places Go for the corners.
   * Then consider which card is hardest to flip in that corner.
   * @return a list of integers that represents the position of that cell.
   */
  List<Integer> strategyTwo();
}
