import biuoop.DrawSurface;

/**
 * task 3.1 Ball.
 *
 * @author maor biton.
 * @version 1.8.0_231.
 * @since 28.03.2020.
 */
public class Ball {
    private int size;
    private java.awt.Color color;
    private Point location;
    private Velocity speed;

    /**
     * Instantiates a new Ball.
     *
     * @param center the location of center ball
     * @param r      the radius
     * @param color  the color
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.location = center;
        this.size = r;
        this.color = color;
        this.setVelocity(0, 0);
    }

    /**
     * Instantiates a new Ball.
     *
     * @param x     the x coordinate of center ball
     * @param y     the y coordinate of center ball
     * @param r     the radius
     * @param color the color
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        this.location = new Point(x, y);
        this.size = Math.abs(r);
        this.color = color;
        this.setVelocity(0, 0);
    }

    /**
     * Gets x.
     *
     * @return the x
     */
    public int getX() {
        return (int) this.location.getX();
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public int getY() {
        return (int) this.location.getY();
    }

    /**
     * Gets size.
     *
     * @return the size
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Gets color.
     *
     * @return the color
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * drawOn - draw the ball.
     *
     * @param surface the surface
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.getSize());
    }

    /**
     * Sets velocity.
     *
     * @param v the velocity
     */
    public void setVelocity(Velocity v) {
        this.speed = v;
    }

    /**
     * Sets velocity.
     *
     * @param dx the change the x axis
     * @param dy the change the y axis
     */
    public void setVelocity(double dx, double dy) {
        this.speed = new Velocity(dx, dy);

    }

    /**
     * Gets velocity.
     *
     * @return the velocity
     */
    public Velocity getVelocity() {
        return this.speed;
    }

    /**
     * AnimationBalls start the animation changing the location of the balls.
     *
     * @param sWidth  the start width - 0
     * @param eWidth  the end width
     * @param sHeight the star height - 0
     * @param eHeight the end height
     */
    public void animationBalls(int sWidth, int eWidth, int sHeight, int eHeight) {
        Point nextLoc = this.nextLoc(this.getX(), this.getY());
        // checking if the ball touch the bound
        if ((nextLoc.getX() - this.getSize() < sWidth) && (this.getVelocity().getDx() < 0)) {
            this.setVelocity(-(this.getVelocity().getDx()), this.getVelocity().getDy());
            changeX(this.getSize());
        } else if (((nextLoc.getX() + this.getSize() > eWidth)) && (this.getVelocity().getDx() > 0)) {
            this.setVelocity(-(this.getVelocity().getDx()), this.getVelocity().getDy());
            changeX((eWidth - this.getSize()));
        }
        if ((nextLoc.getY() - this.getSize() < sHeight) && (this.getVelocity().getDy() < 0)) {
            this.setVelocity(this.getVelocity().getDx(), -(this.getVelocity().getDy()));
            changeY(this.getSize());
        } else if (((nextLoc.getY() + this.getSize() > eHeight)) && (this.getVelocity().getDy() > 0)) {
            this.setVelocity(this.getVelocity().getDx(), -(this.getVelocity().getDy()));
            changeY((eHeight - this.getSize()));
        }
        // moving the ball
        this.moveOneStep();
    }

    /**
     * Calculates the future point.
     *
     * @param x the x location
     * @param y the y location
     * @return point of the future location
     */
    private Point nextLoc(int x, int y) {
        double locX = x + this.getVelocity().getDx();
        double locY = y + this.getVelocity().getDy();
        Point nextLoc = new Point(locX, locY);
        return nextLoc;
    }

    /**
     * Change x location ball.
     *
     * @param x the x location
     * @return the ball
     */
    public Ball changeX(int x) {
        Ball newBall = new Ball(x, this.getY(), this.getSize(), this.getColor());
        newBall.setVelocity(this.getVelocity());
        return newBall;
    }

    /**
     * Change y location ball.
     *
     * @param y the y location
     * @return the new ball
     */
    public Ball changeY(int y) {
        Ball newBall = new Ball(this.getX(), y, this.getSize(), this.getColor());
        newBall.setVelocity(this.getVelocity());
        return newBall;
    }

    /**
     * Move one step.
     */
    public void moveOneStep() {
        this.location = this.getVelocity().applyToPoint(this.location);
    }

    /**
     * Size to speed.
     *
     * @param size the size of the ball
     * @return the the start speed
     */
    public static int sizeToSpeed(int size) {
        // if the size above or equal then 50 speed 1
        if (size >= 50) {
            return 1;
        }
        return (((51 - size) / 2));
    }

}