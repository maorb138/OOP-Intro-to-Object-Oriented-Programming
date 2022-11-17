import animation.AnimationRunner;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import components.GameFlow;
import levels.Green3;
import levels.LevelInformation;
import levels.WideEasy;
import levels.FinalFour;
import levels.DirectHit;

import java.util.ArrayList;
import java.util.List;

import static components.GameLevel.HEIGHT_SCR;
import static components.GameLevel.WIDTH_SCR;

/**
 * Ass6Game class.
 *
 * @author maor biton.
 * 
 */

public class Ass6Game {
    /**
     * main - The main from ass3 game.
     * A program that runs the game
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arknoid", WIDTH_SCR, HEIGHT_SCR);
        AnimationRunner animationRunner = new AnimationRunner(gui, 60);
        KeyboardSensor keyboard = gui.getKeyboardSensor();
        GameFlow game = new GameFlow(animationRunner, keyboard, 7);
        List<LevelInformation> levels = new ArrayList<>();
        levels.add(new DirectHit());
        levels.add(new WideEasy());
        levels.add(new Green3());
        levels.add(new FinalFour());
        List<LevelInformation> playLevels = new ArrayList<>();
        for (String lvl : args) {
            int temp = 0;
            try {
                temp = Integer.parseInt(lvl);
            } catch (NumberFormatException e) {
                temp = 0;
            }
            if (temp <= 4 && temp > 0) {
                playLevels.add(levels.get(temp - 1));
            }
        }
        if (args.length == 0) {
            game.runLevels(levels);
        } else {
            game.runLevels(playLevels);
        }
        gui.close();
    }
}
