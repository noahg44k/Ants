package clickable;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JRadioButton;

import timers.AutomateAntsTimer;

public class AutomateAntsButton extends MouseAdapter{
    @Override
    public void mouseClicked(MouseEvent e){
        JRadioButton button = (JRadioButton)e.getSource();
        AutomateAntsTimer.start(button);
    }
}
