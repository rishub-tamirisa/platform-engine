import java.awt.*;
import java.util.ArrayList;


/**
 * Enemy which is a Character
 * Contains AI code to follow the player and damage it
 */
public class Enemy extends Characters {

    //Player because the enemy has interactions with the Player
    protected Player p;

    public Enemy(int x1, int y1, int w1, int h1, ArrayList<GameObject> l, Player p) {
        super(x1, y1, w1, h1, l);
        this.p = p;

    }

    //4-Zone AI detection code to follow the player
    public void follow() {
        if (p.y > y + h) {
            dy = -2;
        }
        if (p.y + p.h < y) {
            dy = 3;
        }
        if (p.x + p.w < x) {
            dx = -2;
        }
        if (p.x > x + w) {
            dx = 2;
        }



    }
    //Use the inherited methods as well as AI code and damage code to give the enemy the ability to move
    public void action() {
        follow();
        gravity();
        calcForces();
        increment();
        damage();
    }

    //the method is perpetually called, so damage is always checked first before being dealt
    public void damage() {
        if (singleBotIntxn(p) || singleLeftIntxn(p) || singleRightIntxn(p) || singleTopIntxn(p)) {
            p.health--;
        }
    }

    //draw the enemy
    public void paint(Graphics2D g2d) {

        //only draw enemy while alive
        if (getHealth() > 0) {
            //Display text for health and draw enemy body
            g2d.setColor(Color.red);
            g2d.fillRect(x, y, w, h);
            g2d.setColor(Color.black);
            g2d.setFont(new Font("TimesRoman", Font.PLAIN, 11));
            g2d.drawString("Health: " + getHealth(), x - 2, y - 2);

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
        } else {
            //remove enemy from list after it has been defeated
            list.remove(this);
        }
    }
}
