import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.Color.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Character which is a MovingObject
 * Is the abstract class which is extended by the Enemy and Player
 * Holds processes that are used by both
 */

abstract public class Characters extends MovingObject {

    //variable for death
    boolean dead = false;
    //variables for gravity calculation
    protected int g;
    protected int gTime;
    //variables for health
    protected int MAX_HEALTH;
    protected int health;


    public Characters(int x1, int y1, int h1, int w1, ArrayList<GameObject> l) {
        super(x1, y1, h1, w1, l);
        //change the health for whether it is Player versus an enemy
        if (this instanceof Player) {
            dead = false;
            MAX_HEALTH = 300;
        } else if (this instanceof Enemy) {
            MAX_HEALTH = 50;
        }
        health = MAX_HEALTH;

        //initialize to default values
        g = 0;
        gTime = 0;
    }

    //set the health of a Character
    public void setHealth(int n) {
        health = n;
    }

    //get the health of a Character
    public int getHealth() {
        return health;
    }

    //Calculate gravity (quadratic)
    public void gravity() {
        //if player has a bottom intersection with another object, stop gravity from having an impact
        if (botIntxn()) {
            gTime = 0;
            g = 0;
        } else {
            //increment gravity and periodically iterate the value of player acceleration (Every 20)
            gTime++;
            if (gTime % 20 == 0) {
                g += 1;
            }
        }
    }

    //calculations for the gravity to velocity
    public void calcForces() {
        vx = dx;
        vy = dy - g;
    }

}