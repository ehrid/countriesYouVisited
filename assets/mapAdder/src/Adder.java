import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.Line;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Adder extends JFrame implements MouseListener {

    private static final String COMA = ",";

    private static final String COLON = ";";

    private static final String DOT = ".";

    BufferedImage image;

    private String points = "";

    /***/
    private static final long serialVersionUID = 3918280985133017486L;

    public Adder() {
        initImage();
        initUI();
        init();
    }

    private void init() {
        setSize(image.getWidth(), image.getHeight());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private ImagePanel panel;

    private void initUI() {
        panel = new ImagePanel(image);
        panel.addMouseListener(this);
        getContentPane().add(panel);

        panel.setLayout(null);

        addButton();
        nextButton();
    }

    private void initImage() {
        try {
            image = ImageIO.read(new File("map/Caribbean.gif"));
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void paintComponent(Graphics g) {

        g.drawImage(image, 0, 0, null);

    }

    private void nextButton() {
        JButton nextRegionPart = new JButton("Next");
        nextRegionPart.setBounds(80, 0, 80, 30);

        nextRegionPart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                points += DOT;
            }
        });

        panel.add(nextRegionPart);
    }

    private void addButton() {
        JButton addButton = new JButton("Add");
        addButton.setBounds(0, 0, 80, 30);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.out.println(points);
                points = "";
            }
        });

        panel.add(addButton);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Adder ex = new Adder();
                ex.setVisible(true);
            }
        });
    }

    @Override
    public void mouseClicked(MouseEvent evt) {
        int x = evt.getXOnScreen() - getX() - 8;
        int y = evt.getYOnScreen() - getY() - 30;

        points += x + COMA + y + COLON;
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

}
