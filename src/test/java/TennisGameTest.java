import java.util.stream.IntStream;
import org.junit.Assert;
import org.junit.Test;

public class TennisGameTest {

  @Test
  public void shouldAddPointToPlayerOne() {

    TennisGame game = new TennisGame();
    game.addPoint(PlayerNumber.ONE);
    Assert.assertEquals("Fifteen, Love", game.getScore());

  }

  @Test
  public void shouldAddPointToPlayerTwo() {

    TennisGame game = new TennisGame();
    game.addPoint(PlayerNumber.TWO);
    Assert.assertEquals("Love, Fifteen", game.getScore());
  }

  @Test
  public void shouldSetThirtyto40() {

    TennisGame game = new TennisGame();
    game.addPoint(PlayerNumber.ONE);
    Assert.assertEquals("Fifteen, Love", game.getScore());

    game.addPoint(PlayerNumber.TWO);
    Assert.assertEquals("Fifteen, Fifteen", game.getScore());

    game.addPoint(PlayerNumber.TWO);
    Assert.assertEquals("Fifteen, Thirty", game.getScore());

    game.addPoint(PlayerNumber.ONE);
    Assert.assertEquals("Thirty, Thirty", game.getScore());

    game.addPoint(PlayerNumber.TWO);
    Assert.assertEquals("Thirty, Forty", game.getScore());
  }

  @Test
  public void shouldHasScoreDeuce() {
    TennisGame game = new TennisGame();
    addScore(game, PlayerNumber.ONE, 3);
    addScore(game, PlayerNumber.TWO, 3);
    Assert.assertEquals("Deuce", game.getScore());

  }

  @Test
  public void shouldHasAdvantagePlayerOne() {
    TennisGame game = new TennisGame();
    addScore(game, PlayerNumber.ONE, 3);
    addScore(game, PlayerNumber.TWO, 3);
    Assert.assertEquals("Deuce", game.getScore());

    game.addPoint(PlayerNumber.ONE);
    Assert
        .assertEquals("Advantage " + TennisGame.PLAYER_ONE_DEFAULT_NAME, game.getScore());

  }

  @Test
  public void shouldHasAdvantagePlayerTwo() {
    TennisGame game = new TennisGame();
    addScore(game, PlayerNumber.ONE, 3);
    addScore(game, PlayerNumber.TWO, 3);
    Assert.assertEquals("Deuce", game.getScore());

    game.addPoint(PlayerNumber.TWO);
    Assert
        .assertEquals("Advantage " + TennisGame.PLAYER_TWO_DEFAULT_NAME, game.getScore());
  }

  @Test
  public void shouldHasDeuceAfterAdvantagePlayerOne() {
    TennisGame game = new TennisGame();
    addScore(game, PlayerNumber.ONE, 3);
    addScore(game, PlayerNumber.TWO, 3);
    Assert.assertEquals("Deuce", game.getScore());

    game.addPoint(PlayerNumber.ONE);
    Assert
        .assertEquals("Advantage " + TennisGame.PLAYER_ONE_DEFAULT_NAME, game.getScore());

    game.addPoint(PlayerNumber.TWO);
    Assert.assertEquals("Deuce", game.getScore());

  }

  @Test
  public void shouldPlayerOneWin() {
    TennisGame game = new TennisGame();
    addScore(game, PlayerNumber.ONE, 4);
    Assert.assertEquals(TennisGame.PLAYER_ONE_DEFAULT_NAME + " wins", game.getScore());
  }

  @Test(expected = IllegalStateException.class)
  public void shouldThrowExceptionWhenSetWasFinish() {
    TennisGame game = new TennisGame();
    addScore(game, PlayerNumber.ONE, 4);
    Assert.assertEquals(TennisGame.PLAYER_ONE_DEFAULT_NAME + " wins", game.getScore());
    game.addPoint(PlayerNumber.ONE);
  }

  private void addScore(TennisGame game, final PlayerNumber playerNumber, int number) {
    IntStream.range(0, number).forEach(i -> game.addPoint(playerNumber));
  }

}
