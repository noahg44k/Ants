package clickable;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import logic.Game;
import logic.Generator;

public class AntPanel extends JPanel implements MouseListener{


    private Image image;

    
    public AntPanel(String imagePath) {
        addMouseListener(this);
        try {
            image = ImageIO.read(new File(imagePath));
        } catch (IOException ex) {
            ex.printStackTrace();
            // Handle the exception as needed (e.g., show an error message or use a default image)
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
            if(Game.numOfHoney > 4){
                Generator g = new Generator();

                g.GenerateHoney(e.getX(), e.getY());
                Game.numOfHoney-=5;
                Game.honeyCount.setText("Honey: " + Game.numOfHoney);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
             g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this); // Scale image to panel size
        }
    }
}
