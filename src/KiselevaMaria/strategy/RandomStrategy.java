package KiselevaMaria.strategy;

import java.util.concurrent.ThreadLocalRandom;

public class RandomStrategy {
    //private final String player;

  /*  public RandomStrategy(String player) {
        this.player = player;
    }*/

    public RandomStrategy() {
    }

    public Integer getNext(int value) {
        int bet = ThreadLocalRandom.current().nextInt(value)+1;
        return bet;
    }
}
