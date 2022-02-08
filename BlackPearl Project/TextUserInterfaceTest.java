import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class TextUserInterfaceTest.
 *
 * @author Bianchi Arianna & Onciu Razvan Petrica  
 * @version 2020
 */
public class TextUserInterfaceTest {
    
    @Test
    public void test() {
        final TextUserInterface tui = new TextUserInterface("The BlackPearl");
        assertEquals("Welcome to The BlackPearl", tui.getTitle());     
        
    }
    
}
