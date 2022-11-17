package geometry;

/**
 * geometry.Point class.
 *
 * @author maor biton.
 *  
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
     * distance - Calculates the distance between two points.
     *
     * @param other a point to measure the distance to
     * @return the distance to the other point
     */
    public double distance(Point other) {
        double dx = this.x - other.getX();
        double dy = this.y - other.getY();
        return Math.sqrt((dx * dx) + (dy * dy));
    }

    /**
     * equals - Check whether the points are the same.
     *
     * @param other a point we checking if equals
     * @return boolean value, true = equal
     */
    public boolean equals(Point other) {
        return ((this.x == other.getX()) && (this.y == other.getY()))
                || ((this.y == other.getX()) && (this.x == other.getY()));
    }

    /**
     * getX - get the x coordinate.
     *
     * @return the x coordinate
     */
    public double getX() {
        return this.x;
    }

    /**
     * getY - get the y coordinate.
     *
     * @return the y coordinate
     */
    public double getY() {
        return this.y;
    }
}
