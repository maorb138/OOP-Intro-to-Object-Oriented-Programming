package components;

import around.Counter;
import around.HitListener;

/**
 * components.ScoreTrackingListener class.
 *
 * @author maor biton.
 *  
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Instantiates a new Score tracking listener.
     *
     * @param scoreCounter the score counter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }
    /**
     * Const of scores hits.
     */
    private static final int HIT_SC = 5, DESTROYING_SC = 10;
    /**
     * hitEvent - What to do after hit.
     *
     * @param beingHit          the block
     * @param hitter the ball how hit
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(HIT_SC);
        if (beingHit.getHitPoints() == 0) {
            this.currentScore.increase(DESTROYING_SC);
        }
    }
}
