package fr.layce.game.world.blocks;

import fr.layce.engine.entities.Entity;
import fr.layce.game.world.Coordinate;

public class Block {

    private final Coordinate position;
    private final BlockType type;
    private final Entity entity;

    public Block(Coordinate position, BlockType type) {
        this.position = position;
        this.type = type;
        this.entity = new Entity(BlockRegistry.getModel(type), position.toVector3());
    }

    public Coordinate getPosition() {
        return position;
    }

    public BlockType getType() {
        return type;
    }

    public Entity getEntity() {
        return entity;
    }
}
