package fr.layce.engine.display;

import fr.layce.engine.Application;
import org.joml.Vector2f;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import java.nio.DoubleBuffer;

import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {

    private int width;
    private int height;
    private final String title;

    private long window;

    public Window(int width, int height, String title) {
        this.width = width;
        this.height = height;
        this.title = title;
    }

    public void create() {
        if (!GLFW.glfwInit()) {
            System.err.println("Cannot initialize GLFW !");
            System.exit(-1);
        }

        long primaryMonitor = GLFW.glfwGetPrimaryMonitor();
        GLFWVidMode vidMode = GLFW.glfwGetVideoMode(primaryMonitor);

        if (vidMode == null) {
            System.err.println("Cannot retrieve primary monitor !");
            System.exit(-1);
        }

        GLFW.glfwWindowHint(GLFW.GLFW_REFRESH_RATE, vidMode.refreshRate());
        GLFW.glfwWindowHint(GLFW.GLFW_RED_BITS, vidMode.redBits());
        GLFW.glfwWindowHint(GLFW.GLFW_GREEN_BITS, vidMode.greenBits());
        GLFW.glfwWindowHint(GLFW.GLFW_BLUE_BITS, vidMode.blueBits());

        this.window = GLFW.glfwCreateWindow(vidMode.width(), vidMode.height(), this.title, primaryMonitor, NULL);

        GLFW.glfwSetWindowSizeCallback(window, (long targetWindow, int w, int h) -> {
            this.width = w;
            this.height = h;
        });

        GLFW.glfwSetCursorPos(window, width / 2f, height / 2f);

        GLFW.glfwSetWindowCloseCallback(window, (long targetWindow) -> {
            Application.getInstance().close();
        });

        GLFW.glfwMakeContextCurrent(window);
        GLFW.glfwSwapInterval(1);
        GLFW.glfwShowWindow(window);

        GL.createCapabilities();
    }

    public void close() {
        GLFW.glfwDestroyWindow(window);
    }

    public void update() {
        GLFW.glfwSwapBuffers(window);
        GLFW.glfwPollEvents();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Vector2f getMousePosition() {
        DoubleBuffer xBuffer = BufferUtils.createDoubleBuffer(1);
        DoubleBuffer yBuffer = BufferUtils.createDoubleBuffer(1);
        GLFW.glfwGetCursorPos(window, xBuffer, yBuffer);
        return new Vector2f((float)xBuffer.get(0), (float)yBuffer.get(0));
    }

    public int getKey(int keycode) {
        return GLFW.glfwGetKey(window, keycode);
    }

    public boolean isCursorGrabbed() {
        return GLFW.glfwGetInputMode(window, GLFW.GLFW_CURSOR) == GLFW.GLFW_CURSOR_DISABLED;
    }

    public void grabCursor() {
        GLFW.glfwSetInputMode(window, GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_DISABLED);
    }

    public void ungrabCursor() {
        GLFW.glfwSetInputMode(window, GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_NORMAL);
    }
}
