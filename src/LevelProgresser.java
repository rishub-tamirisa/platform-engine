import java.awt.*;
import java.util.ArrayList;

public class LevelProgresser extends GameObject {

   //meed access to player to know whether the level has been completed
    GameObject p;

    public LevelProgresser(int x1, int y1, int w1, int h1, ArrayList<GameObject> l) {
        super(x1, y1, w1, h1, l);
//        ImageIcon ok = new ImageIcon("Webp.net-resizeimage.png");
//        i = ok.getImage();
        //level++;
        //iterate through list to get the player
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i) instanceof Player) {
                p = l.get(i);
                break;
            }
        }

    }

    //paint the powerup
    public void paint(Graphics2D g2d) {
        //if the level is completed, it is blue
        if (((Player) p).completedLevel()) {
            g2d.setColor(Color.blue);
            //draws the vertical rectangle
            g2d.fillRect((x + (getWidth() / 2)) - 4, y, 8, h);
            //draws the horizontal rectangle
            g2d.fillRect(x, (y + (getHeight() / 2)) - 4, w, 8);
        } else {
            //if the level is not completed it is gray
            g2d.setColor(Color.gray);
            //draws the vertical rectangle
            g2d.fillRect((x + (getWidth() / 2)) - 4, y, 8, h);
            //draws the horizontal rectangle
            g2d.fillRect(x, (y + (getHeight() / 2)) - 4, w, 8);
        }
    }

    //inherited; not needed
    public void action() {

    }

}
