package fr.layce.engine.utils;

import fr.layce.engine.entities.Camera;
import org.joml.Math;
import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Maths {

    public static Matrix4f createViewMatrix(Camera camera) {
        return camera.getViewMatrix();
    }

    public static Vector3f cross(Vector3f a, Vector3f b) {
        float rx = Math.fma(a.y, b.z(), -a.z * b.y());
        float ry = Math.fma(a.z, b.x(), -a.x * b.z());
        float rz = Math.fma(a.x, b.y(), -a.y * b.x());
        return new Vector3f(rx, ry, rz);
    }

    public static Vector3f normalize(Vector3f a) {
        float scalar = Math.invsqrt(Math.fma(a.x, a.x, Math.fma(a.y, a.y, a.z * a.z)));
        float x = a.x * scalar;
        float y = a.y * scalar;
        float z = a.z * scalar;
        return new Vector3f(x, y, z);
    }

    public static Vector3f add(Vector3f a, Vector3f b) {
        return new Vector3f(a.x + b.x, a.y + b.y, a.z + b.z);
    }

    public static Vector3f minus(Vector3f a, Vector3f b) {
        return new Vector3f(a.x - b.x, a.y - b.y, a.z - b.z);
    }

    public static Vector3f mul(Vector3f a, float scale) {
        return new Vector3f(a.x * scale, a.y * scale, a.z * scale);
    }

}
