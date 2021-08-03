package fr.layce.engine.models;

import fr.layce.engine.textures.Texture;

public class TexturedModel implements Model {

    private RawModel rawModel;
    private Texture texture;

    public TexturedModel(RawModel rawModel, Texture texture) {
        this.rawModel = rawModel;
        this.texture = texture;
    }

    public RawModel getRawModel() {
        return rawModel;
    }

    public Texture getTexture() {
        return texture;
    }
}
