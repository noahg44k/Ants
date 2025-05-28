package gather_points;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import basic_classes.Ant;
import logic.AntGenerator;

//BUG WHERE ANT GETS HONEY ONLY ONCE EVER

public class HoneyDrop extends GatherPoint{
    Color color = new Color(255, 195, 11, 100);
    public boolean canCheck = true; // can check for nearby dots
    public int attractedAnts = 0;
    public int timesEaten = 0;

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(color);
        g2.fillOval(0, 0, super.size, super.size);
    }

    public void CheckForNearbyAnts(){
        for(Ant ant : AntGenerator.ants){
            double dx = ant.x - (this.getX() + this.size/2);
            double dy = ant.y - (this.getY() + this.size/2);
            double distance = Math.sqrt(dx * dx + dy * dy);

            if (distance <= this.attractRadius && !ant.reachedHoney) {
                if(!ant.movingTowardsHoney){
                    this.attractedAnts++;
                    ant.movingTowardsHoney = true;
                    ant.targetHoney = this;
                }
            }
        }
    }

    public void ShrinkHoney(){
        int centerX = this.getX() + this.getWidth()/2;
        int centerY = this.getY() + this.getHeight()/2;

        this.size = Math.max(4, this.size - 2); // shrink

        int newX = centerX - this.size/2;
        int newY = centerY - this.size/2;

        this.setBounds(newX, newY, this.size, this.size);
        this.repaint();
    }
}
