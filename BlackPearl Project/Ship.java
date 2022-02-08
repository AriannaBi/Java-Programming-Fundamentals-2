/**
 * A Ship can be placed on the grid, and each time a piece of it is shooted.
 * The ship has a dimension, a 2d array containing the coordinate of all its parts,
 * and an array that for each part of the ship tells if is sinked or not.
 * 
 * @author Bianchi Arianna & Onciu Razvan Petrica  
 * @version 2020
 */

public class Ship {

    private final int dimension;
    private int[][] coordinate;
    private boolean[] sinked;

    /**
     * Main constructor that initialized the fileds.
     * @param dimension as the dimension of the ship : 2 for small, 3 for medium and 4 for large.
     */
    public Ship(final int dimension) {
        this.dimension = dimension;
        this.coordinate = new int[dimension][2];
        this.sinked = new boolean[dimension]; //default false
    }

    /**
     * Gets the dimension of the ship.
     * @return int as the dimension of the ship.
     */
    protected int getDimension() {
        return dimension;
    }

    /**
     * Gets the coordinates of the ship.
     * @return a two dimensional array containing the coordinates of the ship.
     */
    protected int[][] getCoordinate() {
        final int[][] coordinateCopy = coordinate;
        return coordinateCopy;
    }

    /**
     * Gets the array containing information about all the part of the ship.
     * In particular tells which part of the ship is sinked and which not.
     * @return an array of boolean as the array_sinked of the ship.
     */
    protected boolean[] getSinked() {
        final boolean[] sinkedCopy = sinked;
        return sinkedCopy;
    }

    /**
     * Sets the coordinates of the ship.
     * @param x as the x coordinate.
     * @param y as the y coordinate.
     * @param orientationShip as the orientation of the ship.
     */
    protected void setCoordinate(final int x, final int y, final int orientationShip) {
        int xCopy = x;
        int yCopy = y;
        for (int i = 0; i < this.dimension; i++) {
            if (orientationShip == 1) {
                helperSetCoordinate(xCopy, y, i);
                xCopy++;
            } else if (orientationShip == 2) {
                helperSetCoordinate(x, yCopy, i);
                yCopy++;
            } else if (orientationShip == 3) {
                helperSetCoordinate(xCopy, y, i);
                xCopy--;
            } else {
                helperSetCoordinate(x, yCopy, i);
                yCopy--;
            }
        }
    }

    /**
     * Helper method for the setCoordinate of the ship.
     * @param x as the x coordinate.
     * @param y as the y coordinate.
     * @param i as the index of the loop.
     */
    protected void helperSetCoordinate(final int x, final int y, final int i) {
        coordinate[i][0] = x;
        coordinate[i][1] = y;
    }

    /**
     * Returns if all the parts of the ship are all sinked. 
     * So if the entire ship is sinked.
     * @return boolean if the ship is sinked or not.
     */
    protected boolean isSinked() {
        for (int i = 0; i < dimension; i++) {
            // if a part of the ship is not sinked
            if (!sinked[i]) { 
                return false;
            }
        }
        return true;
    }

    /**
     * Set true at the array sinked in the position i.
     * Sink one part of the ship.
     * @param x as the x coordinate.
     * @param y as the y coordinate.
     */
    protected void shootOneCellShip(final int x, final int y) {
        final int i = containsCoordinates(x, y);
        sinked[i] = true;
    }

    /**
     * Returns the index i that represent what part of the ship has those coordinates, 
     * @param x as x coordinate of the cell.
     * @param y as y coordinate of the cell.
     * @return int if the part of the ship exists, otherwise return -1.
     */
    protected int containsCoordinates(final int x, final int y) {
        for (int i = 0; i < dimension; i++) {
            if (coordinate[i][0] == x && coordinate[i][1] == y) {
                return i;
            }
        }
        return -1;
    }

}
