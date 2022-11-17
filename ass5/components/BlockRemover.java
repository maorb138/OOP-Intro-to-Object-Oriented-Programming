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
    private Game game;
    private Counter remainingBlocks;

    /**
     * components.BlockRemover - Instantiates a new components.Block remover.
     *
     * @param game          the game
     * @param removedBlocks the removed blocks
     */
    public BlockRemover(Game game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * hitEvent - What to do after hit if count 0.
     * remove this listener from the block, delete from the game
     *
     * @param beingHit the block
     * @param hitter   the ball how hit
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitPoints() == 0) {
            beingHit.removeFromGame(this.game);
            beingHit.removeHitListener(this);
            this.remainingBlocks.decrease(1);
        }

    }
}
