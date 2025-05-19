package engine;

import engine.classes.CFrame;
import engine.classes.Vector3;

import static org.lwjgl.glfw.GLFW.*;

public class Controller {
    public enum Mode {
        FREE_ROAM,
        PLAYER
    }

    private final Window window;
    private final CFrame cframe;
    private Mode mode;

    private double lastMouseX, lastMouseY;
    private boolean firstMouse = true;

    public float speed = 0.02f;
    public float sensitivity = 0.2f;

    public Controller(Window window, CFrame cframe, Mode mode) {
        this.window = window;
        this.cframe = cframe;
        this.mode = mode;

        glfwSetInputMode(window.getWindowHandle(), GLFW_CURSOR, GLFW_CURSOR_DISABLED);

        glfwSetCursorPosCallback(window.getWindowHandle(), (win, xpos, ypos) -> {
            if (firstMouse) {
                lastMouseX = xpos;
                lastMouseY = ypos;
                firstMouse = false;
            }

            double dx = xpos - lastMouseX;
            double dy = lastMouseY - ypos;

            lastMouseX = xpos;
            lastMouseY = ypos;

            cframe.rotation.y += dx * sensitivity;
            cframe.rotation.x += dy * sensitivity;

            cframe.rotation.x = Math.max(-89.9f, Math.min(89.9f, cframe.rotation.x));
        });
    }

    public void update() {
        if (mode == Mode.FREE_ROAM) {
            updateFreeRoam();
        } else if (mode == Mode.PLAYER) {
            updatePlayer();
        }
    }

    private void updateFreeRoam() {
        Vector3 pos = cframe.position;
        Vector3 rot = cframe.rotation;

        float yaw = (float) Math.toRadians(rot.y);
        float pitch = (float) Math.toRadians(rot.x);

        float forwardX = (float) -(Math.cos(pitch) * Math.sin(yaw));
        float forwardY = (float) Math.sin(pitch);
        float forwardZ = (float) (Math.cos(pitch) * Math.cos(yaw));

        float rightX = (float) -Math.sin(yaw + Math.PI / 2);
        float rightZ = (float) Math.cos(yaw + Math.PI / 2);

        if (window.getKeyDown(GLFW_KEY_W)) {
            pos.x += forwardX * speed;
            pos.y += forwardY * speed;
            pos.z += forwardZ * speed;
        }
        if (window.getKeyDown(GLFW_KEY_S)) {
            pos.x -= forwardX * speed;
            pos.y -= forwardY * speed;
            pos.z -= forwardZ * speed;
        }
        if (window.getKeyDown(GLFW_KEY_A)) {
            pos.x -= rightX * speed;
            pos.z -= rightZ * speed;
        }
        if (window.getKeyDown(GLFW_KEY_D)) {
            pos.x += rightX * speed;
            pos.z += rightZ * speed;
        }
        if (window.getKeyDown(GLFW_KEY_SPACE)) {
            pos.y += speed;
        }
        if (window.getKeyDown(GLFW_KEY_LEFT_SHIFT)) {
            pos.y -= speed;
        }
    }


    private void updatePlayer() {
        Vector3 pos = cframe.position;
        Vector3 rot = cframe.rotation;

        float yaw = (float) Math.toRadians(rot.y);
        float pitch = (float) Math.toRadians(rot.x);

        float forwardX = (float) -(Math.cos(pitch) * Math.sin(yaw));
        //float forwardY = (float) Math.sin(pitch);
        float forwardZ = (float) (Math.cos(yaw));

        float rightX = (float) -Math.sin(yaw + Math.PI / 2);
        float rightZ = (float) Math.cos(yaw + Math.PI / 2);

        if (window.getKeyDown(GLFW_KEY_W)) {
            pos.x += forwardX * speed;
            //pos.y += forwardY * speed;
            pos.z += forwardZ * speed;
        }
        if (window.getKeyDown(GLFW_KEY_S)) {
            pos.x -= forwardX * speed;
            //pos.y -= forwardY * speed;
            pos.z -= forwardZ * speed;
        }
        if (window.getKeyDown(GLFW_KEY_A)) {
            pos.x -= rightX * speed;
            pos.z -= rightZ * speed;
        }
        if (window.getKeyDown(GLFW_KEY_D)) {
            pos.x += rightX * speed;
            pos.z += rightZ * speed;
        }
        // if (window.getKeyDown(GLFW_KEY_SPACE)) {
        //     pos.y += speed;
        // }
        // if (window.getKeyDown(GLFW_KEY_LEFT_SHIFT)) {
        //     pos.y -= speed;
        // }
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public Mode getMode() {
        return mode;
    }
}
