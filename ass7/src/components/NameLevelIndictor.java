package components;

import around.Sprite;
import biuoop.DrawSurface;
import geometry.Point;
import geometry.Rectangle;

import java.awt.Color;

/**
 * components.NameLevelIndictor class.
 *
 * @author maor biton.
 *  
 */
public class NameLevelIndictor implements Sprite {
    private String name;
    private Rectangle loc;

    /**
     * Instantiates a new Score indicator.
     *
     * @param name the name level
     */
    public NameLevelIndictor(String name) {
        this.name = name;
        this.loc = new Rectangle(new Point(GameLevel.START_POINT, GameLevel.START_POINT), GameLevel.WIDTH_SCR, 20);
    }

    /**
     * drawOn - draw the name on the screen.
     *
     * @param d the DrawSurface
     */
    @Override
    public void drawOn(DrawSurface d) {
        int a1 = (int) Math.round(this.loc.getUpperLeft().getX());
        int a2 = (int) Math.round(this.loc.getUpperLeft().getY());
        int a3 = (int) Math.round(this.loc.getWidth());
        int a4 = (int) Math.round(this.loc.getHeight());
        d.setColor(Color.black);
        String txt = "Level Name: " + this.name;
        d.drawText(GameLevel.WIDTH_SCR - 250, a2 + 10, txt, 13);
    }

    /**
     * TimePassed - notify the sprite that time has passed.
     */
    public void timePassed() {
    }

    /**
     * addToGame - Add sprite to the gameLevel.
     *
     * @param gameLevel the gameLevel
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }
}
