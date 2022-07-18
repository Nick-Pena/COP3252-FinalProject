import javax.swing.*;
import java.awt.*;

public class Card {
    private int rank;
    private Suit cardSuit;
    public enum Suit {
        clubs, diamonds, hearts, spades
    }
    public Card(int n, Suit s){
        rank = n;
        cardSuit = s;
    }
    public int getRank(){
        return rank;
    }
    public Suit getCardSuit(){
        return cardSuit;
    }

    public Icon getIcon(){
        String output;
        switch(rank)
        {
            case 11:
                output = "jack_of_" + cardSuit + ".png";
                break;
            case 12:
                output = "queen_of_" + cardSuit + ".png";
                break;
            case 13:
                output = "king_of_" + cardSuit + ".png";
                break;
            case 14:
                output = "ace_of_" + cardSuit + ".png";
                break;
            default:
                output = rank + "_of_" + cardSuit + ".png";
                break;
        }
        return new ImageIcon(getClass().getResource(output));
    }

    public String printCard()
    {
        String output;
        switch(rank)
        {
            case 11:
                output = "Jack of " + cardSuit;
                break;
            case 12:
                output = "Queen of " + cardSuit;
                break;
            case 13:
                output = "King of " + cardSuit;
                break;
            case 14:
                output = "Ace of " + cardSuit;
                break;
            default:
                output = rank + " of " + cardSuit;
                break;
        }
        return output;
    }
}
