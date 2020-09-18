public class Player {

  private String name;

  private Score score = Score.LOVE;

  public Player(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Score getScore() {
    return score;
  }

  public void setScore(Score score) {
    this.score = score;
  }
  
}
