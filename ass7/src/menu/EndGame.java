package menu;

import biuoop.GUI;

/**
 * menu.EndGame class.
 *
 * @author maor biton.
 * 
 */
public class EndGame implements Task<Void> {
    private GUI gui;

    /**
     * Instantiates a new End game.
     *
     * @param gui the gui
     */
    public EndGame(GUI gui) {
        this.gui = gui;
    }

    /**
     * Run t.
     *
     * @return the t
     */
    public Void run() {
        System.exit(0);
        return null;
    }
}
