/**
 * Velocity specifies the change in position on the `x` and the `y` axes.
 *
 * @author maor biton.
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
     * From angle and speed to velocity.
     *
     * @param angle the angle
     * @param speed the speed
     * @return the velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.sin(Math.toRadians(angle));
        double dy = -speed * Math.cos(Math.toRadians(angle));
        return new Velocity(dx, dy);
    }

    /**
     * Gets dx velocity.
     *
     * @return the  dx velocity
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Gets velocity.
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
        return (new Point(p.getX() + this.dx, p.getY() + this.dy));
    }

}