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

    String printCard()
    {
        String output;
        switch(rank)
        {
            case 11:
                output = "Jack of " + cardSuit;
                break;
            case 12:
                output = "Queen of " + cardSuit;
                break;
            case 13:
                output = "King of " + cardSuit;
                break;
            case 14:
                output = "Ace of " + cardSuit;
                break;
            default:
                output = rank + " of " + cardSuit;
                break;
        }
        return output;
    }
}
