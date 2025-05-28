package timers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import basic_classes.Ant;

public class MakeAntHungryTimer {
//after a certain delay, ant will be able to eat honey again
    public static void start(Ant ant){
        int timerDelay = 10000;

        ActionListener taskPerformer = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ant.reachedHoney = false;
            }
        };

        Timer timer = new Timer(timerDelay, taskPerformer);
        timer.setRepeats(false);
        timer.start();
    }
}
