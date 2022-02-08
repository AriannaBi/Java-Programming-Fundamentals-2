import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class ShipTest.
 *
 * @authors Bianchi Arianna & Onciu Razvan Petrica 
 * @version 2020
 */
public class ShipTest
{
    @Test
    public void testMainShip() {
        final Ship small = new SmallShip();
        final Ship medium = new MediumShip();
        final Ship large = new LargeShip();
        assertEquals(2, small.getDimension());
        assertEquals(3, medium.getDimension());
        assertEquals(4, large.getDimension());
    }

    @Test
    public void testIsSinked() {
        final Grid g = new Grid();
        final Ship small = new SmallShip();
        final Ship large = new LargeShip();
        g.placeShip(small, "A0", 1);
        g.shootShip("A0");
        g.shootShip("B0");
        assertTrue(small.isSinked());
        g.placeShip(large, "C3", 1);
        assertFalse(large.isSinked());
    }

    @Test
    public void testShootOneCellShip() {
        final Grid g = new Grid();
        final Ship medium = new MediumShip();
        g.placeShip(medium, "F5", 1);
        medium.shootOneCellShip(5,5);
        assertTrue(medium.getSinked()[0]);
        assertFalse(medium.getSinked()[1]);
        assertFalse(medium.getSinked()[2]);
    }

    @Test
    public void testHasTheCoordinates() {
        final Grid g = new Grid();
        final Ship large = new LargeShip();
        g.placeShip(large, "A5", 1);
        assertEquals(0, large.containsCoordinates(0,5));
        assertEquals(-1, large.containsCoordinates(0,6));
        assertEquals(-1, large.containsCoordinates(8,5));
        assertEquals(3, large.containsCoordinates(3,5));
    }

    @Test
    public void testGetCoordinate() {
        final Grid g = new Grid();
        final Ship small = new SmallShip();
        g.placeShip(small, "C1", 1);
        assertEquals(2, small.getCoordinate()[0][0]);
        assertEquals(1, small.getCoordinate()[0][1]);
    }
}
