/// !!!!!!!!!!!!!!!!!have problem with uploading images for jack_of_diamonds, jack_of_hearts and king_of_diamonds

// TODO (low priority): clean up code a little bit
//  check up on deprecated/unused functions from console version
//  probably do this closer to submission time
//  also consider replacing switches with lambda expressions and other Java 8 features
//  and/or certain event handlers

import javax.swing.*;
import java.awt.*;
import java.util.Vector;
import java.awt.event.*;

public class Game extends JFrame
{

    private int attackCardPosition = 0;
    private int defenceCardPosition = 0;

    //private boolean isHumanAttack;

    private CardDeck cd = new CardDeck();
    static Card.Suit trumpSuit;

    private JLabel trumpCard;

    private Vector<Card> turnCards = new Vector<>();
    private Vector<JLabel> humanHandLabels = new Vector<>();
    private Vector<JLabel> computerHandLabels = new Vector<>();
    private Vector<JLabel> attackCards = new Vector<>(6);
    private Vector<JLabel> defenceCards = new Vector<>(6);

    private JLayeredPane remainingDeckPanel = new JLayeredPane();
    private JLayeredPane battlePanel = new JLayeredPane();
    private JPanel computerHandPanel = new JPanel();
    private JPanel humanHandPanel = new JPanel();

    private JLabel deckCardBack = new JLabel(new ImageIcon("./cards/default/card_back.png"));
    private JLabel remainingCards = new JLabel();
    private GridBagConstraints c;

