package command;

import static java.lang.System.exit;

import core.PlayerNumber;
import core.TennisGame;
import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Random;
import java.util.Scanner;


public class TennisCommandLine implements ICommandLineRunner {

  private TennisGame tennisGame = new TennisGame();

  @Override
  public void execute() {

    System.out.println("Tennis Game");
    while (true) {
      try {
        menu();
      } catch (InputMismatchException e) {
        System.out.println("Illegal input.");
      }
    }


  }

  private void menu() throws InputMismatchException {
    final String score = tennisGame.getScore();
    System.out.println(score);
    if (score.endsWith(TennisGame.SCORE_SET)) {
      exit(1);
    }
    System.out.println("Choose from these choices");
    System.out.println("-------------------------\n");
    System.out.println("1 - Add a point to player one");
    System.out.println("2 - Add a point to player two");
    System.out.println("3 - Add a point to a random player");
    System.out.println("4 - Quit");
    System.out.print(">");

    Scanner input = new Scanner(System.in);
    int selection = input.nextInt();
    switch (selection) {
      case 1:
        tennisGame.addPoint(PlayerNumber.ONE);
        break;
      case 2:
        tennisGame.addPoint(PlayerNumber.TWO);
        break;
      case 3:
        final int playerIndex = new Random().nextInt(2);
        final Optional<PlayerNumber> playerNumberOpt = PlayerNumber.fromValue(playerIndex);
        playerNumberOpt.ifPresent(p -> tennisGame.addPoint(p));
        break;
      case 4:
        exit(1);
        break;
      default:
    }
  }
}
