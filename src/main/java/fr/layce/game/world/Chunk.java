package fr.layce.game.world;

import fr.layce.game.world.blocks.Block;

import java.util.List;

public record Chunk(List<Block> blocks, Coordinate location) {

    public List<Block> getBlocks() {
        return blocks;
    }

    public Coordinate getLocation() {
        return location;
    }
}
