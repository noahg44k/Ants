package logic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.UIManager;

import clickable.AntButton;
import clickable.AntPanel;
import clickable.AutomateAntsButton;
import clickable.BuildAntHillButton;
import timers.PassiveHoneyTimer;

public class Game extends JFrame{

    //ant stuff
    public static int numOfAnts = 0;
    public static JLabel antName = new JLabel("Click on an ant!");
    public static JLabel antCount = new JLabel("Ants: " + numOfAnts);
    public static JRadioButton autoAnts = new JRadioButton("Automate Ants");

    public static JFrame frame = new JFrame("Cookies and Cream Simulator");
    public static AntPanel panel = new AntPanel("src/images/Dirt.jpg"); //main area panel
    public static JPanel bp = new JPanel(); //bottom panel
    public static int numOfHoney = 1;
    public static JLabel honeyCount = new JLabel("Honey: " + numOfHoney);

    private static boolean listenerAdded = false;


    private static void createWindow() 
    {       
        //Create and set up the window.     
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
        frame.setResizable(false);
        frame.setSize(new Dimension(600, 500));  
        frame.setLayout(null);
        frame.add(panel); 
        frame.add(bp);

        //panels
        panel.setLayout(null);
        bp.setLayout(null);

        antName.setPreferredSize(new Dimension(300, 100));
        antCount.setPreferredSize(new Dimension(300, 100)); 
        honeyCount.setPreferredSize(new Dimension(300, 100));  
        bp.add(antName, BorderLayout.CENTER);     
        bp.add(antCount, BorderLayout.CENTER);       
        bp.add(honeyCount, BorderLayout.CENTER);  
        bp.setBackground(Color.lightGray);

        JButton button = new JButton("More ants");
        JButton anthill = new JButton("Ant Hill");
        BuildAntHillButton build = new BuildAntHillButton();      
        AntButton b = new AntButton();      
        button.addMouseListener(b);
        button.setBackground(Color.GRAY);
        button.setFocusPainted(false);
        bp.add(button);
        anthill.addMouseListener(build);
        anthill.setBackground(Color.GRAY);
        anthill.setFocusPainted(false);
        bp.add(anthill);

        //Display the window.       
        frame.setLocationRelativeTo(null);     
        frame.setVisible(true);    

        //setting bounds of everything
        Insets insets = frame.getInsets();
        Dimension size = button.getPreferredSize();
        panel.setBounds(0, 0, frame.getWidth() - insets.right - insets.left, 400);
        bp.setBounds(0, frame.getSize().height - 100, frame.getSize().width, frame.getSize().height - (frame.getSize().height - 100));
        button.setBounds(20 + insets.left, 4, size.width, size.height);
        anthill.setBounds(20 + insets.left, 4 + size.height + 4, size.width, size.height);
        antName.setBounds(bp.getWidth() - insets.right - insets.left - 120, 20, size.width, size.height);
        antCount.setBounds(130 + insets.left, 8, size.width, size.height);
        honeyCount.setBounds(130 + insets.left, 28, size.width, size.height);

        PassiveHoneyTimer.start();
    }

    public static void startGame(){
        Generator g = new Generator();

        g.GenerateHoney(panel.getWidth()/2, panel.getHeight()/2);
    }

    //auto generate ants
    public static void autoAnts(){
        //if the specified amount of ants exist
        if(numOfAnts >= 50 && !listenerAdded){
            AutomateAntsButton aa = new AutomateAntsButton();      
            autoAnts.addMouseListener(aa);
            listenerAdded = true;

            // auto make ants is now an option
            Insets insets = frame.getInsets();
            Dimension size = new Dimension(150, 26);
            bp.add(autoAnts);
            autoAnts.setBounds(bp.getWidth() - insets.right - insets.left - 300, 20, size.width, size.height);
            autoAnts.setBackground(null);
            bp.repaint();
        }
    }

    //used for setting default font
    public static void setUIFont (javax.swing.plaf.FontUIResource f){
    java.util.Enumeration keys = UIManager.getDefaults().keys();
    while (keys.hasMoreElements()) {
      Object key = keys.nextElement();
      Object value = UIManager.get (key);
      if (value instanceof javax.swing.plaf.FontUIResource)
        UIManager.put (key, f);
      }
    } 

    public static void main(String[] args) 
    {       
        setUIFont (new javax.swing.plaf.FontUIResource("Century Gothic",Font.BOLD,13));
        createWindow();
        System.out.println("started");
    } 
}