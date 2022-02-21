import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Makes a Bullet which is a MovingObject
 * Contains the code for an angular based projectile to be fired, which
 * deals damage to enemies
 */
public class Bullet extends MovingObject {

    //original x and y positions
    int oPosX;
    int oPosY;
    //the player to get its positions
    public Player p;
    //mouse x and y
    int mouseX;
    int mouseY;
    //bullet damage
    int damage = 25;

    public Bullet(int x1, int y1, ArrayList<GameObject> l, Player p) {
        super(x1, y1, 5, 5, l);
        //set bullet position to start from the player
        oPosX = p.x;
        oPosY = p.y;
        this.p = p;
    }

    public void calcForces() {
        //calculate the velocity values
        vx = dx;
        vy = dy;
    }

    public void mousePressed(MouseEvent e) {
        //start the bullet in the center of the player
        x = p.x + p.w/2;
        y = p.y + p.h/2;
        //decreases ammunition
        p.fire();

        //get mouse x and y
        mouseX = e.getX();
        mouseY = e.getY();

        //get x and y distance
        double temp1 = mouseY - p.y;
        double temp2 = mouseX - p.x;

        //trigonometry for the angle using the slope
        double temp3 = Math.abs(Math.atan(temp1 / temp2));

        //changing value multiplied by sin and cos changes speed
        dy = (int) (10 * Math.sin(temp3));
        dx = (int) (10 * Math.cos(temp3));

        //keep values positive
        if (mouseX - x < 0) {
            dx = -dx;
        }

        if (mouseY - y > 0) {
            dy = -dy;
        }

    }

    //set damage
    public void setDamage(int n) {
        damage = n;
    }

    //remove bullet after collision
    public void killB() {

        if (leftIntxn() || rightIntxn() || topIntxn() || botIntxn()) {
            list.remove(this);

            Enemy e;
            for(int i = 0; i < list.size(); i++) {
                if(list.get(i) instanceof Enemy) {
                    e = ((Enemy) list.get(i));
                    //check to see if the bullet has collided with the enemy
                    if(singleBotIntxn(e) || singleTopIntxn(e) || singleLeftIntxn(e) || singleRightIntxn(e)) {
                        //iterate kills and health accordingly
                        e.health -= damage;
                        if (e.health <= 0) {
                            p.kills++;
                        }
                    }
                }
            }

        }

    }

    //apply forces to the bullet perpetually
    public void action() {
        calcForces();
        killB();
        increment();
    }

    //draw the bullet as a 10x10 rectangle
    public void paint(Graphics2D g2d) {
        g2d.setColor(Color.black);
        g2d.fillRect(x, y, 10, 10);

    }

}