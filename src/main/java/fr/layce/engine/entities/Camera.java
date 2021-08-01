package fr.layce.engine.entities;

import org.lwjgl.util.vector.Vector3f;

public abstract class Camera {
    protected Vector3f position;
    protected float rotX, rotY, rotZ;

    protected Camera(Vector3f position, float rotX, float rotY, float rotZ) {
        this.position = position;
        this.rotX = rotX;
        this.rotY = rotY;
        this.rotZ = rotZ;
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
}
