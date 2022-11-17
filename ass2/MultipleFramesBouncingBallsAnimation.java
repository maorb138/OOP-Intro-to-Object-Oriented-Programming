import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.Random;


/**
 * A MultipleFramesBouncingBallsAnimation class.
 * create multiple bouncing balls animation
 *
 * @author maor biton
 */
public class MultipleFramesBouncingBallsAnimation {
    /**
     * Const of width and height of the board.
     */
    static final int WIDTH_SCR = 1000, HEIGHT_SCR = 1000;
    /**
     * Const of width and height of the rectangle frame num 1.
     */
    static final int RE_ST_WID1 = 50, RE_END_WID1 = 500, RE_ST_HEIGHT1 = 50, RE_END_HEIGHT1 = 500;
    /**
     * Const of width and height of the rectangle frame num 2.
     */
    static final int RE_ST_WID2 = 450, RE_END_WID2 = 600, RE_ST_HEIGHT2 = 450, RE_END_HEIGHT2 = 600;

    /**
     * Strings to int.
     *
     * @param numbers the string of numbers
     * @return arry of int
     */
    public static int[] stringsToInts(String[] numbers) {
        int[] sizeBallarr = new int[numbers.length];
        // loop over all the string and make them to int
        for (int i = 0; i < numbers.length; i++) {
            sizeBallarr[i] = Integer.parseInt(numbers[i]);
        }
        return sizeBallarr;
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
        return new Color(r, g, b);
    }

    /**
     * createBalls - making the start point in the frame, And other features of the ball.
     *
     * @param ballArr     the ball arr
     * @param ballSizeArr the ball size arr
     * @param sWidth       the start width
     * @param sHeight      the start height
     * @param eWidth       the end width
     * @param eHeight      the end height
     * @param i            the index of the loop
     */
    public static void createBalls(Ball[] ballArr, int[] ballSizeArr, int sWidth, int sHeight,
                                   int eWidth, int eHeight, int i) {
        Random rand = new Random();
        // get integer in range radios to width-radios
        int x = rand.nextInt(eWidth - (2 * ballSizeArr[i]) - sWidth) + (ballSizeArr[i] + sWidth);
        // get integer in range radios to height-radios
        int y = rand.nextInt(eHeight - (2 * ballSizeArr[i]) - sHeight) + (ballSizeArr[i] + sHeight);
        Color randomColor = randomColor();
        new Velocity(0, 0);
        Ball ball = new Ball(x, y, ballSizeArr[i], randomColor); // create a ball
        // Add to the ball velocity
        ball.setVelocity(Velocity.fromAngleAndSpeed(rand.nextInt(360), Ball.sizeToSpeed(ballSizeArr[i])));
        ballArr[i] = ball;
    }

    /**
     * createBalls - making the 2 rectangle in the board.
     *
     * @param d the DrawSurface board
     */
    public static void createBoard(DrawSurface d) {
        d.setColor(Color.gray);
        d.fillRectangle(RE_ST_WID1, RE_ST_HEIGHT1, RE_END_WID1 - RE_ST_WID1, RE_END_HEIGHT1 - RE_ST_HEIGHT1);
        d.drawRectangle(RE_ST_WID1, RE_ST_HEIGHT1, RE_END_WID1 - RE_ST_WID1, RE_END_HEIGHT1 - RE_ST_HEIGHT1);
        d.setColor(Color.yellow);
        d.fillRectangle(RE_ST_WID2, RE_ST_HEIGHT2, RE_END_WID2 - RE_ST_WID2, RE_END_HEIGHT2 - RE_ST_HEIGHT2);
        d.drawRectangle(RE_ST_WID2, RE_ST_HEIGHT2, RE_END_WID2 - RE_ST_WID2, RE_END_HEIGHT2 - RE_ST_HEIGHT2);
    }

    /**
     * createArrOfBalls - create a arr of balls in the required size.
     *
     * @param args string of the args that getting from the user
     * @return ballsArr arr of balls in the size required
     */
    public static Ball[] createArrOfBalls(String[] args) {
        // save the size of the balls in arr
        int[] ballSizeArr = stringsToInts(args);
        Ball[] ballsArr = new Ball[ballSizeArr.length];
        // loop over all the balls
        for (int i = 0; i < ballSizeArr.length / 2; ++i) {
            createBalls(ballsArr, ballSizeArr, RE_ST_WID1, RE_ST_HEIGHT1, RE_END_WID1, RE_END_HEIGHT1, i);
        }
        for (int i = ballSizeArr.length / 2; i < ballSizeArr.length; ++i) {
            createBalls(ballsArr, ballSizeArr, RE_ST_WID2, RE_ST_HEIGHT2, RE_END_WID2, RE_END_HEIGHT2, i);
        }
        return ballsArr;
    }

    /**
     * missom - Getting a string of size of balls, and drawing animation of the balls in two different frames.
     *
     * @param args the input arguments
     */
    public static void missom(String[] args) {
        Ball[] ballsArr = createArrOfBalls(args);
        // Preparation of the board and two frames
        GUI gui = new GUI("title", WIDTH_SCR, HEIGHT_SCR);
        Sleeper sleeper = new Sleeper();
        // Start animation
        while (true) {
            DrawSurface board = gui.getDrawSurface();
            createBoard(board);
            // checking if the ball touch the borders
            for (int i = 0; i < ballsArr.length / 2; ++i) {
                ballsArr[i].animationBalls(RE_ST_WID1, RE_END_WID1, RE_ST_HEIGHT1, RE_END_HEIGHT1);
                ballsArr[i].drawOn(board);
            }
            for (int i = ballsArr.length / 2; i < ballsArr.length; ++i) {
                ballsArr[i].animationBalls(RE_ST_WID2, RE_END_WID2, RE_ST_HEIGHT2, RE_END_HEIGHT2);
                ballsArr[i].drawOn(board);
            }
            gui.show(board);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }

    /**
     * Getting a string of size and draw animation of the balls.
     * in two different frames
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        missom(args);
    }
}
