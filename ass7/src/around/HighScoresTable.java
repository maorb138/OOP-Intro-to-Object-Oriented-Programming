package around;

import java.io.Serializable;
import java.io.File;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * around.HighScoresTable class.
 *
 * @author maor biton.
 *  
 */
public class HighScoresTable implements Serializable {
    private int size;
    private List<ScoreInfo> highScoresTable;

    /**
     * Instantiates a new High scores table.
     *
     * @param size the size
     */
    public HighScoresTable(int size) {
        this.highScoresTable = new ArrayList<ScoreInfo>();
        this.size = size;
    }

    /**
     * Add a high-score.
     *
     * @param score the score
     */
    public void add(ScoreInfo score) {
        int index = getRank(score.getScore()) - 1;
        if (index > this.size) {
            return;
        }
        this.highScoresTable.add(index, score);
        try {
            for (int temp = this.highScoresTable.size() - 1; temp >= 5; temp--) {
                this.highScoresTable.remove(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Return table size.
     *
     * @return the int
     */
    public int sizeOfArry() {
        return this.highScoresTable.size();
    }

    /**
     * Return table size.
     *
     * @return the int
     */
    public int size() {
        return this.size;
    }

    /**
     * Gets high scores.
     *
     * @return the high scores
     */
    public List<ScoreInfo> getHighScores() {
        return this.highScoresTable;
    }

    /**
     * return the rank of the current score.
     *
     * @param score the score
     * @return the rank
     */
    public int getRank(int score) {
        int j;
        for (j = 0; j < this.highScoresTable.size(); j++) {
            if (score > this.highScoresTable.get(j).getScore()) {
                return j + 1;
            }
        }
        return j + 1;
    }

    /**
     * Clear the table.
     */
    public void clear() {
        this.highScoresTable.clear();
    }

    /**
     * Load data form data.
     *
     * @param filename the filename
     * @throws IOException the io exception
     */
    public void load(File filename) throws IOException {
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(filename));
            HighScoresTable highScoresTable1 = (HighScoresTable) objectInputStream.readObject();
            this.size = highScoresTable1.size;
            this.highScoresTable = highScoresTable1.highScoresTable;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            HighScoresTable highScoresTable2 = new HighScoresTable(5);
            highScoresTable2.save(filename);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Save table data to the specified file.
     *
     * @param filename the filename
     * @throws IOException the io exception
     */
    public void save(File filename) throws IOException {
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(filename));
            objectOutputStream.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Load from file high scores table.
     *
     * @param filename the filename
     * @return the high scores table
     */
    public static HighScoresTable loadFromFile(File filename) {
        HighScoresTable emptyTable = new HighScoresTable(5);
        try {
            if (!filename.canRead()) {
                return emptyTable;
            }
            emptyTable.load(filename);
        } catch (IOException e) {
            e.printStackTrace();
            return new HighScoresTable(5);
        }
        return emptyTable;
    }
}
