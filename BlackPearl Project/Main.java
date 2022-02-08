/**
 * Main class to run the program in TUI or GUI.
 * 
 * @authors Bianchi Arianna & Onciu Razvan Petrica 
 * @version 2020
 */

public class Main {

    /**
     * Main Constructor.
     */
    private Main() {
        // never instantiated
    }

    /**
     * Run the TUI(from the command line).
     * @param args the command line arguments.
     */
    public static void main(final String[] args) {
        final String title = "BlackPearl";
        final TextUserInterface tui = new TextUserInterface(title);
        tui.run();
    }

    /**
     * Run the GUI(from the command line).
     * 
     */
    public static void mainGui() {
        final GraphicalUserInterface gui = new GraphicalUserInterface();

        final Player computer = new Computer();
        final Player myself = new Myself();

        gui.run(myself, computer);

        
    }

}