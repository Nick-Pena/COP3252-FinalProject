import java.util.Vector;

public class HumanPlayer extends Player {

    HumanPlayer()
    {
        hand = new Vector<>();
        if (turn == 1){
            
        }
    }

    public void playCard(Vector<Card> cards, Card playedCard)
    {
        for(int i = 0; i < hand.size(); i++)
        {
            if(playedCard.getRank() == hand.elementAt(i).getRank()
                    && playedCard.getCardSuit() == hand.elementAt(i).getCardSuit())

            {
                cards.add(hand.elementAt(i));
                hand.removeElementAt(i);
            }
        }
    }
}
