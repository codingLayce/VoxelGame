package fr.layce.game;

import fr.layce.engine.display.Input;
import fr.layce.engine.display.Mouse;
import fr.layce.engine.entities.Camera;
import fr.layce.engine.utils.Maths;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

public class DefaultCamera extends Camera {

    private Vector3f front = new Vector3f(0, 0, -1);
    private Vector3f up = new Vector3f(0, 1, 0);
    private Vector3f right;
    private float yaw = 0;
    private float pitch = 30;

    private final float speed = 10, mouseSensitivity = 0.05f;
    protected float oldMouseX = 0, oldMouseY = 0, newMouseX, newMouseY;

    public DefaultCamera() {
        super(new Vector3f(0, 0, 0));
        this.right = Maths.normalize(Maths.cross(front, up));
    }

    public DefaultCamera(Vector3f position) {
        super(position);
        this.right = Maths.normalize(Maths.cross(front, up));
    }

    @Override
    public void update(float ts) {
        float velocity = speed * ts;

        if (Input.isKeyPressed(GLFW.GLFW_KEY_W)) {
            position.add(Maths.mul(this.front, velocity));
        }
        if (Input.isKeyPressed(GLFW.GLFW_KEY_S)) {
            position = Maths.minus(position, (Maths.mul(this.front, velocity)));
        }
        if (Input.isKeyPressed(GLFW.GLFW_KEY_A)) {
            this.right = Maths.normalize(Maths.cross(front, up));
            position = Maths.minus(position, (Maths.mul(this.right, velocity)));
        }
        if (Input.isKeyPressed(GLFW.GLFW_KEY_D)) {
            this.right = Maths.normalize(Maths.cross(front, up));
            position.add(Maths.mul(this.right, velocity));
        }

        position.y = 2;

        newMouseX = Mouse.getMousePosition().x;
        newMouseY = Mouse.getMousePosition().y;

        if (newMouseX != oldMouseX || newMouseY != oldMouseY) {
            processMouseMovement(newMouseX - oldMouseX, newMouseY - oldMouseY, ts);

            oldMouseX = newMouseX;
            oldMouseY = newMouseY;
        }
    }

    private void processMouseMovement(float xOffset, float yOffset, float ts) {
        xOffset *= mouseSensitivity;
        yOffset *= mouseSensitivity;

        yaw   += xOffset;
        pitch -= yOffset;

        if (pitch > 89.0f)
            pitch = 89.0f;
        if (pitch < -89.0f)
            pitch = -89.0f;

        Vector3f tmp = new Vector3f();
        tmp.x = (float) Math.cos(Math.toRadians(yaw)) * (float) Math.cos(Math.toRadians(pitch));
        tmp.y = (float) Math.sin(Math.toRadians(pitch));
        tmp.z = (float) Math.sin(Math.toRadians(yaw)) * (float) Math.cos(Math.toRadians(pitch));
        this.front = Maths.normalize(tmp);
    }

    public Matrix4f getViewMatrix() {
        Matrix4f mat = new Matrix4f().identity();
        mat.lookAt(this.position, Maths.add(this.position, this.front), this.up);
        return mat;
    }
}
