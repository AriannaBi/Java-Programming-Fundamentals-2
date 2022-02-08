import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * The class has method which allow to place a ship on the
 * grid and two helper functions.
 *
 * @author Arianna Bianchi & Razvan Petrica Onciu.
 * @version 2020.
 */
public class PlaceButton extends Button {
    
    /**
     * Due to pmd complains the constants are in lower case.
     */
    public static String dimension = "Choose the type of the ship: small, medium or large";
    public static String coordinate = "Choose the coordinate of the ship";
    public static String orientation = "Choose the orientation of the ship: up, right, down, left";

    /**
     * Main constructor that inkove the Button constructor
     * and set the dimension.
     * @param panelForButtons as the panel of the buttons.
     */
    public PlaceButton(final JPanel panelForButtons) {
        super(panelForButtons, "Place");
        button.setPreferredSize(new Dimension(100, 40));
    }

    /**
     * Given two player (myself and computer), and the label msgInfo,
     * place the ship on the grid and show the string (corresponding on
     * what we are doing on the grid) in the JLabel msgInfo.
     * @param myself as the player myself. 
     * @param computer as the player computer. 
     * @param msgInfo as the JLabel on which appear what you are doing.
     */
    public void actionButton(final Player myself, final Player computer, final JLabel msgInfo) {

        panelForButtons.add(button);

        button.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent ev) {
                    if (myself.hasLost(0) || computer.hasLost(0)) {
                        msgInfo.setText("");
                    } else if (myself.getGrid().getNavalFleet().getEntireFleet()[5] != null) {
                        button.setEnabled(false);
                    } else if (myself.whoseRound().equals("myself")) {
                        final String textDimension = JOptionPane.showInputDialog(dimension);
                        if (textDimension == null || textDimension.length() == 0) {
                            return;
                        }
                        final int dimension = checkInput(textDimension);
                        if (dimension == -1) {
                            msgInfo.setText("ERROR: Invalid ship type!");
                            return;
                        }

                        final String textCoordinate = JOptionPane.showInputDialog(coordinate);
                        if (textCoordinate == null || textCoordinate.length() == 0) {
                            return;
                        }

                        final String textOrientation = JOptionPane.showInputDialog(orientation);
                        if (textOrientation == null || textOrientation.length() == 0) {
                            return;
                        }
                        final int orientation = checkOrientation(textOrientation, msgInfo);
                        if (orientation == -1) {
                            return;
                        }

                        final String placeS = myself.getGrid().placeShip(new Ship(dimension),
                                                                         textCoordinate, 
                                                                         orientation);
                        msgInfo.setText(placeS);

                    } else {
                        msgInfo.setText("That's not your turn");
                    }
                }
            });
    }

    /**
     * Given a dimension in string, return the corrisponding dimension in number.
     * @param textDimension as the input text of the dimension.
     * @return the corrisponding dimension in number.
     */
    private int checkInput(final String textDimension) {
        if ("small".equalsIgnoreCase(textDimension)) {
            return 2;
        } else if ("medium".equalsIgnoreCase(textDimension)) {
            return 3;
        } else if ("large".equalsIgnoreCase(textDimension)) {
            return 4;
        } else {           
            return -1;
        }
    }

    /**
     * Given an orientation in string, return the corrisponding orientation in number
     * and set the text of the label msgInfo as an error.
     * @param textOrientation as the input text of the dimension.
     * @param msgInfo as the JLabel on which appear what you are doing.
     * @return the corrisponding dimension in number.
     */
    private int checkOrientation(final String textOrientation, final JLabel msgInfo) {
        final Map<String, Integer> orientations = new HashMap<>();
        orientations.put("up", 0);
        orientations.put("right", 1);
        orientations.put("down", 2);
        orientations.put("left", 3);
        if (orientations.get(textOrientation) != null) {
            return orientations.get(textOrientation.toLowerCase(Locale.ENGLISH));
        } else {
            msgInfo.setText("ERROR: Invalid orientation!");
            return -1;
        }
        
        if ("up".equalsIgnoreCase(textOrientation) 
            || "right".equalsIgnoreCase(textOrientation) 
            || "down".equalsIgnoreCase(textOrientation) 
            || "left".equalsIgnoreCase(textOrientation)) {
            // Needed Locale.ENGLISH for PMD complain about the language used
            return orientations.get(textOrientation.toLowerCase(Locale.ENGLISH)); 
        } else {
            msgInfo.setText("ERROR: Invalid orientation!");
            return -1;
        }
    }
}
