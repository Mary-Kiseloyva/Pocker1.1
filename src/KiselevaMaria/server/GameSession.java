package KiselevaMaria.server;

import KiselevaMaria.common.Player;
import KiselevaMaria.Game;
import KiselevaMaria.sample.Controller;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class GameSession implements Runnable{
    private final Game game;
    private Controller controller;
    List<Player> players;

    public GameSession(Socket clientSocket) {
        game = new Game();

    }

    @Override
    public void run() {

    }
}
