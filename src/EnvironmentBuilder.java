import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 * EnvironmentBuilder class which is a JPanel (needs to display items using Swing)
 * - culminates everything
 */
public class EnvironmentBuilder extends JPanel implements ActionListener {

    //Timer used for the firing of perpetually called methods
    Timer t;
    //Menu screen buttons
    Button b1;
    Button b2;
    Button b3;
    //Platforms and level bounds
    Platform ground;
    Platform leftWall;
    Platform rightWall;
    Platform ceiling;
    Platform p1;
    Platform p2;
    Platform p3;
    Platform p4;
    Platform p5;
    Platform p6;
    //Important ArrayList which holds all objects in the game
    ArrayList<GameObject> gameInstances = new ArrayList<GameObject>();
    //The player
    Player player;
    //A healthpowerup
    HealthPowerup healthPowerup;
    //A levelprogresser
    LevelProgresser levelProgresser;
    //the current level
    int level = 0;
    //Frame for displaying everything in a window
    JFrame frame = new JFrame("Game");
    //current gameTick
    double currentTick = 0.0;
    //number of enemies that have been spawned in
    int enemyCount = 0;
    //number of enemies that need to be defeated for a particular level using some arbitrary level calculation
    int numEnemies = level + 3;
    //images for the menu screen buttons
    ImageIcon i1 = new ImageIcon("button_back.png");
    ImageIcon i2 = new ImageIcon("button_back (1).png");
    ImageIcon i3 = new ImageIcon("button_done.png");
    ImageIcon i4 = new ImageIcon("button_done (1).png");
    ImageIcon i5 = new ImageIcon("button_instructions.png");
    ImageIcon i6 = new ImageIcon("button_instructions (1).png");
    ImageIcon i7 = new ImageIcon("button_start.png");
    ImageIcon i8 = new ImageIcon("button_start (1).png");
    ImageIcon i9 = new ImageIcon("button_box-warrior.png");
    ImageIcon i10 = new ImageIcon("Instructions.jpg");


    public EnvironmentBuilder() {
        //display menu upon startup
        menu();

        t = new Timer(5, this);
        t.start();

        //add listeners for key presses and mouse presses
        addKeyListener(new ActListener());
        addMouseListener(new MouListener());
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }

    //display the menu and its buttons and reset current tick and level
    public void menu() {
        currentTick = 0;
        level = 0;
        enemyCount = 0;
        p1 = new Platform(0, 0, 800, 1000, gameInstances, Color.red);
        b1 = new Button(400, 300, 40, 199, i7, i8, gameInstances);
        b2 = new Button(400, 500, 40, 199, i5, i6, gameInstances);
        b3 = new Button(275, 50, 50, 199, i9, gameInstances);
        //b3.setText("");
    }

    //display instructions screen
    public void instructions() {
        level = -1;
        //p1 = new Platform(0, 0, 800, 1000, gameInstances, new Color(24, 24, 250));
        b1 = new Button(400, 700, 40, 199, i1, i2, gameInstances);
        b2 = new Button(0, 0, 500, 400, i10, gameInstances);
        //b2 = new Button(400, 300, 40, 200, "START", gameInstances);
    }

    //instantiate every game object for this particular level
    public void game1() {
        level = 1;
        enemyCount = 0;
        numEnemies = level + 3;
        ground = new Platform(0, 740, 40, 1000, gameInstances);
        leftWall = new Platform(0, 0, 800, 20, gameInstances);
        rightWall = new Platform(980, 0, 800, 20, gameInstances);
        ceiling = new Platform(0, 0, 20, 1000, gameInstances);
        p1 = new Platform(0, 300, 40, 300, gameInstances);
        p2 = new Platform(700, 300, 40, 300, gameInstances);
        p3 = new Platform(360, 400, 40, 300, gameInstances);
        p4 = new Platform(0, 500, 40, 300, gameInstances);
        p5 = new Platform(700, 500, 40, 300, gameInstances);
        p6 = new Platform(360, 200, 40, 300, gameInstances);
        player = new Player(360, 350, 40, 40, gameInstances, this);
        healthPowerup = new HealthPowerup(495, 160, 30, 30, gameInstances, 100);
        levelProgresser = new LevelProgresser(575, 160, 30, 30, gameInstances);
    }

    //instantiate every game object for this particular level
    public void game2() {
        level = 2;
        enemyCount = 0;
        currentTick = 0;
        numEnemies = level * 3;
        ground = new Platform(0, 740, 40, 1000, gameInstances);
        leftWall = new Platform(0, 0, 800, 20, gameInstances);
        rightWall = new Platform(980, 0, 800, 20, gameInstances);
        ceiling = new Platform(0, 0, 20, 1000, gameInstances);
        p1 = new Platform(0, 100, 40, 300, gameInstances);
        p2 = new Platform(100, 200, 40, 300, gameInstances);
        p3 = new Platform(200, 300, 40, 300, gameInstances);
        p4 = new Platform(300, 400, 40, 300, gameInstances);
        p5 = new Platform(400, 500, 40, 300, gameInstances);
        p6 = new Platform(500, 200, 40, 300, gameInstances);
        player = new Player(360, 350, 40, 40, gameInstances, this);
        healthPowerup = new HealthPowerup(495, 160, 30, 30, gameInstances, 100);
        levelProgresser = new LevelProgresser(575, 160, 30, 30, gameInstances);

    }

