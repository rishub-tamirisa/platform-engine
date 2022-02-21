import java.util.ArrayList;

/**
 * MovingObject which is a GameObject
 * Collision detection engine for the whole game
 */
abstract public class MovingObject extends GameObject {

    //moving objects have a vertical and horizontal speeds represented by vy and vx respectively
    //Dx and dy are these speeds before gravity is applied to them
    protected double vx;
    protected double vy;
    protected double dx;
    protected double dy;

    //constructor
    public MovingObject(int x1, int y1, int h1, int w1, ArrayList<GameObject> l) {
        super(x1, y1, h1, w1, l);
        vx = 0;
        vy = 0;
        dx = 0;
        dy = 0;
    }

    //These methods take a Gameobject and check whether the gameobject is on the perimeter of the movingobject
    public boolean singleBotIntxn(GameObject temp) {
        if (y + h == temp.getY()) {
            if (x + w > temp.getX() && x < temp.getX() + temp.getWidth()) {
                return true;
            }
        }
        return false;
    }

    public boolean singleTopIntxn(GameObject temp) {
        if (y == temp.getY() + temp.getHeight()) {
            if (x + w > temp.getX() && x < temp.getX() + temp.getWidth()) {
                return true;
            }
        }
        return false;
    }

    public boolean singleLeftIntxn(GameObject temp) {
        if (x + w == temp.getX()) {
            if (y + h > temp.getY() && y < temp.getY() + temp.getHeight()) {
                return true;
            }
        }
        return false;
    }

    public boolean singleRightIntxn(GameObject temp) {
        if (x == temp.getX() + temp.getWidth()) {
            if (y + h > temp.getY() && y < temp.getY() + temp.getHeight()) {
                return true;
            }
        }
        return false;
    }

    //These methods use previous methods and check through list to see whether anything is on the perimeter of the moving object
    public boolean botIntxn() {
        boolean temp = false;
        for (int i = 0; i < list.size(); i++) {
            if(singleBotIntxn(list.get(i))) {
                temp = true;
            }
        }
        return temp;
    }

    public boolean topIntxn() {
        boolean temp = false;
        for (int i = 0; i < list.size(); i++) {
            if(singleTopIntxn(list.get(i))) {
                temp = true;
            }
        }
        return temp;
    }

    public boolean leftIntxn() {
        boolean temp = false;
        for (int i = 0; i < list.size(); i++) {
            if(singleLeftIntxn(list.get(i))) {
                temp = true;
            }
        }
        return temp;
    }

    public boolean rightIntxn() {
        boolean temp = false;
        for (int i = 0; i < list.size(); i++) {
            if(singleRightIntxn(list.get(i))) {
                temp = true;
            }
        }
        return temp;
    }


    //just tells whether or not to use gravity
    abstract void calcForces();

    //these move the moving objects every unit of time by their speed per unit of time and stop when they hit something
    public void increment() {

        if (vx > 0) {
            for (int i = 0; i < vx; i++) {
                if (!leftIntxn()) {
                        x++;
                }
            }
        } else if (vx < 0) {
            for (int i = 0; i < -vx; i++) {
                if (!rightIntxn()) {
                        x--;
                }
            }
        }
        if (vy > 0) {
            for (int i = 0; i < vy; i++) {
                if (!topIntxn()) {
                    y--;
                }
            }
        } else if (vy < 0) {
            for (int i = 0; i < -vy; i++) {
                if (!botIntxn()) {
                    y++;
                }
            }
        }

    }



}