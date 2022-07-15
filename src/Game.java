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

        // testing with one player and one bot
        while((you.hand.size() != 0 && cpu.hand.size() != 0) && cd.deck.size() > 0)
        {
            System.out.println("Cards left in the deck: " + cd.deck.size());

            // main game loop, controlled by this boolean
            boolean runAgain;
            do
            {
                System.out.println("\nYour hand is as follows.");
                you.printHand();

                System.out.println("\nThe cpu's hand is as follows.");
                cpu.printHand();

                System.out.println("\nThe trump suit is " + trumpSuit + "\n");

                if(you.getTurn() == 1)
                {
                    runAgain = playerAttackBot(you, cpu);
                    if(!runAgain)
                    {
                        break;
                    }

                    runAgain = botAttackPlayer(you, cpu);
                }
                else
                {
                    runAgain = botAttackPlayer(you, cpu);
                    if(!runAgain)
                    {
                        break;
                    }

                    runAgain = playerAttackBot(you, cpu);
                }
            } while(runAgain);

            /*switch(itr % 2)
            {
                case 0:
                    playerAttackBot(you, cpu);
                    break;
                case 1:
                    botAttackPlayer(you, cpu);
                    break;
            }*/

            while(you.hand.size() < 6 && cd.deck.size() > 0)
            {
                cd.dealCard(you);
            }

            while(cpu.hand.size() < 6 && cd.deck.size() > 0)
            {
                cd.dealCard(cpu);
            }
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

        // TODO: add turn-based functionality
        // currently, turns do nothing. player always goes first
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

    private static boolean playerAttackBot(HumanPlayer humanAttacker, ComputerPlayer cpuDefender)
    {

        System.out.println("What do you wanna do? Press 0 to pass.");
        Scanner playerScan = new Scanner(System.in);
        int playerChoice = playerScan.nextInt();
        if(playerChoice == 0)
        {
            return false;
        }
        Card attackerChoice = humanAttacker.hand.elementAt(playerChoice - 1);
        System.out.println("You chose " + attackerChoice.printCard());
        humanAttacker.playCard(turnCards, attackerChoice);

        if(!cpuDefender.defend(attackerChoice, turnCards))
        {
            System.out.println("The bot passes its turn.");
            for(int i = 0; i < turnCards.size(); i++)
            {
                cpuDefender.hand.add(turnCards.elementAt(i));
            }
            return false;
        }

        System.out.println("The CPU (defender) chose " + turnCards.lastElement().printCard());
        return true;
    }

    private static boolean botAttackPlayer(HumanPlayer humanDefender, ComputerPlayer cpuAttacker)
    {
        cpuAttacker.attack(turnCards);
        System.out.println("The CPU (attacker) chose " + turnCards.lastElement().printCard());

        System.out.println("What do you wanna do? Press 0 to pass.");
        Scanner playerScan = new Scanner(System.in);
        int playerChoice = playerScan.nextInt();
        if(playerChoice == 0)
        {
            System.out.println("You chose to pass.");
            for(int i = 0; i < turnCards.size(); i++)
            {
                humanDefender.hand.add(turnCards.elementAt(i));
            }
            return false;
        }

        Card defenderChoice = humanDefender.hand.elementAt(playerChoice - 1);
        while(!validMoveCheck(turnCards.lastElement(), defenderChoice))
        {
            System.out.println("You can't do that");
            playerChoice = playerScan.nextInt();
            if(playerChoice == 0)
            {
                System.out.println("You chose to pass.");
                for(int i = 0; i < turnCards.size(); i++)
                {
                    humanDefender.hand.add(turnCards.elementAt(i));
                }
                return false;
            }

            defenderChoice = humanDefender.hand.elementAt(playerChoice - 1);
        }
        System.out.println("You played " + defenderChoice.printCard());
        humanDefender.playCard(turnCards, defenderChoice);

        return true;
    }

    private static boolean validMoveCheck(Card attackerCard, Card defenderCard)
    {
        // TODO: review this. logic is off
        // maybe walk through vector of cards in play
        // and add each rank to a new array?
        // then check the validity of the play using that
        // and if the suit matches the current suit
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