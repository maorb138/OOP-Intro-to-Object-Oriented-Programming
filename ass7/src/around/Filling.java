package around;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * around.Filling class.
 *
 * @author maor biton.
 *  
 */
public class Filling {
    private Color color;
    private Image image;

    /**
     * Instantiates a new Filling.
     *
     * @param color the color
     * @param image the image
     */
    public Filling(Color color, Image image) {
        this.color = color;
        this.image = image;
    }

    /**
     * Gets color.
     *
     * @return the color
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Gets image.
     *
     * @return the image
     */
    public Image getImage() {
        return this.image;
    }

    /**
     * A image boolean.
     *
     * @return the boolean
     */
    public boolean aImage() {
        return this.color == null;
    }

    /**
     * A color boolean.
     *
     * @return the boolean
     */
    public boolean aColor() {
        return this.image == null;
    }


    /**
     * Color from string java . awt . color.
     *
     * @param s the s
     * @return the java . awt . color
     */
    public static java.awt.Color colorFromString(String s) {
        if (s.contains("RGB")) {
            String[] part1 = s.split("\\(");
            String[] part2 = part1[2].split("\\,");
            String[] part3 = part2[2].split("\\)");
            int r = Integer.parseInt(part2[0]);
            int g = Integer.parseInt(part2[1]);
            int b = Integer.parseInt(part3[0]);
            return (new Color(r, g, b));
        }
        String[] helper = s.split("\\(");
        String[] helper2 = helper[1].split("\\)");
        Map<String, Color> colors = new HashMap<String, Color>();
        colors.put("red", Color.red);
        colors.put("yellow", Color.YELLOW);
        colors.put("blue", Color.BLUE);
        colors.put("cyan", Color.CYAN);
        colors.put("green", Color.GREEN);
        colors.put("magenta", Color.MAGENTA);
        colors.put("black", Color.BLACK);
        colors.put("white", Color.WHITE);
        colors.put("pink", Color.PINK);
        colors.put("orange", Color.ORANGE);
        colors.put("gray", Color.GRAY);
        colors.put("lightGray", Color.LIGHT_GRAY);
        colors.put("darkGray", Color.DARK_GRAY);
        return colors.get(helper2[0]);
    }

    /**
     * Image from string image.
     *
     * @param path the path
     * @return the image
     */
    public static Image imageFromString(String path) {
        Image image = null;
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(path);
        try {
            assert is != null;
            image = ImageIO.read(is);
        } catch (IOException e) {
            System.out.println("Error: failed to load image");
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    System.out.println("Failed closing the image");
                }
            }
        }
        return image;
    }
}
