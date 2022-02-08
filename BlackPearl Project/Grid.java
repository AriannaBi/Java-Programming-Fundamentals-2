/**
 * The grid represent the main place of the battle,
 * if there is a true in the cell at a given position,
 * then there is a ship placed on, 
 * otherwise the cell is free.
 * 
 * @authors Bianchi Arianna & Onciu Razvan Petrica 
 * @version 2020
 */ 

public class Grid {
    // the main array that contains the coordinates of each cell of the grid.
    private boolean[][] grid; 
    private NavalFleet navalFleet;
    private GridMemory gridMemory; // array that contains the already clicked cells.
    private String owner; // either "computer" or "myself".
    private Treasure treasure;
    private Utilities utilities;
    
    /**
     * Main constructor for the grid.
     * Default value False for every element in the array.
     * Orientation_ship sets as 0.
     * At the beginning of the game there are no ships on the grid.
     * At the beginning the owner is the computer, so we can choose in a second time
     * which grid will have the owner as the main player.
     */
    public Grid() {
        this.grid = new boolean[10][10]; // Default value as False
        this.navalFleet = new NavalFleet();
        this.gridMemory = new GridMemory();
        this.owner = "computer"; 
        this.treasure = new Treasure(this);
        this.utilities = new Utilities(this);
    }

    /**
     * Set the owner of the grid as the main player.
     */
    public void setOwner() {
        this.owner = "myself";
    }

    /**
     * Place the given ship on the grid depending on the given orientation of the ship,
     * starting from the given coordinates x and y. 
     * @param ship to get the Dimension of the ship.
     * @param cellName the coordinate x of the initial cell of the ship.
     * @param orientationShip that is 1 for right, 2 for down, 3 for left or  0 for up.
     * @return String the adapt message.
     */
    public String placeShip(final Ship ship, final String cellName, final int orientationShip) {
        final int[][] cell = utilities.cellNameConvert(cellName);
        if (!utilities.isValidCellName(cellName)) {
            return "ERROR: Invalid coordinates";
        }
        final int x = cell[0][0];
        final int y = cell[0][1];
        // first check if the naval fleet is full
        if (navalFleet.getCurrentShipIndex() != 6) {
            // check if there is no ship placed, and if the ship is not out of the grid
            return utilities.helperPlaceShip(grid, ship, cellName, x, y, orientationShip);
        } else {
            return "ERROR: You cannot place more ship"; 
        }
    }

    /**
     * Given a string composed by x and y (letter and number), 
     * shoot this cell if there is a ship.
     * @param cellName the coordinates x (letter) and y (number). 
     * @return a string containing info about the process.
     */
    public String shootShip(final String cellName) {
        // Base cases:
        if (navalFleet.getEntireFleet()[0] == null) {
            return "ERROR: Please place at least one ship first!";
        }
        if (!utilities.isValidCellName(cellName)) {
            return "ERROR: Invalid cell name";
        }               
        final int[][] cell = utilities.cellNameConvert(cellName);  
        final int x = cell[0][0];
        final int y = cell[0][1];
        if (gridMemory.isMemorized(x, y)) {
            return "ERROR: You have already shooted here!";
        }
        // End of base cases       
        gridMemory.setMemory(x, y); // remember that you have shooted in this cell
        if (isPlaced(x, y)) {
            final Ship s = this.getShip(x, y);
            s.shootOneCellShip(x, y);
            return "There is a ship here";
        } else {
            return "You missed the ship";
        }
    }

    /**
     * Returns the object ship.
     * @param x as x coordinate of the cell.
     * @param y as y coordinate of the cell.
     * @return the ship that has those coordinates, null otherwise. 
     */
    public Ship getShip(final int x, final int y) {
        for (final Ship s : navalFleet.getEntireFleet()) {
            // check if the ship has those coordinates 
            // -1 means that the ship doen't have those coordinates
            if (s != null && s.containsCoordinates(x,y) != -1) {
                return s;
            }
        } 
        return null;
    }
    
    /**
     * Return the Treasure object.
     * @return tresure as the treasure
     */
    public Treasure getTreasure() {
        return treasure;
    }

    /**
     * Helper function for the placing process,
     * with the orientation of the ship along the x axis.
     * @param dimension of the ship: small, medium or large.
     * @param x the coordinate x of the initial cell of the ship.
     * @param y the coordinate y of the initial cell of the ship.
     * @param sign is +1 or -1 depending on the orientation_ship.
     */
    public void xaxisPlace(final int dimension, final int x, final int y, final int sign) {
        int x_copy = x;
        for (int i = 0; i < dimension; i++) {
            grid[x_copy][y] = true;
            x_copy = x_copy + sign;
        }
    }

    /**
     * Helper function for the placing process,
     * with the orientation of the ship along the y axis.
     * @param dimension of the ship: small, medium or large.
     * @param x the coordinate x of the initial cell of the ship.
     * @param y the coordinate y of the initial cell of the ship.
     * @param sign is +1 or -1 depending on the orientation_ship.
     */
    public void yaxisPlace(final int dimension, final int x, final int y, final int sign) {
        int y_copy = y;
        for (int i = 0; i < dimension; i++) {
            grid[x][y_copy] = true;
            y_copy = y_copy + sign;
        }
    }

    /**
     * Returns True if there is a ship placed on its x and y coordinates. 
     * Useful to check if there is a ship on that cell.
     * @param x the coordinates x of the cell.
     * @param y the coordinates y of the cell.
     * @return the boolean value of the cell at x and y position.
     */
    public boolean isPlaced(final int x, final int y) {
        return grid[x][y];
    }

    /**
     * Returns the length of the array grid. 
     * @return an int representing the length of the array grid. 
     */
    public int getArrayLength() {
        return grid.length;
    }
    
    /**
     * Returns the utilities object.
     * @return utilities
     */
    public Utilities getUtilities() {
        return utilities;
    }

    /**
     * Return the owner of the grid.
     * @return a string representing the owner.
     */
    public String getOwner() {
        return this.owner;
    }

    /**
     * Return the naval fleet object.
     * @return the naval fleet as a copy
     */
    public NavalFleet getNavalFleet() {
        final NavalFleet navalCopy = navalFleet;
        return navalCopy;
    }

    /**
     * Returns the grid memory.
     * @return the grid memory array. 
     */
    public GridMemory getGridMemory() {
        return gridMemory;
    }

    /**
     * Resets the grid memory.
     */
    public void resetGridMemory() {
        this.gridMemory.resetMemory();
    }

    /**
     * Resets the array of the grid to False value in every cell,
     * and the list of the ships placed, with the index of that list.
     */
    public void reset() {
        this.resetGridMemory();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                grid[i][j] = false;
            }
        }  
        for (int a = 0; a < 6; a++) {
            navalFleet.getEntireFleet()[a] = null;
        }
        navalFleet.resetCurrentShipIndex();       
        if ("computer".equals(owner)) {
            utilities.placeShipRandom();
            treasure.placeLuckyTreasure();
        }        
    }

}
