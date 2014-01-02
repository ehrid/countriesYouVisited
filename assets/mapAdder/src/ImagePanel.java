import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class ImagePanel extends JPanel {

    /***/
    private static final long serialVersionUID = -986206900479234990L;

    private BufferedImage img;

    public ImagePanel(BufferedImage img) {
        this.img = img;
    }

    public void paintComponent(Graphics g) {

        g.drawImage(img, 0, 0, null);

    }

}