    public Game()
    {
        super("Durak Game");
        setSize(900, 650);
        setLayout(new GridBagLayout());

        // TODO (low priority): implement changing of card faces/backs
        //  maybe make a new data member for the Card class of type String
        //  to hold the path to their images and do the same for the card back
        //  map this to some sort of option menu in a dropdown then boom done

        // remaining deck panel
        c = new GridBagConstraints();
        c.weightx = 7;
        //c.weighty = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 3;
        c.fill = GridBagConstraints.BOTH;
        this.getContentPane().add(remainingDeckPanel, c);

        // computer hand panel
        computerHandPanel.setBackground(Color.BLACK);
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 2;
        //c.weighty = 2;
        this.add(computerHandPanel, c);

        // battle panel
        battlePanel.setOpaque(true);
        battlePanel.setBackground(Color.CYAN);
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weighty = 10;
        this.getContentPane().add(battlePanel, c);

        // human hand panel
        humanHandPanel.setBackground(Color.PINK);
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weighty = 0.5;

        this.add(humanHandPanel, c);

        // can change this from JButton to whatever later
        JButton passButton = new JButton("Pass");
        passButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("pass");
                playerPass();
            }
        });

        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.insets = (new Insets(45, 50, 45, 50));
        this.add(passButton, c);
    }

    private void playerPass()
    {
        // TODO: add passing functionality for the player
        //  can either pass as an attacker to end the round
        //  or take the cards as a defender to become the attacker
        //  maybe just invert booleans for players being attacker/defender
        //  and call addExtra? might have to think about implementation
    }

    private void refillHands()
    {
        // TODO: add refill for when hands are over
        //  probably loop while hand.size < 7, or while i < 6, either way works
        //  will use similar logic to the drawHands function to add images to the cards
        //  and draw new cards. do they need to be drawn in a specific order?
    }

    private void setUpBattlePanel(Game game){
        int width, height;
        height = battlePanel.getHeight() / 11;
        width = battlePanel.getWidth() + 40;

        for (int i = 0; i < 6; i ++){
            attackCards.add(new JLabel());
            attackCards.elementAt(i).setOpaque(true);
            attackCards.elementAt(i).setText(String.valueOf(i + 1));
            attackCards.elementAt(i).setBackground(Color.RED);

            if (i == 0) {
                attackCards.elementAt(i).setBounds(width, height, 100, 145);
            } else if (i == 1) {
                attackCards.elementAt(i).setBounds(width + 105, height, 100, 145);
            } else if (i == 2) {
                attackCards.elementAt(i).setBounds(width - 105, height, 100, 145);
            } else if (i == 3) {
                attackCards.elementAt(i).setBounds(width + 210, height, 100, 145);
            } else if (i == 4) {
                attackCards.elementAt(i).setBounds(width - 210, height, 100, 145);
            } else if (i == 5) {
                attackCards.elementAt(i).setBounds(width + 315, height, 100, 145);
            }

            battlePanel.add(attackCards.elementAt(i), JLayeredPane.DEFAULT_LAYER);
        }

        height = height + 50;

        for (int i = 0; i < 6; i ++){
            defenceCards.add(new JLabel());
            defenceCards.elementAt(i).setOpaque(true);
            defenceCards.elementAt(i).setText(String.valueOf(i + 1));
            defenceCards.elementAt(i).setBackground(Color.BLUE);

            if (i == 0) {
                defenceCards.elementAt(i).setBounds(width, height, 100, 145);
            } else if (i == 1) {
                defenceCards.elementAt(i).setBounds(width + 105, height, 100, 145);
            } else if (i == 2) {
                defenceCards.elementAt(i).setBounds(width - 105, height, 100, 145);
            } else if (i == 3) {
                defenceCards.elementAt(i).setBounds(width + 210, height, 100, 145);
            } else if (i == 4) {
                defenceCards.elementAt(i).setBounds(width - 210, height, 100, 145);
            } else if (i == 5) {
                defenceCards.elementAt(i).setBounds(width + 315, height, 100, 145);
            }

            battlePanel.add(defenceCards.elementAt(i), JLayeredPane.POPUP_LAYER);
        }

        /*attackCards.elementAt(0).setText("1");
        attackCards.elementAt(0).setBackground(Color.RED);
        attackCards.elementAt(0).setBounds(width, height, 100, 145);
        battlePanel.add(attackCards.elementAt(0), JLayeredPane.DEFAULT_LAYER);

        attackCards.elementAt(1).setText("2");
        attackCards.elementAt(1).setBackground(Color.red);
        attackCards.elementAt(1).setBounds(width + 105, height, 100, 145);
        battlePanel.add(attackCards.elementAt(1), JLayeredPane.DEFAULT_LAYER);

        attackCards.elementAt(2).setText("3");
        attackCards.elementAt(2).setBackground(Color.red);
        attackCards.elementAt(2).setBounds(width - 105, height, 100, 145);
        battlePanel.add(attackCards.elementAt(2), JLayeredPane.DEFAULT_LAYER);

        attackCards.elementAt(3).setText("4");
        attackCards.elementAt(3).setBackground(Color.red);
        attackCards.elementAt(3).setBounds(width + 210, height, 100, 145);
        battlePanel.add(attackCards.elementAt(3), JLayeredPane.DEFAULT_LAYER);

        attackCards.elementAt(4).setText("5");
        attackCards.elementAt(4).setBackground(Color.red);
        attackCards.elementAt(4).setBounds(width - 210, height, 100, 145);
        battlePanel.add(attackCards.elementAt(4), JLayeredPane.DEFAULT_LAYER);

        attackCards.elementAt(5).setOpaque(true);
        attackCards.elementAt(5).setText("6");
        attackCards.elementAt(5).setBackground(Color.red);
        attackCards.elementAt(5).setBounds(width + 315, height, 100, 145);
        battlePanel.add(attackCards.elementAt(5), JLayeredPane.DEFAULT_LAYER);

        defenceCards.elementAt(0).setText("1");
        defenceCards.elementAt(0).setBackground(Color.BLUE);
        defenceCards.elementAt(0).setBounds(width, height, 100, 145);
        battlePanel.add(defenceCards.elementAt(0), JLayeredPane.POPUP_LAYER);

        defenceCards.elementAt(1).setBackground(Color.BLUE);
        defenceCards.elementAt(1).setText("2");
        defenceCards.elementAt(1).setBounds(width + 105, height, 100, 145);
        battlePanel.add(defenceCards.elementAt(1), JLayeredPane.POPUP_LAYER);

        defenceCards.elementAt(2).setBackground(Color.BLUE);
        defenceCards.elementAt(2).setText("3");
        defenceCards.elementAt(2).setBounds(width - 105, height, 100, 145);
        battlePanel.add(defenceCards.elementAt(2), JLayeredPane.POPUP_LAYER);

        defenceCards.elementAt(3).setText("4");
        defenceCards.elementAt(3).setBackground(Color.BLUE);
        defenceCards.elementAt(3).setBounds(width + 210, height, 100, 145);
        battlePanel.add(defenceCards.elementAt(3), JLayeredPane.POPUP_LAYER);

        defenceCards.elementAt(4).setText("5");
        defenceCards.elementAt(4).setBackground(Color.BLUE);
        defenceCards.elementAt(4).setBounds(width - 210, height, 100, 145);
        battlePanel.add(defenceCards.elementAt(4), JLayeredPane.POPUP_LAYER);

        defenceCards.elementAt(5).setText("6");
        defenceCards.elementAt(5).setBackground(Color.BLUE);
        defenceCards.elementAt(5).setBounds(width + 315, height, 100, 145);
        battlePanel.add(defenceCards.elementAt(5), JLayeredPane.POPUP_LAYER);*/

        battlePanel.revalidate();
        battlePanel.repaint();
    }

    private void drawHand(Player p){
        if (p instanceof HumanPlayer){
            for (int i = 0; i < p.hand.size(); i++){
                final int index = i;
                humanHandLabels.add(i, new JLabel(p.hand.elementAt(i).getIcon()));
                humanHandLabels.elementAt(i).addMouseListener(
                        new MouseAdapter()
                        {
                            private final Card clickedCard = p.hand.elementAt(index);
                            private final JLabel clickedLabel = humanHandLabels.elementAt(index);
                            public void mouseClicked(MouseEvent e)
                            {
                                if (validMoveCheck(clickedCard, turnCards)){
                                    turnCards.add(clickedCard);
                                    if (p.isAttack){
                                        attackCards.elementAt(attackCardPosition).setIcon(turnCards.lastElement().getIcon());
                                        attackCardPosition ++;
                                    }
                                    else {
                                        defenceCards.elementAt(defenceCardPosition).setIcon(turnCards.lastElement().getIcon());
                                        defenceCardPosition ++;
                                    }
                                    p.hand.remove(clickedCard);

                                    //p.hand.trimToSize();
                                    humanHandLabels.remove(clickedLabel);
                                    humanHandPanel.remove(clickedLabel);
                                    //humanHandLabels.trimToSize();
                                    repaint();
                                }
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

    public void setUpGameBoard(){
        trumpCard = new JLabel(cd.deck.firstElement().getIcon());
        trumpCard.setOpaque(true);
        trumpCard.setBounds(30, 170, 
                            trumpCard.getIcon().getIconWidth(), 
                            trumpCard.getIcon().getIconHeight());
        remainingDeckPanel.add(trumpCard,  JLayeredPane.DEFAULT_LAYER);

        deckCardBack.setOpaque(true);
        deckCardBack.setBounds(30, 250,
                               deckCardBack.getIcon().getIconWidth(),
                               deckCardBack.getIcon().getIconHeight());     
        remainingDeckPanel.add(deckCardBack, JLayeredPane.PALETTE_LAYER);

        remainingCards.setText("<html>Remaining cards: " + cd.deck.size() + "<html>");
        remainingCards.setBounds(45, 370, 100, 100);
        remainingDeckPanel.add(remainingCards, JLayeredPane.DEFAULT_LAYER);

        remainingDeckPanel.revalidate();
        remainingDeckPanel.repaint();
    }

    public static void main(String [] args){
        //System.out.println("Starting");

        Game mainGame = new Game();
        //mainGame.setSize(1450, 650);
        mainGame.setVisible(true);
        mainGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        HumanPlayer you = new HumanPlayer();
        ComputerPlayer cpu = new ComputerPlayer();

        mainGame.cd.dealPlayer(you);
        mainGame.cd.dealPlayer(cpu);
        mainGame.setUpBattlePanel(mainGame);

        trumpSuit = mainGame.cd.getTrumpSuit();
        mainGame.setUpGameBoard();

        mainGame.drawHand(you);
        mainGame.drawHand(cpu);

        mainGame.turnOrder(you, cpu);

        // TODO: set up main game loop
        //  computer responding to moves: done
        //  refilling hands: WIP, probably can do it quickly
        //  exchanging attacker/defender: idk, let's figure it out lol

        /*while(!you.hand.isEmpty() || !cpu.hand.isEmpty())
        {
            mainGame.computerMove(cpu);
            mainGame.refillHands();
            //change turn
        }*/

        // TODO: final step?
        //  add winning condition and maybe celebration? idk
        //  we can spend more time on it later
    }

    private void computerMove(ComputerPlayer cpu){
        boolean pass = false;

        if(cpu.getIsAttack())
        {
            cpu.attack(turnCards);
            JLabel cardLabel = new JLabel(turnCards.firstElement().getIcon());
            attackCards.elementAt(attackCardPosition).setIcon(cardLabel.getIcon());
            attackCardPosition ++;
            this.revalidate();
        }
        else
        {
            if (turnCards.size() > 0){
                if (cpu.defend(turnCards.lastElement(), turnCards)){
                    defenceCards.elementAt(defenceCardPosition)
                        .setIcon(turnCards.lastElement().getIcon());
                    defenceCardPosition++;
                    this.revalidate();
                }
                else {
                    pass = true;
                }
            }
        }

        //while(!cpu.hand.isEmpty() || !pass || !human.hand.isEmpty())
        //{
          //  computerMove(cpu, human);
        //}
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

        if(humanBestCard.getRank() < cpuBestCard.getRank())
        {
            human.setTurn(1);
            human.setIsAttack(true);
            computer.setTurn(2);
            computer.setIsAttack(false);

        }
        else
        {
            computer.setTurn(1);
            computer.setIsAttack(true);
            human.setTurn(2);
            human.setIsAttack(false);
        }
    }

    // following functions are deprecated, can likely remove

/*    private boolean playerAttackBot(HumanPlayer humanAttacker, ComputerPlayer cpuDefender)
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
        while(!validMoveCheck(defenderChoice, turnCards))
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
    }*/

    // end deprecated functions

    private boolean validMoveCheck(Card cardToPlay, Vector<Card> turnCards)
    {
        if(turnCards.isEmpty())
        {
            return true;
        }

        Card lastPlayedCard = turnCards.lastElement();

        // we're defending
        if(turnCards.size() % 2 == 1)
        {
            if(lastPlayedCard.getCardSuit() == cardToPlay.getCardSuit() 
                    && lastPlayedCard.getRank() < cardToPlay.getRank())
            {
                return true;
            }
            else if(lastPlayedCard.getCardSuit() != trumpSuit
                    && cardToPlay.getCardSuit() == trumpSuit)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        // we're attacking
        else
        {
            for(Card turnCard : turnCards)
            {
                if(turnCard.getRank() == cardToPlay.getRank())
                {
                    return true;
                }
            }
            return false;
        }
    }
}