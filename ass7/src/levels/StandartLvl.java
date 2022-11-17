package levels;

import around.Filling;
import around.Sprite;
import around.Velocity;
import components.Block;

import java.awt.Color;
import java.util.List;
import java.util.Map;

/**
 * levels.StandartLvl class.
 *
 * @author maor biton.
 *  
 */
public class StandartLvl implements LevelInformation {
    private String levelName;
    private List<Velocity> ballVelocities;
    private Filling background;
    private Map<String, Integer> paddleDetail;
    private int numberOfBlocks;
    private int numberOfBalls;
    private List<Block> blocks;
    private Color ballColor;

    /**
     * Instantiates a new Standart lvl.
     *
     * @param levelName      the level name
     * @param ballVelocities the ball velocities
     * @param background     the background
     * @param paddleDetail   the paddle detail
     * @param numberOfBlocks the number of blocks
     * @param numberOfBalls  the number of balls
     * @param blocks         the blocks
     */
    StandartLvl(String levelName, List<Velocity> ballVelocities,
                Filling background, Map<String, Integer> paddleDetail,
                int numberOfBlocks, int numberOfBalls, List<Block> blocks) {
        this.levelName = levelName;
        this.ballVelocities = ballVelocities;
        this.background = background;
        this.paddleDetail = paddleDetail;
        this.numberOfBlocks = numberOfBlocks;
        this.numberOfBalls = numberOfBalls;
        this.blocks = blocks;
        this.ballColor = Color.WHITE;
    }

    /**
     * Instantiates a new Standart lvl.
     *
     * @param levelName      the level name
     * @param ballVelocities the ball velocities
     * @param background     the background
     * @param paddleDetail   the paddle detail
     * @param numberOfBlocks the number of blocks
     * @param blocks         the blocks
     * @param ballColor      the ball color
     */
    StandartLvl(String levelName, List<Velocity> ballVelocities,
                Filling background, Map<String, Integer> paddleDetail,
                int numberOfBlocks, List<Block> blocks, Color ballColor) {
        this.levelName = levelName;
        this.ballVelocities = ballVelocities;
        this.background = background;
        this.paddleDetail = paddleDetail;
        this.numberOfBlocks = numberOfBlocks;
        this.numberOfBalls = ballVelocities.size();
        this.blocks = blocks;
        this.ballColor = ballColor;
    }

    @Override
    public int numberOfBalls() {
        return this.numberOfBalls;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return this.ballVelocities;
    }

    @Override
    public int paddleSpeed() {
        return this.paddleDetail.get("speed");
    }

    @Override
    public int paddleWidth() {
        return this.paddleDetail.get("width");
    }

    @Override
    public String levelName() {
        return this.levelName;
    }

    @Override
    public Sprite getBackground() {
        return new Background(this.background);
    }

    @Override
    public List<Block> blocks() {
        return this.blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.numberOfBlocks;
    }

    @Override
    public Color getBallColor() {
        return this.ballColor;
    }

    /**
     * Sets ball color.
     *
     * @param color1 the color 1
     */
    public void setBallColor(Color color1) {
        this.ballColor = color1;
    }
}
