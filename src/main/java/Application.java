import command.ICommandLineRunner;
import command.TennisCommandLine;

public class Application {

  public static void main(String[] args) {
    ICommandLineRunner runner = new TennisCommandLine();
    runner.execute();
  }
}
