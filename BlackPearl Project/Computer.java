/**
 * The computer player.
 * 
 * @authors Bianchi Arianna & Onciu Razvan Petrica 
 * @version 2020
 */

public class Computer extends Player {

    /**
     * Main constructor that calls the super class constructor,
     * and place already randomly six ships on the grid.
     */
    public Computer() {
        super();
        grid.getUtilities().placeShipRandom();  
        grid.getTreasure().placeLuckyTreasure();
    }

}