    //instantiate every game object for this particular level
    public void game3() {
        level = 3;
        enemyCount = 0;
        currentTick = 0;
        numEnemies = level * 2 + 3;
        ground = new Platform(0, 740, 40, 1000, gameInstances);
        leftWall = new Platform(0, 0, 800, 20, gameInstances);
        rightWall = new Platform(980, 0, 800, 20, gameInstances);
        ceiling = new Platform(0, 0, 20, 1000, gameInstances);
        p1 = new Platform(0, 100, 40, 300, gameInstances);
        p2 = new Platform(600, 200, 40, 150, gameInstances);
        p3 = new Platform(300, 300, 40, 300, gameInstances);
        p4 = new Platform(100, 400, 40, 150, gameInstances);
        p5 = new Platform(400, 500, 40, 300, gameInstances);
        p6 = new Platform(200, 200, 40, 150, gameInstances);
        new Platform(800, 700, 40, 150, gameInstances);

        player = new Player(360, 350, 40, 40, gameInstances, this);
        healthPowerup = new HealthPowerup(495, 160, 30, 30, gameInstances, 100);
        levelProgresser = new LevelProgresser(575, 160, 30, 30, gameInstances);

    }

    //instantiate every game object for this particular level
    public void game4() {
        level = 4;
        enemyCount = 0;
        currentTick = 0;
        numEnemies = level * 3 + 4;
        ground = new Platform(0, 740, 40, 1000, gameInstances);
        leftWall = new Platform(0, 0, 800, 20, gameInstances);
        rightWall = new Platform(980, 0, 800, 20, gameInstances);
        ceiling = new Platform(0, 0, 20, 1000, gameInstances);
        p1 = new Platform(0, 100, 40, 300, gameInstances);
        p2 = new Platform(100, 200, 40, 150, gameInstances);
        p3 = new Platform(500, 300, 40, 300, gameInstances);
        p4 = new Platform(400, 400, 40, 150, gameInstances);
        p5 = new Platform(200, 500, 40, 300, gameInstances);
        p6 = new Platform(600, 200, 40, 150, gameInstances);
        new Platform(98550, 600, 40, 150, gameInstances);

        player = new Player(360, 350, 40, 40, gameInstances, this);
        healthPowerup = new HealthPowerup(430, 160, 30, 30, gameInstances, 100);
        levelProgresser = new LevelProgresser(575, 160, 30, 30, gameInstances);

    }

    //instantiate every game object for this particular level
    public void game5() {
        level = 5;
        enemyCount = 0;
        currentTick = 0;
        numEnemies = 1;//level * 2 + 2;
        ground = new Platform(0, 740, 40, 1000, gameInstances);
        leftWall = new Platform(0, 0, 800, 20, gameInstances);
        rightWall = new Platform(980, 0, 800, 20, gameInstances);
        ceiling = new Platform(0, 0, 20, 1000, gameInstances);
        /*p1 = new Platform(0, 100, 40, 300, gameInstances);
        p2 = new Platform(100, 600, 40, 150, gameInstances);
        p3 = new Platform(200, 500, 40, 300, gameInstances);
        p4 = new Platform(600, 300, 40, 150, gameInstances);
        p5 = new Platform(500, 400, 40, 300, gameInstances);
        p6 = new Platform(700, 600, 40, 150, gameInstances);*/
        new Platform(450, 400, 40, 100, gameInstances);

        player = new Player(360, 350, 40, 40, gameInstances, this);
        healthPowerup = new HealthPowerup(485, 350, 30, 30, gameInstances, 100);
        levelProgresser = new LevelProgresser(485, 450, 30, 30, gameInstances);

    }

    //winning screen
    public void won() {
        currentTick = 0;
        p1 = new Platform(0, 0, 800, 1000, gameInstances, Color.red);
        b1 = new Button(400, 200, 40, 199, i3, i4, gameInstances);
        //Player is in the winning screen because the user needs to be able to
        //go back to the menu and the player holds the key listeners;
        //NOTE: player functionality is disabled when reaching this screen
        //player = new Player(360, 350, 40, 40, gameInstances, this, true);
    }

    //paint everything (perpetually called method)
    public void paint(Graphics g) {
        //call the super paint
        super.paint(g);
        //make a 2D graphics object
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.black);
        g2d.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        //return minutes + " : " + seconds + " : " + milliseconds;
        //g2d.drawString("Current Tick: " + readable, 20, 40);
        //g2d.setFont(new Font("TimesRoman", Font.PLAIN, 20));

