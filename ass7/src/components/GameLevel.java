package components;

import animation.Animation;
import animation.PauseScreen;
import animation.AnimationRunner;
import animation.CountdownAnimation;
import animation.KeyPressStoppableAnimation;
import around.HitListener;
import around.Sprite;
import around.Collidable;
import around.Counter;
import around.GameEnvironment;
import around.SpriteCollection;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import geometry.Point;
import geometry.Rectangle;
import levels.LevelInformation;

import java.awt.Color;
import java.util.List;
import java.util.Random;

/**
 * components.GameLevel class.
 *
 * @author maor biton.
 *  
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private KeyboardSensor keyboard;
    private Counter remainedBlocks;
    private Counter remainedBall;
    private Counter score;
    private Counter life;
    private AnimationRunner runner;
    private boolean running;
    private LevelInformation level;

    /**
     * Const of width and height of the board.
     */
    public static final int WIDTH_SCR = 800, /**
     * The Height scr.
     */
    HEIGHT_SCR = 600, /**
     * The Start point.
     */
    START_POINT = 0;

    /**
     * Instantiates a new game.
     *
     * @param level    the level
     * @param keyboard the keyboard
     * @param runner   the runner
     * @param score    the score
     * @param life     the life
     */
    public GameLevel(LevelInformation level, KeyboardSensor keyboard,
                     AnimationRunner runner, Counter score, Counter life) {
        this.environment = new GameEnvironment();
        this.sprites = new SpriteCollection();
        this.keyboard = keyboard;
        this.remainedBlocks = new Counter(level.numberOfBlocksToRemove());
        this.remainedBall = new Counter(0);
        this.score = score;
        this.life = life;
        this.running = true;
        this.runner = runner;
        this.level = level;
    }

    /**
     * addCollidable - add a around.Collidable.
     *
     * @param c around.Collidable
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * addSprite - add a sprite.
     *
     * @param s around.Sprite
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * removeCollidable - removome a around.Collidable.
     *
     * @param c around.Collidable
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * removeSprite - removome a sprite.
     *
     * @param s around.Sprite
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * initialize - create the Blocks and components.Ball (and components.Paddle)
     * and add them to the game.
     */
    public void initialize() {
        HitListener scoreTrack = new ScoreTrackingListener(this.score);
        HitListener liveBlocks = new BlockRemover(this, this.remainedBlocks);
        HitListener liveBalls = new BallRemover(this, this.remainedBall);
        Block lowerBorder = new Block(new Rectangle(new Point(0, (HEIGHT_SCR)),
                WIDTH_SCR, 15), 0, Color.white);
        lowerBorder.addToGame(this);
        lowerBorder.addHitListener(liveBalls);
        addSprite(this.level.getBackground());
        frame();
        List<Block> blocks = level.blocks();
        for (Block block : blocks) {
            block.addToGame(this);
            block.addHitListener(liveBlocks);
            block.addHitListener(scoreTrack);
        }
        ScoreIndicator showScore = new ScoreIndicator(this.score);
        showScore.addToGame(this);
        LivesIndicator showLife = new LivesIndicator(this.life);
        NameLevelIndictor nameLvl = new NameLevelIndictor(this.level.levelName());
        nameLvl.addToGame(this);
        showLife.addToGame(this);
    }

    /**
     * playOneTurn - start the one play loop.
     */
    public void playOneTurn() {
        Paddle paddle = this.preparGame();
        this.runner.run(new CountdownAnimation(2, 3, this.sprites)); // countdown before turn starts.
        this.running = true;
        this.runner.run(this);
        paddle.removeFromGame(this);
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
        Color randomColor = new Color(r, g, b);
        return randomColor;
    }

    /**
     * frame -Prepare the squares around the frame.
     */
    public void frame() {
        for (int i = 0; i < 1; i++) {
            Block block = new Block(new Rectangle(new Point(0, 20),
                    WIDTH_SCR, 20), 0, Color.gray);
            block.addToGame(this);
        }
        for (int i = 0; i < 2; i++) {
            Block block = new Block(new Rectangle(new Point(i * (WIDTH_SCR - 25), 20),
                    25, HEIGHT_SCR), 0, Color.gray);
            block.addToGame(this);
        }
    }

    /**
     * preparGame - Preparing the game making paddle and balls.
     *
     * @return the paddle
     */
    public Paddle preparGame() {
        float locPaddle = (760 - (float) level.paddleWidth()) / 2;
        // Preparation of paddle
        Paddle paddle = new Paddle(keyboard, new Rectangle(new Point(locPaddle, HEIGHT_SCR - 12),
                level.paddleWidth(), 10),
                Color.yellow);
        paddle.setSpeed(level.paddleSpeed());
        paddle.addToGame(this);
        float det = (float) level.paddleWidth() / (level.numberOfBalls() + 1);
        for (int i = 0; i < level.numberOfBalls(); i++) {
            // Preparing a balls
            Ball ball = new Ball(new Point((Math.round(det) * (i + 1) + locPaddle), HEIGHT_SCR - 15),
                    4, level.getBallColor(), this.environment);
            ball.setVelocity(level.initialBallVelocities().get(i));
            this.remainedBall.increase(1);
            ball.addToGame(this);
        }
        return paddle;
    }

    /**
     * Do one frame.
     *
     * @param d the Draw Surface
     */
    @Override
    // the logic from the previous playOneTurn method goes here.
    // the `return` or `break` statements should be replaced with
    // this.running = false;
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (this.remainedBlocks.getValue() == 0) {
            this.score.increase(100);
            this.running = false;
        }
        if (this.remainedBall.getValue() == 0) {
            this.life.decrease(1);
            this.running = false;
        }
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, "space",
                    new PauseScreen()));
        }
        if (this.keyboard.isPressed("P")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, "space",
                    new PauseScreen()));
        }
        /**
         if (this.keyboard.isPressed("h")) {
         this.runner.run(new KeyPressStoppableAnimation(this.keyboard, "space",
         new HighScoresAnimation(HighScoresTable.loadFromFile(new File("highscores.txt")))));
         }
         **/
    }

    /**
     * Checking should stop the game.
     *
     * @return true if should stop.
     */
    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * Block remain .
     *
     * @return the number of block that stay in the game.
     */
    public int blockRemain() {
        return this.remainedBlocks.getValue();
    }
}
