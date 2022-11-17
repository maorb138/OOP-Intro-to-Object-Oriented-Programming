package animation;

import biuoop.DrawSurface;

/**
 * Animation interface.
 *
 * @author maor biton.
 * 
 */
public interface Animation {
    /**
     * Do one frame.
     *
     * @param d the Draw Surface
     */
    void doOneFrame(DrawSurface d);

    /**
     * Checking should stop the game.
     *
     * @return true if should stop.
     */
    boolean shouldStop();
}
