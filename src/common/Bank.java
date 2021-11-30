package common;

import ru.vsu.cs.KiselevaMaria.Game;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Bank {

    private int bank = 0;
    private Game game = new Game();
    //List<Player> players;

    public Bank() throws IOException {
    }

    public int bankGame(List<Player> players) {
        StringBuilder info = new StringBuilder();
        Map<String, Integer> map = game.getPlayersMoney();
        for (Player player : players) {
            bank = bank + player.getBet();
            info.append(player.getName()).append(" ").append(map.get(player.getName())).append("\n");
        }
        return bank;
    }
}
