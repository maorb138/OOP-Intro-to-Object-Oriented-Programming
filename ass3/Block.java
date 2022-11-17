import biuoop.DrawSurface;

import java.awt.Color;

/**
 * Block class.
 *
 * @author maor biton.
 * 
 */
public class Block implements Collidable, Sprite {
    private Rectangle shape;
    private Integer hitPoints;
    private Color color;
    /**
     * Const of font size of the txt hit number.
     */
    static final int FONT_SIZE = 13;

    /**
     * Construct a block.
     *
     * @param rectangle Rectangle
     */
    public Block(Rectangle rectangle) {
        this.shape = rectangle;
        this.hitPoints = 1;
        this.color = Color.blue;
    }

    /**
     * Construct a block.
     *
     * @param rectangle Rectangle
     * @param hitPoints How many hits remained
     * @param color     color of the block
     */
    public Block(Rectangle rectangle, int hitPoints, Color color) {
        this.shape = rectangle;
        this.hitPoints = hitPoints;
        this.color = color;
    }

    /**
     * getCollisionRectangle - returns the rectangle that defines the block.
     *
     * @return the rectangle that defines the block.
     */
    public Rectangle getCollisionRectangle() {
        return this.shape;
    }

    /**
     * hit - returns new velocity depends on where collision.
     *
     * @param collisionPoint  the pint of collision
     * @param currentVelocity the past velocity
     * @return new velocity
     */
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        // Reduces the number of vulnerabilities required for a block
        if (this.hitPoints > 0) {
            reductionHit(this);
        }
        Velocity newVelocicy = new Velocity(currentVelocity.getDx(), currentVelocity.getDy());
        // Checks where the damage was and then changes the angle values
        if (this.shape.getHorizontalUpper().containPoint(collisionPoint)
                || this.shape.getHorizontalDown().containPoint(collisionPoint)) {
            newVelocicy.setDy(-newVelocicy.getDy());
        }
        if (this.shape.getVerticalLeft().containPoint(collisionPoint)
                || this.shape.getVerticalRight().containPoint(collisionPoint)) {
            newVelocicy.setDx(-newVelocicy.getDx());
        }
        return newVelocicy;
    }

    /**
     * drawon - draw a block.
     *
     * @param surface the surface
     */
    public void drawOn(DrawSurface surface) {
        int a1 = (int) Math.round(this.shape.getUpperLeft().getX());
        int a2 = (int) Math.round(this.shape.getUpperLeft().getY());
        int a3 = (int) Math.round(this.shape.getWidth());
        int a4 = (int) Math.round(this.shape.getHeight());
        surface.setColor(this.color);
        surface.fillRectangle(a1, a2, a3, a4);
        surface.setColor(Color.black);
        surface.drawRectangle(a1, a2, a3, a4);
        surface.setColor(Color.white);
        addHitLeft(surface);
    }

    /**
     * timePassed - future use.
     */
    public void timePassed() {
    }

    /**
     * addHitLeft - Add a number to the block or X.
     *
     * @param surface DrawSurface
     */
    public void addHitLeft(DrawSurface surface) {
        // Finds the center of the rectangle
        int locX = (int) this.shape.getMiddle().getX();
        int locY = (int) this.shape.getMiddle().getY();
        // If there are any hits left, display  the number on the block
        if (this.hitPoints > 0) {
            surface.drawText(locX, locY, this.hitPoints.toString(), FONT_SIZE);
        } else {
            String zeroLeft = "x";
            surface.drawText(locX, locY, zeroLeft, FONT_SIZE);
        }
    }


    /**
     * getHitPoints - Gets how many hits remained.
     *
     * @return the number of remained hit points
     */
    public int getHitPoints() {
        return this.hitPoints;
    }

    /**
     * reductionHit - Reduce how many hits remained.
     *
     * @param block the block need to reduce
     */
    public void reductionHit(Block block) {
        this.hitPoints = block.getHitPoints() - 1;
    }

    /**
     * addToGame - Add to game. sprite and collidable.
     *
     * @param game the main game
     */
    public void addToGame(Game game) {
        game.addSprite(this);
        game.addCollidable(this);
    }
}
