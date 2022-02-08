/**
 * NavalFleet class contains the entire fleet of the ships that are placed on the grid..
 *
 * @author Bianchi Arianna & Onciu Razvan Petrica  
 * @version 2020
 */
public class NavalFleet {
    //array of all the ships in the grid.
    private Ship[] navalFleet; 
    
    // array of the orientation of each ship.
    private int[] orientationsOfShips; 
    
    //gives me the index of the last ship in the array.
    private int currentShipIndex; 
    
    /**
     * Main Constructor that initialized the fileds.
     */
    public NavalFleet() {
        this.currentShipIndex = 0;
        this.orientationsOfShips = new int[6];
        this.navalFleet = new Ship[6];
    }
    
    /**
     * Return the orientation of the ship at a given index.
     * @param index as the global index of the ship.
     * @return the orientation of that ship.
     */
    public int getOrientationOfShip(final int index) {
        return orientationsOfShips[index];
    }
    
    /**
     * Set the orientation of the ship at the given index.
     * @param index as the index of the ship we want to set the orientation for
     * @param i as the value we want to set the orientation to
     */
    public void setOrientationOfShip(final int index, final int i) {
        orientationsOfShips[index] = i;
    }
    
    /**
     * Return the ship index.
     * @return the current index of the ship
     */
    public int getCurrentShipIndex() {
        final int currentShipIndexCopy = currentShipIndex;
        return currentShipIndexCopy;
    }
    
    /**
     * Reset the current ship index to 0.
     */
    public void resetCurrentShipIndex() {
        currentShipIndex = 0;
    }
    
    /**
     * Increment the ship index by one.
     */
    public void increaseCurrentShipIndex() {
        currentShipIndex++;
    }
    
    /**
     * Returns the list of all placed ship.
     * @return the entire_fleet array. 
     */
    public Ship[] getEntireFleet() {
        final Ship[] naval = navalFleet;
        return naval;
    }

}
