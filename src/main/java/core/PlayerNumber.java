package core;

import java.util.Optional;
import lombok.Getter;

public enum PlayerNumber {
  ONE(0), TWO(1);

  @Getter
  private int index;

  PlayerNumber(int index) {
    this.index = index;
  }

  public static Optional<PlayerNumber> fromValue(int index) {

    for (PlayerNumber playerNumber : PlayerNumber.values()
    ) {
      if (playerNumber.index == index) {
        return Optional.of(playerNumber);
      }
    }
    return Optional.empty();
  }

}