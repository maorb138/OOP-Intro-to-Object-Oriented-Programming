package geometry;

import java.util.ArrayList;

/**
 * geometry.Rectangle class.
 *
 * @author maor biton.
 *  
 */
public class Rectangle {
    private Point startPoint;
    private double width;
    private double height;

    /**
     * Instantiates a new geometry.Rectangle.
     *
     * @param upperLeft the upper left location
     * @param width     the width
     * @param height    the height
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.startPoint = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Instantiates a new geometry.Rectangle.
     *
     * @param x      the x
     * @param y      the y
     * @param width  the width
     * @param height the height
     */
    public Rectangle(double x, double y, double width, double height) {
        this.startPoint = new Point(x, y);
        this.width = width;
        this.height = height;
    }

    /**
     * getWidth - Gets width.
     *
     * @return the width
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * getHeight - Gets height.
     *
     * @return the height
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * getUpperLeft - Gets upper left.
     *
     * @return the upper left
     */
    public Point getUpperLeft() {
        return this.startPoint;
    }

    /**
     * getHorizontalUpper - Get horizontal upper line.
     *
     * @return horizontal upper line
     */
    public Line getHorizontalUpper() {
        // Preparing the other point of the line
        Point ohterUpperPoint = new Point(this.getUpperLeft().getX() + this.getWidth(), this.getUpperLeft().getY());
        Line horizontalUpper = new Line(this.getUpperLeft(), ohterUpperPoint);
        return horizontalUpper;
    }

    /**
     * getHorizontalDown - Get horizontal down line.
     *
     * @return horizontal down line
     */
    public Line getHorizontalDown() {
        // Preparing the other point of the line
        Point ohterPoint = new Point(this.getUpperLeft().getX(), this.getUpperLeft().getY() + this.getHeight());
        return new Line(ohterPoint, this.getDiagonalPoint());
    }

    /**
     * getVerticalRight - Get vertical right line.
     *
     * @return vertical right line
     */
    public Line getVerticalRight() {
        // Preparing the other point of the line
        Point ohterPoint = new Point(this.getUpperLeft().getX() + this.getWidth(), this.getUpperLeft().getY());
        return new Line(ohterPoint, this.getDiagonalPoint());
    }

    /**
     * getVerticalLeft - Get vertical left line.
     *
     * @return vertical left line
     */
    public Line getVerticalLeft() {
        // Preparing the other point of the line
        Point ohterDownPoint = new Point(this.getUpperLeft().getX(), this.getUpperLeft().getY() + this.getHeight());
        Line verticalLeft = new Line(this.getUpperLeft(), ohterDownPoint);
        return verticalLeft;
    }

    /**
     * getDiagonalPoint - Gets diagonal point.
     *
     * @return the diagonal point
     */
    public Point getDiagonalPoint() {
        Point diagonal = new Point(this.getUpperLeft().getX() + this.getWidth(),
                this.getUpperLeft().getY() + this.getHeight());
        return diagonal;
    }

    /**
     * getUpperRight - Gets upper right point.
     *
     * @return the upper right point
     */
    public  Point getUpperRight() {
        Point upperRight = new Point(this.startPoint.getX() + getWidth(), this.startPoint.getY());
        return upperRight;
    }

    /**
     * getDownLeft - Gets down left.
     *
     * @return the down left point
     */
    public Point getDownLeft() {
        Point downLeft = new Point(this.startPoint.getX(), this.startPoint.getY() + this.getHeight());
        return downLeft;
    }

    /**
     * intersectionPoints - find and add to list intersection point.
     *
     * @param line the line
     * @return the java . util . list
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        ArrayList<Point> listOfPoint = new ArrayList<>();
        if (line.isIntersecting(this.getHorizontalUpper())) {
            listOfPoint.add(line.intersectionWith(this.getHorizontalUpper()));
        }
        if (line.isIntersecting(this.getHorizontalDown())) {
            listOfPoint.add(line.intersectionWith(this.getHorizontalDown()));
        }
        if (line.isIntersecting(this.getVerticalLeft())) {
            listOfPoint.add(line.intersectionWith(this.getVerticalLeft()));
        }
        if (line.isIntersecting(this.getVerticalRight())) {
            listOfPoint.add(line.intersectionWith(this.getVerticalLeft()));
        }
        return listOfPoint;
    }

    /**
     * setNewStartX - Sets x value in start point.
     *
     * @param newX the new x value
     */
    private void setNewStarX(double newX) {
        this.startPoint = new Point(newX, this.startPoint.getY());
    }

    /**
     * getMiddle - Return the middle point.
     *
     * @return geometry.Point middel of the rectangle
     */
    public Point getMiddle() {
        return (new Point(this.startPoint.getX() + (this.getWidth() / 2),
                this.startPoint.getY() + (this.getHeight() / 2)));
    }
}
