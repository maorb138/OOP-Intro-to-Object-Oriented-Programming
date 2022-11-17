package components;

import around.CollisionInfo;
import around.GameEnvironment;
import around.Sprite;
import around.Velocity;
import biuoop.DrawSurface;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;

import java.awt.Color;

/**
 * components.Ball class.
 *
 * @author maor biton.
 *  
 */
public class Ball implements Sprite {
    private int size;
    private java.awt.Color color;
    private Point location;
    private Velocity speed;
    private GameEnvironment gameEnvironment;

    /**
     * Instantiates a new components.Ball.
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
        this.gameEnvironment = new GameEnvironment();
    }

    /**
     * Instantiates a new components.Ball.
     *
     * @param center          the location of center ball
     * @param r               the radius
     * @param color           the color
     * @param gameEnvironment around.GameEnvironment
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment gameEnvironment) {
        this.location = center;
        this.size = r;
        this.color = color;
        this.setVelocity(0, 0);
        this.gameEnvironment = gameEnvironment;

    }

    /**
     * Instantiates a new components.Ball.
     *
     * @param x               the x coordinate of center ball
     * @param y               the y coordinate of center ball
     * @param r               the radius
     * @param color           the color
     * @param gameEnvironment the gameEnvironment
     */
    public Ball(int x, int y, int r, java.awt.Color color, GameEnvironment gameEnvironment) {
        this.location = new Point(x, y);
        this.size = r;
        this.color = color;
        this.setVelocity(0, 0);
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * Instantiates a new components.Ball.
     *
     * @param x     the x coordinate of center ball
     * @param y     the y coordinate of center ball
     * @param r     the radius
     * @param color the color
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        this.location = new Point(x, y);
        this.size = r;
        this.color = color;
        this.setVelocity(0, 0);
        this.gameEnvironment = new GameEnvironment();
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
        surface.setColor(Color.BLACK);
        surface.drawCircle(this.getX(), this.getY(), this.getSize());
    }

    /**
     * setVelocity - Sets velocity.
     *
     * @param v the velocity
     */
    public void setVelocity(Velocity v) {
        this.speed = v;
    }

    /**
     * setVelocity - Sets velocity.
     *
     * @param dx the change the x axis
     * @param dy the change the y axis
     */
    public void setVelocity(double dx, double dy) {
        this.speed = new Velocity(dx, dy);
    }

    /**
     * getVelocity - Gets velocity.
     *
     * @return the velocity
     */
    public Velocity getVelocity() {
        return this.speed;
    }

    /**
     * moveFrame - Move the ball to next step (looking to frame).
     *
     * @param sWidth  the start width - 0
     * @param eWidth  the end width
     * @param sHeight the star height - 0
     * @param eHeight the end height
     */
    public void moveFrame(int sWidth, int eWidth, int sHeight, int eHeight) {
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
        this.location = this.getVelocity().applyToPoint(this.location);
    }

    /**
     * nextLoc - Calculates the future point.
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
     * changeX - Change x location ball.
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
     * changeY - Change y location ball.
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
     * moveOneStep - Move one step.
     */
    public void moveOneStep() {
        // Checking the next location
        Point nextLocation = this.getVelocity().applyToPoint(this.location);
        Point startPoint = new Point((this.getX()), (this.getY()));
        Point endPoint = new Point(nextLocation.getX(), (nextLocation.getY()));
        // Makes a line representing the way from where it is to the next point
        Line lineRoute = new Line(startPoint, endPoint);
        // Checks if there is a cut point in the path of the ball
        CollisionInfo data = this.gameEnvironment.getClosestCollision(lineRoute);
        if (data == null) {
            // No cutting is therefore normal movement
            this.location = this.getVelocity().applyToPoint(this.location);
        } else {
            // Maintains that the angle of the ball will not change when it enters to the paddle
            if (isInRec(data.collisionObject().getCollisionRectangle()) && this.speed.getDy() < 0) {
                this.location = new Point(this.location.getX() + speed.getDx(),
                        this.location.getY() + speed.getDy());
            } else {
                // There is a cutting so moving to the nearest point of cutting
                this.location = data.collisionPoint();
                // Changing the direction of the ball according to the location of the obstacle
                double newX, newY;
                // Move the point so he didn't sit on the obstacle line.
                if (this.speed.getDx() > 0) {
                    newX = this.location.getX() - 1;
                } else {
                    newX = this.location.getX() + 1;
                }
                if (this.speed.getDy() > 0) {
                    newY = this.location.getY() - 1;
                } else {
                    newY = this.location.getY() + 1;
                }
                this.location = new Point(newX, newY);
                // Changes the angle by impact
                this.setVelocity(data.collisionObject().hit(this, data.collisionPoint(), this.getVelocity()));
            }
        }
    }

    /**
     * sizeToSpeed - Size to speed.
     *
     * @param size the size of the ball
     * @return the the start speed
     */
    public static int sizeToSpeed(int size) {
        // if the size above or equal then 50 speed 1
        if (size >= 50) {
            return 1;
        }
        return ((int) ((51 - size) / 2));
    }

    /**
     * timePassed - move one step.
     */
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * addToGame - Add sprite to the gameLevel.
     *
     * @param gameLevel the gameLevel
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }

    /**
     * isInRec - check if point is in rectangle.
     *
     * @param rec - a rectangle
     * @return - true if yes and false otherwise.
     */
    private boolean isInRec(Rectangle rec) {
        double x = this.location.getX();
        double y = this.location.getY();
        double upperLeftX = rec.getUpperLeft().getX();
        double upperLeftY = rec.getUpperLeft().getY();
        return (x >= upperLeftX && x <= upperLeftX + rec.getWidth() && y >= upperLeftY
                && y <= upperLeftY + rec.getHeight());
    }

    /**
     * removeFromGame - Remove ball from gameLevel.
     *
     * @param gameLevel the gameLevel
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
    }
}