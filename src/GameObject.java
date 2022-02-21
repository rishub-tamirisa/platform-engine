import java.awt.*;
import java.util.ArrayList;

abstract public class GameObject {

    //x and y values
    protected int x;
    protected int y;
    //width and height values
    protected int h;
    protected int w;
    //list of all instances within the game
    protected ArrayList<GameObject> list;

    public GameObject(int x1, int y1, int h1, int w1, ArrayList<GameObject> l) {
        x = x1;
        y = y1;
        h = h1;
        w = w1;
        list = l;
        //always add an object in the game to the list
        list.add(this);
    }

    //getters and setters for x,y,width,height
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHeight() {
        return h;
    }

    public int getWidth() {
        return w;
    }

    //abstract paint and action methods as different objects look and behave differently
    abstract void paint(Graphics2D g2d);

    abstract void action();

}
