/**
 * Grid Memory contains a copy of the grid with true value if 
 * that cell has been already choosen for placing/shooting.
 * 
 * @authors Bianchi Arianna & Onciu Razvan Petrica 
 * @version 2020
 */


public class GridMemory {
    
    private  boolean[][] gridMemory;
    
    /**
     * Main Constructor.
     */
    public GridMemory() {
        gridMemory = new boolean[10][10];
    }

    /**
     * Set true at the given coordinates,
     * indicating that this cell we have already visited it.
     * @param x as the x coordinate
     * @param y as the y coordinate
     */
    public void setMemory(final int x, final int y) {
        gridMemory[x][y] = true;
    }

    /**
     * Return whether the cell has been already chosen in the past.
     * @param x as the x coordinate
     * @param y as the y coordinate
     * @return true if the cell has been already seen, false otherwise
     */
    public boolean isMemorized(final int x, final int y) {
        return gridMemory[x][y];
    }
    
    /**
     * Set false to the entire array.
     */
    public void resetMemory() {
        for (int j = 0; j < 10; j++) {
            for (int i = 0; i < 10; i++) {
                gridMemory[j][i] = false;
            }
        } 
    }
}