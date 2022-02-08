import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * Main Graphical User Interface called by the class Main.
 * @author Arianna Bianchi & Razvan Petrica Onciu.
 * @version 2020.
 * 
 */
public class GraphicalUserInterface {
    /** 
     * Main frame for the game.
     */
    private final JFrame myFrame = new JFrame("BlackPearl");

    /** 
     * The two panels that will contains the two grids.
     */
    private final JPanel panelMyself = new JPanel();
    private final JPanel panelComputer = new JPanel();

    /**
     * Main players : myself and the computer.
     */
    private Player myself;
    private Player computer;

    /**
     * Computer Grid draw.
     */
    private GridDraw computerGrid;

    /**
     * Main Buttons of the application.
     */
    private PlaceButton placeButton;
    private PlaceButtonRandom placeButtonRandom;
    private ShootButton shootButton;

    /**
     * Main Mouse object that contains all the action performed when the user clicks.
     */
    private Mouse click;

    /**
     * Main Helper GUI object.
     */
    private final HelperGui helper; 

    /**
     * Main constructor that sets some JComponent(JFrame,
     * JPanel).
     */
    public GraphicalUserInterface() {
        helper = new HelperGui(myFrame);

        myFrame.setPreferredSize(new Dimension(1450,700));
        myFrame.setResizable(false);

        panelMyself.setBackground(new Color(204, 255, 229));
        panelMyself.setPreferredSize(new Dimension(600, 600));
        panelMyself.setLayout(null);

        panelComputer.setBackground(new Color(204, 255, 229));
        panelComputer.setLayout(null);

        // Add the panels on the main Frame
        myFrame.add(panelMyself, BorderLayout.WEST);
        myFrame.add(panelComputer, BorderLayout.CENTER);

        myFrame.setVisible(true);
    }

    /**
     * Set the playground of the application with the grids,
     * and the methods that draw them and the ships.
     */
    private void createPlayGround() {
        // Initialize the main grids and the drawing classes
        final Grid firstGrid = myself.getGrid();
        // mygrid used as a local variable due to the fact that we use it 
        // in just this method.
        final GridDraw myGrid = new GridDraw(firstGrid);
        final ShipDraw myShipDraw = new ShipDraw(firstGrid);

        final Grid secondGrid = computer.getGrid();
        computerGrid = new GridDraw(secondGrid);
        final ShipDraw computerShipDraw = new ShipDraw(secondGrid);

        // Draw the components
        this.drawComponents(myGrid, 50);
        this.drawComponents(computerGrid, 50);
        this.drawComponents(myShipDraw, 0);
        this.drawComponents(computerShipDraw, 0);

        // Add the components on the respective panel.
        panelMyself.add(myGrid);
        myGrid.add(myShipDraw);
        panelComputer.add(computerGrid);
        computerGrid.add(computerShipDraw);
    }

    /**
     * Method that sets the place button by calling the class PlaceButton.
     */
    private void setPlaceButton() {
        placeButton = new PlaceButton(helper.getPanelForButtons());
        placeButton.actionButton(myself, computer, helper.getMsgInfo());
    }

    /**
     * Method that sets the random place button by calling the class PlaceButtonRandom.
     */
    private void setPlaceRandomButton() {
        placeButtonRandom = new PlaceButtonRandom(helper.getPanelForButtons());
        placeButtonRandom.actionButton(myself, computer, helper.getMsgInfo());
    }

    /**
     * Method that sets the shoot button by calling the class ShootButton.
     */
    private void setShootButton() {
        shootButton = new ShootButton(helper.getPanelForButtons(), 
            helper.getWinnerLabel(), panelMyself, panelComputer, 
            click, helper.getTreasureInfo(), helper.getMyScoreLabel(), 
            helper.getComputerScoreLabel());
        shootButton.actionButton(myself, computer, helper.getMsgInfo());
    }

