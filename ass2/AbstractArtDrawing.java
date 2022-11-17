import biuoop.GUI;
import biuoop.DrawSurface;

import java.util.Random;
import java.awt.Color;

/**
 * task 2 AbstractArtDrawing.
 *
 * @author maor biton.
 * @version 1.8.0_231.
 * @since 28.03.2020.
 */
public class AbstractArtDrawing {
    /**
     * drawRandomLines - make a random lines and draw them.
     * after this draw a middle point and intersection point
     */
    public void drawRandomLines() {
        // create a random-number generator
        Random rand = new Random();
        // Create a window with the title "Random Lines"
        // which is 400 pixels wide and 300 pixels high.
        GUI gui = new GUI("Random Lines", 400, 300);
        DrawSurface board = gui.getDrawSurface();
        int time = 10;
        Line[] arryLine = new Line[time];
        for (int i = 0; time > i; ++i) {
            int x1 = rand.nextInt(400) + 1; // get integer in range 1-400
            int y1 = rand.nextInt(300) + 1; // get integer in range 1-300
            int x2 = rand.nextInt(400) + 1; // get integer in range 1-400
            int y2 = rand.nextInt(300) + 1; // get integer in range 1-300
            // draw in black this random line
            board.setColor(Color.black);
            board.drawLine(x1, y1, x2, y2);
            // makes this 4 points to line and save it in arry
            arryLine[i] = new Line(x1, y1, x2, y2);
            // find the middle point
            Point mid =  arryLine[i].middle();
            board.setColor(Color.BLUE);
            // draw this middle point in blue
            board.fillCircle((int) mid.getX(), (int) mid.getY(), 3);
            // call drawIntersection to draw intersection points
            drawIntersection(arryLine, i, board);
        }
        gui.show(board);
    }

    /**
     * drawIntersection - draw points of Intersection.
     *
     * @param arryLine arry of all the lines
     * @param now the number of  lines in the arry
     * @param d the DrawSurface
     */
    public static void drawIntersection(Line[] arryLine, int now, DrawSurface d) {
        //loop for checking if the lines are intersection
        for (int i = now; i > 0; --i) {
            Point inter = arryLine[now].intersectionWith(arryLine[i - 1]);
            if (inter == null) {
                continue;
            }
            //drawing the points
            d.setColor(Color.RED);
            d.fillCircle((int) inter.getX(), (int) inter.getY(), 3);
        }
    }

    /**
     * main - drawing 10 lines and point middles and intersection of the lines.
     *
     * @param args string (no need for this exercise)
     */
    public static void main(String[] args) {
        AbstractArtDrawing myLines = new AbstractArtDrawing();
        myLines.drawRandomLines();
    }
}