package fr.layce.engine.entities;

import fr.layce.engine.models.Model;
import org.joml.Vector3f;

public class Entity {

    private final Model model;
    private final Vector3f position, rotation;
    private float scale;

    public Entity(Model model, Vector3f position) {
        this.model = model;
        this.position = position;
        this.rotation = new Vector3f(0, 0, 0);
        this.scale = 1;
    }

    public Entity(Model model, Vector3f position, Vector3f rotation, float scale) {
        this.model = model;
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
    }

    public void increasePosition(float dx, float dy, float dz) {
        this.position.x += dx;
        this.position.y += dy;
        this.position.z += dz;
    }

    public void increaseRotation(float dx, float dy, float dz) {
        this.rotation.x += dx;
        this.rotation.y += dy;
        this.rotation.z += dz;
    }

    public void increaseScale(float scale) {
        this.scale += scale;
    }

    public Model getModel() {
        return model;
    }

    public Vector3f getPosition() {
        return position;
    }

    public Vector3f getRotation() {
        return rotation;
    }

    public float getScale() {
        return scale;
    }
}
