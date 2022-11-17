package around;

import geometry.Line;
import geometry.Point;

import java.util.ArrayList;

/**
 * around.GameEnvironment class.
 *
 * @author maor biton.
 *  
 */
public class GameEnvironment {
    private ArrayList<Collidable> collidableList;

    /**
     * around.GameEnvironment - constructor.
     */
    public GameEnvironment() {
        this.collidableList = new ArrayList<Collidable>();
    }

    /**
     * addCollidable - add to the arrylist collidable.
     *
     * @param newCollision the given collidable
     */
    public void addCollidable(Collidable newCollision) {
        this.collidableList.add(newCollision);
    }

    /**
     * removeCollidable - remove form arrylist collidable.
     *
     * @param c the given collidable
     */
    public void removeCollidable(Collidable c) {
        this.collidableList.remove(c);
    }

    /**
     * getClosestCollision - Get closest collision.
     *
     * @param trajectory the line how represents trajectory
     * @return the collision info
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        ArrayList<Collidable> checkingList = new ArrayList<>(this.collidableList);
        Point collusion = null;
        int count = 0;
        int save;
        for (; count < checkingList.size(); count++) {
            // collusion  get the closest intersection point to the track or null
            collusion = (Point)
                    trajectory.closestIntersectionToStartOfLine(checkingList.get(count).getCollisionRectangle());
            if (collusion != null) {
                break;
            }
        }
        // No cuttings were found
        if (collusion == null) {
            return null;
        }
        save = count;
        count++;
        Point collusionSecond = null;
        // Checks the rest of the crop points
        while (count < checkingList.size()) {
            collusionSecond = trajectory.closestIntersectionToStartOfLine(checkingList.get(count)
                    .getCollisionRectangle());
            // Another cut point was found
            if ((collusionSecond != null)
                    // Check who is closer
                    && collusion.distance(trajectory.start()) > collusionSecond.distance(trajectory.start())) {
                collusion = collusionSecond;
                save = count;
            }
            count++;
        }
        return new CollisionInfo(collusion, checkingList.get(save));
    }
}
