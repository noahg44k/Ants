package clickable;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import gather_points.AntHill;
import logic.Game;

public class BuildAntHillButton extends MouseAdapter{
    public static boolean placeAntHill = false;
    public static boolean added = false;

    public static MouseAdapter ma = new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        int x = e.getX();
                        int y = e.getY();
                        placeAnthill(x, y);
                    }};

    @Override
    public void mousePressed(MouseEvent e){
        if(!placeAntHill){
            placeAntHill = true;
            CheckAnthillSpot();
        }
        else{
            placeAntHill = false;
        }
    }

    public static void CheckAnthillSpot(){
        System.out.println("Running!");
        if(placeAntHill){
            if(!added){
                Game.panel.addMouseListener(ma);
                added = true;
            }
        }
    }

    private static void placeAnthill(int x, int y){
        AntHill ah = new AntHill();

        Game.panel.add(ah);
        ah.setBounds(x - ah.size/2, y - ah.size/2, ah.size, ah.size);
        Game.panel.repaint();
        placeAntHill = false;
        Game.panel.removeMouseListener(ma);
        added = false;

        System.out.println("New anthill!");
    }
}