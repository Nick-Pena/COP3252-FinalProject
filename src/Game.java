public class Game
{
    static CardDeck cd = new CardDeck();
    static Card.Suit trumpSuit;
    public static void main(String [] args){
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

        /*System.out.println("\nTrump suit is " + trumpSuit);

        System.out.println("\nDeck after dealing trump card\n");
        for (Card card : cd.deck) {
            System.out.println(card.getNumber() + " " + card.getCardSuit());
        }*/
    }
}