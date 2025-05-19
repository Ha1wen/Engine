package game;

import engine.*;
import engine.classes.*;
import engine.objects.*;

public class Game {
    private Window window;
    private Renderer renderer;
    private UserInterface ui;

    private Map map;

    private Player player;
    private Camera camera;


    public Game() {
        window = new Window(1920, 1080, "Game");
        renderer = new Renderer(window);

        map = new Map();

        camera = renderer.getCamera();
        player = new Player(new CFrame(0, 1.5f, 0), camera);
        player.setController(new Controller(window, player.cframe, Controller.Mode.PLAYER));
        ui = new UserInterface(player, renderer);

        renderer.addObjects(map.getObjects());

        // renderer.addObject(new Box(
        //         new Vector3(-2, 0, -10),
        //         new Vector3(30, 45, 0),
        //         new Vector3(1, 1, 1),
        //         Color.RED
        // ));

    }

    public void play() {
        loop();

        window.cleanup();
    }

    private void loop() {
        while (!window.shouldClose()) {
            player.move();

            //System.out.println(player.cframe);

            renderer.render();

            ui.render();
            // ui.newFrame();
            // ui.layout();
            // ui.render();

            window.swapBuffers();
            window.pollEvents();
        }
    }
}
