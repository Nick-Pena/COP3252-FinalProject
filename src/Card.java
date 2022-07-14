public class Card {
    private int rank;
    private Suit cardSuit;

    public enum Suit {
        Clubs, Diamonds, Hearts, Spades
    }

    public Card(){

    }

    public Card(int n, Suit s){
        rank = n;
        cardSuit = s;
    }
    int getNumber(){
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
