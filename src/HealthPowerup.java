import java.awt.*;
import java.util.ArrayList;

/**
 * HealthPowerup which is a GameObject
 * restors the Players health by the constructor-passed value
 */
public class HealthPowerup extends GameObject {

    int healthBoost;

    public HealthPowerup(int x1, int y1, int w1, int h1, ArrayList<GameObject> l, int bt) {
        super(x1, y1, w1, h1, l);
//        ImageIcon ok = new ImageIcon("Webp.net-resizeimage.png");
//        i = ok.getImage();
        healthBoost = bt;
    }

    //draw the powerup
    public void paint(Graphics2D g2d) {

        //draw the plus symbol
        g2d.setColor(Color.PINK);
        //draws the vertical rectangle
        g2d.fillRect((x + (getWidth() / 2)) - 4, y, 8, h);
        //draws the horizontal rectangle
        g2d.fillRect(x, (y + (getHeight() / 2)) - 4, w, 8);

    }

    //inherited; not needed
    public void action() {
        //
    }

}
