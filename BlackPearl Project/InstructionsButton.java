import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * Set the text, the dimension, the graphical settings of 
 * the button and of the frame that appears on the button
 * event.
 * 
 * @author Arianna Bianchi & Razvan Petrica Onciu.
 * @version 2020.
 */

public class InstructionsButton extends Button {

    private final String text;

    /**
     * Main constructor that inkove the Button constructor
     * with as parameters the panel and the string of the button.
     * set the background, the bounds and add the button to the panel.
     * @param panelInfo as the panel on the right frame with info.
     */
    public InstructionsButton(final JPanel panelInfo) {
        super(panelInfo, "Instructions");
        button.setOpaque(true);
        button.setBackground(Color.CYAN);
        button.setBounds(86, 550, 200, 40);
        panelInfo.add(button);
        text = getText();
    }

    /**
     * Sets all the JComponent (Jframe, JTextArea and JLabel).
     * @param myself as the player myself. 
     * @param computer as the player computer. 
     * @param msgInfo as the JLabel on which appear what you are doing.
     */
    public void actionButton(final Player myself, final Player computer, final JLabel msgInfo) {
        // The param msgInfo is not used here.
        button.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent ev) {
                    final JFrame instructionsFrame = new JFrame("Instructions");
                    instructionsFrame.setPreferredSize(new Dimension(400,400));
                    instructionsFrame.setResizable(false);
                    instructionsFrame.setLayout(null);
                    final JTextArea instructions_area = new JTextArea(text);
                    instructions_area.setEditable(false);
                    instructions_area.setBounds(10, 10, 380, 380);
                    instructionsFrame.add(instructions_area);
                    instructionsFrame.setVisible(true);
                    instructionsFrame.pack();
                }
            });
    }

    /**
     * Helper method that contains the entire instruction text.
     * @return as a string the entire instructions
     */
    private String getText() {
        return "Welcome to the BlackPearl Captain!, choose your ships  \n" 
            + "and place them on the grid to start the battle. \n" 
            + "You can choose between three different ships: \n" 
            + "The small ship : thank's to its size your enemy \n" 
            + "will have a hard time spotting it. \n" 
            + "The medium ship : a more compact structure, \n" 
            + "but still versatile . \n" 
            + "The large ship : your enemy will loose precious time trying \n" 
            + "to sink this ship. \n" 
            + "You have to place 6 ships Captain and choose your \n" 
            + "strategy carefully because the lost treasure of \n" 
            + "the BlackPearl is waiting for you to find it. \n" 
            + "But be careful, the BlackPearl treasure is cursed! \n" 
            + "We don't know if the curse will hit you or your enemy, \n" 
            + "but we know that whoever is that unluky man, \n" 
            + "the BlackPearl will attack an entire row of its grid, \n" 
            + "and sink the ships on it. \n" 
            + "Place the 6 ships, and then start shooting at \n" 
            + "the enemy's grid, if you sink all its ships then you win. \n" 
            + "Remeber Captain, use the best stategy to win,\n" 
            + "and may the BlackPearl be with you. \n" 
            + "Have Fun!";
    }

}
