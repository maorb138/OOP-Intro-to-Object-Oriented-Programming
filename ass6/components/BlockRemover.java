package components;

import around.Counter;
import around.HitListener;

/**
 * components.BlockRemover class.
 *
 * @author maor biton.
 *  
 */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * components.BlockRemover - Instantiates a new components.Block remover.
     *
     * @param gameLevel          the gameLevel
     * @param removedBlocks the removed blocks
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * hitEvent - What to do after hit if count 0.
     * remove this listener from the block, delete from the gameLevel
     *
     * @param beingHit          the block
     * @param hitter the ball how hit
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitPoints() == 0) {
            beingHit.removeFromGame(this.gameLevel);
            beingHit.removeHitListener(this);
            this.remainingBlocks.decrease(1);
        }
    }
}
