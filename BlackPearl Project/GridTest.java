import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class GridTest.
 *
 * @authors Bianchi Arianna & Onciu Razvan Petrica
 * @version 2020
 */
public class GridTest
{
    @Test
    public void testMainGrid() {
        final Grid g = new Grid();
        final int len = g.getArrayLength();
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                assertFalse(g.isPlaced(i,j));
            }
        } 
    }

    @Test
    public void testCoordinateConversion() {
        final Grid grid = new Grid();
        assertEquals("D0", grid.getUtilities().coordinatesConvert(3,0));
        assertEquals("H1", grid.getUtilities().coordinatesConvert(7,1));
        assertEquals("J9", grid.getUtilities().coordinatesConvert(9,9));
    }

    @Test
    public void testInvalidCellName() {
        final Grid grid = new Grid();
        assertFalse(grid.getUtilities().isValidCellName("A67")); // case with length greater than 2
        assertFalse(grid.getUtilities().isValidCellName("A")); // case with length less than 2
        assertFalse(grid.getUtilities().isValidCellName("AA")); // case with non number digit
        // case with out of range
        assertFalse(grid.getUtilities().isValidCellName("Z9")); 
        assertFalse(grid.getUtilities().isValidCellName(" 9")); 
    }

    @Test
    public void testPlaceShipRight() {
        final Grid g = new Grid();
        final Ship ship = new SmallShip();
        g.placeShip(ship, "E1", 1);
        int end_loop = 4 + ship.getDimension();
        for (int i = 4; i < end_loop; i++) {
            assertTrue(g.isPlaced(i,1));
        }        
    }

    @Test
    public void testPlaceShipLeft() {
        final Grid g = new Grid();
        final Ship ship = new SmallShip();
        g.placeShip(ship, "E1", 3);
        int end_loop = 4 - ship.getDimension();
        for (int i = 4; i > end_loop; i--) {
            assertTrue(g.isPlaced(i,1));
        }
    }

    @Test
    public void testPlaceShipUp() {
        final Grid g = new Grid();
        final Ship ship = new SmallShip();
        g.placeShip(ship, "B4", 0);
        int end_loop = 4 - ship.getDimension();
        for (int i = 4; i > end_loop; i--) {
            assertTrue(g.isPlaced(1,i));
        }
    }

    @Test
    public void testPlaceShipDown() {
        final Grid g = new Grid();
        final Ship ship = new SmallShip();
        g.placeShip(ship, "B4", 2);
        int end_loop = 4 + ship.getDimension();
        for (int i = 4; i < end_loop; i++) {
            assertTrue(g.isPlaced(1,i));
        }
    }

    @Test
    public void testNavalFleet() {
        final Grid g = new Grid();
        final Ship ship = new SmallShip();        
        g.placeShip(ship, "E1", 1);
        // Assert that we have the new ship inserted
        assertEquals(2, g.getNavalFleet().getEntireFleet()[0].getDimension());
        g.placeShip(ship, "E1", 1);
        // Assert that no ship has placed
        assertEquals(2, g.getNavalFleet().getEntireFleet()[0].getDimension());
        final Ship ship_large = new LargeShip();
        g.placeShip(ship_large, "E3", 1);
        assertEquals(4, g.getNavalFleet().getEntireFleet()[1].getDimension());
    }

    @Test
    public void testGetOrientationOfShip() {
        final Grid g = new Grid();
        final Ship ship = new SmallShip();
        g.placeShip(ship, "B4", 2);
        // check if the orientation of first ship (the only one added until now) is 2
        assertEquals(2, g.getNavalFleet().getOrientationOfShip(0));
    }

    @Test
    public void testPlaceShipOutOfGridLeft() {
        final Grid g = new Grid();
        final Ship ship = new LargeShip();
        final Ship ship2 = new LargeShip();
        final Ship ship3 = new LargeShip();
        g.placeShip(ship, "A0", 3);
        assertFalse(g.isPlaced(0,0));  
        g.placeShip(ship2, "B0", 3);
        assertFalse(g.isPlaced(1,0));
        g.placeShip(ship3, "C0", 3);
        assertFalse(g.isPlaced(2,0));
    }

    @Test
    public void testPlaceShipOutOfGridRight() {
        final Grid g = new Grid();
        final Ship ship = new LargeShip();
        g.placeShip(ship, "J0", 1);
        assertFalse(g.isPlaced(9,0)); 
        g.placeShip(ship, "I0", 1);
        assertFalse(g.isPlaced(8,0));
    }

    @Test
    public void testPlaceShipOutOfGridDown() {
        final Grid g = new Grid();
        final Ship ship = new LargeShip();
        g.placeShip(ship, "A8", 2);
        assertFalse(g.isPlaced(0,8));  
        g.placeShip(ship, "A7", 2);
        assertFalse(g.isPlaced(0,7));
    }

    @Test
    public void testPlaceShipOutOfGridUp() {
        final Grid g = new Grid();
        final Ship ship = new LargeShip();
        g.placeShip(ship, "A1", 0);
        assertFalse(g.isPlaced(0,1)); 
        g.placeShip(ship, "A2", 0);
        assertFalse(g.isPlaced(0,2));
    }

    @Test
    public void testPlaceShipRandom() {
        final Grid g = new Grid();
        g.getUtilities().placeShipRandom();
        for (int a = 0; a < 6; a++) {
            assertNotEquals(null, g.getNavalFleet().getEntireFleet()[a]);
        }    
        assertEquals("You already have 6 ships placed", g.getUtilities().placeShipRandom());
    }

    @Test
    public void testPlaceShipFull() {
        final Grid g = new Grid();
        final Ship ship = new LargeShip();
        g.getUtilities().placeShipRandom();
        assertEquals("ERROR: You cannot place more ship", g.placeShip(ship, "A0", 1));      
    }

    @Test
    public void testPlaceShipInvalidCoordinate() {
        final Grid g = new Grid();
        final Ship ship = new LargeShip(); 
        assertEquals("ERROR: Invalid coordinates", g.placeShip(new Ship(2), "Z0", 1));      
    }

    @Test 
    public void testResetMyself() {
        final Grid g = new Grid();
        final Ship ship = new SmallShip();
        final int len = g.getArrayLength();
        g.setOwner();
        g.getUtilities().placeShipRandom();
        for (int i = 0; i < len; i++) {
            for (char j = 'A' ; j <= 'J'; j++) {
                g.shootShip(j + Integer.toString(i));
            }
        }
        g.reset();
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                assertFalse(g.isPlaced(i,j));
            }
        }
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                assertFalse(g.getGridMemory().isMemorized(i,j));
            }
        }
        for (int a = 0; a < 6; a++) {
            assertNull(g.getNavalFleet().getEntireFleet()[a]);
        }
    }

    @Test 
    public void testResetComputer() {
        final Grid g = new Grid();
        final Ship ship = new SmallShip();
        final int len = g.getArrayLength();
        g.getUtilities().placeShipRandom();
        g.reset();
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                assertFalse(g.getGridMemory().isMemorized(i,j));
            }
        }
    }

    @Test
    public void testCellNameConvert() {
        final Grid g = new Grid();
        // A6
        assertEquals(0, g.getUtilities().cellNameConvert("A6")[0][0]);
        assertEquals(6, g.getUtilities().cellNameConvert("A6")[0][1]);
        // E0
        assertEquals(4, g.getUtilities().cellNameConvert("E0")[0][0]);
        assertEquals(0, g.getUtilities().cellNameConvert("E0")[0][1]);
        // 
        assertNull(g.getUtilities().cellNameConvert("Z0"));
    }

    @Test
    public void testShootShip() {
        final Grid g = new Grid();
        final Ship ship = new LargeShip();
        g.placeShip(ship, "E1", 1);
        assertEquals("You missed the ship", g.shootShip("A4"));
        assertEquals("There is a ship here", g.shootShip("E1"));
        assertEquals("There is a ship here", g.shootShip("G1"));        
    }

    @Test
    public void testShootShipBeforePlacing() {
        final Grid g = new Grid();
        final Ship ship = new LargeShip();
        assertEquals("ERROR: Please place at least one ship first!", g.shootShip("A4"));
    }

    @Test
    public void testShootShipInvalidCoordinate() {
        final Grid g = new Grid();
        final Ship ship = new LargeShip();
        g.placeShip(ship, "E1", 1);
        assertEquals("ERROR: Invalid cell name", g.shootShip("Z9"));
    }

    @Test
    public void testShootShipTwiceWithSameCoordinate() {
        final Grid g = new Grid();
        final Ship ship = new LargeShip();
        g.placeShip(ship, "E1", 1);
        g.shootShip("E1");
        assertEquals("ERROR: You have already shooted here!", g.shootShip("E1"));
    }

    @Test
    public void testCanBePlaced() {
        // return false if there is already a ship there, so if the two ships make contact between them (overlap)
        final Grid g = new Grid();
        final Ship ship = new SmallShip();
        final Ship ship2 = new LargeShip();
        g.placeShip(ship, "A0", 1);
        assertTrue(g.getUtilities().canBePlaced(4, 6, 0, 2));
        
        g.placeShip(ship, "F8", 2);
           
        assertFalse(g.getUtilities().canBePlaced(3, 1, 0, 1));        
        assertFalse(g.getUtilities().canBePlaced(3, 1, 0, 2));
        assertFalse(g.getUtilities().canBePlaced(2, 1, 1, 0));
        assertFalse(g.getUtilities().canBePlaced(3, 3, 0, 3));
        assertFalse(g.getUtilities().canBePlaced(4, 3, 9, 1));
    }

    @Test
    public void testsetOwner() {
        final Grid g = new Grid();
        g.setOwner();
        for (int a = 0; a < 6; a++) {
            assertNull(g.getNavalFleet().getEntireFleet()[a]);
        }       
    }

    @Test
    public void testgetOwner() {
        final Grid my_g = new Grid();
        final Grid computer_g = new Grid();
        my_g.setOwner();        
        assertEquals("myself", my_g.getOwner()); 
        assertEquals("computer",computer_g.getOwner());
    }

    @Test
    public void testgetMemoryGrid() {
        final Grid my_g = new Grid();
        my_g.getUtilities().placeShipRandom();
        my_g.shootShip("A0");
        assertTrue(my_g.getGridMemory().isMemorized(0,0));
    }

    @Test
    public void testgetShip() {
        final Grid g = new Grid();
        final Ship ship = new LargeShip();
        g.placeShip(ship, "A0", 1);
        assertEquals(ship, g.getShip(0,0)); 
        assertNull(g.getShip(4,4));
    }

    @Test
    public void testgetShipNull() {
        // If the user tries to get a ship before placing anyone, it should return null.
        final Grid g = new Grid();
        assertNull(g.getShip(0,0));     
    }

    @Test
    public void testPlaceLucky() {
        final Grid g = new Grid();
        g.getTreasure().placeLuckyTreasure(); 
        assertTrue(g.getTreasure().getLucky() == 0 || g.getTreasure().getLucky() == 1);
        // Since it's random, we check that the treasure is in one and only one cell on the grid
        // we use a counter to make sure that is always 1
        int counter = 0;
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                if (g.getTreasure().isPlacedTreasure(x,y)) {
                    counter++;
                }
            }
        }
        assertEquals(1, counter);
    }

    @Test
    public void testLuckyShoot() {
        final Grid g = new Grid();
        g.getUtilities().placeShipRandom();
        g.getTreasure().placeLuckyTreasure();
        // We get the coordinates of the lucky treasure
        int y = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (g.getTreasure().isPlacedTreasure(j,i)) {
                    y = i;
                    break;
                }
            }
            if (y != 0) {
                break;
            }
        }
        g.getTreasure().luckyShoot(y, 'A');
        // check that this row is all shooted;
        for (int x = 0; x < 10; x++) {
            assertTrue(g.getGridMemory().isMemorized(x, y));
        }
    }

}
