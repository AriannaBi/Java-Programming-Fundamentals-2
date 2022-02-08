import java.awt.event.MouseEvent;

import javax.swing.JLabel;

/**
 * Executes the method run during the click event, 
 * so when we click on the grid.
 * 
 * @author Arianna Bianchi & Razvan Petrica Onciu.
 * @version 2020.
 */

public class Clicked extends Mouse {

    /**
     * Main Constructor that calls the superclass' constructor
     * and takes the existing grid of the computer and score's label.
     * @param computerGrid as the grid drawn of the computer(second).
     * @param myScoreLabel as the label of the score.
     */
    public Clicked(final GridDraw computerGrid, final JLabel myScoreLabel) {
        super(computerGrid, myScoreLabel);
    }

    /**
     * When we click on a cell we have to set the relatives act
     * so it check if we can shoot the cell and set the label msgInfo
     * with a message of what you have done, otherwise
     * set the label msgInfo with a message of error.
     * @param mv as the mouse event.
     * @param myself as the player myself. 
     * @param computer as the player computer.
     * @param msgInfo as the JLabel on which appear what you are doing.
     * @param treasureInfo the label where appear info about the treasure.
     */
    public void run(final MouseEvent mv, final Player myself, final Player computer, 
        final JLabel msgInfo, final JLabel treasureInfo) {
        //if it's my turn and on sx grid there are 6 ship placed 
        if ("myself".equals(myself.whoseRound()) 
            && myself.getGrid().getNavalFleet().getEntireFleet()[5] != null) { 
            final int x = (int)Math.ceil(mv.getX() / 40);
            final int y = (int)Math.ceil(mv.getY() / 40);
            final String nameCell =  computer.getGrid().getUtilities().coordinatesConvert(x, y);
            if (computer.getGrid().getTreasure().isPlacedTreasure(x, y) 
                && computer.getGrid().getTreasure().getLucky() == 0 
                && !computer.getMemoryGrid().isMemorized(x, y)) {
                super.setComponent(computer, myself, y, treasureInfo);
                treasureInfo.setText("You find the luky treasure! Good job");
                scoreLabel.setText("My score: " + myself.getScore());
                myself.increaseRound();
            } else {
                this.shoot(myself, computer, msgInfo, nameCell);
            }
        } else if (myself.whoseRound().equals("myself")) {
            msgInfo.setText("You have to place all the 6 ships first");
        } else {
            msgInfo.setText("That's not your turn");
        }
    }

    /**
     * Do the shoot of a cell ship and increase the relatives score.
     * @param myself as the player myself. 
     * @param computer as the player computer.
     * @param msgInfo as the JLabel on which appear what you are doing.
     * @param nameCell as the name String of the coordinate.
     */
    public void shoot(final Player myself, final Player computer, 
                      final JLabel msgInfo, final String nameCell) {
        final String shooting = computer.getGrid().shootShip(nameCell);
        msgInfo.setText(shooting);
        if ("There is a ship here".equals(shooting)) {
            myself.increaseScore();
            scoreLabel.setText("My score: " + myself.getScore());
            myself.increaseRound();
        }
        if ("You missed the ship".equals(shooting)) {
            myself.increaseRound();
        }
    }
}