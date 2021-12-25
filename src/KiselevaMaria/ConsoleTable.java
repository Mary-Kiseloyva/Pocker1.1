package KiselevaMaria;


import KiselevaMaria.common.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ConsoleTable {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public Game game;

    public ConsoleTable() throws IOException {
        List<String> playerList = new ArrayList<>();
        game = new Game();
        String name = br.readLine();
        int countPlayers = Integer.parseInt(br.readLine());
        playerList.add(name);
        for (int i = 1; i < countPlayers; i++) {
            playerList.add(i, "Player" + i);
        }
        game.createPlayers(playerList);
        while (true) {
            System.out.println(game.getPlayersMoney().toString());
            game.giveCardsToPlayers();
            for (Player player : game.getPlayers()) {
                System.out.println(player.getName() + " Делайте ставку");
                game.makeBet(player, br.read());
            }
            for (int i = 0; i < 3; i++) {
                game.putCardOnTable();
            }
            System.out.println(game.getCardsOnTable().toArray().toString());
            for (Player player : game.getPlayers()) {
                System.out.println(player.getName() + " Делайте ставку");
                game.makeBet(player, br.read());
            }
            game.getCardsOnTable();
            System.out.println(game.getCardsOnTable().toArray().toString());
            for (Player player : game.getPlayers()) {
                System.out.println(player.getName() + " Делайте ставку");
                game.makeBet(player, br.read());
            }
            game.getCardsOnTable();
            System.out.println(game.getCardsOnTable().toArray().toString());
            System.out.println(game.getWinner().getName());


        }
    }

}

