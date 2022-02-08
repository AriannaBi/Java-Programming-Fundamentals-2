
import java.awt.event.MouseEvent;
import javax.swing.JLabel;

/**
 * The abstract class has a constructor that
 * takes a GridDraw and the label of the score, plus
 * some functions for the subclasses.
 * 
 * @author Arianna Bianchi & Razvan Petrica Onciu.
 * @version 2020.
 */
public abstract class Mouse {

    protected final GridDraw computerGrid;
    protected final JLabel scoreLabel;

    /**
     * Main Constructor which takes an existing GridDraw and 
     * an existing label for the score.
     * @param computerGrid as the grid drawn of the computer(second).
     * @param scoreLabel as the label of the score.
     */
    public Mouse(final GridDraw computerGrid, final JLabel scoreLabel) {
        this.computerGrid = computerGrid;  
        this.scoreLabel = scoreLabel;
    }

    /**
     * Check if the cells of the line shooted by the treasure are not
     * shooted, if they are not, increase the score.
     * @param grid as the grid where there is the line shooted.
     * @param player of the player whose score have to increase.
     * @param y as the line of grid that is shooted by the treasure. 
     */
    private void checkScore(final Grid grid, final Player player, final int y) {
        for (int i = 0; i < 10; i++) {
            if (grid.isPlaced(i, y) && !grid.getGridMemory().isMemorized(i, y)) {
                player.increaseScore();
            }
        }
    }

    /**
     * An helper function for pmd errors that sets the score and shot the line
     * of the lucky treasure. 
     * @param player1 that is either myself or computer.
     * @param player2 that is either myself or computer.
     * @param y as the line of grid that is shooted by the treasure. 
     * @param treasureInfo the label where appear info about the treasure.
     */
    protected void setComponent(final Player player1, final Player player2, 
                             final int y, final JLabel treasureInfo) {
        checkScore(player1.getGrid(), player2, y);
        player1.getGrid().getTreasure().luckyShoot(y,'A');
    }

    protected abstract void run(final MouseEvent mv, final Player myself, final Player computer, 
                                final JLabel msgInfo, final JLabel treasureInfo);
                                
    protected abstract void shoot(final Player myself, final Player computer, 
                                  final JLabel msgInfo, final String nameCell);
}
