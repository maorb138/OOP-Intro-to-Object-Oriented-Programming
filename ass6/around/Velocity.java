package around;

import geometry.Point;

/**
 * around.Velocity class.
 *
 * @author maor biton.
 * 
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * Instantiates a new Velocity.
     *
     * @param dx the dx
     * @param dy the dy
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Velocity - From angle and speed to velocity.
     *
     * @param angle the angle
     * @param speed the speed
     * @return the velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = Math.sin(Math.toRadians(angle)) * speed;
        double dy = -1 * Math.cos(Math.toRadians(angle)) * speed;
        return new Velocity(dx, dy);
    }

    /**
     * getDx - Gets dx velocity.
     *
     * @return the  dx velocity
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * getDy - Gets velocity.
     *
     * @return the velocity
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * applyToPoint - Take a point with position (x,y) and return a new point.
     * with position (x+dx, y+dy)
     *
     * @param p point
     * @return the new location point
     */
    public Point applyToPoint(Point p) {
        return (new Point((Math.round(p.getX() + this.dx)), (Math.round(p.getY() + this.dy))));
    }

    /**
     * setDx - update the dx of velocity.
     *
     * @param newX - the new x value of velocity
     */
    public void setDx(double newX) {
        this.dx = newX;
    }

    /**
     * setDy - update the dy of velocity.
     *
     * @param newY - the new y value of velocity
     */
    public void setDy(double newY) {
        this.dy = newY;
    }

    /**
     * getSpeed - return the speed.
     *
     * @return the speed of velocity
     */
    public double getSpeed() {
        return Math.sqrt(this.dx * this.dx + this.dy * this.dy);
    }
}