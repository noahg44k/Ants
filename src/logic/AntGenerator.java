package logic;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import basic_classes.Ant;
import basic_classes.Tuple;
import clickable.AntDrag;

public class AntGenerator{

    public static ArrayList<Ant> ants = new ArrayList<>();

    public Tuple RandomXY(){
        Random rand = new Random();

        int x = rand.nextInt(Game.panel.getSize().width);
        int y = rand.nextInt(Game.panel.getSize().height);
        Tuple XY = new Tuple(x, y);
        return XY;
    }
    public Tuple AntSize(){
        Random rand = new Random();
        int width;
        int height;

        //generate huge ant
        int hugeAntChance = rand.nextInt(8);
        if(hugeAntChance == 1){
            width = rand.nextInt(30, 35);
        }
        else{
            //generate regular ant
            width = rand.nextInt(20, 25);
        }

        int minHeight = Math.max(4, width - 1);
        int maxHeight = width + 2;
        height = rand.nextInt(minHeight, maxHeight); // maxHeight is exclusive
        Tuple XY = new Tuple(width, height);
        return XY;
    }

    private Color RandomAntColor(){
        Random rand = new Random();
        int color = rand.nextInt(80);
        Color col = new Color(color, color, color, 255);
        return col;
    }

    public void CreateAnt(){
        int dotWidth = AntSize().first;
        int dotHeight = AntSize().second;
        int dotX = RandomXY().first;
        int dotY = RandomXY().second;

        while(dotX > Game.panel.getSize().width - dotWidth){
            dotX = RandomXY().first;
        }

        Ant guy = new Ant(dotX, dotY, dotWidth, dotHeight);
        guy.color = RandomAntColor();
        //guy.setBounds(0, 0, Window.panel.getWidth(), Window.panel.getHeight());
        guy.setBounds(dotX, dotY, dotWidth, dotHeight);

        AntDrag controller = new AntDrag();
        guy.addMouseListener(controller);
        guy.addMouseMotionListener(controller);

        ants.add(guy);
        Game.panel.add(guy);   // Add to the visible panel
        guy.repaint();  // Trigger repaint to show it
        
        Game.numOfAnts++;
        Game.antCount.setText("Ants: " + Game.numOfAnts);
        Game.numOfHoney--;
        Game.honeyCount.setText("Honey: " + Game.numOfHoney);
        guy.hungerTimer.start(guy);
    }
}