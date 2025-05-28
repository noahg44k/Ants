package clickable;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import basic_classes.Ant;
import logic.Game;

public class AntDrag extends MouseAdapter {
    private int offsetX, offsetY;
    private boolean dragging = false;

    //lock ant position to cursor position
    @Override
    public void mousePressed(MouseEvent e) {
        offsetX = e.getX();
        offsetY = e.getY();
        dragging = true;
        Ant ant = (Ant)e.getSource();
        ant.stopMovement = true;
        Game.antName.setText(ant.name);
        Game.antName.setForeground(ant.color);
    }

    //unlock ant position from cursor position
    @Override
    public void mouseReleased(MouseEvent e) {
        dragging = false;
        Ant ant = (Ant)e.getSource();
        ant.stopMovement = false;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (!dragging) return;
        Ant ant = (Ant) e.getSource();

        // New top-left position based on mouse cursor
        int newX = ant.getX() + e.getX() - offsetX;
        int newY = ant.getY() + e.getY() - offsetY;

        // Update logical position
        ant.x = newX;
        ant.y = newY;

        // Update actual screen position
        ant.setBounds(newX, newY, ant.w, ant.h);
        ant.repaint();
    }
}