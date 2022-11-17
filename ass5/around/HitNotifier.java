package around;

/**
 * The interface around.HitNotifier.
 *
 * @author maor biton
 *  
 */
public interface HitNotifier {
    /**
     * addHitListener - Add hl as a listener to hit events.
     *
     * @param hl the hl
     */
    void addHitListener(HitListener hl);

    /**
     * removeHitListener - Remove hl from the list of listeners to hit events.
     *
     * @param hl the hl
     */
    void removeHitListener(HitListener hl);
}
