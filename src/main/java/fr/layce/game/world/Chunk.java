package fr.layce.game.world;

import fr.layce.game.world.blocks.Block;

import java.util.List;

public class Chunk {

    private final List<Block> blocks;
    private final Coordinate location;

    public Chunk(List<Block> blocks, Coordinate location) {
        this.blocks = blocks;
        this.location = location;
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public Coordinate getLocation() {
        return location;
    }
}
