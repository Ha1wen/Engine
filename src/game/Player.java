package game;

import engine.Camera;
import engine.Controller;
import engine.classes.*;

public class Player {
    public CFrame cframe;
    private Camera camera;
    private Controller controller;

    private boolean camOn;

    private int money;

    public Player(CFrame cframe, Camera camera) {
        this.cframe = cframe;
        this.camera = camera;
        this.controller = null;

        camOn = true;

        money = 69;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void move() {
        if (controller==null) return;

        controller.update();
        updateCam();
    }

    private void updateCam() {
        if (!camOn) return;

        camera.cframe = cframe;
    }
    
    public int getMoney() {
        return money;
    }
}
