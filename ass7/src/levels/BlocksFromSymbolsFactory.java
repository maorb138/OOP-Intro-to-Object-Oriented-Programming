package levels;

import around.BlockCreator;
import components.Block;

import java.util.Map;

/**
 * levels.BlocksFromSymbolsFactory class.
 *
 * @author maor biton.
 *  
 */
public class BlocksFromSymbolsFactory {
    private Map<String, Integer> spacerWidths;
    private Map<String, BlockCreator> blockCreators;

    /**
     * Instantiates a new Blocks from symbols factory.
     *
     * @param blockCreators the block creators
     * @param spacerWidths  the spacer widths
     */
    public BlocksFromSymbolsFactory(Map<String, BlockCreator> blockCreators,
                                    Map<String, Integer> spacerWidths) {
        this.blockCreators = blockCreators;
        this.spacerWidths = spacerWidths;
    }

    /**
     * Is space symbol boolean.
     *
     * @param s the s
     * @return the boolean
     */
// returns true if 's' is a valid space symbol.
    public boolean isSpaceSymbol(String s) {
        return this.spacerWidths.containsKey(s);
    }

    /**
     * Is block symbol boolean.
     *
     * @param s the s
     * @return the boolean
     */
// returns true if 's' is a valid block symbol.
    public boolean isBlockSymbol(String s) {
        return blockCreators.containsKey(s);
    }

    /**
     * Gets block.
     *
     * @param s    the s
     * @param xpos the xpos
     * @param ypos the ypos
     * @return the block
     */
// Return a block according to the definitions associated
    // with symbol s. The block will be located at position (xpos, ypos).
    public Block getBlock(String s, int xpos, int ypos) {
        return this.blockCreators.get(s).create(xpos, ypos);
    }

    /**
     * Get space width int.
     *
     * @param s the s
     * @return the int
     */
// Returns the width in pixels associated with the given spacer-symbol.
    public int getSpacerWidth(String s) {
        return this.spacerWidths.get(s);
    }
}
