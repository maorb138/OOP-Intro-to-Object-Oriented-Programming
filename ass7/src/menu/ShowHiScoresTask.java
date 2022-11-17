package menu;

import animation.AnimationRunner;
import animation.HighScoresAnimation;
import animation.KeyPressStoppableAnimation;
import around.HighScoresTable;
import biuoop.KeyboardSensor;

/**
 * menu.ShowHiScoresTask class.
 *
 * @author maor biton.
 *  
 */

public class ShowHiScoresTask implements Task<Void> {
    private AnimationRunner runner;
    private HighScoresTable highScoresAnimation;
    private KeyboardSensor keyboardSensor;

    /**
     * Instantiates a new Show high scores task.
     *
     * @param runner              the runner
     * @param highScoresAnimation the high scores animation
     * @param keyboardSensor      the keyboard sensor
     */
    public ShowHiScoresTask(AnimationRunner runner,
                            HighScoresTable highScoresAnimation, KeyboardSensor keyboardSensor) {
        this.runner = runner;
        this.highScoresAnimation = highScoresAnimation;
        this.keyboardSensor = keyboardSensor;

    }

    /**
     * Run t.
     *
     * @return the t
     */
    public Void run() {
        this.runner.run(new KeyPressStoppableAnimation(
                this.keyboardSensor, "space", new HighScoresAnimation(highScoresAnimation)));
        return null;
    }
}
