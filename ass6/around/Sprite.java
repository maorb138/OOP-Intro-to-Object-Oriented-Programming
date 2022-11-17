package around;

import biuoop.DrawSurface;

/**
 * around.Sprite interface.
 *
 * @author maor biton.
 *  
 */
public interface Sprite {
    /**
     * drawOn - draw the sprite to the screen.
     *
     * @param d the DrawSurface
     */
    void drawOn(DrawSurface d);

    /**
     * TimePassed - notify the sprite that time has passed.
     */
    void timePassed();
}
