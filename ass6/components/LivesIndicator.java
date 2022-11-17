package components;

import around.Counter;
import around.Sprite;
import biuoop.DrawSurface;
import geometry.Point;
import geometry.Rectangle;

import java.awt.Color;

/**
 * components.LivesIndicator class.
 *
 * @author maor biton.
 *  
 */
public class LivesIndicator implements Sprite {
    private Counter life;
    private Rectangle loc;

    /**
     * Instantiates a new Score indicator.
     *
     * @param life the score
     */
    public LivesIndicator(Counter life) {
        this.life = life;
        this.loc = new Rectangle(new Point(GameLevel.START_POINT, GameLevel.START_POINT), GameLevel.WIDTH_SCR, 15);
    }

    /**
     * drawOn - draw the score on the screen.
     *
     * @param d the DrawSurface
     */
    @Override
    public void drawOn(DrawSurface d) {
        String txt = " \ud83d\udc3b ";
        String repTxt = repeat(txt, this.life.getValue());
        int a2 = (int) Math.round(this.loc.getUpperLeft().getY());
        d.setColor(Color.black);
        String txt2 = "Lives : ";
        d.drawText(23, a2 + 15, txt2, 15);
        d.setColor(Color.black);
        d.drawText(67, a2 + 15, repTxt, 15);
    }

    /**
     * repeat - make a repeat word.
     *
     * @param s     string
     * @param times how much time to repeat
     * @return repeat string
     */
    private static String repeat(String s, int times) {
        if (times <= 0) {
            return "";
        } else if (times % 2 == 0) {
            return repeat(s + s, times / 2);
        } else {
            return s + repeat(s + s, times / 2);
        }
    }

    /**
     * TimePassed - notify the sprite that time has passed.
     */
    @Override
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
