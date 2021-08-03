package fr.layce.game.world.blocks;

import fr.layce.engine.models.CubeModel;

import java.io.*;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class BlockRegistry {

    private static final Map<BlockType, CubeModel> blocksModel = new HashMap<>();

    public static void loadTexturesPath(final String configFile) {
        FileInputStream in = null;
        File file = null;

        try {
            file = new File(Objects.requireNonNull(BlockRegistry.class.getResource(configFile)).toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        try {
            in = new FileInputStream(Objects.requireNonNull(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String line;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(in)));
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
