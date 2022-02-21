import java.awt.*;
import java.util.ArrayList;

/**
 * Platform which is a GameObject
 * allows for the player to move on a platform throughout each level
 */
public class Platform extends GameObject {

    //color of the platform
    private Color color = Color.black;

    public Platform(int x1, int y1, int height1, int width1, ArrayList<GameObject> l) {
        super(x1, y1, height1, width1, l);
    }

    public Platform(int x1, int y1, int height1, int width1, ArrayList<GameObject> l, Color c) {
        super(x1, y1, height1, width1, l);
        //constructor which can take a specified platform color
        color = c;
    }

    //paint a rectangle using the passed dimensions
    public void paint(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.fillRect(getX(), getY(), getWidth(), getHeight());
    }

    //inherited; not needed
    public void action() {

    }

}
