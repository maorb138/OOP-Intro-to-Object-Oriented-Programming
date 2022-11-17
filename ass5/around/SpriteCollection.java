package around;

import biuoop.DrawSurface;

import java.util.ArrayList;

/**
 * around.SpriteCollection class.
 *
 * @author maor biton.
 *  
 */
public class SpriteCollection {
    private ArrayList<Sprite> spriteList;

    /**
     * constructor.
     */
    public SpriteCollection() {
        this.spriteList = new ArrayList<Sprite>();
    }

    /**
     * addSprite - Add sprite.
     *
     * @param s the Sprite
     */
    public void addSprite(Sprite s) {
        this.spriteList.add(s);
    }

    /**
     * notifyAllTimePassed - call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        for (int i = 0; i < spriteList.size(); ++i) {
            this.spriteList.get(i).timePassed();
        }
    }

    /**
     * drawAllOn - draw all sprites.
     *
     * @param d the d
     */
    public void drawAllOn(DrawSurface d) {
        for (int i = 0; i < spriteList.size(); ++i) {
            this.spriteList.get(i).drawOn(d);
        }
    }

    /**
     * removeSprite - remove sprite.
     *
     * @param s the around.Sprite
     */
    public void removeSprite(Sprite s) {
        this.spriteList.remove(s);
    }
}
