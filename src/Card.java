public class Card {
    private int rank;
    private suit cardSuit;

    public enum suit{
        Club, Diamonds, Hearts, Spade
    }

    public Card(){

    }

    public Card(int n, suit s){
        rank = n;
        cardSuit = s;
    }
    int getNumber(){
        return rank;
    }
    void setNumber(int n){
        rank = n;
    }
    suit getCardSuit(){
        return cardSuit;
    }
    void setCardSuit(suit s){
        cardSuit = s;
    }
}
