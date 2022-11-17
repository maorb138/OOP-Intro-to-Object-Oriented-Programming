package menu;

import animation.Animation;

/**
 * Menu interfaces.
 *
 * @param <T> parameter type.
 * @author maor biton.
 *  
 */
public interface Menu<T> extends Animation {
    /**
     * Add selection.
     *
     * @param key       the key
     * @param message   the message
     * @param returnVal the return val
     */
    void addSelection(String key, String message, T returnVal);

    /**
     * Gets status.
     *
     * @return the status
     */
    T getStatus();

    /**
     * Add sub menu.
     *
     * @param key     the key
     * @param message the message
     * @param subMenu the sub menu
     */
    void addSubMenu(String key, String message, Menu<T> subMenu);

    /**
     * resets this status.
     */
    void resrtStatus();
}
