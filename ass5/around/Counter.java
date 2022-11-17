package around;


/**
 * around.Counter class.
 *
 * @author maor biton.
 * 
 */
public class Counter {
    private int count;

    /**
     * Instantiates a new around.Counter.
     *
     * @param count the count
     */
    public Counter(int count) {
        this.count = count;
    }

    /**
     * Increase - add number to current count.
     *
     * @param number the number
     */
    public void increase(int number) {
        this.count = this.count + number;
    }

    /**
     * Decrease - subtract number from current count.
     *
     * @param number the number
     */
    public void decrease(int number) {
        this.count = this.count - number;
    }

    /**
     * getsValue - get current count.
     *
     * @return the value
     */
    public int getValue() {
        return this.count;
    }
}
