package animation;

import around.SpriteCollection;
import biuoop.DrawSurface;


import java.awt.Color;


import static components.GameLevel.HEIGHT_SCR;
import static components.GameLevel.WIDTH_SCR;

/**
 * animation.CountdownAnimation class.
 *
 * @author maor biton.
 *  
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private long startTime;
    private double delta;
    private boolean running;


    /**
     * Instantiates a new Countdown animation.
     *
     * @param numOfSeconds the num of seconds
     * @param countFrom    the count from
     * @param gameScreen   the game screen
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.startTime = System.currentTimeMillis();
        this.delta = numOfSeconds / countFrom;
        this.running = false;

    }

    /**
     * Do one frame.
     *
     * @param d the d
     */
    public void doOneFrame(DrawSurface d) {
        if (countFrom == 0) {
            running = true;
        }
        this.gameScreen.drawAllOn(d);
        d.setColor(new Color(100, 80, 50));
        d.drawText(WIDTH_SCR / 2, (HEIGHT_SCR / 2 + 75), Integer.toString(countFrom), 80);
        double seconds = (double) (System.currentTimeMillis() - this.startTime) / 1000;
        if (seconds > delta) {
            this.startTime = System.currentTimeMillis();
            --countFrom;
        }
    }

    /**
     * Checking should stop the game.
     *
     * @return true if should stop.
     */
    public boolean shouldStop() {
        return this.running;
    }
}
