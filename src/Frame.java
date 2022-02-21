
import javax.swing.*;

/**
 * Frame, which has an EnvironmentBuilder and essentially just creates the frame
 */
public class Frame {

    public static void main (String[] args) {

        //Make a game instance
        EnvironmentBuilder e = new EnvironmentBuilder();
        //add the environment builder to the frame within environment builder
        e.frame.add(e);
        //close when the red x is hit on the window
        e.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //define dimensions
        e.frame.setSize(1000, 800);
        //make window visible
        e.frame.setVisible(true);
    }
}

