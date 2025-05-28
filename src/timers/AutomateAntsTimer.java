package timers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;
import javax.swing.Timer;

import logic.AntGenerator;
import logic.Game;

public class AutomateAntsTimer {
    
    private static Timer timer;
    
    public static void start(JRadioButton button){
        if(Game.numOfHoney <= 0){
            return;
        }
        if(button.isSelected()){
            int timerDelay = 3000;

            ActionListener taskPerformer = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    AntGenerator g = new AntGenerator();

                    g.CreateAnt();
                }
            };

            timer = new Timer(timerDelay, taskPerformer);
            timer.setRepeats(true);
            timer.start();
        }else {
            if (timer != null && timer.isRunning()) {
                timer.stop();
            }
        }
    }
}
