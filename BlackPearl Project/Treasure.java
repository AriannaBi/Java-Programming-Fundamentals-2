import java.util.Random;

/**
 * contains the treasure and all its actions like place, 
 * and if its shooted.
 *
 * @authors Bianchi Arianna & Onciu Razvan Petrica
 * @version 2020
 */
public class Treasure {
    // contains the position of the lucky treasure
    private int[][] luckyTreasure; 
    private int lucky;
    private Grid grid;

    /**
     * Main Constructor that takes as parameter an existing grid.
     * @param grid as the Grid of the game that already exists.
     */
    public Treasure(final Grid grid) {
        this.luckyTreasure = new int[1][2];
        this.luckyTreasure[0][0] = -1;
        this.luckyTreasure[0][1] = -1;
        this.grid = grid;
    }

    /**
     * Place the lucky treasure randomly on the grid.
     * The lucky treasure will give to the player a bomb that 
     * can shoot an entire row on the grid.
     * But this has a 50% chance that even if the player finds it,
     * he can loose it and so the enemy can use it.
     */
    public void placeLuckyTreasure() {
        final Random random = new Random();
        // if lucky is 1, then the enemy will receive it,
        // if is 0, then the player can use it.
        lucky = random.nextInt(2); 
        int x = random.nextInt(10);
        int y = random.nextInt(10);
        while (grid.isPlaced(x,y)) {
            x = random.nextInt(10);
            y = random.nextInt(10);
        }  
        this.luckyTreasure[0][0] = x;
        this.luckyTreasure[0][1] = y;
    }

    /**
     * Shoot an entire row in a recursive mode.
     * @param y as the line to shoot.
     * @param a as the initial char of the recursion (always 'A').
     */
    public void luckyShoot(final int y, final char a) {
        char aCopy = a;
        if (a == 'J') {
            grid.shootShip(a + Integer.toString(y));
        } else {
            grid.shootShip(a + Integer.toString(y));
            aCopy++;
            this.luckyShoot(y, aCopy);
        }
    }

    /**
     * Return 0 or 1 that is the lucky.
     * @return the lucky 0 or 1.
     */
    public int getLucky() {
        return this.lucky;
    }

    /**
     * Return if there is the lucky treasure.
     * @param x as the x coordinate.
     * @param y as the y coordinate.
     * @return a boolean that indicates if there is the lucky treasure.
     */
    public boolean isPlacedTreasure(final int x, final int y) {
        return this.luckyTreasure[0][0] == x && this.luckyTreasure[0][1] == y;                
    }

}
