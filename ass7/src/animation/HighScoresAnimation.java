package animation;

import around.HighScoresTable;
import biuoop.DrawSurface;
import components.GameLevel;

import java.awt.Color;

/**
 * around.HighScoresAnimation class.
 *
 * @author maor biton.
 *  
 */
public class HighScoresAnimation implements Animation {
    private HighScoresTable table;


    /**
     * Instantiates a new High scores animation.
     *
     * @param score the score
     */
    public HighScoresAnimation(HighScoresTable score) {
        this.table = score;
    }


    /**
     * Do one frame.
     *
     * @param d the Draw Surface
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(new Color(0xF2E0EF));
        d.fillRectangle(GameLevel.START_POINT, GameLevel.START_POINT, GameLevel.WIDTH_SCR, GameLevel.HEIGHT_SCR);
        d.setColor(Color.BLACK);
        d.drawText(GameLevel.WIDTH_SCR / 2 - 100, 100, "table score : ", 40);
        d.setColor(Color.red);
        d.drawText(GameLevel.WIDTH_SCR / 2 - 100 + 2, 100 + 2, "table score : ", 40);

        for (int i = 0; i < this.table.getHighScores().size(); i++) {
            String tempName = this.table.getHighScores().get(i).getName();
            Integer tempScore = this.table.getHighScores().get(i).getScore();
            d.drawText(GameLevel.WIDTH_SCR / 4, 180 + (i * 40), tempName, 20);
            d.drawText((GameLevel.WIDTH_SCR / 4) + 400, 180 + (i * 40), tempScore.toString(), 20);
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
