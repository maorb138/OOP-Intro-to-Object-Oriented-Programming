package components;

import around.Velocity;
import around.Filling;
import around.Collidable;
import around.HitListener;
import around.HitNotifier;
import around.Sprite;
import biuoop.DrawSurface;
import geometry.Point;
import geometry.Rectangle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * components.Block class.
 *
 * @author maor biton.
 * 
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle shape;
    private Integer hitPoints;
    private List<HitListener> hitListeners;
    private Map<Integer, Filling> filling;
    private Color stroke;

    /**
     * Const of font size of the txt hit number.
     */
    private static final int FONT_SIZE = 5;

    /**
     * Construct a block.
     *
     * @param rectangle Rectangle
     */
    public Block(Rectangle rectangle) {
        this.shape = rectangle;
        this.hitPoints = 1;
        this.hitListeners = new ArrayList<HitListener>();
        this.stroke = null;
        this.filling = new HashMap<Integer, Filling>();
    }

    /**
     * Construct a block.
     *
     * @param rectangle Rectangle
     * @param hitPoints How many hits remained
     * @param filling   the filling
     * @param stroke    the stroke
     */
    public Block(Rectangle rectangle, int hitPoints, Map<Integer, Filling> filling, Color stroke) {
        this.shape = rectangle;
        this.hitPoints = hitPoints;
        this.stroke = stroke;
        this.filling = filling;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * Construct a block.
     *
     * @param rectangle Rectangle
     * @param hitPoints How many hits remained
     * @param stroke    the stroke
     */
    public Block(Rectangle rectangle, int hitPoints, Color stroke) {
        this.shape = rectangle;
        this.hitPoints = hitPoints;
        this.stroke = stroke;
        this.hitListeners = new ArrayList<HitListener>();
        this.filling = new HashMap<Integer, Filling>();

    }

    /**
     * Instantiates a new Block.
     *
     * @param x       the x
     * @param y       the y
     * @param width   the width
     * @param height  the height
     * @param stroke  the stroke
     * @param filling the filling
     * @param hits    the hits
     */
    public Block(double x, double y, double width, double height, java.awt.Color stroke,
                 Map<Integer, Filling> filling, int hits) {
        this.shape = new Rectangle(x, y, width, height);
        this.hitPoints = hits;
        this.filling = filling;
        this.stroke = stroke;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * Instantiates a new Block.
     *
     * @param copy the copy
     */
    public Block(Block copy) {
        this.shape = new Rectangle(copy.getCollisionRectangle().getUpperLeft().getX(),
                copy.getCollisionRectangle().getUpperLeft().getY(), copy.getCollisionRectangle().getWidth(),
                copy.getCollisionRectangle().getHeight());
        this.filling = copy.filling;
        this.stroke = copy.stroke;
        this.hitPoints = copy.hitPoints;
        this.hitListeners = new ArrayList<HitListener>();
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
     * @param hitter          components.Ball hitter
     * @return new velocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
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
        this.notifyHit(hitter);
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
        if (this.filling.containsKey(this.hitPoints)) {
            if (this.filling.get(this.hitPoints).aImage()) {
                surface.drawImage(a1, a2, this.filling.get(this.hitPoints).getImage());
            } else {
                surface.setColor(this.filling.get(this.hitPoints).getColor());
                surface.fillRectangle(a1, a2, a3, a4);
            }
        } else if (this.filling.containsKey(0)) {
            if (this.filling.get(0).aImage()) {
                surface.drawImage(a1, a2, this.filling.get(0).getImage());
            } else {
                surface.setColor(this.filling.get(0).getColor());
                surface.fillRectangle(a1, a2, a3, a4);
            }
            if (this.stroke != null) {
                surface.setColor(this.stroke);
                surface.drawRectangle(a1, a2, a3, a4);
            }
        }
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
     * addToGame - Add to gameLevel. sprite and collidable.
     *
     * @param gameLevel the main gameLevel
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
        gameLevel.addCollidable(this);
    }

    /**
     * removeFromGame - remove gameLevel. sprite and collidable.
     *
     * @param gameLevel the main gameLevel
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }

    /**
     * addHitListener - add a lister h1.
     *
     * @param hl around.HitListener
     */
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * removeHitListener - remove a lister h1.
     *
     * @param hl around.HitListener
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * notifyHit - listeners about a hit event.
     *
     * @param hitter ball how hit
     */
    private void notifyHit(Ball hitter) {
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * setColorBlock - set color of the block.
     *
     * @param color1 color of txt
     */
    public void setColorBlock(Color color1) {
        Map<Integer, Filling> map = new HashMap<Integer, Filling>();
        map.put(0, new Filling(color1, null));
        this.filling = map;
    }
}
