package animation;
import biuoop.DrawSurface;
import components.GameLevel;

import java.awt.Color;
/**
 * animation.PauseScreen class.
 *
 * @author maor biton.
 *  
 */
public class PauseScreen implements Animation {
    private double loc;
    private boolean left;

    /**
     * Instantiates a new Pause screen.
     */
    public PauseScreen() {
        this.loc = 0.0;
        this.left = false;
    }
    /**
     * Do one frame.
     *
     * @param d the Draw Surface
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(new Color(255, 255, 204));
        d.fillRectangle(GameLevel.START_POINT, GameLevel.START_POINT, GameLevel.WIDTH_SCR, GameLevel.HEIGHT_SCR);
        d.setColor(Color.BLACK);
        d.drawText(220, 200, "paused ", 100);
        d.setColor(new Color(51, 153, 255));
        d.drawText(214, 196, "paused ", 100);
        d.setColor(Color.BLACK);
        d.drawText(240, 280, "Press space to continue", 25);
        d.setColor(Color.black);
        d.drawCircle((int) Math.round(400 + loc), 430, 100);
        d.setColor(Color.gray);
        d.fillCircle((int) Math.round(400 + loc), 430, 100);
        d.setColor(new Color(51, 153, 255));
        d.fillCircle((int) Math.round(400 + loc), 430, 97);
        d.setColor(Color.black);
        d.fillCircle((int) Math.round(400 + loc), 430, 90);
        d.setColor(new Color(204, 204, 204));
        d.fillRectangle((int) Math.round(340 + loc), 375, 47, 110);
        d.fillRectangle((int) Math.round(410 + loc), 375, 47, 110);
        if (this.loc < 200 && (!left)) {
            this.loc = this.loc + 0.8;
        } else {
            this.loc = this.loc - 0.8;
            this.left = !(this.loc < -200);
        }
    }

    /**
     * Checking should stop the game.
     *
     * @return true if should stop.
     */
    @Override
    public boolean shouldStop() {
        return false;
    }
}
