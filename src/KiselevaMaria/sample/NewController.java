package KiselevaMaria.sample;

import java.io.*;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import KiselevaMaria.card.Card;
import KiselevaMaria.common.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import KiselevaMaria.Game;
import KiselevaMaria.PrintPicturesApplication;
import KiselevaMaria.strategy.RandomStrategy;


public class NewController {
    private final Game game = new Game();



    List<Player> players;


    @FXML
    private ImageView Card1;

    @FXML
    private ImageView Card2;

    @FXML
    private ImageView Card3;

    @FXML
    private ImageView Card4;

    @FXML
    private ImageView Card5;

    @FXML
    private ImageView Card6;

    @FXML
    private ImageView Card7;

    @FXML
    private Button HandOutCards;

    @FXML
    private Button Pass;

    @FXML
    private TextArea Bank;

    @FXML
    private TextArea Bet;

    @FXML
    private Button PlaseABet;

    @FXML
    private Button FndWnnr;

    @FXML
    private javafx.scene.control.Label Label;


    public NewController() {

        for (Card card: game.getDeck()){
            String s1 = card.getColor().name();
            String s2 = card.getValue().name();
            URL resource = PrintPicturesApplication.class.getResource("image/");
            String path = resource + s1 + " " + s2 + ".png";
            String file;
            file = URLDecoder.decode(path, StandardCharsets.UTF_8);
            File f = new File(file);
            Image image = new Image(String.valueOf(f));
            card.setImage(image);
        }

        List<String> playerName = new ArrayList<>();
        Controller controller = new Controller();
        playerName.add(controller.getName());
        for (int i = 0; i < controller.getN(); i++) {
            playerName.add("Player " + i);
        }
        players = game.createPlayers(playerName);
    }



    @FXML
    void initialize() {
        List<Card> cards = game.getCards();


        HandOutCards.setOnAction(actionEvent -> {
                    if (players.get(0).getCards().size() == 0) {
                        game.giveCardsToPlayers();
                        game.getPlayersMoney();
                        Card6.setImage(players.get(0).getCards().get(0).getImage());
                        Card7.setImage(players.get(0).getCards().get(1).getImage());
                    } else if (cards.size() == 0) {
                        for (int i = 0; i < 3; i++) {
                            game.putCardOnTable();
                        }
                        Card1.setImage(cards.get(0).getImage());////
                        Card2.setImage(cards.get(1).getImage());
                        Card3.setImage(cards.get(2).getImage());
                    } else if (cards.size() == 3) {
                        game.putCardOnTable();
                        Card4.setImage(cards.get(3).getImage());
                    } else if (cards.size() == 4) {
                        game.putCardOnTable();
                        Card5.setImage(cards.get(4).getImage());
                    } else {
                        Label.setText("There are already max cards on the table");
                    }
                }
        );


        PlaseABet.setOnAction(actionEvent -> {
            RandomStrategy randomStrategy = new RandomStrategy();
            int bet = 0;
            for (Player player : game.getPlayers()) {
                if(player.isAi()){
                 randomStrategy.getNext(1000);
                }
                if (player.isPass()){
                    Bank.setText(Bank.getText() + player.getName() + "-" + "pass" + "\n");
                    continue;
                }
                bet = Integer.parseInt(Bet.getText());
                game.makeBet(player, bet);
                Bank.setText(Bank.getText() + player.getName() + "-" + bet + "\n");
            }
            Bank.setText(Bank.getText() + "bank - " + game.getBank() + "\n");

        });


        Pass.setOnAction(event -> {
            players.get(0).setPass(true);
            Label.setText("Вы спасовали");
        });

        FndWnnr.setOnAction(actionEvent -> {
            Player winner = game.getWinner();
            String str = winner.getName() + "\n";
            str += winner.getWinnerCombination().toString();
            Label.setText(str);
        });


    }




}