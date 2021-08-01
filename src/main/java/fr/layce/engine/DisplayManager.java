package fr.layce.engine;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.*;

public class DisplayManager {

    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;
    private static final int FPS = 144;

    public static void createDisplay() {
        ContextAttribs attribs = new ContextAttribs(3, 2)
                .withForwardCompatible(true)
                .withProfileCore(true);

        try {
            Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
            Display.setTitle("Voxel Engine");
            Display.create(new PixelFormat(), attribs);
            GL11.glViewport(0, 0, Display.getWidth(), Display.getHeight());
        } catch (LWJGLException e) {
            e.printStackTrace();
        }

        Mouse.setGrabbed(true);
    }

    public static void updateDisplay() {
        Display.sync(FPS);
        Display.update();

        while (Keyboard.next()) {
            if (Keyboard.getEventKeyState()) {
                if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
                    Mouse.setGrabbed(!Mouse.isGrabbed());
                }
            }
        }
    }

    public static void closeDisplay() {
        Display.destroy();
        System.exit(0);
    }

}
