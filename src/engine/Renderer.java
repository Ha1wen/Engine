package engine;

import engine.classes.*;
import engine.objects.*;

import org.lwjgl.opengl.GL;

import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.*;

public class Renderer {
    private final ArrayList<Object3D> objects;

    public Renderer() {
        objects = new ArrayList<>();
    }

    public void init(int width, int height) {
        GL.createCapabilities();

        glEnable(GL_DEPTH_TEST);
        glViewport(0, 0, width, height);

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        float aspect = (float) width / height;
        perspective(60.0f, aspect, 0.1f, 100.0f);
        glMatrixMode(GL_MODELVIEW);
    }

    private void perspective(float fovY, float aspect, float zNear, float zFar) {
        float fH = (float) Math.tan(Math.toRadians(fovY / 2)) * zNear;
        float fW = fH * aspect;
        glFrustum(-fW, fW, -fH, fH, zNear, zFar);
    }

    public void addObject(Object3D obj) {
        objects.add(obj);
    }

    public void removeObject(Object3D obj) {
        objects.remove(obj);
    }

    public void clearObjects() {
        objects.clear();
    }

    public void render() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        glLoadIdentity();
        glTranslatef(0, 0, 0);  // Camera

        for (Object3D obj : objects) {
            if (!(obj instanceof Box)) continue;

            Box box = (Box) obj;
            Vector3[] corners = box.getCorners();

            Color c = box.color;
            glColor3f(c.r, c.g, c.b);

            glBegin(GL_QUADS);
            drawFace(corners[0], corners[1], corners[5], corners[4]); // Bottom
            drawFace(corners[3], corners[2], corners[6], corners[7]); // Top
            drawFace(corners[4], corners[5], corners[6], corners[7]); // Front
            drawFace(corners[0], corners[1], corners[2], corners[3]); // Back
            drawFace(corners[0], corners[3], corners[7], corners[4]); // Left
            drawFace(corners[1], corners[2], corners[6], corners[5]); // Right
            glEnd();
        }
    }

    private void drawFace(Vector3 a, Vector3 b, Vector3 c, Vector3 d) {
        glVertex3f(a.x, a.y, a.z);
        glVertex3f(b.x, b.y, b.z);
        glVertex3f(c.x, c.y, c.z);
        glVertex3f(d.x, d.y, d.z);
    }
}
