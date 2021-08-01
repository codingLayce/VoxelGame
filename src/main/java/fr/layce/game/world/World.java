package fr.layce.game.world;

import fr.layce.game.world.blocks.Block;
import fr.layce.game.world.blocks.BlockType;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class World {

    private static final int CHUNK_RENDER_DISTANCE = 3;
    private static final int CHUNK_SIZE = 8;

    private final Map<Coordinate, Chunk> chunks;

    private Vector3f playerPosition;

    public World() {
        this.chunks = new HashMap<>();
    }

    public void startGenerator() {
        new Thread(() -> {
            while (!Display.isCloseRequested()) {
                Coordinate chunkPos = getChunkCoordinateFromPosition(this.playerPosition);
                for (int x = chunkPos.x - CHUNK_RENDER_DISTANCE; x < chunkPos.x + CHUNK_RENDER_DISTANCE; x++) {
                    for (int z = chunkPos.z - CHUNK_RENDER_DISTANCE; z < chunkPos.z + CHUNK_RENDER_DISTANCE; z++) {
                        Coordinate pos = new Coordinate(x, 0, z);
                        if (!chunks.containsKey(pos)) {
                            chunks.put(pos, createChunk(pos, BlockType.LEAF));
                        }
                    }
                }
            }

        }).start();
    }

    public void generate(Vector3f origin) {
        this.playerPosition = origin;
        Coordinate playerOrigin = getChunkCoordinateFromPosition(origin);

        for (int x = playerOrigin.x - CHUNK_RENDER_DISTANCE; x < playerOrigin.x + CHUNK_RENDER_DISTANCE; x++) {
            for (int z = playerOrigin.z - CHUNK_RENDER_DISTANCE; z < playerOrigin.z + CHUNK_RENDER_DISTANCE; z++) {
                Coordinate pos = new Coordinate(x, 0, z);
                chunks.put(pos, createChunk(pos, BlockType.DIRT));
            }
        }
    }

    public synchronized void updatePlayerPosition(Vector3f position) {
        this.playerPosition = position;
    }

    public List<Chunk> getChunksToRender(Vector3f position) {
        Coordinate chunkPos = getChunkCoordinateFromPosition(position);

        List<Chunk> toRender = new ArrayList<>();
        for (int x = chunkPos.x - CHUNK_RENDER_DISTANCE; x < chunkPos.x + CHUNK_RENDER_DISTANCE; x++) {
            for (int z = chunkPos.z - CHUNK_RENDER_DISTANCE; z < chunkPos.z + CHUNK_RENDER_DISTANCE; z++) {
                Coordinate pos = new Coordinate(x, 0, z);
                if (chunks.containsKey(pos))
                    toRender.add(chunks.get(pos));
            }
        }

        return toRender;
    }

    public Coordinate getChunkCoordinateFromPosition(Vector3f position) {
        return new Coordinate((int) (position.x / CHUNK_SIZE), 0, (int) (position.z / CHUNK_SIZE));
    }

    private Chunk createChunk(Coordinate coord, BlockType type) {
        List<Block> blocks = new ArrayList<>();
        for (int i = 0; i < CHUNK_SIZE; i++) {
            for (int j = 0; j < CHUNK_SIZE; j++) {
                blocks.add(new Block(new Coordinate((coord.x * CHUNK_SIZE) + i, 0, (coord.z * CHUNK_SIZE) + j), type));
            }
        }

        return new Chunk(blocks, coord);
    }

}
