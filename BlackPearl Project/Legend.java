
import java.awt.Color;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * Set the legend of the game, which is on the right 
 * of the main frame. 
 *
 * @author Arianna Bianchi & Razvan Petrica Onciu.
 * @version 2020.
 */
public class Legend {
    private LinkedHashMap<String, JTextArea> textAreas = new LinkedHashMap<>();
    private LinkedHashMap<String, JLabel> labels = new LinkedHashMap<>();
    private final JPanel panelInfo;

    /**
     * Main constructor that instantiate the fields and
     * fill the textAreas map.
     * @param panelInfo the panel with the description of the legend.
     */
    public Legend(final JPanel panelInfo) {
        this.panelInfo = panelInfo;
        textAreas.put("green" , new JTextArea());
        textAreas.put("red" , new JTextArea());
        textAreas.put("black" , new JTextArea());
        textAreas.put("gold" , new JTextArea());
        textAreas.put("blue" , new JTextArea());
        for (final String key : textAreas.keySet()) {
            textAreas.get(key).setEditable(false);
        }
    }

    /**
     * Set the text area of the class legend. 
     */
    public void setLegendAreaText() {
        int x = 350;
        for (final String key : textAreas.keySet()) {
            textAreas.get(key).setBounds(50, x, 20, 20);
            x = x + 25;
        }
        textAreas.get("green").setBackground(new Color(0, 230, 0));
        textAreas.get("red").setBackground(new Color(255, 0, 0, 200));
        textAreas.get("black").setBackground(Color.BLACK);
        textAreas.get("gold").setBackground(new Color(255, 215, 0));
        textAreas.get("blue").setBackground(new Color(0, 100, 255, 100));
        for (final Map.Entry<String, JTextArea> map: textAreas.entrySet()) {
            panelInfo.add(map.getValue());
        }
        setLegendLabel();
    }

    /**
     * Set the label of the class legend.
     */
    public void setLegendLabel() {
        labels.put("Ship", new JLabel("Ship"));
        labels.put("Sinked cell", new JLabel("Sinked cell"));
        labels.put("Sinked Ship", new JLabel("Sinked Ship"));
        labels.put("Treasure", new JLabel("Treasure"));
        labels.put("Sea", new JLabel("Sea"));

        int x = 350;
        for (final String key : labels.keySet()) {
            labels.get(key).setBounds(100, x, 100, 20);
            x = x + 25;
        }
        for (final Map.Entry<String, JLabel> map: labels.entrySet()) {
            panelInfo.add(map.getValue());
        }
    }
}
