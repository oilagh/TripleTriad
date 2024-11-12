package cs3500.tripletriad.gamecomponents;

public enum AttackValue {
  ONE("1"), TWO("2"), THREE("3"), FOUR("4"), FIVE("5"), SIX("6"),
  SEVEN("7"), EIGHT("8"), NINE("9"), TEN("A");
  private final String value;

  AttackValue(String s) {
    this.value = s;
  }

  public String toString() {
    return this.value;
  }

  public boolean greaterAttackValue(AttackValue that) {
    int thisNum, thatNum;
    if(!this.toString().equals("A")) {
      thisNum = Integer.parseInt(this.toString());
    }
    else {
      thisNum = 10;
    }

    if(!that.toString().equals("A")) {
      thatNum = Integer.parseInt(that.toString());
    }
    else {
      thatNum = 10;
    }
    return thisNum > thatNum;
  }
}
