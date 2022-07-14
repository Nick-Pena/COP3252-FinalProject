import java.util.Vector;

public class Game
{
    static CardDeck cd = new CardDeck();
    static Card.Suit trumpSuit;
    public Vector<Card> turnCards = new Vector<Card>();
    public static void main(String [] args){
        Game g = new Game();
        HumanPlayer you = new HumanPlayer();
        ComputerPlayer bot = new ComputerPlayer();

        //you.setTurn(1);
       cd.dealPlayer(you);

        bot.setTurn(2);
       // cd.dealPlayer(bot);

        trumpSuit = cd.getTrumpSuit();

        System.out.println("\nTrump suit: " + trumpSuit);
        bot.hand.add(new Card(11, Card.Suit.Club));
        bot.hand.add(new Card(10, Card.Suit.Diamonds));
        bot.hand.add(new Card(14, Card.Suit.Hearts));
        System.out.println("\n hand\n");
        for (Card card : bot.hand) {
            System.out.println(card.getRank() + " " + card.getCardSuit());
        }

        g.turnCards.add(new Card(14, Card.Suit.Spade));
        g.turnCards.add(new Card(10, Card.Suit.Spade));
        g.turnCards.add(new Card(6, Card.Suit.Spade));

        System.out.println("The turn deck before INSIDE: ");
        for (Card card : g.turnCards){
          System.out.println(card.getRank() + " " + card.getCardSuit());
        }


        System.out.println(bot.extraAttack(g.turnCards, you.hand, cd));

        System.out.println("The turn deck after INSIDE: ");
        for (Card card : g.turnCards){
          System.out.println(card.getRank() + " " + card.getCardSuit());
        }
    }
}