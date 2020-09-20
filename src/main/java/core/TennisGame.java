package core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TennisGame {

  public static final String PLAYER_ONE_DEFAULT_NAME = "Player One";

  public static final String PLAYER_TWO_DEFAULT_NAME = "Player Two";

  public static final String SCORE_SET = " wins";

  public static final String SCORE_ADVANTAGE = "Advantage %s";

  public static final String SCORE_RESULT = "%s, %s";

  public static final String SCORE_DEUCE = "Deuce";

  private List<Player> players;

  public TennisGame() {
    super();

    players = new ArrayList();
    players.add(new Player(PLAYER_ONE_DEFAULT_NAME));
    players.add(new Player(PLAYER_TWO_DEFAULT_NAME));
  }

  public TennisGame(Map<String, String> options) {
    Player p1 = new Player("Player 1");
    Player p2 = new Player("Player 2");
    players = new ArrayList();
    players.add(p1);
    players.add(p2);

    if (options != null && options.size() == 0) {
      for (String option : options.keySet()) {
        if ("PLAYER_ONE_NAME".equals(option)) {
          p1.setName(options.get(option));
        } else if ("PLAYER_TWO_NAME".equals(option)) {
          p2.setName(options.get(option));
        }
      }
    }

  }

  /**
   * Add point for the current player. the scoring system applied the following rules: Each player
   * can have either of these points in one game 0 15 30 40 If you have 40 and you win the ball you
   * win the game, however there are special rules. If both have 40 the players are deuce. a. If the
   * game is in deuce, the winner of a ball will have advantage and game ball. b. If the player with
   * advantage wins the ball he wins the game c. If the player without advantage wins they are back
   * at deuce.
   *
   * @param playerNumber index of the current Player
   */
  public void addPoint(PlayerNumber playerNumber) {

    final Player currentPlayer = players.get(playerNumber.getIndex());
    final Player otherPlayer = players.get(Math.abs(playerNumber.getIndex() - 1));

    if (getWinnerPlayer().isPresent()) {
      throw new IllegalStateException("The score was already set.");
    }

    // n - 40A
    if (Score.ADVANTAGE == otherPlayer.getScore()) {
      otherPlayer.setScore(otherPlayer.getScore().previous());
      return;
    }

    // specific to rule 2 ( If you have 40 and you win the ball you win the game)
    if (Score.FORTY == currentPlayer.getScore() && Score.FORTY != otherPlayer.getScore()) {
      currentPlayer.setScore(Score.SET);
      return;
    }

    currentPlayer.setScore(currentPlayer.getScore().next());
  }

  /**
   * Get the current score of the set. example : 0 - 15, 30 - 30, Advantage to Player One !,
   *
   * @return
   */
  public String getScore() {

    final Optional<Player> winner = getWinnerPlayer();
    if (winner.isPresent()) {
      return winner.get().getName() + SCORE_SET;
    }

    if (hasDeuce()) {
      return SCORE_DEUCE;
    }

    final Optional<Player> advantageOpt = getAdvantagePlayer();
    if (advantageOpt.isPresent()) {
      return String.format(SCORE_ADVANTAGE, advantageOpt.get().getName());
    }

    return String.format(SCORE_RESULT, players.get(0).getScore().getPoint(),
        players.get(1).getScore().getPoint());
  }

  private Optional<Player> getWinnerPlayer() {
    final Score score = Score.SET;
    final Optional<Player> playerOpt = matchScore(score, players.get(0));
    return playerOpt.isPresent() ? playerOpt : matchScore(score, players.get(1));
  }

  private Optional<Player> getAdvantagePlayer() {

    final Score score = Score.ADVANTAGE;
    final Optional<Player> playerOpt = matchScore(score, players.get(0));
    return playerOpt.isPresent() ? playerOpt : matchScore(score, players.get(1));
  }

  private boolean hasDeuce() {
    return Score.FORTY == players.get(0).getScore() && Score.FORTY == players.get(1).getScore();
  }


  private Optional<Player> matchScore(Score score, Player player) {
    return score == player.getScore() ? Optional.of(player) : Optional.empty();
  }


}

