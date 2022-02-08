import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class TreasureTest.
 *
 * @authors Bianchi Arianna & Onciu Razvan Petrica
 * @version 2020
 */
public class TreasureTest
{
    @Test
    public void placeTreasure() {
        final Grid g= new Grid();
        final Treasure treasure= new Treasure(g);
        treasure.placeLuckyTreasure();
        int counter = 0;
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                if (treasure.isPlacedTreasure(x, y)) {
                    counter++;
                }
            }
        }
        assertEquals(1, counter);
        
    }
}
