import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * A BouncingBallAnimation class.
 *
 * @author maor biton.
 */
public class BouncingBallAnimation {
    /**
     * Produces an animation of a ball and board.
     * and each vulnerable board continues the movement of the ball
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        final int width = 200;
        final int height = 200;
        GUI gui = new GUI("title", width, height);
        Sleeper sleeper = new Sleeper();
        Ball ball = new Ball(0, 0, 30, java.awt.Color.BLACK);
        ball.setVelocity(2, 2);
        while (true) {
            ball.animationBalls(0, width, 0, height);
            DrawSurface board = gui.getDrawSurface();
            ball.drawOn(board);
            gui.show(board);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }
}
