package KiselevaMaria.common;


import KiselevaMaria.card.Card;
import KiselevaMaria.card.Color;
import KiselevaMaria.card.Value;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Deck {
    public Stack<Card> getDeck() {
        return deck;
    }

    private final Stack<Card> deck = new Stack<>();

    public Deck()  {
        for (Color color : Color.values()) {
            for (Value value : Value.values()) {
                Card card = new Card(color, value);
                deck.add(card);
            }

        }
        Collections.shuffle(deck);

    }


    public Card pop() {
        return deck.pop();
    }

    public void put(List<Card> cards) {
        for (Card card : cards) {
            deck.push(card);
        }
    }

}
