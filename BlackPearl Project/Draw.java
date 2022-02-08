import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;



/**
 * Is the abtract class for drawing the ship.
 * 
 * @author (il tuo nome) 
 * @version (un numero di versione o una data)
 */
public abstract class Draw extends JComponent {

    protected abstract void paintComponent(final Graphics g) ; 
    
    /**
     * Helper function to draw the ship.
     * This one instead of coloring by cells,
     * colors by width and height.
     * Note that we use drawRect as a kind of border coloring.
     * @param g as the Graphics.
     * @param fillColor as the color to use to fill the rectangle.
     * @param borderColor as the color to use for the border of the rectangle.
     * @param x as the x coordinate.
     * @param y as the y coordinate.
     * @param width as the width of the rectangle.
     * @param height as the height of the rectangle.
     */
    protected void setDrawing(final Graphics g, final Color fillColor, final Color borderColor, 
                           final int x, final int y, final int width, final int height) {
        g.setColor(fillColor);
        g.fillRect(x, y, width, height);
        g.setColor(borderColor);
        g.drawRect(x, y, width, height);
    }
}
