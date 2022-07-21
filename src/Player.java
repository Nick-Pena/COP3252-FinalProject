import java.util.Vector;

public class Player {
    protected int turnOrder;
    protected Vector<Card> hand;
    protected boolean isAttack;

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

    protected void setIsAttack(boolean attack){
        isAttack = attack;
    }

    protected boolean getIsAttack(){
        return isAttack;
    }


    protected void printHand()
    {
        for(int i = 0; i < hand.size(); i++)
        {
            System.out.println(i + 1 + ") " + hand.elementAt(i).printCard());
        }
    }
    public void sortCardsRank(Vector<Card> vec){
        for (int i = 0; i < vec.size(); i++){
            for (int j = 0; j < vec.size() - i - 1; j++){
                if (vec.elementAt(j).getRank() > vec.elementAt(j + 1).getRank()){
                    Card temp = vec.elementAt(j);
                    vec.set(j, vec.elementAt(j + 1));
                    vec.set(j + 1, temp);
                }
            }
        }
    }
    protected boolean addExtra(Vector<Card> turnCards){
        for (int i = 0; i < turnCards.size(); i++){
            for (int j = 0; j < hand.size(); j++){
                if (turnCards.elementAt(i).getRank() == hand.elementAt(j).getRank()){
                    turnCards.add(hand.elementAt(j));
                    hand.removeElementAt(j);
                    return true;
                }
            }
        }
        return false;
    }
}
