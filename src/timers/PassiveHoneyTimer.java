package timers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import logic.Game;

public class PassiveHoneyTimer {
    public static void start(){
        int timerDelay = 7000;

        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Game.numOfHoney++;
                Game.honeyCount.setText("Honey: " + Game.numOfHoney);
            }
        };
        Timer timer = new Timer(timerDelay, taskPerformer);
        timer.setRepeats(true);
        timer.start();
    }
}
