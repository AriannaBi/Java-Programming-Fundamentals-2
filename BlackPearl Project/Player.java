/**
 * A player playes the main actions in the game.
 * He can shoot, place and he receive the feedback for the actions that he performed.
 * If we choose the computer as a player, then every action is done randomly.
 * @authors Bianchi Arianna & Onciu Razvan Petrica 
 * @version 2020
 */

public class Player {

    protected int score;
    protected Grid grid;
    protected GridMemory gridMemory;
    private static int round = 0;

    /**
     * Main constructor.
     */
    public Player() {
        this.score = 0;
        this.grid = new Grid();
        this.gridMemory = grid.getGridMemory();
    }

    /**
     * Returns the grid of this player.
     * @return the player's grid.
     */
    protected Grid getGrid() {
        return grid;       
    }
    
    /**
     * Returns the memory grid of the player.
     * @return the player's memory grid.
     */
    protected GridMemory getMemoryGrid() {
        return gridMemory;       
    }

    /**
     * Increment the score of the player.
     */
    protected void increaseScore() {
        score++;
    }

    /**
     * Return the score of the player.
     * @return a int representing the score of the player.
     */
    protected int getScore() {
        return score;
    }
    
    /**
     * End the current turn and pass the round to the opponent.
     */
    protected void increaseRound() {
        round++;
    }
    
    /**
     * If the field round is odd then is the computer's round, 
     * otherwise is the round of the main player.
     * @return a string containint the player that has the round.
     */
    protected String whoseRound() {
        if (round % 2 == 0) {
            return "myself";
        } else {
            return "computer";
        }
    }
    
    /**
     * Return if the player has lost.
     * Done in recursive mode.
     * @param index as the initial index for the recursion wich is 0
     * @return true if all the ships are sinked and so if the player has lost
     */
    protected boolean hasLost(final int index) {
        if (index == 6) {
            return true;
        } else if (grid.getNavalFleet().getEntireFleet()[index] == null 
                   || !grid.getNavalFleet().getEntireFleet()[index].isSinked()) {
            return false;
        } else {
            return hasLost(index + 1);
        }
    }
    
    /**
     * Reset the score of the player.
     */
    protected void resetScore() {
        score = 0;
    }
    
    /**
     * Reset the round of the players.
     */
    protected void resetRound() {
        round = 0;
    }
    
    

    
    
    
    
    
}
