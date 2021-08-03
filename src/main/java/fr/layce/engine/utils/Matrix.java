package fr.layce.engine.utils;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Matrix {
    public static Matrix4f transform(Vector3f position, Vector3f rotation, float scale) {
        Matrix4f translation = new Matrix4f().identity().translation(position);
        Matrix4f rotX = new Matrix4f().identity().rotate((float) Math.toRadians(rotation.x), 1, 0, 0);
        Matrix4f rotY = new Matrix4f().identity().rotate((float) Math.toRadians(rotation.y), 0, 1, 0);
        Matrix4f rotZ = new Matrix4f().identity().rotate((float) Math.toRadians(rotation.z), 0, 0, 1);
        Matrix4f scaleMatrix = new Matrix4f().identity().scale(scale);

        Matrix4f rotationMatrix = new Matrix4f(rotX).mul(rotY).mul(rotZ);

        return new Matrix4f().identity().mul(rotationMatrix).mul(scaleMatrix).mul(translation);
    }

    public static Matrix4f projection(float fov, int width, int height, float near, float far) {
        Matrix4f result = new Matrix4f();
        result.identity();

        float aspect = (float) width / (float) height;
        float y_scale = (float) (1f / Math.tan(Math.toRadians(fov / 2f)));
        float x_scale = y_scale / aspect;
        float zp = far + near;
        float zm = far - near;

        result.set(0, 0, x_scale);
        result.set(1, 1, y_scale);
        result.set(2, 2, -zp / zm);
        result.set(2, 3, -1);
        result.set(3, 2, -(2 * far + near) / zm);
        result.set(3, 3, 0);

        return result;
    }
}