        //loop object
        GameObject p;
        for (int i = 0; i < gameInstances.size(); i++) {
            p = gameInstances.get(i);
            //paint objects using their defined paint methods
            p.paint(g2d);
        }

    }

    @Override
    //controls button press actions and level control
    public void actionPerformed(ActionEvent e) {
        //title screen buttons
        if (level == -1) {
            if (b1.isReleased()) {
                level = 0;
                reset();
            }
        }
        //button for starting the game
        if (level == 0) {
            if (b1.isReleased()) {
                level = 1;
                reset();
            }
            //button for reverting back
            if (b2.isReleased()) {
                level = -1;
                reset();


            }
        }

        //go back to menu after done screen
        if (level == 6) {
            if (b1.isReleased()) {
                level = 0;
                reset();
            }
        }

        //if in game
        if (level > 0 && level < 6) {
            //call the action methods for all objects in the game
            GameObject temp;
            for (int i = 0; i < gameInstances.size(); i++) {
                temp = gameInstances.get(i);
                temp.action();
            }
            //FRAME REFRESH CALC ZONE
            currentTick++;

            //if the tick is divisible by 650 (arbitrarily chosen value)
            if (currentTick % 650 == 0.0) {
                //while the level must still be completed
                if (!player.completedLevel() && enemyCount != numEnemies) {
                    //increment the number of enemies
                    if (level == 5 ) {
                        enemyCount++;
                        int ranX = (int) (Math.random() * 900) + 100;
                        int ranY = (int) (Math.random() * 600) + 100;
                        Enemy boss = new Enemy(ranX, ranY, 100, 100, gameInstances, player);
                        boss.setHealth(1000);
                    } else {
                        enemyCount++;
                        //System.out.println(enemyCount + " " + numEnemies);
                        //generate random spawn positions for enemies
                        int ranX = (int) (Math.random() * 900) + 100;
                        int ranY = (int) (Math.random() * 600) + 100;
                        //make a new enemy at the random position
                        Enemy levelEnemy = new Enemy(ranX, ranY, 40, 40, gameInstances, player);

                        //control the health of enemies based on the level
                        if (level > 1) {
                            levelEnemy.setHealth(levelEnemy.health + level * 15);
                        }
                    }

                } else {
                    //System.out.println("Reached");
                }

            }

            //go back to menu once dead or ESC key pressed
            if (player.getHealth() <= 0) {
                level = 0;
                reset();

            }
        }

        repaint();

    }

    //nullify all values and remove them and reset the list and level
    public void reset() {

        //nullify values
        for (int i = 0; i < gameInstances.size(); i++) {
            gameInstances.set(i, null);
            gameInstances.remove(i);
            i--;
        }

        //call the corresponding level depending on the level variable
        switch (level) {
            case -1:
                instructions();
                break;
            case 0:
                menu();
                break;
            case 1:
                game1();
                break;
            case 2:
                game2();
                break;
            case 3:
                game3();
                break;
            case 4:
                game4();
                break;
            case 5:
                game5();
                break;
            case 6:
                won();
                break;
        }


    }

    //private class to hold key functions
    private class ActListener extends KeyAdapter {
        //call the player key press method when a key is pressed
        public void keyPressed(KeyEvent e) {
            if (level > 0) {
                player.keyPressed(e);
            }
        }

        //call the player key release method when a key is released
        public void keyReleased(KeyEvent e) {
            if (level > 0) {
                player.keyReleased(e);
            }
        }
    }

    //private class to hold Mouse functions
    private class MouListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        //call the button mousePressed methods and bullet mousePressed when the mouse is pressed
        @Override
        public void mousePressed(MouseEvent e) {

            //allow for the mouse to be pressed on each level screen
            if (level == -1) {
                b1.mousePressed(e);
            }
            if (level == 0) {
                b1.mousePressed(e);
                b2.mousePressed(e);
            } else if (level > 0 && level < 6) {
                //when in a game level, create a bullet and call its mousePressed method, while the player has ammo
                if (player.getAmmoCount() > 0) {
                    new Bullet(player.x, player.y, gameInstances, player).mousePressed(e);
                }
            }
            if (level == 6) {
                b1.mousePressed(e);
            }
        }

        //call the button mouseReleased methods and bullet mouseReleased when the mouse is pressed
        @Override
        public void mouseReleased(MouseEvent e) {
            //allow for the mouse to be released on each level screen
            if (level == -1) {
                b1.mouseReleased(e);
            }
            if (level == 0) {
                b1.mouseReleased(e);
                b2.mouseReleased(e);
            }
            if (level == 6) {
                b1.mouseReleased(e);
            }
        }

        ///////////////// NOT USED //////////////////////////
        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }

}
