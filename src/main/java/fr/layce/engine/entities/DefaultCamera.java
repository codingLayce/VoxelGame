package fr.layce.engine.entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

public class DefaultCamera extends Camera {

    private final float speed = 0.05f;
    private final float sensitivity = 0.15f;

    private float moveAt;

    public DefaultCamera() {
        super(new Vector3f(0, 0, 0), 0, 0, 0);
    }

    public DefaultCamera(Vector3f position) {
        super(position, 0, 0, 0);
    }

    public DefaultCamera(Vector3f position, float rotX, float rotY, float rotZ) {
        super(position, rotX, rotY, rotZ);
    }

    public void move() {
        if (Keyboard.isKeyDown(Keyboard.KEY_Z)) {
            moveAt = -speed;
        } else if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
            moveAt = speed;
        } else {
            moveAt = 0;
        }

        rotX -= Mouse.getDY() * sensitivity;
        rotY += Mouse.getDX() * sensitivity;

        float dx = (float) -(moveAt * Math.sin(Math.toRadians(rotY)));
        float dy = (float) (moveAt * Math.sin(Math.toRadians(rotX)));
        float dz = (float) (moveAt * Math.cos(Math.toRadians(rotY)));

        position.x += dx;
        position.y += dy;
        position.z += dz;
    }
}
