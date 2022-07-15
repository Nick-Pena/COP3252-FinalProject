import java.util.Vector;

public class Player {
    protected int turn;
    protected Vector<Card> hand;

    public Player(){
        hand = new Vector<>();
    }

    protected int getTurn()
    {
        return turn;
    }

    protected void setTurn(int _turn)
    {
        turn = _turn;
    }


    protected void printHand()
    {
        for(int i = 0; i < hand.size(); i++)
        {
            System.out.println(i + 1 + ") " + hand.elementAt(i).printCard());
        }
    }
}
