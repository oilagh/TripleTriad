package cs3500.tripletriad.gamecomponents;

/**
 * Represents an enumeration of the attack values for the card.
 * The valid attack values are 1, 2, 3, 4, 5, 6, 7, 8, 9, A.
 */
public enum AttackValue {
  ONE("1"), TWO("2"), THREE("3"), FOUR("4"), FIVE("5"), SIX("6"),
  SEVEN("7"), EIGHT("8"), NINE("9"), TEN("A");
  private final String value;

  AttackValue(String s) {
    this.value = s;
  }

  /**
   * Represents a string value of the attack value.
   * @return a string representation of attack value.
   */
  public String toString() {
    return this.value;
  }

  /**
   * determines if an attack value is greater than the given attack value.
   * @param that the attack value to be compared against.
   * @return true if the attack value is greater and false if not.
   */
  public boolean greaterAttackValue(AttackValue that) {
    int thisNum;
    int thatNum;
    if (!this.toString().equals("A")) {
      thisNum = Integer.parseInt(this.toString());
    }
    else {
      thisNum = 10;
    }

    if (!that.toString().equals("A")) {
      thatNum = Integer.parseInt(that.toString());
    }
    else {
      thatNum = 10;
    }
    return thisNum > thatNum;
  }
}
