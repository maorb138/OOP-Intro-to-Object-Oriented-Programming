package animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * animation.KeyPressStoppableAnimation class.
 *
 * @author maor biton.
 *  
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor keyboardSensor;
    private String key;
    private Animation animation;
    private Boolean stop;
    private boolean isAlreadyPressed;

    /**
     * Instantiates a new Key press stoppable animation.
     *
     * @param keyboardSensor the keyboard sensor
     * @param key            the key
     * @param animation      the animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor keyboardSensor, String key, Animation animation) {
        this.keyboardSensor = keyboardSensor;
        this.key = key;
        this.animation = animation;
        this.isAlreadyPressed = true;
        this.stop = false;
    }
    /**
     * Do one frame.
     *
     * @param d the Draw Surface
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
        if (this.keyboardSensor.isPressed(this.key)) {
            if (!this.isAlreadyPressed) {
                this.stop = true;
            }
        } else {
            this.isAlreadyPressed = false;
        }
    }

    /**
     * Checking should stop the game.
     *
     * @return true if should stop.
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
