import animation.AnimationRunner;
import around.HighScoresTable;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import levels.SetReader;
import menu.Menu;
import menu.MenuAnimation;
import menu.MenuSel;
import menu.Task;
import menu.PlayMenu;
import menu.ShowHiScoresTask;
import menu.EndGame;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static components.GameLevel.HEIGHT_SCR;
import static components.GameLevel.WIDTH_SCR;

/**
 * Ass7Game class.
 *
 * @author maor biton.
 * 
 */

public class Ass7Game {
    /**
     * main - The main ass3 game.
     * A program that runs the game
     *
     * @param args the input arguments
     * @throws IOException the io exception
     */
    public static void main(String[] args) throws IOException {
        GUI gui = new GUI("title", WIDTH_SCR, HEIGHT_SCR);
        AnimationRunner animationRunner = new AnimationRunner(gui, 60);
        KeyboardSensor keybord = gui.getKeyboardSensor();
        File file = new File("highscores.txt");
        HighScoresTable highScoresTable = HighScoresTable.loadFromFile(file);
        Menu<Task<Void>> subMenu = new MenuAnimation<Task<Void>>(
                "Level Sets", gui.getKeyboardSensor(), animationRunner);

        List<MenuSel> levlSet;
        levlSet = SetReader.fromReader(args);
        for (MenuSel selection : levlSet) {
            subMenu.addSelection(selection.getKey(),
                    selection.getMessage(),
                    new PlayMenu(gui, animationRunner, highScoresTable,
                            selection.getLevelSet(), file, 7));
        }
        //List<LevelInformation> levels = new ArrayList<>();
        //LevelSpecificationReader play = new LevelSpecificationReader();
        Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>("Arkanoid", keybord, animationRunner);
        menu.addSubMenu("s", "Start game", subMenu);
        menu.addSelection("h", "High Scores", new ShowHiScoresTask(
                animationRunner, highScoresTable, gui.getKeyboardSensor()));
        menu.addSelection("e", "Exit", new EndGame(gui));
        while (true) {
            animationRunner.run(menu);
            Task<Void> task = menu.getStatus();
            task.run();
            menu.resrtStatus();
        }

    }
}