import javax.swing.*;
import java.awt.*;

public class Game
{
    static CardDeck cd = new CardDeck();
    static Card.Suit trumpSuit;

    static JFrame gameFrame = new JFrame("Durak Game");
    public static void main(String [] args){

        HumanPlayer you = new HumanPlayer();
        ComputerPlayer bot = new ComputerPlayer();

        you.setTurn(1);
        cd.dealPlayer(you);

        bot.setTurn(2);
        cd.dealPlayer(bot);

        trumpSuit = cd.getTrumpSuit();

        playerCardDisplay playerHand = new playerCardDisplay(you);

        gameFrame.add(playerHand);
        gameFrame.setSize(800, 600);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setVisible(true);
    }

    // vector/other collection for graveyard?
    // every index % 2 can be a new pair of cards

    // TODO: create method to handle "battles" and their outcomes

    // TODO: check for win condition should be simple. just check if any player's hand == 0, game over/they are a winner

    boolean validMoveCheck(Card attackerCard, Card defenderCard)
    {
        if(attackerCard.getCardSuit() == defenderCard.getCardSuit()
                && defenderCard.getNumber() > attackerCard.getNumber())
        {
            return true;
        }
        // returns if the card's suit is the trump suit
        // if true, we can assume the move is valid
        // if false, the move is assumed to be invalid
        else return defenderCard.getCardSuit() == trumpSuit;
    }
}

class playerCardDisplay extends JPanel
{

    // placeholder hand display for any given player
    // use primarily for debugging/testing for now
    // TODO: work on display first maybe? figure it out on thursday
    public playerCardDisplay(Player player)
    {
        setLayout(new GridBagLayout());

        JButton[] cardButtons = new JButton[player.hand.size()];
        for(int i = 0; i < player.hand.size(); i++)
        {
            // jack, queen, king, ace assignment of text
            switch(player.hand.elementAt(i).getNumber())
            {
                case 11:
                    cardButtons[i] = new JButton("Jack of "
                            + player.hand.elementAt(i).getCardSuit());
                    break;
                case 12:
                    cardButtons[i] = new JButton("Queen of "
                            + player.hand.elementAt(i).getCardSuit());
                    break;
                case 13:
                    cardButtons[i] = new JButton("King of "
                            + player.hand.elementAt(i).getCardSuit());
                    break;
                case 14:
                    cardButtons[i] = new JButton("Ace of "
                            + player.hand.elementAt(i).getCardSuit());
                    break;
                default:
                    cardButtons[i] = new JButton(player.hand.elementAt(i).getNumber() + " of "
                            + player.hand.elementAt(i).getCardSuit());
                    break;
            }

            if(player.hand.elementAt(i).getCardSuit() == Card.Suit.Hearts ||
                    player.hand.elementAt(i).getCardSuit() == Card.Suit.Diamonds)
            {
                cardButtons[i].setForeground(Color.RED);
            }
            else if(player.hand.elementAt(i).getCardSuit() == Card.Suit.Spades ||
                        player.hand.elementAt(i).getCardSuit() == Card.Suit.Clubs)
            {
                cardButtons[i].setForeground(Color.BLACK);
            }

            add(cardButtons[i]);
            cardButtons[i].setVisible(true);
        }
    }
}