import java.awt.*;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class AddObjects {
    public void addObjects(JComponent component, JPanel panel, GridBagLayout layout, GridBagConstraints gbc, int x, int y, int width, int height) {
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = width;
        gbc.gridheight = height;
        layout.setConstraints(component, gbc);
        panel.add(component);
    }
}
