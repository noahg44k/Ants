package basic_classes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JComponent;

import gather_points.HoneyDrop;
import logic.AntGenerator;
import logic.Game;
import timers.DecreaseAntHungerTimer;

public class Ant extends JComponent {
    public double x;
    public double y;
    public int w;
    public int h;
    public int speed = 5;
    public Color color;
    public String name = "";
    public int steps = 0;
    public int direction = 0;
    public int hunger = 100;
    public boolean stopMovement = false;
    public boolean movingTowardsHoney = false;
    public boolean reachedHoney = false;
    public HoneyDrop targetHoney;
    public DecreaseAntHungerTimer hungerTimer;

    public Ant(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.name = generateName();
        hungerTimer = new DecreaseAntHungerTimer();
    }

    private boolean isVowel(char letter) {
        switch (letter) {
            case 'a':
                return true;
            case 'e':
                return true;
            case 'i':
                return true;
            case 'o':
                return true;
            case 'u':
                return true;
        }
        return false;
    }

    private String generateName() {
        String name = "";
        Random random = new Random();

        int nameLength = random.nextInt(3, 8);
        ArrayList<Character> letters = new ArrayList<>();

        for (int i = 0; i < nameLength; i++) {
            int randomNumber = random.nextInt(26);
            char randomLetter = (char) (97 + randomNumber);

            if (i > 0) {
                char prevLet = letters.get(i - 1);
                // if the previous letter is a vowel
                if (isVowel(prevLet)) {
                    // regenerate a second letter that is a consonant
                    // THIS DOESNT WORK
                    while (isVowel(randomLetter)) {
                        randomNumber = random.nextInt(26);
                        randomLetter = (char) (97 + randomNumber);
                    }
                }
                // if the previous letter is a consonant
                else {
                    // regenerate a second letter that is a vowel
                    while (!isVowel(randomLetter)) {
                        randomNumber = random.nextInt(27);
                        randomLetter = (char) (97 + randomNumber);
                    }
                }
            }
            letters.add(randomLetter);
        }

        for (char c : letters) {
            name += c;
        }

        return name;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(color);
        g2.fillRect(0, 0, w, h);
    }

    public void Die(){
        this.hungerTimer.stop();
        AntGenerator.ants.remove(this);
        Game.panel.remove(this);
        Game.numOfAnts--;
        Game.antCount.setText("Ants: " + Game.numOfAnts);
        Game.panel.repaint();
    }
}