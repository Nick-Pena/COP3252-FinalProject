public class Game
{
    public static void main(String [] args){
        CardDeck cd = new CardDeck();
        /*for (int i = 0; i < cd.deck.size(); i++){
            System.out.println(cd.deck.elementAt(i));
            System.out.println(cd.deck.elementAt(i).getNumber() + " " + cd.deck.elementAt(i).getCardSuit());
        }*/
        System.out.println("Deck before\n");
    
        for (Card card : cd.deck) {
            System.out.println(card.getNumber() + " " + card.getCardSuit());
        }
        //System.out.println("lsfnselhgbou");

        HumanPlayer you = new HumanPlayer();
        ComputerPlayer bot = new ComputerPlayer();

        you.setTurn(1);
        cd.dealPlayer(you);

        System.out.println("Player's hand\n");
        for (Card card : you.hand) {
            System.out.println(card.getNumber() + " " + card.getCardSuit());
        }

        System.out.println("Deck After\n");
        for (Card card : cd.deck) {
            System.out.println(card.getNumber() + " " + card.getCardSuit());
        }

        bot.setTurn(2);
        cd.dealPlayer(bot);
    }
}