package menu;

/**
 * menu.Task interface.
 *
 * @param <T> the type parameter.
 * @author maor biton.
 * 
 */

public interface Task<T> {
    /**
     * Run t.
     *
     * @return the t
     */
    T run();
}
