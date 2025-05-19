package engine.classes;

public class Object3D {
    public CFrame cframe;
    public Vector3 size;
    public Color color;

    public Object3D() {
        this.cframe = new CFrame();
        this.size = Vector3.ONE;
        this.color = Color.WHITE; // default: white
    }

    public Object3D(CFrame cframe, Vector3 size, Color color) {
        this.cframe = cframe;
        this.size = size;
        this.color = color;
    }

    public Object3D(Vector3 position, Vector3 rotation, Vector3 size, Color color) {
        this.cframe = new CFrame(position, rotation);
        this.size = size;
        this.color = color;
    }

    public Object3D(float px, float py, float pz,
                    float rx, float ry, float rz,
                    float sx, float sy, float sz,
                    float r, float g, float b) {
        this.cframe = new CFrame(px, py, pz, rx, ry, rz);
        this.size = new Vector3(sx, sy, sz);
        this.color = new Color(r, g, b);
    }

    public void move(Vector3 positionOffset) {
        this.cframe.position = this.cframe.position.add(positionOffset);
    }

    public void rotate(Vector3 rotationOffset) {
        this.cframe.rotation = this.cframe.rotation.add(rotationOffset);
    }

    // transform applies both position and rotation offsets at once
    public void transform(Vector3 positionOffset, Vector3 rotationOffset) {
        move(positionOffset);
        rotate(rotationOffset);
    }

    @Override
    public String toString() {
        return "Object3D(CFrame: " + cframe + ", Size: " + size + ", Color: " + color + ")";
    }
}
