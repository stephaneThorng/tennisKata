public enum Score {
  LOVE("Love"), FIFTEEN("Fifteen"), THIRTY("Thirty"), FORTY("Forty"), ADVANTAGE("40A"), SET("X");

  private static Score[] scores = values();

  private String point;

  Score(String point) {
    this.point = point;
  }

  public String getPoint() {
    return this.point;
  }

  public Score previous() {
    return scores[(this.ordinal() - 1) % scores.length];
  }

  public Score next() {
    return scores[(this.ordinal() + 1) % scores.length];
  }
}
