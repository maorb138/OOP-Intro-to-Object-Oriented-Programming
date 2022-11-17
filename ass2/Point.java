/**
 * task 1.1 Point.
 *
 * @author maor biton.
 * @version 1.8.0_231.
 * @since 28.03.2020.
 */
public class Point {
    private double x;
    private double y;

    /**
     * Construct a point with x and y coordinates.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculates the distance between two points.
     *
     * @param other a point to measure the distance to
     * @return the distance to the other point
     */
    public double distance(Point other) {
        double dx = this.x - other.getX();
        double dy = this.y - other.getY();
        return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    }

    /**
     * Check whether the points are the same.
     *
     * @param other a point we checking if equals
     * @return boolean value, true = equal
     */
    public boolean equals(Point other) {
        return ((this.x == other.getX()) && (this.y == other.getY()))
                || ((this.y == other.getX()) && (this.x == other.getY()));
    }

    /**
     * @return the x coordinate
     */
    public double getX() {
        return this.x;
    }

    /**
     * @return the y coordinate
     */
    public double getY() {
        return this.y;
    }

}
