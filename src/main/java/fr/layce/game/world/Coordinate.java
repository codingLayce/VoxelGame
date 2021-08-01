package fr.layce.game.world;

import org.lwjgl.util.vector.Vector3f;

import java.util.Objects;

public class Coordinate {

    public final int x, y, z;

    public Coordinate(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3f toVector3() {
        return new Vector3f(x, y, z);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return x == that.x && y == that.y && z == that.z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    public String toString() {
        return String.format("(%d:%d:%d)", x, y, z);
    }
}
