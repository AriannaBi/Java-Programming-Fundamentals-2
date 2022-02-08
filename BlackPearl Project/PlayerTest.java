import static org.junit.Assert.*;

import org.junit.Test;

/**
 * The test class PlayerTest.
 *
 * @authors Bianchi Arianna & Onciu Razvan Petrica
 * @version 2020
 */
public class PlayerTest {
    
    @Test
    public void iLostTest() {
        final Player p = new Myself();
        assertFalse(p.hasLost(0)); // case where we don't have any ship placed
        final Ship ship = new SmallShip();
        final Ship ship1 = new SmallShip();
        final Ship ship2 = new SmallShip();
        final Ship ship3 = new SmallShip();
        final Ship ship4 = new SmallShip();
        final Ship ship5 = new SmallShip();
        
        p.getGrid().placeShip(ship, "E0", 1);
        p.getGrid().placeShip(ship1, "E1", 1);
        p.getGrid().placeShip(ship2, "E2", 1);
        assertFalse(p.hasLost(0)); // case where we don't have placed all the ship.
        
        p.getGrid().placeShip(ship3, "E3", 1);
        p.getGrid().placeShip(ship4, "E4", 1);
        p.getGrid().placeShip(ship5, "E5", 1);
        assertFalse(p.hasLost(0)); // case where we haven't shooted any ship yet
        for (int i = 0; i < 6; i++) {
             p.getGrid().shootShip("E" + Integer.toString(i));
        }
        assertFalse(p.hasLost(0)); // case where we haven't have shoot all the sheep yet
        for (int i = 0; i < 6; i++) {
             p.getGrid().shootShip("F" + Integer.toString(i));
        }
        assertTrue(p.hasLost(0));// case where we have shooted all the ship
    }
    
    @Test
    public void testIncreaseScore() {
        final Player p = new Myself();
        final Player c = new Computer();
        p.getGrid().placeShip(new Ship(2), "A0", 1);
        p.getGrid().shootShip("A0");
        c.increaseScore();
        p.getGrid().shootShip("B0");
        c.increaseScore();
        assertEquals(2, c.getScore());
    }
    
    @Test
    public void testIncreaseRound() {
        final Player p = new Myself();
        final Player c = new Computer();
        assertEquals("myself", p.whoseRound());
        assertEquals("myself", c.whoseRound());
        p.increaseRound();
        assertEquals("computer", p.whoseRound());
        assertEquals("computer", c.whoseRound());
        p.resetRound();
    }
    
    @Test
    public void testResetMyself() {
        final Player p = new Myself();
        p.getGrid().getUtilities().placeShipRandom();
        for (int i = 0; i < 50; i++) {
            p.getGrid().getUtilities().shootShipRandom();
        }
        p.getGrid().reset();
        p.resetScore();
        for (int j = 0; j < 9; j++) {
            for (int i = 0; i < 9; i++) {
                assertFalse(p.getMemoryGrid().isMemorized(j,i));
            }
        }
        assertEquals(0, p.getScore());
    }
    
    @Test
    public void testResetComputer() {
        final Player p = new Computer();
        for (int i = 0; i < 50; i++) {
            p.getGrid().getUtilities().shootShipRandom();
        }
        p.getGrid().reset();
        p.resetScore();
        for (int j = 0; j < 9; j++) {
            for (int i = 0; i < 9; i++) {
                assertFalse(p.getMemoryGrid().isMemorized(j,i));
            }
        }
        assertEquals(0,p.getScore());
    }
}
