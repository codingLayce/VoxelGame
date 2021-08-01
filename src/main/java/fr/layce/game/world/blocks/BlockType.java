package fr.layce.game.world.blocks;

public enum BlockType {
    DIRT("dirtTex"),
    LEAF("Leaf");

    private final String textureName;

    BlockType(String textureName) {
        this.textureName = textureName;
    }

    public String getTextureName() {
        return textureName;
    }
}
