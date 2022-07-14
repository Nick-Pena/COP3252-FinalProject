import java.util.Scanner;
import java.util.Vector;

public class Game
{
    static CardDeck cd = new CardDeck();
    static Card.Suit trumpSuit;
    static Vector<Card> turnCards = new Vector<>();
    public static void main(String [] args){

        int numPlayers = 2;

        HumanPlayer you = new HumanPlayer();
        ComputerPlayer cpu = new ComputerPlayer();

        cd.dealPlayer(you);
        cd.dealPlayer(cpu);
        trumpSuit = cd.getTrumpSuit();

        turnOrder(you, cpu);
        System.out.println("Human's turn is " + you.getTurn());
        System.out.println("CPU's turn is " + cpu.getTurn());

        System.out.println("The trump suit is " + trumpSuit + "\n");

        // testing with one player and one bot
        int itr = 0;
        while(you.hand.size() != 0 && cpu.hand.size() != 0)
        {
            System.out.println("Your hand is as follows.");
            you.printHand();

            System.out.println("\nThe cpu's hand is as follows.");
            cpu.printHand();

            switch(itr % 2)
            {
                case 0:
                    playerAttackBot(you, cpu);
                    break;
                case 1:
                    botAttackPlayer(you, cpu);
                    break;
            }

            itr++;
        }

        if(you.hand.size() == 0)
        {
            System.out.println("you are winner");
        }
        else
        {
            System.out.println("cpu is winner");
        }
    }

    // TODO: create method to handle "battles" and their outcomes

    private static void turnOrder(HumanPlayer human, ComputerPlayer computer)
    {
        Card humanBestCard = new Card(14, trumpSuit),
             cpuBestCard = new Card(14, trumpSuit);

        for(int i = 0; i < 6; i++)
        {
            if(human.hand.elementAt(i).getCardSuit() == trumpSuit
                    && humanBestCard.getRank() > human.hand.elementAt(i).getRank())
            {
                humanBestCard = human.hand.elementAt(i);
            }
            if(computer.hand.elementAt(i).getCardSuit() == trumpSuit
                    && cpuBestCard.getRank() > computer.hand.elementAt(i).getRank())
            {
                cpuBestCard = computer.hand.elementAt(i);
            }
        }

        if(humanBestCard.getRank() < cpuBestCard.getRank())
        {
            human.setTurn(1);
            computer.setTurn(2);
        }
        else
        {
            computer.setTurn(1);
            human.setTurn(2);
        }
    }

    private static void playerAttackBot(HumanPlayer attacker, ComputerPlayer defender)
    {
        Scanner playerScan = new Scanner(System.in);

        System.out.println("\nWhat card are you gonna use?");
        Card attackerChoice = attacker.playCard(attacker.hand.elementAt(playerScan.nextInt() - 1));
        System.out.println("You chose " + attackerChoice.printCard());
        attacker.playCard(attackerChoice);
        Card defenderChoice = new Card();
        if(!defender.defend(attackerChoice, turnCards))
        {
            System.out.println("The bot passes its turn.");
            for(int i = 0; i < turnCards.size(); i++)
            {
                defender.hand.add(turnCards.elementAt(i));
            }
            return;
        }

        System.out.println("The bot chose " + defenderChoice.printCard());
        defender.hand.remove(defenderChoice);
    }

    private static void botAttackPlayer(HumanPlayer defender, ComputerPlayer attacker)
    {
        Card attackerChoice = attacker.attack();
        System.out.println("The attacker chose " + attackerChoice.printCard());
        attacker.hand.remove(attackerChoice);

        System.out.println("What do you wanna do?");
        Scanner playerScan = new Scanner(System.in);
        Card defenderChoice = defender.hand.elementAt(playerScan.nextInt() - 1);
        while(!validMoveCheck(attackerChoice, defenderChoice))
        {
            System.out.println("Choose again");
            defenderChoice = defender.hand.elementAt(playerScan.nextInt() - 1);
        }
        System.out.println("You played " + defenderChoice.printCard());
        defender.playCard(defenderChoice);
    }

    private static boolean validMoveCheck(Card attackerCard, Card defenderCard)
    {
        if(attackerCard.getCardSuit() == defenderCard.getCardSuit()
                && defenderCard.getRank() > attackerCard.getRank())
        {
            return true;
        }
        // returns if the card's suit is the trump suit
        // if true, we can assume the move is valid
        // if false, the move is assumed to be invalid
        else return defenderCard.getCardSuit() == trumpSuit;
    }
}
