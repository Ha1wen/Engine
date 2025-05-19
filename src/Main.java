import engine.*;
import engine.classes.*;
import engine.objects.*;

public class Main {

    private Window window;
    private Renderer renderer;
    private int rot;

    public void run() {
        window = new Window(800, 600, "3D Box Renderer");
        window.init();

        renderer = new Renderer();
        renderer.init(window.getWidth(), window.getHeight());

        rot = 0;

        // Add boxes
        renderer.addObject(new Box(
                new Vector3(-2, 0, -10),
                new Vector3(30, 45, 0),
                new Vector3(1, 1, 1),
                Color.RED
        ));
        renderer.addObject(new Box(
                new Vector3(2, 0, -10),
                new Vector3(0, 45, 45),
                new Vector3(1.5f, 2, 0.5f),
                Color.GREEN
        ));
        renderer.addObject(new Box(
                new Vector3(0, 2, -8),
                new Vector3(45, 0, 0),
                new Vector3(1, 0.5f, 1),
                Color.BLUE
        ));

        loop();

        window.cleanup();
    }

    private void loop() {
        while (!window.shouldClose()) {
            renderer.render();

            window.swapBuffers();
            window.pollEvents();
        }
    }

    public static void main(String[] args) {
        new Main().run();
    }
}
