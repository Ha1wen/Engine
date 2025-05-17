import static org.lwjgl.glfw.GLFW.*;

public class WaylandCheck {
    public static void main(String[] args) {
        if (!glfwInit()) {
            throw new IllegalStateException("Failed to initialize GLFW");
        }

        boolean waylandSupported = glfwPlatformSupported(GLFW_PLATFORM_WAYLAND);
        boolean x11Supported = glfwPlatformSupported(GLFW_PLATFORM_X11);

        System.out.println("Wayland supported: " + waylandSupported);
        System.out.println("X11 supported: " + x11Supported);

        glfwTerminate();
    }
}
