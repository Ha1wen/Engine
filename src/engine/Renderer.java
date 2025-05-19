package engine;

import engine.classes.*;
import engine.objects.*;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL;

import java.nio.ByteBuffer;
import java.util.ArrayList;

import static org.lwjgl.stb.STBEasyFont.*;
import static org.lwjgl.opengl.GL11.*;

public class Renderer {
    private final ArrayList<Object3D> objects;

    private final ByteBuffer charBuffer;
    private final int maxBufferSize = 99999;

    private final Camera camera;

    public Renderer(Window window) {
        objects = new ArrayList<>();
        camera = new Camera();

        charBuffer = BufferUtils.createByteBuffer(maxBufferSize);

        int width = window.getWidth();
        int height = window.getHeight();

        camera.aspect = (float) width / height;

        GL.createCapabilities();

        glEnable(GL_DEPTH_TEST);
        glViewport(0, 0, width, height);

        updatePerspective();
    }

    public void updateCFrame() {
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();

        Vector3 rot = camera.cframe.rotation;
        Vector3 pos = camera.cframe.position;

        glRotatef(-rot.x, 1, 0, 0);
        glRotatef(rot.y, 0, 1, 0);
        glRotatef(rot.z, 0, 0, 1);

        glTranslatef(pos.x, -pos.y, pos.z);  // Camera
    }

    private void updatePerspective() {
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();

        float fH = (float) Math.tan(Math.toRadians(camera.fov / 2)) * camera.zNear;
        float fW = fH * camera.aspect;
        glFrustum(-fW, fW, -fH, fH, camera.zNear, camera.zFar);
    }

    public void addObject(Object3D obj) {
        objects.add(obj);
    }

    public void addObjects(ArrayList<Object3D> objs) {
        for (Object3D obj: objs) {
            addObject(obj);
        }
    }

    public void removeObject(Object3D obj) {
        objects.remove(obj);
    }

    public void clearObjects() {
        objects.clear();
    }

    public void renderText(String text, float x, float y, float size, Color color) {
        glMatrixMode(GL_PROJECTION);
        glPushMatrix();
        glLoadIdentity();
        glOrtho(0, 800, 600, 0, -1, 1); // Replace 800x600 with your actual window size

        glMatrixMode(GL_MODELVIEW);
        glPushMatrix();
        glLoadIdentity();

        glDisable(GL_DEPTH_TEST); // Draw on top of 3D

        glTranslatef(x, y, 0);   // Move to position
        glScalef(size, size, 1); // Scale text

        int numQuads = stb_easy_font_print(0, 0, text, null, charBuffer);

        glColor3f(color.r, color.g, color.b); // Set custom color

        glBegin(GL_QUADS);
        for (int i = 0; i < numQuads * 4; i++) {
            float vx = charBuffer.getFloat(i * 16);
            float vy = charBuffer.getFloat(i * 16 + 4);
            glVertex2f(vx, vy);
        }
        glEnd();

        glEnable(GL_DEPTH_TEST); // Restore depth test

        glMatrixMode(GL_PROJECTION);
        glPopMatrix();
        glMatrixMode(GL_MODELVIEW);
        glPopMatrix();
    }




    public void render() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        updatePerspective();
        updateCFrame();

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

    public Camera getCamera() {
        return camera;
    }
}
