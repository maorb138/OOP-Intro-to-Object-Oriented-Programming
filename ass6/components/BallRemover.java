package components;

import around.Counter;
import around.HitListener;

/**
 * components.BallRemover class.
 *
 * @author maor biton.
 *  
 */
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBalls;

    /**
     * components.BallRemover - Instantiates a new balls remover.
     *
     * @param gameLevel    the gameLevel
     * @param removedBalls the removed balls
     */
    public BallRemover(GameLevel gameLevel, Counter removedBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = removedBalls;
    }

    /**
     * hitEvent - What to do after hit if count 0.
     * remove this listener from the block, delete from the gameLevel
     *
     * @param beingHit the block
     * @param hitter   the ball how hit
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.remainingBalls.decrease(1);
        hitter.removeFromGame(this.gameLevel);
    }
}
