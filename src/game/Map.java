package game;

import java.util.ArrayList;

import engine.*;
import engine.classes.*;
import engine.objects.*;

public class Map {
    private ArrayList<Object3D> objects;

    private Object3D floor;
    private Object3D skybox;
    private Object3D pond;

    public Map() {
        objects = new ArrayList<>();

        floor = new Box(
            new CFrame(), 
            new Vector3(10, 1, 10), 
            Color.GREEN
        );
        skybox = new Box(
            new CFrame(), 
            new Vector3(100, 100, 100), 
            Color.CYAN
        );
        pond = new Box(
            new CFrame(0, .5f, 2), 
            new Vector3(1, 1, 1), 
            Color.BLUE
        );

        objects.add(floor);
        objects.add(skybox);
        objects.add(pond);
    }   

    public ArrayList<Object3D> getObjects() {
        return objects;
    }
}
