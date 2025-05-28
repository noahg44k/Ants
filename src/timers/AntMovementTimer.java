package timers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import gather_points.HoneyDrop;
import logic.AntMovement;
import logic.Game;
import logic.Generator;

public class AntMovementTimer {
    //ant idle movment timer
    public static void start(){
        int timerDelay = 90;

        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                AntMovement.AntMovement();
                for (int i = 0; i < Generator.honeyDrops.size(); i++) {
                    HoneyDrop honey = Generator.honeyDrops.get(i);

                    if(honey.attractedAnts >= 10){
                        honey.canCheck = false;
                        
                        if(honey.timesEaten == 10){
                            Game.panel.remove(honey);
                            Generator.honeyDrops.remove(honey);
                            Game.panel.repaint();
                            honey = null;
                            continue;
                        }
                    } 
                    if(honey.canCheck){
                        honey.CheckForNearbyAnts();
                    }
                }
            }
        };
        Timer timer = new Timer(timerDelay, taskPerformer);
        timer.start();
    }
}
