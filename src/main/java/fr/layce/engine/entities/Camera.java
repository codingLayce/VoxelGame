package fr.layce.engine.entities;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public abstract class Camera {
    protected Vector3f position;

    protected Camera(Vector3f pos) {
        this.position = pos;
    }

    public abstract void update(float ts);

    public abstract Matrix4f getViewMatrix();

    public Vector3f getPosition() {
        return position;
    }
}
