import java.util.Scanner;

/**
 * Main class for the TUI.
 * 
 * @authors Bianchi Arianna & Onciu Razvan Petrica 
 * @version 2020
 */
public class TextUserInterface {

    private final String name;
    private final Scanner scan;
    private final Grid g;
    private final String exit;

    /**
     * Create a new TUI operating on the given Model.
     * @param title The title of this application
     */
    public TextUserInterface(final String title) {
        this.name = title;
        System.out.println("Welcome to " + name);
        this.scan = new Scanner(System.in);
        this.g = new Grid();
        this.exit = "exit";
    }

    /**
     * Run the application.
     */
    public void run() {
        while (true) {
            System.out.println("What would you like to do? : place, shoot or exit? "); 
            final String word = scan.next();
            if ("place".equalsIgnoreCase(word)) {
                this.place(scan);             
            } else if ("shoot".equalsIgnoreCase(word)) { 
                this.runHelper(g, scan);
            } else if (exit.equalsIgnoreCase(word)) {
                break;                
            } else {
                System.out.println("ERROR: Invalid command!");
            }            
        }
        System.out.println("Goodbye Captain!");
        scan.close();
    }

    /**
     * Helper method to run the application.
     * @param g as the main grid.
     * @param scan as the scanner of the application.
     */
    private void runHelper(final Grid g, final Scanner scan) {
        if ("ERROR: Please place at least one ship first!".equals(g.shootShip(""))) {
            System.out.println("ERROR: Please place at least one ship first!");
        } else {
            this.shoot(scan, g);
        }
    }

    /**
     * Returns the name of the project.
     * @return the string of the name of the project.
     */
    public String getTitle() {
        return "Welcome to " + name;
    }

    /**
     * Shoot at grid with user inputs.
     * @param scan as the scanner of the application.
     * @param g as the main grid.
     */
    public void shoot(final Scanner scan, final Grid g) {
        while (true) {
            System.out.println("Choose the coordinates: for example A0...A-J, 0-9");
            final String word = scan.next();
            if (exit.equalsIgnoreCase(word)) {
                break;               
            } else if (g.getUtilities().isValidCellName(word)) { 
                // call the helper
                final String a = shootHelper(word, g);
                System.out.println(a);
            } else {
                System.out.println("ERROR: Invalid Coordinate!");
            }
        } 
    }

    /**
     * Helper method for shooting,
     * this is the method that does the actual shoot.
     * @param word as the coordinates given by the user.
     * @param g as the main grid.
     * @return the string to output for the user.
     */
    private String shootHelper(final String word, final Grid g) {
        System.out.println(word);
        final int[][] coordinates = g.getUtilities().cellNameConvert(word);
        String a = g.shootShip(word);
        if ("There is a ship here".equals(a)) {
            final Ship ship = g.getShip(coordinates[0][0], coordinates[0][1]);
            if (ship.isSinked()) {
                a = a + " Congratulations, you sinked the ship! ";
                return a;
            }
        }  
        return a;
    }

    /**
     * Place a ship with the given inputs of the user.
     * It delegates for each input to other helper mnethods.
     * @param scan as the scanner of the application.
     */
    public void place(final Scanner scan) {
        final Ship s = shipDimension(scan);                
        if (null == s) {
            return;
        }
        final int orientation = shipOrientation(scan);
        if (orientation == -1) {
            return;
        }
        this.placeHelper(scan, orientation, s);
    }
    
    /**
     * Helper method for the coordinates of the ship to place.
     * @param scan as the scanner of the application.
     * @param orientation as the orientation of the ship.
     * @param s the main ship to place.
     */
    private void placeHelper(final Scanner scan, final int orientation, final Ship s) {
        while (true) {
            System.out.println("Choose the coordinates: for example A0...A-J, 0-9");
            final String word = scan.next();
            if (g.getUtilities().isValidCellName(word)) {
                System.out.println(g.placeShip(s, word, orientation));
                break;
            } else if (exit.equalsIgnoreCase(word)) {
                break;
            } else {
                System.out.println("ERROR: Invalid Coordinate!");
            }
        }
    }

    /**
     * Helper method for the place method.
     * @param scan as the scanner of the application.
     * @return the ship that we would then place on the grid.
     */
    private Ship shipDimension(final Scanner scan) {
        System.out.println("Choose the ship: small, medium or large?");
        final String word = scan.next();
        if (exit.equalsIgnoreCase(word)) {
            return null;
        }
        // delegate to the helper method
        final Ship s = shipDimensionHelper(word);
        if (s != null) {
            return s;
        }
        System.out.println("ERROR: Invalid ship!");
        return s;
    }

    /**
     * Helper method that is in charge 
     * to check the dimension of the ship to place.
     * @param word as the input the user gave
     * @return the Ship or null based on the conditions checked.
     */
    private Ship shipDimensionHelper(final String word) {
        if ("small".equalsIgnoreCase(word)) {
            return new SmallShip();                       
        } else if ("medium".equalsIgnoreCase(word)) {
            return new MediumShip();
        } else if ("large".equalsIgnoreCase(word)) {
            return new LargeShip();                    
        } else {
            return null;
        }
    }

    /**
     * Helper method for the place method.
     * @param scan as the scanner of the application.
     * @return an int that represents the orientation (-1 if invalid).
     */
    private int shipOrientation(final Scanner scan) {
        int orientation = -1;
        final String a = "Choose the orientation of the ship: ";
        System.out.println(a + "up, right, down or left?");
        final String word = scan.next();
        if (exit.equalsIgnoreCase(word)) {
            return orientation; // -1
        }
        // delegates to helper method
        orientation = shipOrientationHelper(word);
        if (orientation == -1) {
            System.out.println("ERROR: Invalid orientation!");
        }
        return orientation;
    }

    /**
     * Helper method for ship orientation method,
     * that is in charge of controlling the orientation.
     * @param word as the string orientation that the user gave.
     * @return the orientation as an int (-1 if invalid).
     */
    private int shipOrientationHelper(final String word) {
        if ("up".equalsIgnoreCase(word)) {
            return 0;
        } else if ("right".equalsIgnoreCase(word)) {
            return 1;
        } else if ("down".equalsIgnoreCase(word)) {
            return 2;
        } else if ("left".equalsIgnoreCase(word)) {
            return 3;
        } else {
            return -1;
        }
    }

}