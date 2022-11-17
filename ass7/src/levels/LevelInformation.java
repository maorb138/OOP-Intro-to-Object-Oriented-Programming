package levels;

import around.Sprite;
import around.Velocity;
import components.Block;

import java.awt.Color;
import java.util.List;

/**
 * levels.LevelInformation interface.
 *
 * @author maor biton.
 *  
 */
public interface LevelInformation {
    /**
     * Number of balls int.
     *
     * @return the int
     */
    int numberOfBalls();

    /**
     * Initial ball velocities list.
     * The initial velocity of each ball
     *
     * @return the list
     */
    List<Velocity> initialBallVelocities();

    /**
     * Paddle speed int.
     *
     * @return the int
     */
    int paddleSpeed();

    /**
     * Paddle width int.
     *
     * @return the int
     */
    int paddleWidth();

    /**
     * Level name string.
     *
     * @return the string
     */
    String levelName();

    /**
     * Gets background.
     *
     * @return the background
     */
    Sprite getBackground();

    /**
     * Blocks list.
     * The Blocks that make up this level, each block contains
     * its size, color and location.
     *
     * @return the list
     */
    List<Block> blocks();

    /**
     * Number of blocks to remove int.
     * Number of levels that should be removed
     *
     * @return the int
     */
    int numberOfBlocksToRemove();

    /**
     * Gets ball color.
     *
     * @return the ball color
     */
    Color getBallColor();
}
