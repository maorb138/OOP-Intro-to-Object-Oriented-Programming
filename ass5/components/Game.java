package components;

import around.Collidable;
import around.Counter;
import around.GameEnvironment;
import around.HitListener;
import around.Sprite;
import around.SpriteCollection;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import geometry.Point;
import geometry.Rectangle;

import java.awt.Color;
import java.util.Random;

/**
 * components.Game class.
 *
 * @author maor biton.
 *  
 */
public class Game {
    private GUI gui;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private KeyboardSensor keyboard;
    private Counter remainedBlocks;
    private Counter remainedBall;
    private Counter score;
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
        this.remainedBlocks = new Counter(0);
        this.remainedBall = new Counter(3);
        this.score = new Counter(0);

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
        HitListener scoreTrack = new ScoreTrackingListener(this.score);
        HitListener liveBlocks = new BlockRemover(this, this.remainedBlocks);
        HitListener liveBalls = new BallRemover(this, this.remainedBall);
        Block lowerBorder = new Block(new Rectangle(new Point(0, (HEIGHT_SCR)),
                WIDTH_SCR, 15), 0, Color.white);
        lowerBorder.addToGame(this);
        lowerBorder.addHitListener(liveBalls);
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
                Block block = new Block(new Rectangle(new Point(785 - (i * 40), 125 + (j * 20)),
                        40, 20), hitPoint, colors[j]);
                this.remainedBlocks.increase(1);
                block.addHitListener(liveBlocks);
                block.addToGame(this);
                block.addHitListener(scoreTrack);
                frame();
            }
        }
        ScoreIndicator showScore = new ScoreIndicator(this.score);
        showScore.addToGame(this);
    }


    /**
     * run - start the animation loop.
     */
    public void run() {
        if (this.remainedBlocks.getValue() == 0) {
            System.out.println("win the game and the score is: " + Integer.toString(this.score.getValue()));
            gui.close();
        }
        if (this.remainedBall.getValue() == 0) {
            System.out.println("You lost all the balls and the score is: " + Integer.toString(this.score.getValue()));
            gui.close();
        }
        letsPlay();
    }

    /**
     * letsPlay -start loop of game.
     */
    public void letsPlay() {
        Paddle paddle = this.preparGame();
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
            if (this.remainedBlocks.getValue() == 0) {
                this.score.increase(100);
                run();
            }
            if (this.remainedBall.getValue() == 0) {
                paddle.removeFromGame(this);
                run();
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
        for (int i = 0; i < 1; i++) {
            Block block = new Block(new Rectangle(new Point(0, 15),
                    WIDTH_SCR, 15), 0, Color.gray);
            block.addToGame(this);
        }
        for (int i = 0; i < 2; i++) {
            Block block = new Block(new Rectangle(new Point(i * (WIDTH_SCR - 15), 20),
                    15, HEIGHT_SCR), 0, Color.gray);
            block.addToGame(this);
        }
    }

    /**
     * removeCollidable - remove a around.Collidable.
     *
     * @param c around.Collidable
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * removeSprite - remove a sprite.
     *
     * @param s around.Sprite
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * preparGame - Preparing the game making paddle and balls.
     *
     * @return the paddle
     */
    public Paddle preparGame() {
        // Preparation of paddle
        Paddle paddle = new Paddle(keyboard, new Rectangle(new Point(WIDTH_SCR / 2, HEIGHT_SCR - 12), 100, 20),
                Color.yellow);
        paddle.addToGame(this);
        for (int i = 1; i < 4; i++) {
            // Preparing a balls
            Ball ball = new Ball(new Point(Math.round(WIDTH_SCR / 2) + 30 * i, HEIGHT_SCR / 2),
                    4, Color.white, this.environment);
            ball.setVelocity(0, 4);
            this.remainedBall.increase(0);
            ball.addToGame(this);
        }
        return paddle;
    }
}
