import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



/**
 * HelperGui creates the main frame, the labels and the panel
 * of the main interface GUI.
 * 
 * @author (il tuo nome) 
 * @version (un numero di versione o una data)
 */
public class HelperGui {
    /** 
     * Main frame for the game.
     */
    private final JFrame myFrame;
    
    /**
     * Main Panel for the application infos.
     */
    private JPanel panelInfo;
    
    /**
     * Panel for the buttons at the bottom.
     */
    private final JPanel panelForButtons;

    /**
     * Main Label for the output messages to the user.
     */
    private JLabel msgInfo;
     
    /**
     * Main Label for the info about the treasure.
     */
    private JLabel treasureInfo;

    /**
     * Main label that shows who is the winner.
     */
    private JLabel winner;
    
    /**
     * Labels for the score of the player and the computer.
     */
    private JLabel myScoreLabel;
    private JLabel computerScoreLabel;
    
    /**
     * Main constructor of the helper that sets the main panels
     * and labels of the applications.
     * @param myFrame as the main frame of this application
     */
    public HelperGui(final JFrame myFrame) {
        this.myFrame = myFrame;
        // Set the main info panel
        this.setInfoPanel();
        
        // Set winner laber
        this.setWinnerLabel();
        
        // Construct the main label for the messages
        this.setMessageLabel();
        
        this.panelForButtons = new JPanel();
        panelForButtons.setBackground(Color.BLACK);
        panelForButtons.setPreferredSize(new Dimension(1450, 50));
        
        // Construct the main label for the treasure info
        this.setTreasureInfo();
        
        // Set the labels for the score 
        this.setScoreLabel();
        

    }
    
    /**
     * Set panel for the info about the application.
     */
    public void setInfoPanel() {
        panelInfo = new JPanel();
        panelInfo.setLayout(null);
        myFrame.add(panelInfo, BorderLayout.EAST);
        // Add the legenda on the info panel
        new Legend(panelInfo).setLegendAreaText();
        panelInfo.setBackground(Color.WHITE);   
        panelInfo.setPreferredSize(new Dimension(350, 800));
    }

    /**
     * Set the msg Info label.
     */
    public void setMessageLabel() {
        msgInfo = new JLabel("");
        msgInfo.setHorizontalAlignment(JLabel.CENTER);
        msgInfo.setBounds(0, 180, 350, 50);
        msgInfo.setFont(new Font("Dialog", Font.ITALIC, 15));
        msgInfo.setLayout(null);
        panelInfo.add(msgInfo);
    }

    /**
     * Set the label tresure info.
     */
    public void setTreasureInfo() {
        treasureInfo = new JLabel("Go and find the treasure! Good Luck Captain");
        treasureInfo.setBounds(0, 250, 350, 50);
        treasureInfo.setHorizontalAlignment(JLabel.CENTER);
        treasureInfo.setLayout(null);
        panelInfo.add(treasureInfo);
        myFrame.add(panelForButtons, BorderLayout.SOUTH);
    }

    /**
     * Set the label winner that shows info about the winner.
     */
    public void setWinnerLabel() {
        winner = new JLabel("");
        winner.setFont(new Font("Monospaced", Font.PLAIN, 15));
        winner.setBounds(150, 550, 300, 50);
    }
        
    /**
     * Set the labels with the score of the two players.
     */
    public void setScoreLabel() {
        myScoreLabel = new JLabel("My score: 0");
        computerScoreLabel = new JLabel("Enemy score: 0");
        myScoreLabel.setHorizontalAlignment(JLabel.CENTER);
        computerScoreLabel.setHorizontalAlignment(JLabel.CENTER);
        myScoreLabel.setBounds(0, 50, 350, 50);
        computerScoreLabel.setBounds(0, 100, 350, 50);
        panelInfo.add(myScoreLabel);
        panelInfo.add(computerScoreLabel);
    }
    
    /**
     * Return the panelInfo.
     * @return panelInfo.
     */
    public JPanel getPanelInfo() {
        return panelInfo;
    }
    
    /**
     * Return the panelForButtons.
     * @return panelForButtons.
     */
    public JPanel getPanelForButtons() {
        return panelForButtons;
    }
    
    /**
     * Return the msgInfo label.
     * @return msgInfo.
     */
    public JLabel getMsgInfo() {
        return msgInfo;
    }
    
    /**
     * Return the winner label.
     * @return winner.
     */
    public JLabel getWinnerLabel() {
        return winner;
    }
    
    /**
     * Return the treasureInfo label.
     * @return treasureInfo.
     */
    public JLabel getTreasureInfo() {
        return treasureInfo;
    }
    
    /**
     * Return the myScoreLabel label.
     * @return myScoreLabel.
     */
    public JLabel getMyScoreLabel() {
        return myScoreLabel;
    }
    
    /**
     * Return the computerScoreLabel label.
     * @return computerScoreLabel.
     */
    public JLabel getComputerScoreLabel() {
        return computerScoreLabel;
    }
    
    
    
    
}
