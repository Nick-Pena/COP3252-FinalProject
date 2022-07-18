import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Scanner;
import java.util.Vector;

public class Game extends JFrame
{
    private CardDeck cd = new CardDeck();
    static Card.Suit trumpSuit;
    private JLabel trumpCard;
    private Vector<Card> turnCards = new Vector<>();
    private Vector<JLabel> humanHandLabels = new Vector<>();
    private Vector<JLabel> computerHandLabels = new Vector<>();
    private JPanel innerPanel = new JPanel();
    private JPanel humanHandPanel = new JPanel();
    private JPanel computerHandPanel = new JPanel();
    public JPanel deckWestPanel = new JPanel();
    public JPanel battlePanel = new JPanel();
    private JLabel deckCardBack = new JLabel(new ImageIcon(getClass().getResource("card_back.png")));
    public Game()
    {
        super("Durak Game");
        innerPanel.setLayout(new BorderLayout());

        innerPanel.add(humanHandPanel, BorderLayout.SOUTH);
        innerPanel.add(computerHandPanel, BorderLayout.NORTH);
        innerPanel.add(deckWestPanel, BorderLayout.WEST);
        innerPanel.add(battlePanel, BorderLayout.CENTER);

        add(innerPanel);
    }

    public static void main(String [] args){
        System.out.println("Starting");

        Game mainGame = new Game();
        mainGame.setSize(1450, 650);
        mainGame.setVisible(true);
        mainGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        HumanPlayer you = new HumanPlayer();
        ComputerPlayer cpu = new ComputerPlayer();

        mainGame.cd.dealPlayer(you);
        mainGame.cd.dealPlayer(cpu);

        trumpSuit = mainGame.cd.getTrumpSuit();

        mainGame.trumpCard = new JLabel(mainGame.cd.deck.firstElement()
                .getIcon());
        mainGame.deckWestPanel.add(mainGame.trumpCard);
        mainGame.deckWestPanel.add(mainGame.deckCardBack);
        mainGame.deckCardBack.setText("Remaining cards: " + mainGame.cd.deck.size());
        mainGame.deckWestPanel.revalidate();
        mainGame.deckWestPanel.repaint();

        cpu.setTurn(1);
        you.setTurn(2);

        if(cpu.getTurnOrder() == 1)
        {
            cpu.attack(mainGame.turnCards);
            JLabel cardLabel = new JLabel(mainGame.turnCards.firstElement().getIcon());
            mainGame.battlePanel.add(cardLabel);
            mainGame.revalidate();
        }
        else
        {

        }

        mainGame.drawHand(you);
        mainGame.drawHand(cpu);

        //mainGame.turnOrder(you, cpu);
        System.out.println("Human's turn is " + you.getTurnOrder());
        System.out.println("CPU's turn is " + cpu.getTurnOrder());

        // testing with one player and one bot
        /*while((you.hand.size() != 0 && cpu.hand.size() != 0) && mainGame.cd.deck.size() > 0)
        {
            System.out.println("Cards left in the deck: " + mainGame.cd.deck.size());

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
                    runAgain = mainGame.playerAttackBot(you, cpu);
                    if(!runAgain)
                    {
                        break;
                    }

                    runAgain = mainGame.botAttackPlayer(you, cpu);
                }
                else
                {
                    runAgain = mainGame.botAttackPlayer(you, cpu);
                    if(!runAgain)
                    {
                        break;
                    }

                    runAgain = mainGame.playerAttackBot(you, cpu);
                }
            } while(runAgain);

                switch(itr % 2)
                {
                    case 0:
                        playerAttackBot(you, cpu);
                        break;
                    case 1:
                        botAttackPlayer(you, cpu);
                        break;
                }

            while(you.hand.size() < 6 && mainGame.cd.deck.size() > 0)
            {
                mainGame.cd.dealCard(you);
            }

            while(cpu.hand.size() < 6 && mainGame.cd.deck.size() > 0)
            {
                mainGame.cd.dealCard(cpu);
            }
        }

        if(you.hand.size() == 0)
        {
            System.out.println("you are winner");
        }
        else
        {
            System.out.println("cpu is winner");
        }*/
    }

    private void drawHand(Player p){

        if (p instanceof HumanPlayer){

            for (int i = 0; i < p.hand.size(); i++){
                final int index = i;
                humanHandLabels.add(i, new JLabel(p.hand.elementAt(i).getIcon()));
                humanHandLabels.elementAt(i).addMouseListener(
                        new MouseAdapter()
                        {
                            public void mouseClicked(MouseEvent e)
                            {
                                // TODO: index out of bounds, wtf?
                                turnCards.add(p.hand.elementAt(index));
                                battlePanel.add(humanHandLabels.elementAt(index));
                                p.hand.removeElementAt(index);
                                p.hand.trimToSize();
                                humanHandPanel.remove(humanHandLabels.elementAt(index));
                                humanHandLabels.trimToSize();
                                repaint();
                            }
                        });
                humanHandPanel.add(humanHandLabels.elementAt(i));
            }
            humanHandPanel.revalidate();
            humanHandPanel.repaint();
        }
        else if (p instanceof ComputerPlayer){
            for (int i = 0; i < p.hand.size(); i++){
                computerHandLabels.add(i, new JLabel(p.hand.elementAt(i).getIcon()));
                computerHandPanel.add(computerHandLabels.elementAt(i));
            }
            computerHandPanel.revalidate();
            computerHandPanel.repaint();
        }
    }

    private void turnOrder(HumanPlayer human, ComputerPlayer computer)
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

    private boolean playerAttackBot(HumanPlayer humanAttacker, ComputerPlayer cpuDefender)
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

    private boolean botAttackPlayer(HumanPlayer humanDefender, ComputerPlayer cpuAttacker)
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

    private boolean validMoveCheck(Card attackerCard, Card defenderCard)
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