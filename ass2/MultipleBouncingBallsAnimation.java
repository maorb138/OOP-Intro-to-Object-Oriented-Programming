import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.Random;

/**
 * A MultipleBouncingBallsAnimation class.
 * create multiple bouncing balls animation
 *
 * @author maor biton.
 */
public class MultipleBouncingBallsAnimation {
    /**
     * The const Width, const Height.
     */
    static final int WIDTH = 400, HEIGHT = 600;

    /**
     * Strings to int.
     *
     * @param numbers the string of numbers
     * @return arr of int
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
        Color randomColor;
        randomColor = new Color(r, g, b);
        return randomColor;
    }

    /**
     * Create arr of balls with the size we get from the user.
     *
     * @param args the args
     * @return the balls arr
     */
    public static Ball[] createArrOfBalls(String[] args) {
        // save the size of the balls in arr
        int[] ballSizeArr = stringsToInts(args);
        Ball[] ballsArr = new Ball[ballSizeArr.length];
        Random rand = new Random(); // create a random-number generator
        // loop over all the balls
        for (int i = 0; i < ballSizeArr.length; ++i) {
            // get integer in range radios to width-radios
            int x = rand.nextInt(WIDTH - (2 * ballSizeArr[i])) + ballSizeArr[i];
            // get integer in range radios to height-radios
            int y = rand.nextInt(HEIGHT - (2 * ballSizeArr[i])) + ballSizeArr[i];
            Color randomColor;
            randomColor = randomColor();
            new Velocity(0, 0);
            Ball ball = new Ball(x, y, ballSizeArr[i], randomColor); // create a ball
            // Add to the ball velocity
            ball.setVelocity(Velocity.fromAngleAndSpeed(rand.nextInt(360), Ball.sizeToSpeed(ballSizeArr[i])));
            ballsArr[i] = ball;
        }
        return ballsArr;

    }

    /**
     * Getting a string of size and draw animation of the balls.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        //Builds an array of balls by the size that we received from the user
        Ball[] ballsArr = createArrOfBalls(args);
        GUI gui = new GUI("title", WIDTH, HEIGHT);
        Sleeper sleeper = new Sleeper();
        // Start animation
        while (true) {
            DrawSurface board = gui.getDrawSurface();
            // checking if the ball touch the borders
            for (int i = 0; i < args.length; ++i) {
                ballsArr[i].animationBalls(0, WIDTH, 0, HEIGHT);
                ballsArr[i].drawOn(board);
            }
            gui.show(board);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }
}

