import java.util.Vector;

public class Game
{
    static CardDeck cd = new CardDeck();
    static Card.Suit trumpSuit;
    public Vector<Card> turnCards = new Vector<Card>();
    public static void main(String [] args){
        Game g = new Game();
        //CardDeck cd = new CardDeck();
        //Card.Suit trumpSuit = cd.deck.firstElement().getCardSuit();

        /*System.out.println("\nDeck before dealing\n");
        for (Card card : cd.deck) {
            System.out.println(card.getNumber() + " " + card.getCardSuit());
        }*/

        HumanPlayer you = new HumanPlayer();
        ComputerPlayer bot = new ComputerPlayer();

        you.setTurn(1);
        cd.dealPlayer(you);

        /*System.out.println("\nPlayer's hand\n");
        for (Card card : you.hand) {
            System.out.println(card.getNumber() + " " + card.getCardSuit());
        }

        System.out.println("\nDeck after\n");
        for (Card card : cd.deck) {
            System.out.println(card.getNumber() + " " + card.getCardSuit());
        }*/

        bot.setTurn(2);
        cd.dealPlayer(bot);

        /*System.out.println("\nDeck after dealing\n");
        for (Card card : cd.deck) {
            System.out.println(card.getNumber() + " " + card.getCardSuit());
        }*/

        trumpSuit = cd.getTrumpSuit();

        System.out.println("\nTrump suit: " + trumpSuit);
        System.out.println("\n hand\n");
        for (Card card : bot.hand) {
            System.out.println(card.getRank() + " " + card.getCardSuit());
        }

       /* g.turnCards.add(new Card(14, Card.Suit.Spade));
        g.turnCards.add(new Card(6, Card.Suit.Spade));
        g.turnCards.add(new Card(11, Card.Suit.Spade));
        g.turnCards.add(new Card(8, Card.Suit.Spade));
        g.turnCards.add(new Card(10, Card.Suit.Spade));
        */

        Card c = new Card(14, Card.Suit.Spade);
        System.out.println(bot.defend(c, g.turnCards));

        System.out.println("The turn deck: ");
        for (Card card : g.turnCards){
          System.out.println(card.getRank() + " " + card.getCardSuit());
        }


        //System.out.println("\nThe turn cards: ");
        //for (Card card : g.turnCards){
          //  System.out.println(card.getRank() + " " + card.getCardSuit());
        //}

        //System.out.println();
        //System.out.println(bot.isExtraAttack(g.turnCards));

        //if(bot.isExtraAttack(g.turnCards))
            //bot.extraAttack(g.turnCards);
        /*System.out.println("\nTrump suit is " + trumpSuit);

        System.out.println("\nDeck after dealing trump card\n");
        for (Card card : cd.deck) {
            System.out.println(card.getNumber() + " " + card.getCardSuit());
        }*/
    }
}