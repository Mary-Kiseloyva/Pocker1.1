package sample;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import card.Card;
import common.Bank;
import common.Deck;
import common.Player;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.vsu.cs.KiselevaMaria.Game;

import static java.awt.SystemColor.info;

public class NewController {
    //private static int bank = 0;
    private final Controller controller = new Controller();
    private Game game = new Game();
    private Deck deck = new Deck();
    private common.Bank bank = new Bank();


    List<Player> players;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView Card1;

    @FXML
    private HBox ABC;

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


    public NewController() throws IOException {

        for (Card card: game.getCards()){
            String s1 = card.getColor().name();
            String s2 = card.getValue().name();
            String path = "src/ru/vsu/cs/KiselevaMaria/image/" + s1 + " " + s2 + ".png";
            File file = new File(path);
            Image image = new Image(file.toURI().toString());
            card.setImage(image);
        }

        List<String> playerName = new ArrayList<>();
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
                        Label.setText("На столе максимальное количество карт");
                    }
                }
        );


        PlaseABet.setOnAction(actionEvent -> {
            for (Player player : game.getPlayers()) {
                if (player.isPass()) continue;
                int bet = Integer.parseInt(Bet.getText());
                game.makeBet(player, bet);
            }
            int bank1 = bank.bankGame(players);
            Bank.setText("bank - " + String.valueOf(bank1) + "\n" + info);
           // bankGame();

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
//28.11
   /* public void bankGame() {
        StringBuilder info = new StringBuilder();
        Map<String, Integer> map = game.getPlayersMoney();
        for (Player player : players) {
            bank = bank + player.getBet();
            info.append(player.getName()).append(" ").append(map.get(player.getName())).append("\n");
        }
        Bank.setText("bank - " + String.valueOf(bank) + "\n" + info);

    }*/

    public Button getPass() {
        return Pass;
    }

    public void setPass(Button pass) {
        Pass = pass;
    }

    public TextArea getBank() {
        return Bank;
    }

    public void setBank(TextArea bank) {
        Bank = bank;
    }

}