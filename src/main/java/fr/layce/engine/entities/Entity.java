package fr.layce.engine.entities;

import fr.layce.engine.models.Model;
import org.lwjgl.util.vector.Vector3f;

public class Entity {

    private final Model model;
    private final Vector3f position;
    private float rotX, rotY, rotZ;
    private float scale;

    public Entity(Model model, Vector3f position) {
        this.model = model;
        this.position = position;
        this.rotX = 0;
        this.rotY = 0;
        this.rotZ = 0;
        this.scale = 1;
    }

    public Entity(Model model, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
        this.model = model;
        this.position = position;
        this.rotX = rotX;
        this.rotY = rotY;
        this.rotZ = rotZ;
        this.scale = scale;
    }

    public void increasePosition(float dx, float dy, float dz) {
        this.position.x += dx;
        this.position.y += dy;
        this.position.z += dz;
    }

    public void increaseRotation(float dx, float dy, float dz) {
        this.rotX += dx;
        this.rotY += dy;
        this.rotZ += dz;
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

    public float getRotX() {
        return rotX;
    }

    public float getRotY() {
        return rotY;
    }

    public float getRotZ() {
        return rotZ;
    }

    public float getScale() {
        return scale;
    }
}
