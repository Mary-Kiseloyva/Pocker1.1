package KiselevaMaria.strategy;

public interface BetStrategy {
    Integer getNext(int i);

    default void endGame() {

    }
}
