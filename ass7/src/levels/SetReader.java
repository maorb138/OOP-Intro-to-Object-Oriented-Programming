package levels;

import menu.MenuSel;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * levels.SetReader class.
 *
 * @author maor biton.
 *  
 */
public class SetReader {
    /**
     * From reader list.
     *s
     * @param args the args
     * @return the list
     */
    public static List<MenuSel> fromReader(String[] args) {
        List<MenuSel> returnVal = new ArrayList<MenuSel>();
        List<LevelInformation> levelSet;
        LevelSpecificationReader levelSpecificationReader = new LevelSpecificationReader();
        String line;
        String path;
        try {
            String key = null;
            String message = null;
            if (args.length == 0) {
                path = ("level_sets.txt");
            } else {
                path = (args[0]);
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    ClassLoader.getSystemClassLoader().getResourceAsStream(path)));
            while ((line = ((BufferedReader) reader).readLine()) != null) {
                String[] param = line.split(":");
                key = param[0];
                message = param[1];
                line = ((BufferedReader) reader).readLine();
                Reader levelsReader = new InputStreamReader(
                        ClassLoader.getSystemClassLoader().getResourceAsStream(line));
                levelSet = levelSpecificationReader.fromReader(levelsReader);
                returnVal.add(new MenuSel(key, message, levelSet));
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("Error in opening file.");
        }
        return returnVal;
    }
}
