import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.Random;

/**
 * Game class.
 *
 * @author maor biton.
 *  
 */
public class Game {
    private GUI gui;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private KeyboardSensor keyboard;
    /**
     * Const of width and height of the board.
     */
    static final int WIDTH_SCR = 800, HEIGHT_SCR = 600, START_POINT = 0, LOC_Y = 574, LOC_X = 290;

    /**
     * Instantiates a new game.
     */
    public Game() {
        this.environment = new GameEnvironment();
        this.sprites = new SpriteCollection();
        // mack the board and the game object
        this.gui = new GUI("Arkanoid", WIDTH_SCR, HEIGHT_SCR);
        this.keyboard = gui.getKeyboardSensor();
    }

    /**
     * addCollidable - add a Collidable.
     *
     * @param c Collidable
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * addSprite - add a sprite.
     *
     * @param s Sprite
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * initialize - create the Blocks and Ball (and Paddle)
     * and add them to the game.
     */
    public void initialize() {
        Color[] colors = new Color[6];
        int hitPoint;
        for (int j = 1; j < 6; j++) {
            // Preparation of an array of random colors
            colors[j] = randomColor();
            for (int i = 1; i < 14 - j; i++) {
                // First line providing hit values 2
                if (j == 1) {
                    hitPoint = 2;
                } else {
                    hitPoint = 1;
                }
                // Preparation of blocks as required in the exercise
                Block block = new Block(new Rectangle(new Point(785 - (i * 33), 75 + (j * 15)),
                        33, 15), hitPoint, colors[j]);
                block.addToGame(this);
            }
        }
        // Preparation of paddle
        Paddle paddle = new Paddle(keyboard, new Rectangle(new Point(LOC_X, LOC_Y), 130, 10),
                Color.yellow);
        paddle.addToGame(this);
        for (int i = 1; i < 3; i++) {
            // Preparing a balls
            Ball ball = new Ball(new Point((i * 60) + 270, 200), 5, Color.white, this.environment);
            ball.setVelocity(0, 4);
            ball.addToGame(this);
        }
        frame();
    }


    /**
     * run - start the animation loop.
     */
    public void run() {
        Sleeper sleeper = new Sleeper();
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            d.setColor(Color.BLUE);
            d.fillRectangle(0, 0, WIDTH_SCR, HEIGHT_SCR);
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();
            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    /**
     * randomColor - make a random color.
     *
     * @return the color
     */
    public static Color randomColor() {
        Random rand = new Random();
        float r = rand.nextFloat(); // get float in range 0-1
        float g = rand.nextFloat(); // get float in range 0-1
        float b = rand.nextFloat(); // get float in range 0-1
        Color randomColor;
        randomColor = new Color(r, g, b);
        return randomColor;
    }

    /**
     * frame -Prepare the squares around the frame.
     */
    public void frame() {
        for (int i = 0; i < 2; i++) {
            Block block = new Block(new Rectangle(new Point(0, i * (HEIGHT_SCR - 15)),
                    WIDTH_SCR, 15), 0, Color.gray);
            block.addToGame(this);
        }
        for (int i = 0; i < 2; i++) {
            Block block = new Block(new Rectangle(new Point(i * (WIDTH_SCR - 15), 0),
                    15, HEIGHT_SCR), 0, Color.gray);
            block.addToGame(this);
        }
    }
}
