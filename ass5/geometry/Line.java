package geometry;

import static java.lang.Float.MAX_VALUE;
import static java.lang.StrictMath.max;
import static java.lang.StrictMath.min;

/**
 * geometry.Line class.
 *
 * @author maor biton.
 *  
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
     * length - Given the length of this line.
     *
     * @return the length of the line.
     */
    public double length() {
        return this.start.distance(end);
    }

    /**
     * middle - Given the middle of the line.
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
     * start - give a start point.
     *
     * @return the start point
     */
    public Point start() {
        return this.start;
    }

    /**
     * end - give a end point.
     *
     * @return the end point
     */
    public Point end() {
        return this.end;
    }

    /**
     * isIntersecting - Returns true if the lines intersect, false otherwise.
     *
     * @param other a line we checking if intersect
     * @return boolean value, true = intersect
     */
    public boolean isIntersecting(Line other) {
        Point intersec = helpIntersecting(other, this);
        if (intersec.getX() == Double.MAX_VALUE && intersec.getY() == Double.MAX_VALUE) {
            return false;
        }
        return (inRange(other, intersec));
    }

    /**
     * other - Returns the intersection point if the lines intersect.
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
    public Point helpIntersecting(Line other, Line thisLine) {
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
     * @param other the first line
     * @param x     point of intersection
     * @return eturns true if the point in the range
     */
    public boolean inRange(Line other, Point x) {
        if ((min(this.start.getX(), this.end.getX()) <= x.getX())
                && (x.getX() <= max(this.start.getX(), this.end.getX()))
                && ((min(this.start.getY(), this.end.getY()) <= x.getY())
                && (x.getY() <= max(this.start.getY(), this.end.getY())))) {
            if ((min(other.start.getX(), other.end.getX()) <= x.getX())
                    && (x.getX() <= max(other.start.getX(), other.end.getX()))
                    && ((min(other.start.getY(), other.end.getY()) <= x.getY())
                    && (x.getY() <= max(other.start.getY(), other.end.getY())))) {
                return true;
            }
        }
        return false;
    }

    /**
     * closestIntersectionToStartOfLine - Finds the closest intersection point between a line and a rectangle.
     * if there is no cropping null returns.
     *
     * @param rect rectangle
     * @return point of intersection or null
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        // Preparing a point with maximum values
        Point intersecPoint = new Point(MAX_VALUE, MAX_VALUE);
        intersecPoint = helpClosestIntersectionToStartOfLine(rect.getVerticalLeft(), intersecPoint);
        intersecPoint = helpClosestIntersectionToStartOfLine(rect.getHorizontalUpper(), intersecPoint);
        intersecPoint = helpClosestIntersectionToStartOfLine(rect.getHorizontalDown(), intersecPoint);
        intersecPoint = helpClosestIntersectionToStartOfLine(rect.getVerticalRight(), intersecPoint);
        //If the values no changes return null (no intersection)
        if (intersecPoint.getY() == MAX_VALUE) {
            return null;
        } else {
            return intersecPoint;
        }
    }

    /**
     * helpClosestIntersectionToStartOfLine - Checks whether there is a cropping between the lines.
     * and whether it is the first crop, returns the nearest crop point
     *
     * @param other line of the rectangle
     * @param first the old crop point
     * @return point of intersection or the max value point
     */
    public Point helpClosestIntersectionToStartOfLine(Line other, Point first) {
        //Checks whether there is a cropping between the lines
        Point checkingPoint = this.intersectionWith(other);
        if (checkingPoint != null) {
            // Checking if is the first crop
            if (first.getY() != MAX_VALUE) {
                // Not first then checking how closer to the start
                first = (whoIsFartherAway(first, checkingPoint));
            } else {
                // First crop then return checkingPoint
                first = checkingPoint;
            }
        }
        return first;
    }

    /**
     * whoIsFartherAway - Returns the nearest crop point to the start.
     *
     * @param first point of first crop
     * @param last  the second point corp
     * @return point of intersection that closer to start point
     */
    public Point whoIsFartherAway(Point first, Point last) {
        if (this.start().distance(first) < this.start.distance(last)) {
            return first;
        } else {
            return last;
        }
    }

    /**
     * containPoint - checking if the point in the line.
     *
     * @param check the check point
     * @return the boolean value if the point contain return true
     */
    public boolean containPoint(Point check) {
        if (check == null) {
            return false;
        }
        Line newLine = new Line(this.start, check);
        Point newPoint = this.intersectionWith(newLine);
        return (newPoint == null && this.inRange(this, check));
    }

}

