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
    private Game game;
    private Counter remainingBalls;

    /**
     * components.BallRemover - Instantiates a new balls remover.
     *
     * @param game         the game
     * @param removedBalls the removed balls
     */
    public BallRemover(Game game, Counter removedBalls) {
        this.game = game;
        this.remainingBalls = removedBalls;
    }

    /**
     * hitEvent - What to do after hit if count 0.
     * remove this listener from the block, delete from the game
     *
     * @param beingHit the block
     * @param hitter   the ball how hit
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.remainingBalls.decrease(1);
        hitter.removeFromGame(this.game);
    }
}
