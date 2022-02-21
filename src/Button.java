import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 * Button which is a GameObject
 * Creates a button which contains an image of the button, and is used in the menu screen
 */
public class Button extends GameObject {

    //variables to hold the booleans of having used the mouse to click on its position
    private boolean pressed;
    private boolean released;
    private boolean isPressable;
    //Images for the button
    private Image image;
    private Image image2;

    //Two constructors which allow for the distinction between the state of a pressed and released button
    public Button(int x, int y, int h, int w, ImageIcon i, ArrayList<GameObject> l) {
        super(x, y, h, w, l);
        pressed = false;
        released = false;
        isPressable = false;
        image = i.getImage();
        list.add(this);
    }

    public Button(int x, int y, int h, int w, ImageIcon i, ImageIcon i2, ArrayList<GameObject> l) {
        super(x, y, h, w, l);
        pressed = false;
        released = false;
        isPressable = true;
        image = i.getImage();
        image2 = i2.getImage();
        list.add(this);
    }

    //Is called when the mouse is clicked
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        //if the mouse is within the bounds of the button, then it has been pressed
        if (mx >= x && mx <= x + w && my >= y && my <= y + h) {
            pressed = true;
        }

    }

    //is called when the mouse is released
    public void mouseReleased(MouseEvent e) {
        //int num = e.getX();
        //when the mouse is released, then the subsequent actions may take place
        if(isPressed()) {
            released = true;
            pressed = false;
        }

    }

    //returns the state of being pressed
    public boolean isPressed() {
        return pressed;
    }

    //returns the state of being released
    public boolean isReleased() {
        return released;
    }

    //draws the button
    public void paint(Graphics2D g2d) {

        //Logic to change the image to look as if it has been pressed using the prior methods
        if (isPressable) {
            if(isPressed()) {
                g2d.drawImage(image2, x, y, null);
            } else {
                g2d.drawImage(image, x, y, null);
            }
        } else {
            g2d.drawImage(image, x, y, null);
        }


    }

    //not used, but inherited
    public void action() {

    }

}
