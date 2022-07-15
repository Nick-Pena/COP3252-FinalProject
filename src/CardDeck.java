import java.util.Vector;

public class CardDeck{
    //public HashSet<Card> deck;
    public Vector<Card> deck;
    //public Card[] deck;

    private final int DECK_SIZE = 36;

    public CardDeck(){
        //deck = new HashSet<Card>(36);
        //deck = new Card[36];
        deck = new Vector<Card>();
        
        // start at 6 to match card ranks with the back-end
        int rank = 6;
        for (int i = 0; i < DECK_SIZE; i++){
            switch(i % 4){
                case 0:
                    deck.addElement(new Card(rank, Card.Suit.Clubs));
                    break;
                case 1:
                    deck.addElement(new Card(rank, Card.Suit.Hearts));
                    break;
                case 2:
                    deck.addElement(new Card(rank, Card.Suit.Spades));
                    break;
                case 3:
                    deck.addElement(new Card(rank, Card.Suit.Diamonds));
                    rank++;
                    break;
            }
        }
        // shuffle
        for (int i = 0; i < DECK_SIZE; i++){
            int random = i + (int)(Math.random() * (DECK_SIZE - i));
            Card temp = deck.elementAt(random);
            deck.set(random, deck.elementAt(i));
            deck.set(i, temp);
        }
    }

    public int getDeckSize()
    {
        return DECK_SIZE;
    }

    public void dealPlayer(Player player)
    {
        if(deck.size() > 0)
        {
            for(int i = 0; i < 6; i++)
            {
                player.hand.add(deck.lastElement());
                deck.remove(deck.lastElement());
            }
        }
    }

    public void dealCard(Player player)
    {
        player.hand.add(deck.lastElement());
        deck.remove(deck.lastElement());
    }

    public Card.Suit getTrumpSuit()
    {
        Card.Suit returnSuit = deck.lastElement().getCardSuit();
        deck.insertElementAt(deck.lastElement(), 0);
        deck.removeElementAt(deck.size() - 1);
        return returnSuit;
    }
}