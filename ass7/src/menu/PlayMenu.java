package menu;

import animation.AnimationRunner;
import around.HighScoresTable;
import biuoop.GUI;
import components.GameFlow;
import levels.LevelInformation;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * menu.PlayMenu class.
 *
 * @author maor biton.
 *  
 */
public class PlayMenu implements Task<Void> {
    private GUI gui;
    private AnimationRunner animationRunner;
    private HighScoresTable score;
    private List<LevelInformation> levels;
    private int life;
    private File tableFile;

    /**
     * Instantiates a new Start menu.
     *
     * @param gui             the gui
     * @param animationRunner the animation runner
     * @param score           the score
     * @param levels          the levels
     * @param tableFile       the table file
     * @param life            the life
     */
    public PlayMenu(GUI gui, AnimationRunner animationRunner, HighScoresTable score,
                    List<LevelInformation> levels, File tableFile, int life) {
        this.gui = gui;
        this.animationRunner = animationRunner;
        this.score = score;
        this.levels = levels;
        this.life = life;
        this.tableFile = tableFile;
    }

    /**
     * Run t.
     *
     * @return the t
     */
    public Void run() {
        GameFlow gameFlow = new GameFlow(this.animationRunner, this.gui.getKeyboardSensor(), this.life,
                this.score, this.gui.getDialogManager());
        gameFlow.runLevels(this.levels);
        try {
            this.score.save(this.tableFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
