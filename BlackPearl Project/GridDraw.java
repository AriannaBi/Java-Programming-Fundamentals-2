import java.awt.Color;
import java.awt.Graphics;


/**
 * Main class that draws the grid and the ship.
 * 
 * @author Bianchi Arianna & Onciu Razvan Petrica  
 * @version 2020
 */

public class GridDraw extends Draw {

    private final Grid gridDraw;

    /**
     * Main constructor.
     * @param grid as the grid we want to draw.
     */
    public GridDraw(final Grid grid) {
        super();
        this.gridDraw = grid;
    }

    /**
     * Draw the grid.
     * @param g as the graphics.
     */
    public void paintComponent(final Graphics g) { 
        int x = 0;
        int y = 0;  
        final int size = 40;
        for (int i = 0; i < gridDraw.getArrayLength(); i++) {  
            for (int j = 0; j < gridDraw.getArrayLength(); j++) {
                final GridMemory grid_memory = gridDraw.getGridMemory();
                if (grid_memory.isMemorized(i, j)) {
                    setColor(g, new Color(0, 100, 255, 100), i, j, size);
                }
                // Draw the treasure only when discovered.
                if (gridDraw.getTreasure().isPlacedTreasure(i,j)
                    && gridDraw.getGridMemory().isMemorized(i,j)) {
                    setColor(g, new Color(255, 215, 0), i, j, size);
                }
                g.setColor(Color.BLACK);
                g.drawRect(i * size, j * size, size, size);
                x = x + size;
            }
            x = 0;
            y = y + size;
        }
        repaint();
    }
    
    /**
     * Helper method that sets the color of a filled rectangle.
     * @param g as the Graphics component
     * @param color as the color of the rectangle
     * @param i as the x value
     * @param j as the y value
     * @param size as the size of one rectangle
     */
    private void setColor(final Graphics g, final Color color, 
                         final int i, final int j, final int size) {
        g.setColor(color);
        g.fillRect(i * size, j * size, size, size);     
    }

}