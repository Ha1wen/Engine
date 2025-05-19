package engine.classes;

public class Vector3 {
    public float x, y, z;

    public static final Vector3 ZERO = new Vector3();
    public static final Vector3 ONE = new Vector3(1,1,1);

    public Vector3() {
        this(0f, 0f, 0f);
    }

    public Vector3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3(int x, int y, int z) {
        this((float)x, (float)y, (float)z);
    }

    public Vector3 add(Vector3 other) {
        return new Vector3(this.x + other.x, this.y + other.y, this.z + other.z);
    }

    public Vector3 subtract(Vector3 other) {
        return new Vector3(this.x - other.x, this.y - other.y, this.z - other.z);
    }

    public Vector3 scale(float scalar) {
        return new Vector3(this.x * scalar, this.y * scalar, this.z * scalar);
    }

    public float dot(Vector3 other) {
        return this.x * other.x + this.y * other.y + this.z * other.z;
    }

    public Vector3 cross(Vector3 other) {
        return new Vector3(
            this.y * other.z - this.z * other.y,
            this.z * other.x - this.x * other.z,
            this.x * other.y - this.y * other.x
        );
    }

    public float length() {
        return (float) Math.sqrt(x * x + y * y + z * z);
    }

    public Vector3 normalize() {
        float len = length();
        if (len == 0) return new Vector3(0, 0, 0);
        return scale(1f / len);
    }

    @Override
    public String toString() {
        return String.format("Vector3(%.3f, %.3f, %.3f)", x, y, z);
    }
}
