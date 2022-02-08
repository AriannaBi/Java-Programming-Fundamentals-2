import java.awt.Color;
import java.awt.Graphics;

/**
 * ShipDraw is the main class that draws each ship.
 *
 * @author Bianchi Arianna & Onciu Razvan Petrica  
 * @version (a version number or a date)
 */
public class ShipDraw extends Draw {

    private final Ship[] shipDraw;
    private final NavalFleet navalFleet;
    private final Grid grid;

    /**
     * Main constructor that takes an existing grid and 
     * instantiate the fields.
     * @param grid as the grid we want to draw.
     */
    public ShipDraw(final Grid grid) {
        super();
        this.grid = grid;
        this.shipDraw = grid.getNavalFleet().getEntireFleet();
        this.navalFleet = grid.getNavalFleet();
    }

    /**
     * Draw the ship.
     * @param g as the graphics.
     */
    public void paintComponent(final Graphics g) {
        if (shipDraw[0] != null) {
            int index = 0;       
            for (final Ship s : shipDraw) {
                if (s == null) {
                    break;
                }
                if (s.isSinked()) { // if the ship is all sinked
                    final int orientation = navalFleet.getOrientationOfShip(index);
                    drawShip(g, orientation, s, Color.BLACK, Color.RED);
                } else { // draw the ship only on the player grid
                    if ("myself".equalsIgnoreCase(grid.getOwner())) {                        
                        final int orientation = navalFleet.getOrientationOfShip(index);
                        drawShip(g, orientation, s, new Color(0, 230, 0), Color.BLACK);
                    }
                    // check if there are cells of the ship that are sinked
                    drawOneCellShipShooted(g, s);
                }
                index++;
            }
        }
        repaint();
    }

    /**
     * Draw the part of the ship that is shooted.
     * @param g as the main graphic
     * @param s as the ship to consider
     */
    private void drawOneCellShipShooted(final Graphics g, final Ship s) {
        int x;
        int y;
        for (int a = 0; a < s.getDimension(); a++) { 
            x = s.getCoordinate()[a][0] * 40; 
            y = s.getCoordinate()[a][1] * 40;
            if (s.getSinked()[a]) {
                setDrawing(g, new Color(255, 0, 0, 200), Color.BLACK, x, y, 40, 40);
            } 
        }
    }

    /**
     * Helper function for paintComponent.
     * @param g as the Graphics.
     * @param orientation as the orientation of the ship.
     * @param s as the ship
     * @param fillColor as the color to use to fill the rectangle
     * @param borderColor as the color to use for the border of the rectangle
     */
    private void drawShip(final Graphics g, final int orientation, final Ship s, 
        final Color fillColor, final Color borderColor) {
        final int size = 40;
        int width = size;
        int height = size;
        int x = s.getCoordinate()[0][0] * size;
        int y = s.getCoordinate()[0][1] * size;
        if (orientation == 0 || orientation == 2)  {
            height = size * s.getDimension();  
            y = helperDrawShip(y, orientation, s);
        } else {
            width = size * s.getDimension();
            x = helperDrawShip(x, orientation, s);
        }
        setDrawing(g, fillColor, borderColor, x, y, width, height);
    }

    /**
     * Helper method for drawing the ship.
     * @param a as the x or y of the ship.
     * @param orientation as the orientation of the ship.
     * @param s as the ship to consider.
     * @return the x or the y of the tail of the ship.
     */
    private int helperDrawShip(final int a, final int orientation, final Ship s) {
        int aCopy = a;
        if (orientation == 0 || orientation == 3) {
            aCopy = aCopy - (40 * (s.getDimension() - 1));
        }
        return aCopy;
    }
}