    /**
     * Method that sets the reset button.
     */
    private void setResetButton(final Grid firstGrid, final Grid secondGrid) {
        final JButton resetButton;
        resetButton = new JButton("Reset All");
        resetButton.setPreferredSize(new Dimension(100, 40));
        helper.getPanelForButtons().add(resetButton);
        resetButton.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent ev) {
                    secondGrid.reset();
                    firstGrid.reset();    
                    helper.getMsgInfo().setText("");
                    placeButton.enableButton();
                    placeButtonRandom.enableButton();
                    shootButton.enableButton();
                    myself.resetScore();
                    computer.resetScore();
                    helper.getMyScoreLabel().setText("My score: 0");
                    helper.getComputerScoreLabel().setText("Enemy score: 0");
                    helper.getWinnerLabel().setText("");
                    helper.getTreasureInfo().setText("Go and find the treasure! Good Luck Captain");
                }
            });
    }

    /**
     * This method allow to draw all the grid on the frame, and it is called 
     * when we firstly call the static method mainGui on the Main class.
     * @param myself as the player myself. 
     * @param computer as the player computer.
     */
    public void run(final Player myself, final Player computer) {

        this.myself = myself;
        this.computer = computer;
        // Create the playground
        this.createPlayGround();

        // Draw the coordinates of the cells in the two grids
        this.drawCoordinates();

        // Set the PlaceButton 
        this.setPlaceButton();

        // Set the PlaceButtonRandom
        this.setPlaceRandomButton();

        // Instantiating Instruction button class 
        new InstructionsButton(helper.getPanelInfo()).actionButton(myself, computer, null);

        // Set the shoot button
        this.setShootButton();

        // set reset button
        this.setResetButton(myself.getGrid(), computer.getGrid());

        // add the main mouse event listeners
        this.addMouseClicked();
        this.addMouseMoved(myself.getGrid());

        myFrame.pack();
    }

    /**
     * Add the mouse click listener to the computer grid.
     */
    private void addMouseClicked() {
        // Set the main mouse click object "listener"
        click = new Clicked(computerGrid, helper.getMyScoreLabel());

        computerGrid.addMouseListener(new MouseListener() {
                public void mouseClicked(final MouseEvent mv) {
                    if (!winner()) {
                        click.run(mv, myself, computer, helper.getMsgInfo(), 
                            helper.getTreasureInfo());
                    }
                }

                public void mousePressed(final MouseEvent mv) {
                    // We don't need this method
                }

                public void mouseReleased(final MouseEvent mv) {
                    // We don't need this method
                }

                public void mouseEntered(final MouseEvent mv) {
                    // We don't need this method
                }

                public void mouseExited(final MouseEvent mv) {
                    // We don't need this method
                }
            });
    }

    /**
     * Add the mouse motion listener to the computerGrid.
     * @param firstGrid as the grid of the player.
     */
    private void addMouseMoved(final Grid firstGrid) {
        final Mouse moved = new Moved(computerGrid, helper.getComputerScoreLabel());

        computerGrid.addMouseMotionListener(new MouseMotionListener() {
                public void mouseMoved(final MouseEvent mv) {
                    if (firstGrid.getNavalFleet().getEntireFleet()[5] != null) {
                        placeButton.disableButton();
                        placeButtonRandom.disableButton();
                    }
                    if (!winner()) {
                        moved.run(mv, myself, computer, helper.getMsgInfo(), 
                            helper.getTreasureInfo());
                    }
                }

                public void mouseDragged(final MouseEvent mv) {
                    // We don't need this method
                }
            });

    }

    /**
     * Draw the varius component. It's an helper method.
     * @param component of the JComponent.
     * @param value as the value of the bounds.
     */
    private void drawComponents(final JComponent component, final int value) {
        component.setPreferredSize(new Dimension(600, 600));
        component.setBounds(value, value, 600, 600);
    }

    /**
     * Draw the numbers and the letters on each grid.
     */
    private void drawCoordinates() {
        final String letters = this.drawCoordinatesLetters();

        final JLabel lettersMyself = new JLabel(letters);
        final JLabel lettersComputer = new JLabel(letters);

        this.setLetters(lettersMyself, panelMyself);
        this.setLetters(lettersComputer, panelComputer);

        final String numbers = this.drawCoordinatesNumbers();

        final JTextArea numbersComputer = new JTextArea(numbers);
        this.setNumbers(numbersComputer, panelComputer, 800);

        final JTextArea numbersMyself = new JTextArea(numbers);
        this.setNumbers(numbersMyself, panelMyself, 500);

        // Increase the font of the text area
        final Font font = numbersComputer.getFont();
        final float font_number = font.getSize() + 3;
        numbersComputer.setFont(font.deriveFont(font_number));

        // Increase the font of the text area by using the same font size of the last text area
        numbersMyself.setFont(font.deriveFont(font_number));

    }

    /**
     * Helper method to draw coordinates.
     * Draw the letters.
     * @return the string with the letters
     */
    private String drawCoordinatesLetters() {
        String text = "";
        char c = 'A';
        for (int i = 0; i < 10; i++) {
            text = text + "        " + c;
            c++;
        }
        return text;
    }

    /**
     * Helper method to draw coordinates.
     * Draw the numbers.
     * @return the string with the numbers
     */
    private String drawCoordinatesNumbers() {
        String text = "";
        for (int i = 0; i < 10; i++) {
            text = text + Integer.toString(i) + "\n \n";            
        }
        return text;
    }

    /**
     * Set the letters to the panel.
     * @param label as the label containing the letters
     * @panel as the panel where we want to put the label
     */
    private void setLetters(final JLabel label, final JPanel panel) {
        label.setBounds(33,20,500,15);
        panel.add(label); 
    }

    /**
     * Set the numbers to the text areas.
     * @param textArea as the textarea containing the letters.
     * @param panel as the panel where we want to put the label.
     * @param height as the height of the textarea.
     */
    private void setNumbers(final JTextArea textArea, final JPanel panel, final int height) {
        textArea.setEditable(false);
        textArea.setBackground(new Color(204, 255, 229));
        textArea.setBounds(20, 60, 100, height);
        panel.add(textArea);
    }

    /**
     * Return who is the winner, myself or computer.
     * @return boolean if there is a winner or not.
     */
    private boolean winner() {
        if (myself.hasLost(0)) {
            helper.getWinnerLabel().setText("The computer wins!");
            panelComputer.add(helper.getWinnerLabel());
            helper.getMsgInfo().setText("");
            helper.getTreasureInfo().setText("");
        } else if (computer.hasLost(0)) {
            helper.getWinnerLabel().setText("Congratulations, you WIN!");
            panelMyself.add(helper.getWinnerLabel());
            helper.getMsgInfo().setText("");
            helper.getTreasureInfo().setText("");
            shootButton.disableButton();
        } else {
            return false;
        }
        return true;
    }
}
