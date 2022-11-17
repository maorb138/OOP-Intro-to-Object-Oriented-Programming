package menu;

import levels.LevelInformation;

import java.util.List;
/**
 * menu.MenuSel class.
 *
 * @author maor biton.
 *  
 */
public class MenuSel {
    private String key;
    private String message;
    private List<LevelInformation> levelSet;

    /**
     * Instantiates a new Menu selection.
     *
     * @param key      the key
     * @param message  the message
     * @param levelSet the level set
     */
    public MenuSel(String key, String message, List<LevelInformation> levelSet) {
        this.key = key;
        this.message = message;
        this.levelSet = levelSet;
    }

    /**
     * Gets key.
     *
     * @return the key
     */
    public String getKey() {
        return this.key;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Gets level set.
     *
     * @return the level set
     */
    public List<LevelInformation> getLevelSet() {
        return this.levelSet;
    }
}
