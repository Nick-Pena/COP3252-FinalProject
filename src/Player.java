import java.util.Vector;

public class Player {
    protected int turnOrder;
    protected Vector<Card> hand;

    public Player(){
        hand = new Vector<>();
    }

    protected int getTurnOrder()
    {
        return turnOrder;
    }

    protected void setTurn(int _turn)
    {
        turnOrder = _turn;
    }


    protected void printHand()
    {
        for(int i = 0; i < hand.size(); i++)
        {
            System.out.println(i + 1 + ") " + hand.elementAt(i).printCard());
        }
    }
}
