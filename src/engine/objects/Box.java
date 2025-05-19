package engine.objects;

import engine.classes.*;

public class Box extends Object3D {

    public Box() {
        super();
    }

    public Box(CFrame cframe, Vector3 size, Color color) {
        super(cframe, size, color);
    }

    public Box(Vector3 position, Vector3 rotation, Vector3 size, Color color) {
        super(position, rotation, size, color);
    }

    /**
     * Returns the 8 corners of the 3D box with rotation and position applied.
     * The order of corners is:
     * 0: (-x, -y, -z)
     * 1: ( x, -y, -z)
     * 2: ( x,  y, -z)
     * 3: (-x,  y, -z)
     * 4: (-x, -y,  z)
     * 5: ( x, -y,  z)
     * 6: ( x,  y,  z)
     * 7: (-x,  y,  z)
     * 
     * @return Vector3[8] array of corner positions in world space
     */
    public Vector3[] getCorners() {
        Vector3 halfSize = new Vector3(size.x / 2f, size.y / 2f, size.z / 2f);

        Vector3[] localCorners = new Vector3[] {
            new Vector3(-halfSize.x, -halfSize.y, -halfSize.z),
            new Vector3( halfSize.x, -halfSize.y, -halfSize.z),
            new Vector3( halfSize.x,  halfSize.y, -halfSize.z),
            new Vector3(-halfSize.x,  halfSize.y, -halfSize.z),
            new Vector3(-halfSize.x, -halfSize.y,  halfSize.z),
            new Vector3( halfSize.x, -halfSize.y,  halfSize.z),
            new Vector3( halfSize.x,  halfSize.y,  halfSize.z),
            new Vector3(-halfSize.x,  halfSize.y,  halfSize.z),
        };

        Vector3[] worldCorners = new Vector3[8];

        for (int i = 0; i < 8; i++) {
            Vector3 rotated = rotateVector(localCorners[i], cframe.rotation);
            worldCorners[i] = cframe.position.add(rotated);
        }

        return worldCorners;
    }

    /**
     * Rotates a vector by Euler angles (degrees) in order X -> Y -> Z.
     *
     * @param vec Vector to rotate
     * @param rotation Euler angles in degrees (x, y, z)
     * @return rotated Vector3
     */
    private Vector3 rotateVector(Vector3 vec, Vector3 rotation) {
        double radX = Math.toRadians(rotation.x);
        double radY = Math.toRadians(rotation.y);
        double radZ = Math.toRadians(rotation.z);

        // Rotate around X
        double cosX = Math.cos(radX);
        double sinX = Math.sin(radX);
        double y1 = vec.y * cosX - vec.z * sinX;
        double z1 = vec.y * sinX + vec.z * cosX;
        double x1 = vec.x;

        // Rotate around Y
        double cosY = Math.cos(radY);
        double sinY = Math.sin(radY);
        double z2 = z1 * cosY - x1 * sinY;
        double x2 = z1 * sinY + x1 * cosY;
        double y2 = y1;

        // Rotate around Z
        double cosZ = Math.cos(radZ);
        double sinZ = Math.sin(radZ);
        double x3 = x2 * cosZ - y2 * sinZ;
        double y3 = x2 * sinZ + y2 * cosZ;
        double z3 = z2;

        return new Vector3((float)x3, (float)y3, (float)z3);
    }
}
