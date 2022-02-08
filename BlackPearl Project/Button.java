import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * The abstract class of the button have the control of the buttons 
 * for placing, shooting and of the instructions. 
 * 
 *
 * @author Arianna Bianchi & Razvan Petrica Onciu.
 * @version 2020.
 */
public abstract class Button {
    protected final JButton button;
    protected final JPanel panelForButtons;

    /**
     * Main Constructor that sets the button and the panelForButtons.
     * @param panelForButtons as the panel on the frame.
     * @param str as the string of the button.
     */
    public Button(final JPanel panelForButtons, final String str) {
        button = new JButton(str);
        this.panelForButtons = panelForButtons;
    }

    /**
     * Enable button.
     */
    protected void enableButton() {
        button.setEnabled(true);
    }

    /**
     * Disable button.
     */
    protected void disableButton() {
        button.setEnabled(false);
    }

    protected abstract void actionButton(final Player myself, 
                                        final Player computer, final JLabel msgInfo);
}
