package around;

import components.Ball;
import geometry.Point;
import geometry.Rectangle;


/**
 * The interface around.Collidable.
 *
 * @author maor biton
 *  
 */
public interface Collidable {
    /**
     * getCollisionRectangle - Return the "collision shape" of the object.
     *
     * @return the collision rectangle
     */
    Rectangle getCollisionRectangle();

    /**
     * Hit - gave as  whatWhat happens after the collision.
     *
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity
     * @param hitter          components.Ball hitter
     * @return the velocity
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
