import components.Game;

/**
 * Ass5Game class.
 *
 * @author maor biton.
 * 
 */

public class Ass5Game {
    /**
     * main - The main ass3 game.
     * A program that runs the game
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        // run the game code
        game.run();
    }
}
