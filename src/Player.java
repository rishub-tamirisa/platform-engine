import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Player extends Characters {

    //image of the player
    public Image i;
    //number of kills and ammount of ammo
    int kills = 0;
    int ammunition = 10;
    //environment builder for access to the current level
    EnvironmentBuilder eb;
    //boolean bg;

    public Player(int x1, int y1, int w1, int h1, ArrayList<GameObject> l, EnvironmentBuilder e/*, boolean beatGame*/) {
        super(x1, y1, w1, h1, l);
        //store the player image
        ImageIcon ok = new ImageIcon("Webp.net-resizeimage.png");
        i = ok.getImage();
        eb = e;
        //bg = beatGame;

    }

    //takes key input for up-down-left-right
    public void keyPressed(KeyEvent e) {
        int num = e.getKeyCode();

        //if (!bg) {
            if (num == KeyEvent.VK_LEFT) {
                dx = -5;
            }
            if (num == KeyEvent.VK_RIGHT) {
                dx = 5;
            }
            if (num == KeyEvent.VK_UP) {
                dy = 5;
            }
            if (num == KeyEvent.VK_DOWN) {
                dy = -5;
            }
            if (num == KeyEvent.VK_R) {
                //checks to make sure that you can reload before reloading
                if (isReloadAvailable()) {
                    reload();
                }
            }
        //}
            //triggers the menu screen because hitting ESC is like dying in-game
            if (num == KeyEvent.VK_ESCAPE) {
                setHealth(0);
            }
    }

    //stop moving once keys are released (up-down-left-right)
    public void keyReleased(KeyEvent e) {
        int num = e.getKeyCode();

            if (num == KeyEvent.VK_LEFT) {
                dx = 0;
            }
            if (num == KeyEvent.VK_RIGHT) {
                dx = 0;
            }
            if (num == KeyEvent.VK_UP) {
                dy = 0;
            }
            if (num == KeyEvent.VK_DOWN) {
                dy = 0;
            }
    }

    //method is perpetually called; health is only restored when the player collides with the powerup
    public void restore() {
        for(int i = 0; i < list.size(); i ++) {
            if (list.get(i) instanceof HealthPowerup) {
                HealthPowerup h = ((HealthPowerup) list.get(i));
                if(singleBotIntxn(h) || singleLeftIntxn(h) || singleRightIntxn(h) || singleTopIntxn(h)) {
                    //increase player health
                    health += h.healthBoost;
                    list.remove(list.get(i));
                }
            }
        }
    }

    //method is perpetually called; level is only increased when player collides with the symbol and it is blue
    public void moveOn() {
        for(int i = 0; i < list.size(); i ++) {
            if (list.get(i) instanceof LevelProgresser) {
                LevelProgresser h = ((LevelProgresser) list.get(i));
                if(singleBotIntxn(h) || singleLeftIntxn(h) || singleRightIntxn(h) || singleTopIntxn(h)) {
                    //increase the level and reset values to save memory
                   eb.level++;
                   eb.reset();
                   //list.remove(list.get(i));
                }
                break;
            }
        }
    }

    //use pre-coded forces
    //method is perpetually called
    public void action() {
        gravity();
        calcForces();
        restore();
        increment();
        //only move on to the next level once the current level has been cleared
        if (completedLevel()) {
            moveOn();
        }

    }

    //if the player kills the required number of enemies, the level is completed
    public boolean completedLevel() {
        return kills == eb.numEnemies;
    }
    //return the ammunition count
    public int getAmmoCount() {
        return ammunition;
    }

    //set ammo back to 10
    public void reload() {
        ammunition = 10;
    }

    //only allow for a reload when ammo is less than 3
    public boolean isReloadAvailable() {
        if (ammunition <= 3) {
            return true;
        } else {
            return false;
        }

    }

    //decrement ammo
    public void fire() {
        ammunition--;
    }

    //draw player
    public void paint(Graphics2D g2d) {

            //draw player health and ammo
            g2d.setColor(Color.black);
            g2d.setFont(new Font("TimesRoman", Font.PLAIN, 11));
            g2d.drawString("Health: " + getHealth(), x - 2, y - 2);
            g2d.setColor(Color.black);
            g2d.drawString("AMMO: " + getAmmoCount(), x - 2, y - 35);

            //display text indicating that a reload is available
            if (isReloadAvailable()) {
                g2d.setColor(Color.red);
                g2d.drawString("RELOAD AVAILABLE", x - 25, y - 55);
            }

            //the enemy defeated text will become green once the required number of enemies have been defeated
            if (!completedLevel()) {
                g2d.setColor(Color.black);
            } else {
                g2d.setColor(Color.green);
            }
            //display the number of enemies defeated for each level at the top right of the screen
            g2d.setFont(new Font("TimesRoman", Font.PLAIN, 20));
            g2d.drawString("Enemies Defeated: " + kills + "/" + eb.numEnemies, 20, 55);

            //g2d.drawImage(i, x, y, null);
            g2d.setColor(Color.blue);
            g2d.fillRect(x, y, w, h);

            //draw the health bar so it is centered above the position of the enemy
            if (getHealth() >= 120) {
                g2d.setColor(Color.green);
                g2d.fillRect((x + getWidth() / 2) - getHealth() / 2, y - 30, MAX_HEALTH - (MAX_HEALTH - getHealth()), 10);
            } else if (getHealth() >= 40 && getHealth() < 120) {
                g2d.setColor(Color.yellow);
                g2d.fillRect((x + getWidth() / 2) - getHealth() / 2, y - 30, MAX_HEALTH - (MAX_HEALTH - getHealth()), 10);
            } else if (getHealth() < 40) {
                g2d.setColor(Color.red);
                g2d.fillRect((x + getWidth() / 2) - getHealth() / 2, y - 30, MAX_HEALTH - (MAX_HEALTH - getHealth()), 10);
            }

    }


}
