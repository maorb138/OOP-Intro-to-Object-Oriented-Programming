package levels;

import around.Filling;
import around.Sprite;
import biuoop.DrawSurface;
import components.GameLevel;

/**
 * levels.Background class.
 *
 * @author maor biton.
 *  
 */
public class Background implements Sprite {
    private Filling background;

    /**
     * Instantiates a new Background.
     *
     * @param background the background
     */
    public Background(Filling background) {
        this.background = background;
    }


    @Override
    public void drawOn(DrawSurface d) {
        if (this.background.aColor()) {
            d.setColor(this.background.getColor());
            d.fillRectangle(15, 20, GameLevel.WIDTH_SCR, GameLevel.HEIGHT_SCR);
        } else {
            d.drawImage(0, 0, background.getImage());
        }
    }

    @Override
    public void timePassed() {

    }

    /**
     * Add to game.
     *
     * @param g the g
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
