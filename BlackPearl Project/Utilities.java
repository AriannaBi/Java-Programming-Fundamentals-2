import java.util.Random;

/**
 * Write a description of class Utilities here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Utilities {

    private Grid gridUtilities;
    private NavalFleet navalFleet;

    /** Main Constructor that takes an existing gris.
     * and initialized the field.
     * @param grid as the main grid.
     */
    public Utilities(final Grid grid) {
        this.gridUtilities = grid;
        this.navalFleet = gridUtilities.getNavalFleet();
    }

    /**
     * Checks if the given cell name is valid.
     * @param cellName as the name of the cell.
     * @return true if it is a valid cell name, false otherwise.
     */
    public boolean isValidCellName(final String cellName) {
        // False if the given cell name is not 2 length, and the second letter is not a number
        // isDigit checks if a char is a digit
        if (cellName.length() != 2 || !Character.isDigit(cellName.charAt(1))) { 
            return false;
        }
        final char x = cellName.charAt(0);
        // Checks if the given name is in the range of cells
        // note that we don't need to check the y in the range[0,9]
        //because the user cannot pass a value out of those bonds
        // since if he does so, then the length of the input is greater
        //than 2 (also with a negative number)
        return x >= 'A' && x <= 'J';
    }

    /**
     * Converts two number representing the coordinates into a cell name.
     * THE CASE OF THE LETTER IS IMPORTANT.
     * @param x as the x-coordinate.
     * @param y as the y-coordinate.
     * @return a string containing the right cell name as "(A-J)(1-10)".
     */
    public String coordinatesConvert(final int x, final int y, int index) {
        
        String converted = "";
        char letter = 'A';
        //int index = 0;
        if (index == x) {
            return converted;
        } else {
            letter++;
            converted = letter + Integer.toString(y);
            return coordinatesConvert(x, y, index++);
        }
        /*
        while (index < x) {
            letter = letter + 1;
            index++;
        }

        for (int index = 0; index < x; index++) {
            letter++;
        }
        */
        converted = letter + Integer.toString(y);
        //return converted;
    }   

    /**
     * Convert a cell name into two coordinates.
     * @param cellName as the name of the cell
     * @return Array of two dimensions containing the right coordinates
     */
    public int[][] cellNameConvert(final String cellName) {
        if (!this.isValidCellName(cellName)) {
            return null;
        }
        final int[][] coordinates = new int[1][2];  
        // -X Coordinate-
        final char letter = cellName.charAt(0);
        int x_coordinate = 0;
        for (char i = 'A'; i != letter; i++) {
            x_coordinate++;
        }
        coordinates[0][0] = x_coordinate;
        // -Y Coordinate-
        // parseInt converts the string into an integer
        final String a = cellName.substring(1, cellName.length());
        coordinates[0][1] = Integer.parseInt(a);
        return coordinates;        
    }

    /**
     * Helper method for placing a ship when this is horizontal.
     * @param grid as the grid. 
     * @param dimension as the dimension of the ship.
     * @param x as the coordinate x of the ship.
     * @param y as the coordinate y of the ship.
     * @param sign as the 1 or -1.
     * @return true if the ship is in the grid bounds, false otherwise. 
     */
    public boolean helperCanBePlacedHorizontal(final Grid grid, final int dimension, 
                                               final int x, final int y, final int sign) {
        int tail_ship = x + (dimension * sign - 1);
        // first check if the last cell of the ship will be in the grid or out
        if (tail_ship >= 10 || tail_ship < 0) {
            return false;
        }
        tail_ship = x;
        // check if there is already a ship placed in the way of the new ship that we want to place
        for (int i = 0; i < dimension; i++) {
            if (grid.isPlaced(tail_ship,y)) {
                return false;
            }
            tail_ship = tail_ship + sign;
        }
        return true; 
    }

    /**
     * Helper method for placing a ship when this is vertical.
     * @param grid as the main grid.
     * @param dimension as the dimension small medium or large of the ship. 
     * @param x as the coordinate x of the ship.
     * @param y as the coordinate y of the ship.
     * @param tailShip as the finish point of the ship.
     * @param sign as +1 or -1 that indicates whether we are substracting or adding
     * @return true if the ship is in the grid bounds, false otherwise. 
     */
    private boolean helperCanBePlacedVertical(final Grid grid, final int dimension, 
                                             final int x, final int y, final int tailShip, 
                                             final int sign) {
        int tail = tailShip;
        if (tail >= 10 || tail < 0) {
            return false;
        }
        tail = y;
        for (int i = 0; i < dimension; i++) {
            if (grid.isPlaced(x,tail)) {
                return false;
            }
            tail = tail + sign;
        }
        return true;
    }

    /**
     * Place the given ship on the grid depending on the given orientation of the ship,
     * starting from the given coordinates x and y. 
     * @param grid as the grid.
     * @param ship to get the Dimension of the ship.
     * @param cellName the coordinate x of the initial cell of the ship.
     * @param x the coordinate x of the initial cell of the ship.
     * @param y the coordinate y of the initial cell of the ship.
     * @param orientationShip that is 1 for right, 2 for down, 3 for left or  0 for up.
     * @return String the adapt message.
     */
    public String helperPlaceShip(final boolean[][] grid, final Ship ship, 
                                  final String cellName, final int x, final int y, 
                                  final int orientationShip) {
        if (!grid[x][y] && canBePlaced(ship.getDimension(), x, y, orientationShip)) { 
            // add the ship to the fleet
            navalFleet.getEntireFleet()[navalFleet.getCurrentShipIndex()] = ship;
            // save the orientation of the ship
            navalFleet.setOrientationOfShip(navalFleet.getCurrentShipIndex(), orientationShip);
            // increase the index of the fleet array
            navalFleet.increaseCurrentShipIndex();
            // save the coordinate of the ship
            ship.setCoordinate(x, y, orientationShip);
            helperOrientation(ship, x, y, orientationShip);
            return "Ship placed in " + cellName.charAt(0) + y;
        } else if (grid[x][y]) { 
            return "ERROR: There is already a ship placed"; 
        } else {
            return "ERROR: Your ship is out of the grid";
        }
    }

    /**
     * Helper method for the place execution.
     * @param ship as the main ship.
     * @param x as the x coordinate.
     * @param y as the y coordinate.
     * @param orientationShip as the orientation of the ship.
     */
    private void helperOrientation(final Ship ship, final int x,
                                  final int y, final int orientationShip) {
        if (orientationShip == 1) { // right
            gridUtilities.xaxisPlace(ship.getDimension(), x, y, 1);
        } else if (orientationShip == 3) { // left
            gridUtilities.xaxisPlace(ship.getDimension(), x, y, -1);
        } else if (orientationShip == 0) { // up
            gridUtilities.yaxisPlace(ship.getDimension(), x, y, -1);
        } else { // down
            gridUtilities.yaxisPlace(ship.getDimension(), x, y, 1);
        }
    }

    /** 
     * Place randomly 6 ships on the grid.
     * This method is mainly for the computer,
     * but the user can choose to also place randomly 6 ships.
     * @return a string containing info about the process
     */
    public String placeShipRandom() {
        final Random random = new Random();
        int x;
        int y;
        int dimension;
        int orientation;
        if (navalFleet.getCurrentShipIndex() == 6) {
            return "You already have 6 ships placed";
        }
        while (navalFleet.getCurrentShipIndex() < 6) {
            x = random.nextInt(10);
            y = random.nextInt(10);
            dimension = random.nextInt(3) + 2;
            orientation = random.nextInt(4);
            gridUtilities.placeShip(new Ship(dimension), 
                this.coordinatesConvert(x, y), orientation);
        }
        return "You placed 6 ships randomly";
    }

    /** 
     * Shoot randomly on the grid.
     * This method is only used by the computer.
     * @return a string containing info about the process
     */
    public String shootShipRandom() {
        final Random random = new Random();
        int x;
        int y;
        x = random.nextInt(10);
        y = random.nextInt(10);
        while (gridUtilities.getGridMemory().isMemorized(x,y)) {
            x = random.nextInt(10);
            y = random.nextInt(10);            
        }
        final String cell_name = this.coordinatesConvert(x, y);
        return gridUtilities.shootShip(cell_name);
    }

    /**
     * Given a dimension, the coordinates and the orientation of the ship, 
     * return if the ship is in the grid and not out,
     * and if the new ship is not overlapping with the ship in place on the grid.
     * @param dimension as the dimension small medium or large of the ship. 
     * @param x as the coordinate x of the ship.
     * @param y as the coordinate y of the ship.
     * @param orientationShip as the orientation of the ship.
     * @return true if the ship is in the grid bounds, false otherwise. 
     */
    public boolean canBePlaced(final int dimension, final int x, 
                               final int y, final int orientationShip) {
        int tail_ship;
        if (orientationShip == 1) {
            return this.helperCanBePlacedHorizontal(gridUtilities, dimension, x, y, 1);
        } else if (orientationShip == 2) {
            tail_ship = y + (dimension - 1);
            return this.helperCanBePlacedVertical(gridUtilities, dimension, x, y, tail_ship, 1);
        } else if (orientationShip == 3) {
            return this.helperCanBePlacedHorizontal(gridUtilities, dimension, x, y, -1);
        } else {
            tail_ship = (y - dimension) + 1;
            return helperCanBePlacedVertical(gridUtilities, dimension, x, y, tail_ship, -1);      
        }
    }

}
