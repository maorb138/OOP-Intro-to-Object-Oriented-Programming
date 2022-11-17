import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * Paddle class.
 *
 * @author maor biton.
 *  
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle shape;
    private Color color;

    /**
     * Instantiates a new Paddle.
     *
     * @param keyboard the keyboard
     * @param shape    the shape
     * @param color    the color
     */
    public Paddle(KeyboardSensor keyboard, Rectangle shape, Color color) {
        this.keyboard = keyboard;
        this.shape = shape;
        this.color = color;
    }

    /**
     * Move left.
     */
    public void moveLeft() {
        // The left key is pressed and not at the end
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)
                && (this.shape.getUpperLeft().getX() > Game.START_POINT + 15)) {
            this.shape = new Rectangle(
                    new Point(this.shape.getUpperLeft().getX() - 5, this.shape.getUpperLeft().getY()),
                    this.shape.getWidth(), this.shape.getHeight());
        }
    }

    /**
     * Move right.
     */
    public void moveRight() {
        // The right key is pressed and not at the end
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)
                && (this.shape.getUpperRight().getX() < Game.WIDTH_SCR - 15)) {
            this.shape = new Rectangle(
                    new Point(this.shape.getUpperLeft().getX() + 5, this.shape.getUpperLeft().getY()),
                    this.shape.getWidth(), this.shape.getHeight());
        }
    }

    /**
     * timePassed - Checks left and right movement.
     */
    public void timePassed() {
        this.moveLeft();
        this.moveRight();
    }

    /**
     * drawOn - draw the paddle.
     *
     * @param d DrawSurface
     */
    public void drawOn(DrawSurface d) {
        d.setColor(color);
        Rectangle rec = this.shape;
        d.fillRectangle((int) Math.round(rec.getUpperLeft().getX()), (int) Math.round(rec.getUpperLeft().getY()),
                (int) Math.round(rec.getWidth()), (int) Math.round(rec.getHeight()));
        d.setColor(Color.black);
        d.drawRectangle((int) Math.round(rec.getUpperLeft().getX()), (int) Math.round(rec.getUpperLeft().getY()),
                (int) Math.round(rec.getWidth()), (int) Math.round(rec.getHeight()));
    }

    /**
     * getCollisionRectangle - return the rectangle.
     *
     * @return shape rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.shape;
    }

    /**
     * hit - return the new speed after the collision.
     *
     * @param collisionPoint  the point of collision
     * @param currentVelocity the velocity before the collision
     * @return velocity
     */
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        double div = this.shape.getWidth() / 5;
        double[] arryRegion = new double[6];
        // Preparing an array of distribution zones of the paddle
        for (int i = 0; i < 6; i++) {
            arryRegion[i] = this.shape.getUpperLeft().getX() + (div * i);
        }
        // region  1 - lefter
        if (collisionPoint.getX() >= arryRegion[0] && collisionPoint.getX() < arryRegion[1]) {
            return Velocity.fromAngleAndSpeed(300, currentVelocity.getSpeed());
        }
        // region  2 - middle left
        if (collisionPoint.getX() >= arryRegion[1] && collisionPoint.getX() < arryRegion[2]) {
            return Velocity.fromAngleAndSpeed(330, currentVelocity.getSpeed());
        }
        // region  3 - middle
        if (collisionPoint.getX() >= arryRegion[2] && collisionPoint.getX() < arryRegion[3]) {
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }
        // region  4 - middle right
        if (collisionPoint.getX() >= arryRegion[3] && collisionPoint.getX() < arryRegion[4]) {
            return Velocity.fromAngleAndSpeed(30, currentVelocity.getSpeed());
        }
        // region  5 - righter
        if (collisionPoint.getX() >= arryRegion[4] && collisionPoint.getX() < arryRegion[5]) {
            return Velocity.fromAngleAndSpeed(60, currentVelocity.getSpeed());
        }
        // hit on sides of paddle.
        if (this.shape.getVerticalLeft().containPoint(collisionPoint)) {
            return Velocity.fromAngleAndSpeed(290, currentVelocity.getSpeed());
        }
        if (this.shape.getVerticalRight().containPoint(collisionPoint)) {
            return Velocity.fromAngleAndSpeed(80, currentVelocity.getSpeed());
        }
        return currentVelocity;
    }

    /**
     * addToGame - Add to game.
     *
     * @param game the game
     */
    public void addToGame(Game game) {
        game.addSprite(this);
        game.addCollidable(this);
    }
}
