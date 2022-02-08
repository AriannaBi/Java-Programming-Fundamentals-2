import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Shoot one cell of the ship by clicking on the button.
 * 
 * @author Arianna Bianchi & Razvan Petrica Onciu.
 * @version 2020.
 */
public class ShootButton extends Button {

    private final JLabel winner;
    private final JPanel panelMyself;
    private final JPanel panelComputer;
    private final Mouse clicked;
    private final JLabel treasureInfo;
    private final JLabel scoreLabel;
    private final JLabel computerScoreLabel;
    
    public static String coordinate = "Choose the coordinate of the ship";

    /**
     * Main Constructor that invoke the Button constructor with 
     * a panel and the string shoot, set the size of the button, 
     * takes an existings winner panel for the button and the 
     * panel of the computer. 
     * @param panelForButtons as the panel of the buttons.
     * @param winner as the label of who wins.
     * @param panelMyself the panel on which there is the grid myself.
     * @param panelComputer the panel on which there is the grid computer.
     * @param clicked as just an helper to call the function setComponent().
     * @param treasureInfo as the label with the treasure infos.
     * @param scoreLabel as the label for the score of the myself player.
     * @param computerScoreLabel as the label for the score of the computer.
     */
    public ShootButton(final JPanel panelForButtons, final JLabel winner, 
        final JPanel panelMyself, final JPanel panelComputer, final Mouse clicked,
        final JLabel treasureInfo, final JLabel scoreLabel,final JLabel computerScoreLabel) {
        super(panelForButtons, "Shoot");
        button.setPreferredSize(new Dimension(100, 40));
        panelForButtons.add(button);
        this.winner = winner;
        this.panelMyself = panelMyself;
        this.panelComputer = panelComputer;
        this.clicked = clicked;
        this.treasureInfo = treasureInfo;
        this.scoreLabel = scoreLabel;
        this.computerScoreLabel = computerScoreLabel;
    }

    /**
     * Given two player (myself and computer), and the label msgInfo,
     * check if we can do the shoot, shoot the ship on the grid and 
     * show the string (corresponding on what we are doing on the grid) 
     * in the JLabel msgInfo.
     * @param myself as the player myself. 
     * @param computer as the player computer. 
     * @param msgInfo as the JLabel on which appear what you are doing.
     */
    public void actionButton(final Player myself, final Player computer, final JLabel msgInfo ) { 
        button.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent ev) {
                    if (myself.hasLost(0)) {
                        winner.setText("The computer wins!");
                        panelComputer.add(winner);
                    } else if (computer.hasLost(0)) {
                        winner.setText("Congratulations, you WIN!");
                        panelMyself.add(winner);
                    } else {
                        if (myself.whoseRound().equals("myself") 
                            && myself.getGrid().getNavalFleet().getEntireFleet()[5] != null) {
                            final String text = JOptionPane.showInputDialog(coordinate);
                            if (text == null 
                                || !myself.getGrid().getUtilities().isValidCellName(text)) {
                                msgInfo.setText("ERROR: Invalid Coordinate!");
                                return;
                            } 
                            shoot(myself, computer, msgInfo, text);
                        } else if (myself.getGrid().getNavalFleet().getEntireFleet()[5] == null) {
                            msgInfo.setText("You have to place all the 6 ships first");
                        } else {
                            msgInfo.setText("That's not your turn");
                        }
                    }
                }
            });
    }

    /**
     * Do the luckyShot if we have the treasure, otherwise
     * shoot the cell. 
     * @param myself as the player myself. 
     * @param computer as the player computer. 
     * @param msgInfo as the JLabel on which appear what you are doing.
     * @param text as the cellName.
     */
    public void shoot(final Player myself, final Player computer, 
        final JLabel msgInfo, final String text) {
        final int x = myself.getGrid().getUtilities().cellNameConvert(text)[0][0];
        final int y = myself.getGrid().getUtilities().cellNameConvert(text)[0][1];
        if (computer.getGrid().getTreasure().isPlacedTreasure(x, y)
            && !computer.getMemoryGrid().isMemorized(x, y))  {
            if (computer.getGrid().getTreasure().getLucky() == 0) {
                clicked.setComponent(computer, myself, y, treasureInfo);
                treasureInfo.setText("You find the luky treasure! Good job");
                scoreLabel.setText("My score: " + myself.getScore());           
            } else {
                clicked.setComponent(myself, computer, y, treasureInfo);
                treasureInfo.setText("You have been cursed! Don't give up");
                computerScoreLabel.setText("Enemy score: " + computer.getScore());
            }
        } else {
            helperShoot(myself, computer, msgInfo, text);
        }
        myself.increaseRound();
    }

    /**
     * Helper for the shooting method.
     * @param myself as the player myself. 
     * @param computer as the player computer. 
     * @param msgInfo as the JLabel on which appear what you are doing.
     * @param text as the cellName.
     */
    private void helperShoot(final Player myself, final Player computer,
        final JLabel msgInfo, final String text) {
        final String score = computer.getGrid().shootShip(text);
        if ("There is a ship here".equals(score)) {
            myself.increaseScore();
            scoreLabel.setText("My score: " + myself.getScore());   
        }
        msgInfo.setText(score);
    }
}
