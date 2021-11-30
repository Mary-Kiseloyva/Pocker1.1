package ru.vsu.cs.KiselevaMaria;

import card.Card;
import common.Deck;
import common.Player;
import ru.vsu.cs.KiselevaMaria.combination.AbstractCombination;
import ru.vsu.cs.KiselevaMaria.combination.CombinationDTO;
import ru.vsu.cs.KiselevaMaria.combination.CombinationStorage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {

    public Game() throws IOException {
    }


    public int getBank() {
        return bank;
    }

    public Map<String, Integer> getPlayersMoney() {
        Map<String, Integer> playersMoney = new HashMap<>();
        for (Player player : players) {
            playersMoney.put(player.getName(), player.getMoney());
        }
        return playersMoney;
    }

    public List<Card> getCardsOnTable() {
        List<Card> list = new ArrayList<>();
        for (Card card : cards) {
            list.add(card);
        }
        return list;
    }

    public Player getWinner(){
        Candidate candidate = findWinner();
        return candidate.getPlayer();
    }


    private static int bank = 0;
    private Deck deck = new Deck();


    public List<Player> getPlayers() {
        return players;
    }

    private List<Player> players = new ArrayList<>();

    public List<Card> getCards() {
        return cards;
    }

    private List<Card> cards = new ArrayList<>(5);
    private Map<String, Integer> bets = new HashMap<>();


    public void putCardOnTable() {
        cards.add(deck.pop());
    }

    public void giveCardsToPlayers()  {
        for (Player player : players) {
            giveStartCards(player);
        }
    }

    public List<Player> createPlayers(List<String> names) throws IOException {
        for (String name : names) {
            players.add(new Player(name));
        }
        players.get(0).setAi(false);
        return players;
    }

    public void makeStartBet(Player player, int bet) throws IOException {
        makeBet(player, bet);
    }

    public void makeBet(Player player, int bet) {
        if (bet > 0) {
            player.setBet(bet);
            bank += player.decreaseMoney(bet);

        } else {
            player.setPass(true);
        }
    }


    public void giveStartCards(Player player) {
        player.getCards().add(deck.pop());
        player.getCards().add(deck.pop());
    }


    public Candidate findWinner() {
        Candidate winner = new Candidate();
        for (Player player : players) {
            if (!player.isPass()) {
                findAllPlayerCombinations(player, cards);
                compareWithWinner(player, winner);
            }
        }
        return winner;
    }

    public void compareWithWinner(Player player, Candidate winner) {
        for (CombinationDTO combination : player.getCombinations()) {
            System.out.println(combination);
            Candidate candidate = new Candidate(combination, player);
            findOrSetWinner(winner, candidate);
        }
    }

    public void findAllPlayerCombinations(Player player, List<Card> cards) {
        for (AbstractCombination combination : CombinationStorage.combinations) {
            combination.check(player, cards);
        }
    }


    public void finishGame(Player player, List<Player> players) {
        player.increaseMoney(bank);
        bank = 0;
        for (Player player1 : players) {
            deck.put(player1.getCards());
            player1.getCards().clear();
            player1.setPass(false);
            player1.getCombinations().clear();
        }

    }

    public void findWinner(Candidate winner, Candidate candidate) {
        if (isPriorityBigger(winner, candidate) ||
                isPriorityTheSameAndCardsBetter(winner, candidate)) {
            setWinner(winner, candidate);
        }
    }

    public void findOrSetWinner(Candidate winner, Candidate candidate) {
        if (winner.getCombination() != null) {
            findWinner(winner, candidate);
        } else {
            setWinner(winner, candidate);
        }
    }

    public boolean isPriorityBigger(Candidate winner, Candidate candidate) {
        return candidate.getCombination().getPriority() >
                winner.getCombination().getPriority();
    }

    public boolean isPriorityTheSameAndCardsBetter(Candidate winner, Candidate candidate) {
        return candidate.getCombination().getPriority() ==
                winner.getCombination().getPriority() &&
                candidate.getCombination().getCardsSumValue() >
                        winner.getCombination().getCardsSumValue();
    }

    public void setWinner(Candidate winner, Candidate candidate) {
        winner.setCombination(candidate.getCombination());
        winner.setPlayer(candidate.getPlayer());
    }


    private static class Candidate {
        private CombinationDTO combination;
        private Player player;

        public Candidate() {
        }

        public Candidate(CombinationDTO combination, Player player) {
            this.combination = combination;
            this.player = player;
        }

        public CombinationDTO getCombination() {
            return combination;
        }

        public Player getPlayer() {
            return player;
        }

        public void setCombination(CombinationDTO combination) {
            this.combination = combination;
        }

        public void setPlayer(Player player) {
            this.player = player;
        }


    }

}



