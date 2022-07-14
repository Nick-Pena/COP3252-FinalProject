import java.util.Vector;

public class ComputerPlayer extends Player {

    public ComputerPlayer()
    {
        hand = new Vector<>();
    }

    public Card attack(){
        boolean foundLowestRegularCard = false;
        Card lowestCard = hand.elementAt(0);
        for (int i = 0; i < hand.size(); i ++){
            if (hand.elementAt(i).getCardSuit() != Game.trumpSuit){
                lowestCard = hand.elementAt(i);
                break;
            }
        }
        // find lowest rank regular card
        for (int i = 1; i < hand.size(); i++){
            if (lowestCard.getRank() > hand.elementAt(i).getRank()){
                if (hand.elementAt(i).getCardSuit() != Game.trumpSuit){
                    lowestCard = hand.elementAt(i);
                }
                foundLowestRegularCard = true;
            }
        }
        // if there is no lowest rank regular card, find lowest rank trump card
        if (foundLowestRegularCard == false){
            for (int i = 1; i < hand.size(); i++){
                if (lowestCard.getRank() > hand.elementAt(i).getRank()){
                        lowestCard = hand.elementAt(i);
                    }
            }
        }
        return lowestCard;
    }

    /*public boolean canDefend(Card card){
        for (int i = 0; i < hand.size(); i++){
            // if a card is higher with the same suit
            if (hand.elementAt(i).getRank() > card.getRank() &&
                hand.elementAt(i).getCardSuit() == card.getCardSuit()){
                    return true;
                }
            // if a card is a trump card
            else if (hand.elementAt(i).getCardSuit() == Game.trumpSuit &&
                    (card.getCardSuit() != Game.trumpSuit)){
                        return true;
                    }
        }
        return false;
    }
    */

    public boolean defend(Card card, Vector<Card> turnCards){
        // find the lowest rank card 
        sortCardsRank();
        System.out.println("\n hand\n");
        for (int i = 0; i < hand.size(); i ++){
            System.out.println(hand.elementAt(i).getRank() + " " + hand.elementAt(i).getCardSuit());
        }
        Card lowestDefendCard = hand.elementAt(0);

        // find the lowest rank card with the same suit
        // it can be a regular card and a trump card
        for (int i = 0; i < hand.size(); i++){
            if (hand.elementAt(i).getCardSuit() == card.getCardSuit()){
                if (hand.elementAt(i).getRank() > card.getRank()){                    
                    lowestDefendCard = hand.elementAt(i);
                    turnCards.add(lowestDefendCard);
                    return true;
                    }
                }
        }
         // if the attacker card is not trump
         if (card.getCardSuit() != Game.trumpSuit){
            // find a lowest rank trump suit
            for (int i = 0; i < hand.size(); i ++){
                if (hand.elementAt(i).getCardSuit() == Game.trumpSuit){
                    lowestDefendCard = hand.elementAt(i);
                    turnCards.add(lowestDefendCard);
                    return true;
                }
            }
         }
         return false;
    }

   /* public boolean isExtraAttack(Vector<Card> turnCards){
        for (int i = 0; i < turnCards.size(); i++){
            for (int j = 0; j < hand.size(); j++){
                if (turnCards.elementAt(i).getRank() == hand.elementAt(j).getRank()){
                    return true;
                }
            }
        }
        return false;
    }
    // depends on the isExtraAttack boolean
    public boolean extraAttack(Vector<Card> turnCards){
        // sort turn cards
        for (int i = 0; i < turnCards.size(); i++){
            for (int j = 0; j < turnCards.size() - i - 1; j++){
                if (turnCards.elementAt(j).getRank() > turnCards.elementAt(j + 1).getRank()){
                    Card temp = turnCards.elementAt(j);
                    //turnCards.elementAt(j) = turnCards.elementAt(j + 1);
                    turnCards.set(j, turnCards.elementAt(j + 1));
                    turnCards.set(j + 1, temp);
                }
            }
        }
    }
    */
    

    public void sortCardsRank(){
        for (int i = 0; i < hand.size(); i++){
            for (int j = 0; j < hand.size() - i - 1; j++){
                if (hand.elementAt(j).getRank() > hand.elementAt(j + 1).getRank()){
                    Card temp = hand.elementAt(j);
                    hand.set(j, hand.elementAt(j + 1));
                    hand.set(j + 1, temp);
                }
            }
        }
    }
}

