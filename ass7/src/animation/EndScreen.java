package animation;

import biuoop.DrawSurface;
import components.GameLevel;

import java.awt.Color;

/**
 * animation.EndScreen class.
 *
 * @author maor biton.
 *  
 */
public class EndScreen implements Animation {
    private int score;
    private Boolean winOrLose;
    private double help;
    private double help1;
    private boolean flag;


    /**
     * Instantiates a new End screen.
     *
     * @param score     the score
     * @param winOrLose the win or lose
     */
    public EndScreen(int score, Boolean winOrLose) {
        this.score = score;
        this.winOrLose = winOrLose;
        this.help = 1;
        this.help1 = 1;
        this.flag = true;
    }

    /**
     * Do one frame.
     *
     * @param d the Draw Surface
     */
    public void doOneFrame(DrawSurface d) {
        d.setColor(new Color(0xF2E0EF));
        d.fillRectangle(GameLevel.START_POINT, GameLevel.START_POINT, GameLevel.WIDTH_SCR, GameLevel.HEIGHT_SCR);
        d.setColor(Color.BLACK);
        if (this.winOrLose) {
            d.drawText(130, 150, "Good job!", 80);
            d.setColor(new Color(0x7A947A));
            d.drawText(134, 146, "Good job!", 80);
            d.setColor(Color.BLACK);
            d.drawText(210, 240, "You Won ", 60);
            d.setColor(new Color(0xB8DFB8));
            d.drawText(214, 236, "You Won ", 60);
            for (int i = 1; i < 4; i++) {
                if (this.help < 50) {
                    d.setColor(Color.black);
                    d.drawText(620 - (i * 85), 440, "\u263A", (int) Math.round(20 + this.help));
                    this.help = help + 0.4;
                    if (this.help > 50) {
                        this.help = 50;
                    }
                } else if (this.help == 50 && flag) {
                    d.setColor(new Color(0xB02BF2));
                    d.drawText((int) Math.round(620 + help1) - (i * 85), (int) Math.round(440 + help1),
                            "\u263A", (int) Math.round(20 + this.help));
                    this.help1 = this.help1 + 1;
                }
                if (this.help1 == 140 || !flag) {
                    this.flag = false;
                    this.help1 = this.help1 - 1;
                    d.setColor(Color.red);
                    d.drawText((int) Math.round(620 + help1) - (i * 85), (int) Math.round(440 + help1),
                            "\u263A", (int) Math.round(20 + this.help));
                }
                if (help1 < -370) {
                    flag = true;
                }
            }
        } else {
            d.drawText(150, 150, "Game Over", 100);
            d.setColor(new Color(0xFA4C69));
            d.drawText(154, 146, "Game Over ", 100);
            d.setColor(Color.BLACK);
            d.drawText(280, 230, "You Lost ", 60);
            d.setColor(new Color(0xD51535));
            d.drawText(284, 234, "You Lost ", 60);

            d.setColor(Color.black);
            d.drawOval(590, 350, 100, 100);
            d.setColor(new Color(0x47DBF2));
            d.fillOval(610, 380, 20, 20);
            d.setColor(new Color(0x145FD0));
            d.fillOval(610, 380, 20, 20);
            d.fillOval(650, 380, 20, 20);
            d.setColor(Color.red);
            d.drawLine(620, 430, 625, 425);
            d.drawLine(625, 425, 653, 425);
            d.drawLine(653, 425, 658, 430);
            d.setColor(Color.black);
            d.drawLine(640, 450, 640, (int) Math.round(451 + this.help));
            if (this.help < 80) {
                this.help = this.help + 1;
            } else {
                d.drawLine(640, 530, (int) Math.round(640 - this.help1), (int) Math.round(530 + this.help1));
                d.drawLine(640, 530, (int) Math.round(640 + this.help1), (int) Math.round(530 + this.help1));
                d.drawLine(640, 490, (int) Math.round(640 - this.help1), (int) Math.round(490 + this.help1));
                d.drawLine(640, 490, (int) Math.round(640 - this.help1), (int) Math.round(490 - this.help1));
                if (this.help1 < 70) {
                    this.help1 = this.help1 + 1;
                }
            }
        }
        d.setColor(Color.BLACK);
        d.drawText(250, 320, "Press space to continue", 25);
        d.setColor(new Color(0x785CE6));
        d.drawText(320, 380, "Final score: " + this.score, 20);
    }

    /**
     * Checking should stop the game.
     *
     * @return true if should stop.
     */
    @Override
    public boolean shouldStop() {
        return false;
    }
}
