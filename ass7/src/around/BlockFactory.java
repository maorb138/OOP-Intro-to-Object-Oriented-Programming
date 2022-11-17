package around;
import components.Block;

import java.awt.Color;
import java.util.Map;

/**
 * around.BlockFactory class.
 *
 * @author maor biton.
 *  
 */
public class BlockFactory implements BlockCreator {
    private int height;
    private int width;
    private int hits;
    private Color stroke;
    private Map<Integer, Filling> filling;

    /**
     * Instantiates a new Block factory.
     *
     * @param height  the height
     * @param width   the width
     * @param hits    the hits
     * @param stroke  the stroke
     * @param filling the filling
     */
    public BlockFactory(int height, int width, int hits, Color stroke, Map<Integer, Filling> filling) {
        this.height = height;
        this.width = width;
        this.hits = hits;
        this.stroke = stroke;
        this.filling = filling;
    }
    /**
     * create a block.
     * @param xpos the x value of the upper left point of the rectangle
     * @param ypos the y value of the upper left point of the rectangle
     * @return new block
     */
    public Block create(int xpos, int ypos) {
        return new Block(xpos, ypos, this.width, this.height, this.stroke, this.filling, this.hits);
    }
}
