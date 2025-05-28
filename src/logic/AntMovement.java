package logic;

import java.util.Random;

import basic_classes.Ant;
import timers.MakeAntHungryTimer;

public class AntMovement {

    //idle movement
    public static void AntMovement(){
        int stepMin = 10;
        int stepMax = 30;
        int stepSize = 2;
        double diagStepSize = 1.41421356237;
        for (Ant ant : AntGenerator.ants) {
            
            int maxX = Game.panel.getSize().width - ant.w;
            int maxY = Game.panel.getSize().height - ant.h;

            //if moving towards honey
            if (ant.movingTowardsHoney) {
                double dx = (ant.targetHoney.x + ant.targetHoney.size/2) - ant.x;
                double dy = (ant.targetHoney.y + ant.targetHoney.size/2) - ant.y;
                double distance = Math.sqrt(dx * dx + dy * dy);

                //when ant touches honey
                if (distance <= (ant.targetHoney.size/2) + ant.w/2) {
                    ant.movingTowardsHoney = false;
                    ant.reachedHoney = true;
                    ant.targetHoney.timesEaten++;
                    ant.hunger += 30;
                    if(ant.hunger > 100){
                        ant.hunger = 100;
                    }
                    Game.numOfHoney++;
                    Game.honeyCount.setText("Honey: " + Game.numOfHoney);
                    ant.targetHoney.ShrinkHoney();
                    ant.targetHoney = null;
                    MakeAntHungryTimer.start(ant);

                //when ant is moving towards honey
                } else {
                    ant.x += (dx / distance) * stepSize;
                    ant.y += (dy / distance) * stepSize;
                    ant.setBounds((int)ant.x, (int)ant.y, ant.w, ant.h);
                    ant.repaint();
                }
            } 
            //all other movement logic
            else 
            {
                if(ant.stopMovement){
                    continue;
                }
                // If done moving in current direction, pick a new one
                if (ant.steps == 0) {
                    Random rand = new Random();
                    ant.direction = rand.nextInt(9);
                    ant.steps = rand.nextInt(stepMin, stepMax);
                }

                // Move one step in current direction
                switch(ant.direction){
                    case 0: ant.y+=stepSize; break; // Down
                    case 1: ant.x+=stepSize; break; // Right
                    case 2: ant.y-=stepSize; break; // Up
                    case 3: ant.x-=stepSize; break; // Left
                    case 4: break; // Stay still
                    case 5: ant.y+=diagStepSize; ant.x+=diagStepSize; break; // down right
                    case 6: ant.y+=diagStepSize; ant.x-=diagStepSize; break; // down left
                    case 7: ant.y-=diagStepSize; ant.x-=diagStepSize; break; //up left
                    case 8: ant.y-=diagStepSize; ant.x+=diagStepSize; break; //up right
                }
                if(ant.x < 0){
                    ant.x = 0;
                    ant.steps = 1;
                }
                if(ant.x > maxX){
                    ant.x = maxX;
                    ant.steps = 1;
                }
                if(ant.y < 0){
                    ant.y = 0;
                    ant.steps = 1;
                }
                if(ant.y > maxY){
                    ant.y = maxY;
                    ant.steps = 1;
                }

                ant.steps--;
                ant.setBounds((int)ant.x, (int)ant.y, ant.w, ant.h);
                ant.repaint();
            }
        }
    }
}
