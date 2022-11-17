package around;


import geometry.Point;

/**
 * around.CollisionInfo class.
 *
 * @author maor biton.
 *  
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * CollisionInfo - create a collision info.
     *
     * @param collisionPoint  the collision point.
     * @param collisionObject the collision object.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * collisionPoint -  return the point of collision.
     *
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * collisionObject - gives the object collidable.
     *
     * @return the collidable object
     */
    // the collidable object involved in the collision.
    public Collidable collisionObject() {
        return this.collisionObject;
    }


}
