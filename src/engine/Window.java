package engine;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Window {

    private long windowHandle;
    private final int width;
    private final int height;
    private final String title;

    public Window(int width, int height, String title) {
        this.width = width;
        this.height = height;
        this.title = title;

        if (!glfwInit())
            throw new IllegalStateException("Unable to initialize GLFW");

        // Set OpenGL version to 2.1 (same as before)
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 2);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 1);
        //glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);

        windowHandle = glfwCreateWindow(width, height, title, NULL, NULL);
        if (windowHandle == NULL)
            throw new RuntimeException("Failed to create the GLFW window");

        glfwMakeContextCurrent(windowHandle);
        glfwSwapInterval(1);  // Enable v-sync
        glfwShowWindow(windowHandle);

        System.out.println("Is context current? " + (glfwGetCurrentContext() == windowHandle));
    }

    public boolean shouldClose() {
        return glfwWindowShouldClose(windowHandle) || getKeyDown(GLFW_KEY_Q);
    }

    public void swapBuffers() {
        glfwSwapBuffers(windowHandle);
    }

    public void pollEvents() {
        glfwPollEvents();
    }

    public void cleanup() {
        glfwDestroyWindow(windowHandle);
        glfwTerminate();
    }

    public long getWindowHandle() {
        return windowHandle;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getTitle() {
        return title;
    }

    public boolean getKeyDown(int key) {
        return glfwGetKey(windowHandle, key) == GLFW_PRESS;
    }
}
