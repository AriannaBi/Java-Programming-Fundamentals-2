import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The class has method which allow to place randomly a ship 
 * on the grid.
 *
 * @author Arianna Bianchi & Razvan Petrica Onciu.
 * @version 2020.
 */
public class PlaceButtonRandom extends Button {
    /**
     * Main constructor that invoke the Button constructor with 
     * a panel and a string, sets the dimension and add 
     * the button to the panel. 
     * @param panelForButtons as the panel of the buttons on the main frame.
     */
    public PlaceButtonRandom(final JPanel panelForButtons) {
        super(panelForButtons, "Place ships randomly");
        button.setPreferredSize(new Dimension(200, 40));
        panelForButtons.add(button);
    }

    /**
     * Given two player (myself and computer), and the label msgInfo,
     * place the ship randomly on the grid and show the string (corresponding on
     * what we are doing on the grid) in the JLabel msgInfo. We can
     * place randomly or given a ship as input.
     * @param myself as the player myself. 
     * @param computer as the player computer. 
     * @param msgInfo as the JLabel on which appear what you are doing.
     */
    public void actionButton(final Player myself, final Player computer, final JLabel msgInfo) {

        button.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent ev) {
                    if (myself.hasLost(0) || computer.hasLost(0)) {
                        msgInfo.setText("");
                    } else {
                        final String placeS = myself.getGrid().getUtilities().placeShipRandom();
                        msgInfo.setText(placeS);
                    }
                }
            });
    }
}
