package timers;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import basic_classes.Ant;

public class DecreaseAntHungerTimer {
    private Timer timer;

    public void start(Ant ant){
        int timerDelay = 1000;

        ActionListener taskPerformer = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ant.hunger == 0){
                    ant.Die();
                    return;
                }
                ant.hunger--;

                double alpha = ant.hunger * 2.55;
                if(alpha > 0){
                    ant.color = new Color(ant.color.getRed(), ant.color.getGreen(), ant.color.getBlue(), (int)alpha);
                }
            }
        };

        timer = new Timer(timerDelay, taskPerformer);
        timer.setRepeats(true);
        timer.start();
    }

    public void stop(){
        timer.stop();
    }
}
