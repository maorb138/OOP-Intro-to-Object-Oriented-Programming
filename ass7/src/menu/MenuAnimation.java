package menu;

import animation.AnimationRunner;
import around.Filling;
import biuoop.DrawSurface;

import java.util.ArrayList;

import biuoop.KeyboardSensor;

import java.awt.Color;
import java.awt.Image;
import java.util.List;

/**
 * menu.MenuAnimation class.
 *
 * @param <T> the type parameter.
 * @author maor biton.
 *  
 */
public class MenuAnimation<T> implements Menu<T> {
    private String title;
    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;
    private List<String> messages;
    private List<T> options;
    private List<String> keys;
    private ArrayList<Boolean> helpOption;
    private T status;
    private Boolean stop;
    private List<Menu<T>> subMenus;
    private Image menuImage;


    /**
     * Instantiates a new Menu animation.
     *
     * @param title           the title
     * @param keyboardSensor  the keyboard sensor
     * @param animationRunner the animation runner
     */
    public MenuAnimation(String title, KeyboardSensor keyboardSensor,
                         AnimationRunner animationRunner) {
        this.title = title;
        this.keyboardSensor = keyboardSensor;
        this.animationRunner = animationRunner;
        this.keys = new ArrayList<String>();
        this.messages = new ArrayList<String>();
        this.options = new ArrayList<T>();
        this.helpOption = new ArrayList<Boolean>();
        this.stop = false;
        this.subMenus = new ArrayList<Menu<T>>();
        this.menuImage = Filling.imageFromString("background_images/main.jpg");

    }

    /**
     * Add selection.
     *
     * @param key       the key
     * @param message   the message
     * @param returnVal the return val
     */
    public void addSelection(String key, String message, T returnVal) {
        this.keys.add(key);
        this.messages.add(message);
        this.options.add(returnVal);
        this.helpOption.add(true);
        this.subMenus.add(null);
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public T getStatus() {
        return this.status;
    }

    /**
     * Do one frame.
     *
     * @param d the Draw Surface
     */
    public void doOneFrame(DrawSurface d) {
        d.drawImage(0, 0, this.menuImage);
        int item = this.keys.size();
        for (int i = 0; i < item; i++) {
            d.setColor(Color.black);
            d.drawText(160, 180 + (i * 100), "(" + this.keys.get(i) + ")  for : ", 60);
            d.drawText(390, 180 + (i * 100), this.messages.get(i), 60);
            d.setColor(Color.white);
            d.drawText(163, 183 + (i * 100), "(" + this.keys.get(i) + ")  for : ", 60);
            d.drawText(393, 183 + (i * 100), this.messages.get(i), 60);
        }
        for (int i = 0; i < item; i++) {
            if (this.keyboardSensor.isPressed(this.keys.get(i))) {
                if (this.helpOption.get(i)) {
                    this.status = this.options.get(i);
                    this.stop = true;
                } else {
                    this.animationRunner.run(this.subMenus.get(i));
                    this.status = this.subMenus.get(i).getStatus();
                    this.subMenus.get(i).resrtStatus();
                    this.stop = true;
                    break;
                }
            }
        }
    }

    /**
     * Checking should stop the game.
     *
     * @return true if should stop.
     */
    public boolean shouldStop() {
        return this.stop;
    }

    /**
     * Add sub menu.
     *
     * @param key     the key
     * @param message the message
     * @param subMenu the sub menu
     */
    public void addSubMenu(String key, String message, Menu<T> subMenu) {
        this.keys.add(key);
        this.messages.add(message);
        this.options.add(null);
        this.helpOption.add(false);
        this.subMenus.add(subMenu);
    }

    @Override
    public void resrtStatus() {
        this.status = null;
        this.stop = false;
    }
}
