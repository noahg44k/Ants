package logic;

import java.util.ArrayList;

import gather_points.HoneyDrop;

public class Generator{

    public static ArrayList<HoneyDrop> honeyDrops = new ArrayList<>();

    public void GenerateHoney(int x, int y){
        HoneyDrop honey = new HoneyDrop();
        honey.x = x - honey.size/2;
        honey.y = y - honey.size/2;
        Game.panel.add(honey);
        honey.setBounds(honey.x, honey.y, honey.size, honey.size);
        honey.repaint();

        honeyDrops.add(honey);
    }
}
