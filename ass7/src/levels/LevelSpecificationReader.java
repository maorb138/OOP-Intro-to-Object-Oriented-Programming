package levels;

import around.Filling;
import around.Velocity;
import components.Block;

import java.awt.Color;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * levels.LevelSpecificationReader class.
 *
 * @author maor biton.
 *  
 */
public class LevelSpecificationReader {
    /**
     * From reader list.
     *
     * @param reader the reader
     * @return the list
     */
    public List<LevelInformation> fromReader(java.io.Reader reader) {
        List<LevelInformation> levels = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line;
        String levelName = null, blockDefinitions = null;
        List<Velocity> ballVelocities = new ArrayList<Velocity>();
        int paddleSpeed = 0, paddleWidth = 0, numberBlocks = 0, numberOfBalls = 0, rowHeight = 0;
        int blockStratX = 0, blockStratY = 0;
        Filling backGround = null;
        List<Block> blocks = new ArrayList<Block>();
        Map<Integer, String> locationBlocks = new HashMap<Integer, String>();
        int countIfLegal = 8;

        try {
            while ((line = ((BufferedReader) bufferedReader).readLine()) != null) {
                line = line.trim();
                if (!line.equals("") && !line.startsWith("#")) {
                    if (line.equals("START_LEVEL")) {
                        line = ((BufferedReader) bufferedReader).readLine();
                    }
                    if (line.equals("START_BLOCKS")) {
                        line = ((BufferedReader) bufferedReader).readLine();
                        int i = 1;
                        while (!line.equals("END_BLOCKS")) {
                            locationBlocks.put(i, line);
                            i++;
                            line = ((BufferedReader) bufferedReader).readLine();
                        }
                        countIfLegal--;
                    }
                    if (line.equals("END_LEVEL")) {
                        if (countIfLegal == 0) {
                            Map<String, Integer> paddleDetail = new HashMap<String, Integer>();
                            paddleDetail.put("speed", paddleSpeed);
                            paddleDetail.put("width", paddleWidth);
                            blocks = this.helpBlocks(blockStratX, blockStratY, rowHeight,
                                    locationBlocks, blockDefinitions, bufferedReader);
                            StandartLvl oneLevel = new StandartLvl(levelName, ballVelocities,
                                    backGround, paddleDetail, numberBlocks, numberOfBalls, blocks);
                            levels.add(oneLevel);
                            levelName = null;
                            blockDefinitions = null;
                            ballVelocities = new ArrayList<Velocity>();
                            paddleSpeed = 0;
                            paddleWidth = 0;
                            numberBlocks = 0;
                            numberOfBalls = 0;
                            rowHeight = 0;
                            blockStratX = 0;
                            blockStratY = 0;
                            backGround = null;
                            blocks = new ArrayList<Block>();
                            locationBlocks = new HashMap<Integer, String>();
                            countIfLegal = 8;

                        } else {
                            System.out.println("err no get 8 element in the file");
                        }
                    }
                    String[] splite = line.split(":");
                    switch (splite[0]) {
                        case "level_name":
                            levelName = splite[1];
                            countIfLegal--;
                            break;
                        case "ball_velocities":
                            ballVelocities = helpVelocities(splite[1], ballVelocities);
                            numberOfBalls = ballVelocities.size();
                            countIfLegal = countIfLegal - 2;
                            break;
                        case "background":
                            backGround = helpBackground(splite[1]);
                            countIfLegal--;
                            break;
                        case "paddle_speed":
                            paddleSpeed = Integer.parseInt(splite[1]);
                            countIfLegal--;
                            break;
                        case "paddle_width":
                            paddleWidth = Integer.parseInt(splite[1]);
                            countIfLegal--;
                            break;
                        case "block_definitions":
                            blockDefinitions = splite[1];
                            //countIfLegal--;
                            break;
                        case "blocks_start_x":
                            blockStratX = Integer.parseInt(splite[1]);
                            break;
                        case "blocks_start_y":
                            blockStratY = Integer.parseInt(splite[1]);
                            break;
                        case "row_height":
                            rowHeight = Integer.parseInt(splite[1]);
                            break;
                        case "num_blocks":
                            numberBlocks = Integer.parseInt(splite[1]);
                            countIfLegal--;
                            break;
                        default:
                            break;
                    }
                }
            }
            bufferedReader.close();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        return levels;
    }

    /**
     * Help velocities list.
     *
     * @param line           the line
     * @param ballVelocities the ball velocities
     * @return the list
     */
    private List<Velocity> helpVelocities(String line, List<Velocity> ballVelocities) {
        String[] velocity = line.split(" ");
        for (String s : velocity) {
            String[] param = s.split(",");
            int angle = Integer.parseInt(param[0]);
            int speed = Integer.parseInt(param[1]);
            Velocity newVel = Velocity.fromAngleAndSpeed(angle, speed);
            ballVelocities.add(newVel);
        }
        return ballVelocities;
    }

    /**
     * Help background filling.
     *
     * @param line the line
     * @return the filling
     */
    private Filling helpBackground(String line) {
        String[] param = line.split("\\(");
        Filling filling = null;
        String color;
        if (param[0].equals("image")) {
            String[] temp = param[1].split("\\)");
            String image = temp[0];
            Image newImage = Filling.imageFromString(image);
            filling = new Filling(null, newImage);
            // color.
        } else if (param[0].equals("color")) {
            if (param[1].equals("RGB")) {
                color = "(" + param[1] + "(" + param[2];
            } else {
                color = param[1].split("\\)")[0];
                color = "(" + color + ")";
            }
            Color newColor = Filling.colorFromString(color);
            filling = new Filling(newColor, null);
        }
        return filling;
    }
    /**
     * create the block list.
     * @param x the x value that the blocks starts
     * @param y the y value that the blocks starts
     * @param rowHeight the row height
     * @param locBlocks the location of the blocks
     * @param path the file of the blocks
     * @param reader the file of the blocks
     * @return the block list
     */
    private List<Block> helpBlocks(int x, int y, int rowHeight, Map<Integer, String> locBlocks,
                                   String path, java.io.Reader reader) {
        List<Block> blockList = new ArrayList<>();
        int xpos = x, ypos = y;
        String line;
        try {
            reader = new BufferedReader(new InputStreamReader(ClassLoader
                    .getSystemClassLoader().getResourceAsStream(path)));
            BlocksFromSymbolsFactory factory = BlocksDefinitionReader
                    .fromReader(reader);
            for (Integer key : locBlocks.keySet()) {
                String data = locBlocks.get(key);
                for (int i = 0; i < data.length(); i++) {
                    String symbol = String.valueOf(data.charAt(i));
                    if (factory.isSpaceSymbol(symbol)) {
                        xpos += factory.getSpacerWidth(symbol);
                    } else if (factory.isBlockSymbol(symbol)) {
                        Block block = factory.getBlock(symbol, xpos, ypos);
                        blockList.add(block);
                        xpos += block.getCollisionRectangle().getWidth();
                    }
                }
                ypos += rowHeight;
                xpos = x;
            }

        } catch (Exception e) {
            System.out.println("fail loading file");
        }
        return blockList;
    }

    /**
     * Split string to path string.
     *
     * @param s the s
     * @return the string
     */
    public static String splitStringToPath(String s) {
        String[] param = s.split("\\(");
        String path = param[1].split("\\)")[0];
        return path;
    }
}
