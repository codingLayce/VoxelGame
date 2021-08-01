package fr.layce.game.world.blocks;

import fr.layce.engine.models.CubeModel;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class BlockRegistry {

    private static final Map<BlockType, CubeModel> blocksModel = new HashMap<>();

    public static void loadTexturesPath(final String configFile) {
        FileInputStream in = null;
        try {
            in = new FileInputStream(configFile);
        } catch (FileNotFoundException e) {
            System.err.println("Cannot open " + configFile);
            e.printStackTrace();
            System.exit(-1);
        }

        String line;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            while ((line = reader.readLine()) != null) {
                BlockType type = BlockType.valueOf(line);
                blocksModel.put(type, new CubeModel(type));
                System.out.printf("[BlockRegistry] - Configured Block '%s'\n", type);
            }
        } catch (IOException e) {
            System.err.println("Error while reading blocks file");
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public static CubeModel getModel(BlockType type) {
        return blocksModel.get(type);
    }

}
