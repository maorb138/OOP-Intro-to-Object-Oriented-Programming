import static java.lang.Double.max;
import static java.lang.Double.min;

/**
 * task 1.2 Line.
 *
 * @author maor biton.
 * @version 1.8.0_231.
 * @since 28.03.2020.
 */
public class Line {
    private Point start;
    private Point end;

    /**
     * Construct a line with start point and end point.
     *
     * @param start point
     * @param end   point
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Construct a line whit start point and end point.
     *
     * @param x1 coordinate
     * @param y1 coordinate
     * @param x2 coordinate
     * @param y2 coordinate
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Given the length of this line.
     *
     * @return the length of the line.
     */
    public double length() {
        return this.start.distance(end);
    }

    /**
     * Given the middle of the line.
     *
     * @return point in the middle of the line
     */
    public Point middle() {
        double mx = this.start.getX() + this.end.getX();
        double my = this.start.getY() + this.end.getY();
        Point mid = new Point(mx / 2, my / 2);
        return mid;
    }

    /**
     * @return the start point
     */
    public Point start() {
        return this.start;
    }

    /**
     * @return the end point
     */
    public Point end() {
        return this.end;
    }

    /**
     * Returns true if the lines intersect, false otherwise.
     *
     * @param other a line we checking if intersect
     * @return boolean value, true = intersect
     */
    public boolean isIntersecting(Line other) {
        Point intersec = helpIntersecting(other, this);
        if (intersec.getX() == Double.MAX_VALUE && intersec.getY() == Double.MAX_VALUE) {
            return false;
        }
        return (inRange(other, this, intersec));
    }

    /**
     * Returns the intersection point if the lines intersect.
     *
     * @param other a line we checking if intersect
     * @return Point if intersect else return null
     */
    public Point intersectionWith(Line other) {
        if (isIntersecting(other)) {
            return helpIntersecting(other, this);
        } else {
            return null;
        }
    }

    /**
     * equals -- return true is the lines are equal, false otherwise.
     *
     * @param other a line we checking if equals
     * @return boolean value, true = equal
     */
    public boolean equals(Line other) {
        return ((this.start == other.start() && this.end == other.end)
                || (this.end == other.start() && this.start == other.end));
    }

    /**
     * helpIntersecting - Returns the intersection point of two line.
     *
     * @param other    the first line
     * @param thisLine the second line
     * @return point of intersection if parallel, returning a pair of FLT_MAX
     */
    public static Point helpIntersecting(Line other, Line thisLine) {
        // Line ohter represented as a1x + b1y = c1
        double a1 = other.end.getY() - other.start.getY();
        double b1 = other.start.getX() - other.end.getX();
        double c1 = a1 * (other.start.getX()) + b1 * (other.start.getY());
        // Line this represented as a2x + b2y = c2
        double a2 = thisLine.end.getY() - thisLine.start.getY();
        double b2 = thisLine.start.getX() - thisLine.end.getX();
        double c2 = a2 * (thisLine.start.getX()) + b2 * (thisLine.start.getY());
        double determinant = (a1 * b2) - (a2 * b1);
        if (determinant == 0) {
            if (thisLine.end().getX() == other.start().getX() && (thisLine.end().getY() == other.start().getY())) {
                return thisLine.end();
            }
            if ((thisLine.start().getX() == other.end().getX()) && (thisLine.start.getY() == other.end().getY())) {
                return thisLine.start();
            }
            // The lines are parallel, returning a pair of FLT_MAX
            return new Point(Double.MAX_VALUE, Double.MAX_VALUE);
        } else {
            double x = (b2 * c1 - b1 * c2) / determinant;
            double y = (a1 * c2 - a2 * c1) / determinant;
            return new Point(x, y);
        }
    }

    /**
     * inRange - checking if the point in the range.
     *
     * @param other    the first line
     * @param thisLine the second line
     * @param x        point of intersection
     * @return return true if the point in the range
     */
    public static boolean inRange(Line other, Line thisLine, Point x) {
        if ((min(thisLine.start.getX(), thisLine.end.getX()) <= x.getX())
                && (x.getX() <= max(thisLine.start.getX(), thisLine.end.getX()))
                && ((min(thisLine.start.getY(), thisLine.end.getY()) <= x.getY())
                && (x.getY() <= max(thisLine.start.getY(), thisLine.end.getY())))) {
            if ((min(other.start.getX(), other.end.getX()) <= x.getX())
                    && (x.getX() <= max(other.start.getX(), other.end.getX()))
                    && ((min(other.start.getY(), other.end.getY()) <= x.getY())
                    && (x.getY() <= max(other.start.getY(), other.end.getY())))) {
                return true;
            }
        }
        return false;
    }
}
