package gather_points;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class AntHill extends GatherPoint{
    Color color = new Color(194, 178, 128);

    public int totalAntCapacity = 10;
    public int currentAntCapacity = 10;
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(color);
        g2.fillOval(0, 0, super.size, super.size);
    }
}
