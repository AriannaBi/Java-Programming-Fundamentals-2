import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class GridMemoryTest.
 *
 * @authors Bianchi Arianna & Onciu Razvan Petrica 
 * @version 2020
 */
public class GridMemoryTest {
    
    @Test
    public void testMainGridMemory() {
        final GridMemory gm = new GridMemory();
        gm.setMemory(0,0);
        assertFalse(gm.isMemorized(1,1));
        assertTrue(gm.isMemorized(0,0));
    }
    
    @Test
    public void testGridMemoryShoot() {
        final Grid g = new Grid();
        g.placeShip(new Ship(2), "A0", 1);
        g.shootShip("A0");
        g.shootShip("B0");
        assertTrue(g.getGridMemory().isMemorized(0,0));
        assertTrue(g.getGridMemory().isMemorized(1,0));
    }
    
    @Test
    public void testResetGridMemory() {
        final GridMemory gm = new GridMemory();
        for (int j = 0; j < 9; j++) {
            for (int i = 0; i < 9; i++) {
               gm.setMemory(j,i);
               assertTrue(gm.isMemorized(j,i));
            }
        }
        gm.resetMemory();
        for (int j = 0; j < 9; j++) {
            for (int i = 0; i < 9; i++) {
                assertFalse(gm.isMemorized(j,i));
            }
        }
    }
    
    
}
