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

    protected Card playCard()
    {
        return new Card(1, Card.Suit.Hearts);
    }

    protected void printHand()
    {

    }
}
