package KiselevaMaria.server;

import KiselevaMaria.common.Player;
import KiselevaMaria.Game;
import KiselevaMaria.sample.Controller;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class GameServer implements Runnable{
    private final Game game;
    private Controller controller;
    List<Player> players;

    public GameServer(Socket clientSocket) {
      game = new Game();
        List<String> playerName = new ArrayList<>();
        //playerName.add(controller.getName());
        for (int i = 1; i < controller.getN(); i++) {
            playerName.add("Player " + i);
        }
        players = game.createPlayers(playerName);
    }

    @Override
    public void run() {
        while (true){
            game.giveCardsToPlayers();
            for (Player player : game.getPlayers()) {
                game.makeBet(player, 50);
            }
            for (int i = 0; i < 3; i++) {
                game.putCardOnTable();
            }
            System.out.println(game.getCardsOnTable().toArray().toString());
            for (Player player : game.getPlayers()) {
                game.makeBet(player, 50);
            }
            game.getCardsOnTable();
            System.out.println(game.getCardsOnTable().toArray().toString());
            for (Player player : game.getPlayers()) {
                game.makeBet(player, 50);
            }
            game.getCardsOnTable();
            System.out.println(game.getCardsOnTable().toArray().toString());
            System.out.println(game.getWinner().getName());
        }
    }
}
