import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

public class MainClass {
    public static void main(String[] args) {
        // Setup error callback to print GLFW errors
        System.out.println("DISPLAY = " + System.getenv("DISPLAY"));
        System.out.println("GLFW_PLATFORM = " + System.getenv("GLFW_PLATFORM"));
        
        System.setProperty("org.lwjgl.glfw.platform", "x11");

        GLFWErrorCallback.createPrint(System.err).set();

        // Initialize GLFW
        if (!glfwInit()) {
            System.err.println("Failed to initialize GLFW");
            System.exit(-1);
        }

        // Configure GLFW (OpenGL 3.3 Core Profile)
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);

        // Create window
        long window = glfwCreateWindow(640, 480, "Quick GL Test", NULL, NULL);
        if (window == NULL) {
            System.err.println("Failed to create GLFW window");
            glfwTerminate();
            System.exit(-1);
        }

        // Make OpenGL context current
        glfwMakeContextCurrent(window);

        // Enable v-sync
        glfwSwapInterval(1);

        // Make the window visible
        glfwShowWindow(window);

        // Create OpenGL capabilities (important!)
        GL.createCapabilities();

        // Main loop
        while (!glfwWindowShouldClose(window)) {
            // Clear screen to blue
            glClearColor(0.0f, 0.3f, 0.6f, 1.0f);
            glClear(GL_COLOR_BUFFER_BIT);

            // Swap buffers and poll events
            glfwSwapBuffers(window);
            glfwPollEvents();
        }

        // Cleanup
        glfwDestroyWindow(window);
        glfwTerminate();
    }
}
