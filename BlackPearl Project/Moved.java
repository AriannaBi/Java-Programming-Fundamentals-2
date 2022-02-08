import java.awt.event.MouseEvent;

import java.util.Random;

import javax.swing.JLabel;

/**
 * Executes the method run during the move mouse event, 
 * so when we move the mouse after the click on the grid.
 * 
 * @author Arianna Bianchi & Razvan Petrica Onciu.
 * @version 2020.
 */
public class Moved extends Mouse {

    /**
     * Main Constructor that calls the superclass' constructor
     * and takes the existing grid of the computer and score's label.
     * @param computerGrid as the grid drawn of the computer(second).
     * @param computerLabel as the label of the computer.
     */
    public Moved(final GridDraw computerGrid, final JLabel computerLabel) {
        super(computerGrid, computerLabel);
    }

    /**
     * When we move after clicking on a cell we have to set the relatives act
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
        if ("computer".equals(myself.whoseRound())) {
            // Possible bug when moving the cursor too fast.
            final int x = (int)Math.ceil(mv.getX() / 40);
            int y = (int)Math.ceil(mv.getY() / 40);
            if (computer.getGrid().getTreasure().isPlacedTreasure(x, y) 
                && computer.getGrid().getTreasure().getLucky() == 1) {
                final Random r = new Random();
                y = r.nextInt(10);
                super.setComponent(myself, computer, y, treasureInfo);
                treasureInfo.setText("You have been cursed! Don't give up");
                scoreLabel.setText("Enemy score: " + computer.getScore());
                myself.increaseRound();
            } else {
                this.shoot(myself, computer, msgInfo, null);
            }
            msgInfo.setText("It's your turn");
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
        // we don't use the parameter nameCell in this case
        final String shoot = myself.getGrid().getUtilities().shootShipRandom();
        if ("There is a ship here".equals(shoot)) {
            computer.increaseScore();
        }
        scoreLabel.setText("Enemy score: " + computer.getScore());
        myself.increaseRound();
    }
}
