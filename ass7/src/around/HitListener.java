package around;

import components.Ball;
import components.Block;

/**
 * The interface around.HitListener.
 *
 * @author maor biton
 *  
 */
public interface HitListener {
    /**
     * hitEvent - Hit event.
     * This method is called whenever the beingHit object is hit.
     *
     * @param beingHit the being hit
     * @param hitter   parameter is the components.Ball that's doing the hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
