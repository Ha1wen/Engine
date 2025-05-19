package engine;

import engine.classes.*;

public class Camera {
    public CFrame cframe;

    public float fov;
    public float aspect;
    public float zNear;
    public float zFar;


    public Camera() {
        cframe = new CFrame();

        fov = 60f;
        aspect = 3/4f;
        zNear = 0.1f;
        zFar = 100f;
    }
}
