package components;

import animation.AnimationRunner;
import animation.EndScreen;
import animation.HighScoresAnimation;
import animation.KeyPressStoppableAnimation;
import around.Counter;
import around.HighScoresTable;
import around.ScoreInfo;
import biuoop.DialogManager;
import biuoop.KeyboardSensor;
import levels.LevelInformation;

import java.util.List;

/**
 * components.GameFlow class.
 *
 * @author maor biton.
 *  
 */
public class GameFlow {
    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;
    private Counter life;
    private Counter score;
    private HighScoresTable highScoresTable;
    private DialogManager dialog;

    /**
     * Instantiates a new Game flow.
     *
     * @param animation       the AnimationRunner
     * @param keyboard        the KeyboardSensor
     * @param life            number of lives in the game
     * @param highScoresTable the high scores table
     * @param dialog          the dialog
     */
    public GameFlow(AnimationRunner animation, KeyboardSensor keyboard, int life,
                    HighScoresTable highScoresTable, DialogManager dialog) {
        this.animationRunner = animation;
        this.keyboardSensor = keyboard;
        this.life = new Counter(life);
        this.score = new Counter(0);
        this.highScoresTable = highScoresTable;
        this.dialog = dialog;
    }

    /**
     * Run levels.
     *
     * @param levels the levels
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor,
                    this.animationRunner, this.score, this.life);
            level.initialize();
            while (this.life.getValue() > 0) {
                level.playOneTurn();
                if (level.blockRemain() <= 0) {
                    break;
                }
            }
            if (this.life.getValue() == 0) {
                break;
            }

        }
        if (this.life.getValue() == 0) {
            this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, "space",
                    new EndScreen(this.score.getValue(), false)));
        } else {
            this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, "space",
                    new EndScreen(this.score.getValue(), true)));
        }
        if (this.highScoresTable.getRank(this.score.getValue()) <= this.highScoresTable.size()) {
            String name = dialog.showQuestionDialog("Name", "What is your name?", "");
            ScoreInfo scoreInfo = new ScoreInfo(name, this.score.getValue());
            this.highScoresTable.add(scoreInfo);
        }
        this.animationRunner.run((new KeyPressStoppableAnimation(this.keyboardSensor, "space",
                new HighScoresAnimation(this.highScoresTable))));
    }
}
