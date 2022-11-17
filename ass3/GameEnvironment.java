import java.util.ArrayList;

/**
 * GameEnvironment class.
 *
 * @author maor biton.
 *  
 */
public class GameEnvironment {
    private ArrayList<Collidable> collidableList;

    /**
     * GameEnvironment - constructor.
     */
    public GameEnvironment() {
        this.collidableList = new ArrayList<>();
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
            collusion = trajectory.closestIntersectionToStartOfLine(checkingList.get(count).getCollisionRectangle());
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
        Point collusionSecond;
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
