package engine.classes;

public class CFrame {
    public Vector3 position;
    public Vector3 rotation; // In degrees: pitch (x), yaw (y), roll (z)

    public CFrame() {
        this.position = new Vector3();
        this.rotation = new Vector3();
    }

    public CFrame(Vector3 position) {
        this.position = position;
        this.rotation = new Vector3();
    }

    public CFrame(float x, float y, float z) {
        this.position = new Vector3(x, y, z);
        this.rotation = new Vector3();
    }

    public CFrame(Vector3 position, Vector3 rotation) {
        this.position = position;
        this.rotation = rotation;
    }

    public CFrame(float px, float py, float pz, float rx, float ry, float rz) {
        this.position = new Vector3(px, py, pz);
        this.rotation = new Vector3(rx, ry, rz);
    }

    public CFrame copy() {
        return new CFrame(position, rotation);
    }

    public CFrame add(CFrame other) {
        return new CFrame(
            this.position.add(other.position),
            this.rotation.add(other.rotation)
        );
    }

    public CFrame add(Vector3 position) {
        return new CFrame(
            this.position.add(position),
            this.rotation
        );
    }

    public CFrame subtract(CFrame other) {
        return new CFrame(
            this.position.subtract(other.position),
            this.rotation.subtract(other.rotation)
        );
    }

    @Override
    public String toString() {
        return "CFrame(Position: " + position + ", Rotation: " + rotation + ")";
    }
}
