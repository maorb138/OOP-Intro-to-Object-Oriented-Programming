package components;

import around.Counter;
import around.Sprite;
import biuoop.DrawSurface;
import geometry.Point;
import geometry.Rectangle;

import java.awt.Color;

/**
 * components.ScoreIndicator class.
 *
 * @author maor biton.
 *  
 */
public class ScoreIndicator implements Sprite {
    private Counter score;
    private Rectangle loc;

    /**
     * Instantiates a new Score indicator.
     *
     * @param score the score
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
        this.loc = new Rectangle(new Point(Game.START_POINT, Game.START_POINT), Game.WIDTH_SCR, 15);
    }

    /**
     * drawOn - draw the score on the screen.
     *
     * @param d the DrawSurface
     */
    @Override
    public void drawOn(DrawSurface d) {
        String txt = "Score: " + Integer.toString(this.score.getValue());
        d.setColor(Color.white);
        int a1 = (int) Math.round(this.loc.getUpperLeft().getX());
        int a2 = (int) Math.round(this.loc.getUpperLeft().getY());
        int a3 = (int) Math.round(this.loc.getWidth());
        int a4 = (int) Math.round(this.loc.getHeight());
        d.fillRectangle(a1, a2, a3, a4);
        d.setColor(Color.BLACK);
        d.drawText(a3 / 2 - 20, a2 + 15, txt, 15);
    }

    @Override
    public void timePassed() {

    }

    /**
     * addToGame - Add sprite to the game.
     *
     * @param game the game
     */
    public void addToGame(Game game) {
        game.addSprite(this);
    }
}
