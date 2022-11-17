/**
 * The interface Collidable.
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
     * @return the velocity
     */
    Velocity hit(Point collisionPoint, Velocity currentVelocity);
}
