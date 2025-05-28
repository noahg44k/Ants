package clickable;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import logic.AntGenerator;
import logic.Game;
import timers.AntMovementTimer;

public class AntButton extends MouseAdapter{
    
    AntGenerator dg = new AntGenerator();

    @Override
    public void mousePressed(MouseEvent e){
        if(Game.numOfHoney > 0){
            dg.CreateAnt();
            if(Game.numOfAnts == 1){
                AntMovementTimer.start();
            }
            Game.autoAnts();
        }
    }
}
