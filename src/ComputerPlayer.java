import java.util.Vector;

public class ComputerPlayer extends Player {

    public ComputerPlayer()
    {
        hand = new Vector<>();
    }

    public void attack(Vector<Card> turnCards){
        // sort hand
        if (hand.size() > 0){
            sortCardsRank(hand);
            Card lowestAttackCard = hand.elementAt(0);

            // find lowest rank regular card
            for (int i = 1; i < hand.size(); i++){
                if (hand.elementAt(i).getCardSuit() != Game.trumpSuit){
                    lowestAttackCard = hand.elementAt(i);
                    turnCards.add(lowestAttackCard);
                    hand.removeElementAt(i);
                    return;
                }
            }
            // if there is no lowest rank regular card, attack with the lowest rank trump card
            turnCards.add(lowestAttackCard);
            System.out.println("The CPU (attacker) chose " + lowestAttackCard.printCard());
            hand.removeElementAt(0);
            return;
        }
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
        sortCardsRank(hand);
        Card lowestDefendCard = hand.elementAt(0);

        // find the lowest rank card with the same suit
        // it can be a regular card and a trump card
        for (int i = 0; i < hand.size(); i++){
            if (hand.elementAt(i).getCardSuit() == card.getCardSuit()){
                if (hand.elementAt(i).getRank() > card.getRank()){
                    lowestDefendCard = hand.elementAt(i);
                    turnCards.add(lowestDefendCard);
                    hand.removeElementAt(i);
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
                    hand.removeElementAt(i);
                    return true;
                }
            }
        }
        return false;
    }

    /*public boolean isExtraAttack(Vector<Card> turnCards){
        for (int i = 0; i < turnCards.size(); i++){
            for (int j = 0; j < hand.size(); j++){
                if (turnCards.elementAt(i).getRank() == hand.elementAt(j).getRank()){
                    return true;
                }
            }
        }
        return false;
    }*/

    public boolean extraAttack(Vector<Card> turnCards, Vector<Card> opponentHand, CardDeck cd){
        if (opponentHand.size() > 0){
            // sort turn cards
            sortCardsRank(turnCards);
            sortCardsRank(hand);
            System.out.println("The hand before: ");
            for (int i = 0; i < hand.size(); i++){
                System.out.println(hand.elementAt(i).getRank() + " " + hand.elementAt(i).getCardSuit());
            }
            System.out.println("The turn deck before: ");
            for (int i = 0; i < turnCards.size(); i++){
                System.out.println(turnCards.elementAt(i).getRank() + " " + turnCards.elementAt(i).getCardSuit());
            }
            if (cd.deck.size() > 4){
                // add a card that is not a trump card and its rank lower than 11 (Jack)
                for (int i = 0; i < turnCards.size(); i++){
                    for (int j = 0; j < hand.size(); j++){
                        if (turnCards.elementAt(i).getRank() == hand.elementAt(j).getRank()){
                            if (hand.elementAt(j).getCardSuit() != Game.trumpSuit &&
                                    hand.elementAt(j).getRank() < 11){
                                turnCards.add(hand.elementAt(j));
                                return true;
                            }
                        }
                    }
                }
            }
            else {
                // add a card if its rank higher than 11 (Jack), still not trump
                for (int i = 0; i < turnCards.size(); i++){
                    for (int j = 0; j < hand.size(); j++){
                        if (turnCards.elementAt(i).getRank() == hand.elementAt(j).getRank()){
                            if (hand.elementAt(j).getCardSuit() != Game.trumpSuit){
                                turnCards.add(hand.elementAt(j));
                                return true;
                            }
                        }
                    }
                }

                // add a card even if its a trump card
                for (int i = 0; i < turnCards.size(); i++){
                    for (int j = 0; j < hand.size(); j++){
                        if (turnCards.elementAt(i).getRank() == hand.elementAt(j).getRank()){
                            turnCards.add(hand.elementAt(j));
                            return true;
                        }
                    }
                }
            }
        }
        return false;

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
}