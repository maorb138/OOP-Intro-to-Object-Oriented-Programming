package levels;

import around.BlockCreator;
import around.BlockFactory;
import around.Filling;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * levels.BlocksDefinitionReader class.
 *
 * @author maor biton.
 *  
 */
public class BlocksDefinitionReader {
    /**
     * From reader blocks from symbols factory.
     *
     * @param reader the reader
     * @return the blocks from symbols factory
     */
    public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader) {
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line;
        Color stroke = null;
        String symbol = null, spacerSymbol = null;
        int defHeight = 0, defWidth = 0, defHit = 0, spacerWidth = 0;
        Map<Integer, Filling> filling = new HashMap<Integer, Filling>();
        Map<String, BlockCreator> symbolBlocks = new HashMap<String, BlockCreator>();
        Map<String, Integer> spacers = new HashMap<String, Integer>();
        try {
            while ((line = ((BufferedReader) bufferedReader).readLine()) != null) {
                line = line.trim();
                if (line.equals("") || line.startsWith("#")) {
                    continue;
                } else {
                    String[] split = line.split(" ");
                    if (split[0].equals("default")) {
                        for (int i = 1; i < split.length; i++) {
                            String[] param = split[i].split(":");
                            switch (param[0]) {
                                case "height":
                                    defHeight = Integer.parseInt((param[1]));
                                    break;
                                case "width":
                                    defWidth = Integer.parseInt((param[1]));
                                    break;
                                case "hit_points":
                                    defHit = Integer.parseInt((param[1]));
                                    break;
                                case "stroke":
                                    stroke = Filling.colorFromString(param[1]);
                                    break;
                                default:
                                    if (param[0].contains("fill")) {
                                        String[] cut = param[0].split("\\-");
                                        int temp;
                                        if (cut.length == 1) {
                                            temp = 0;
                                        } else {
                                            temp = Integer.parseInt(cut[1]);
                                        }
                                        if (param[1].contains("image")) {
                                            String path = LevelSpecificationReader.splitStringToPath(param[1]);
                                            filling.put(temp, new Filling(null, Filling.imageFromString(path)));
                                        } else {
                                            filling.put(temp, new Filling(Filling.colorFromString(param[1]),
                                                    null));
                                        }
                                    }
                                    break;
                            }
                        }
                    } else if (split[0].equals("bdef")) {
                        Map<Integer, Filling> firstFilling = new HashMap<Integer, Filling>();
                        int numberOfHit = defHit;
                        int width = defWidth;
                        int height = defHeight;
                        Color firstStroke = stroke;
                        java.util.List<Integer> keys = new ArrayList<Integer>(filling.keySet());
                        for (int i = 0; i < filling.size(); i++) {
                            firstFilling.put(keys.get(i), filling.get(i));
                        }
                        for (int i = 1; i < split.length; i++) {
                            String[] param = split[i].split(":");
                            switch (param[0]) {
                                case "symbol":
                                    symbol = param[1];
                                    break;
                                case "height":
                                    height = Integer.parseInt((param[1]));
                                    break;
                                case "width":
                                    width = Integer.parseInt((param[1]));
                                    break;
                                case "hit_points":
                                    numberOfHit = Integer.parseInt((param[1]));
                                    break;
                                case "stroke":
                                    firstStroke = Filling.colorFromString(param[1]);
                                    break;
                                default:
                                    if (param[0].contains("fill")) {
                                        String[] cut = param[0].split("\\-");
                                        int temp;
                                        if (cut.length == 1) {
                                            temp = 0;
                                        } else {
                                            temp = Integer.parseInt(cut[1]);
                                        }
                                        if (param[1].contains("image")) {
                                            String path = LevelSpecificationReader.splitStringToPath(param[1]);
                                            firstFilling.put(temp, new Filling(null,
                                                    Filling.imageFromString(path)));
                                        } else {
                                            firstFilling.put(temp, new Filling(Filling.colorFromString(param[1]),
                                                    null));
                                        }
                                    }
                                    break;
                            }
                        }
                        // invalid parameters.
                        if (height <= 0 || width <= 0 || numberOfHit <= 0
                                || firstFilling.isEmpty() || symbol == null) {
                            System.out.print("Not enough parameters.");
                            System.exit(1);
                        } else {
                            symbolBlocks.put(symbol, new BlockFactory(
                                    height, width, numberOfHit, firstStroke, firstFilling));
                        }
                    } else if (split[0].equals("sdef")) {
                        for (int i = 1; i < split.length; i++) {
                            String[] param = split[i].split(":");
                            switch (param[0]) {
                                case "symbol":
                                    spacerSymbol = param[1];
                                    break;
                                case "width":
                                    spacerWidth = Integer.parseInt((param[1]));
                                    break;
                                default:
                                    break;
                            }
                        }
                        spacers.put(spacerSymbol, spacerWidth);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                return new BlocksFromSymbolsFactory(symbolBlocks, spacers);
            }
        }
        return new BlocksFromSymbolsFactory(symbolBlocks, spacers);
    }
}
