public class Card {
    private int rank;
    private Suit cardSuit;

    public enum Suit {
        Club, Diamonds, Hearts, Spade
    }

    public Card(){

    }

    public Card(int n, Suit s){
        rank = n;
        cardSuit = s;
    }
    int getRank(){
        return rank;
    }
    void setNumber(int n){
        rank = n;
    }
    Suit getCardSuit(){
        return cardSuit;
    }
    void setCardSuit(Suit s){
        cardSuit = s;
    }
}
