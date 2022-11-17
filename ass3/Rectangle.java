import java.util.ArrayList;

/**
 * Rectangle class.
 *
 * @author maor biton.
 *  
 */
public class Rectangle {
    private Point startPoint;
    private double width;
    private double height;

    /**
     * Instantiates a new Rectangle.
     *
     * @param upperLeft the upper left location
     * @param width     the width
     * @param height    the height
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.height = height;
        this.width = width;
        this.startPoint = upperLeft;
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
            listOfPoint.add(line.intersectionWith(this.getVerticalRight()));
        }
        return listOfPoint;
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
        return (new Line(this.getUpperLeft(), this.getUpperRight()));
    }

    /**
     * getHorizontalDown - Get horizontal down line.
     *
     * @return horizontal down line
     */
    public Line getHorizontalDown() {
        return (new Line(this.getDownLeft(), this.getDiagonalPoint()));
    }

    /**
     * getVerticalRight - Get vertical right line.
     *
     * @return vertical right line
     */
    public Line getVerticalRight() {
        return (new Line(this.getUpperRight(), this.getDiagonalPoint()));
    }

    /**
     * getVerticalLeft - Get vertical left line.
     *
     * @return vertical left line
     */
    public Line getVerticalLeft() {
        return (new Line(this.getUpperLeft(), this.getDownLeft()));
    }

    /**
     * getDiagonalPoint - Gets diagonal point.
     *
     * @return the diagonal point
     */
    public Point getDiagonalPoint() {
        double x1 = this.getUpperLeft().getX() + this.getWidth();
        double y1 = this.getUpperLeft().getY() + this.getHeight();
        return (new Point(x1, y1));
    }

    /**
     * getUpperRight - Gets upper right point.
     *
     * @return the upper right point
     */
    public Point getUpperRight() {
        double x1 = this.startPoint.getX() + this.getWidth();
        double y1 = this.startPoint.getY();
        return (new Point(x1, y1));
    }

    /**
     * getDownLeft - Gets down left.
     *
     * @return the down left point
     */
    public Point getDownLeft() {
        double x1 = this.startPoint.getX();
        double y1 = this.startPoint.getY() + this.getHeight();
        return (new Point(x1, y1));
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
     * @return Point  middle of the rectangle
     */
    public Point getMiddle() {
        double midX = this.startPoint.getX() + (this.getWidth() / 2);
        double midY = this.startPoint.getY() + (this.getHeight() / 2);
        return (new Point(midX, midY));
    }

}
