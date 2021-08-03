package fr.layce.engine;

import fr.layce.engine.display.Window;
import fr.layce.engine.textures.Texture;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.List;

public class Application {

    private static Application instance;

    private Application() {
        window = new Window(1280, 720, "Test");
        isRunning = true;
        lastFrameTime = 0;
        layers = new ArrayList<>();

        window.create();
    }

    public static Application getInstance() {
        if (instance == null)
            instance = new Application();
        return instance;
    }

    public static Application createApplication() {
        instance = new Application();
        return instance;
    }

    private final Window window;
    private boolean isRunning;
    private float lastFrameTime;
    private final List<Layer> layers;

    public Window getWindow() {
        return window;
    }

    public void start() {
        this.isRunning = true;

        while (this.isRunning) {
            float time = (float) GLFW.glfwGetTime();
            float timestep = time - lastFrameTime;
            lastFrameTime = time;

            layers.forEach((layer) -> layer.onUpdate(timestep));

            window.update();
        }
    }

    public void pushLayer(Layer layer) {
        this.layers.add(layer);
        layer.onAttach();
    }

    public void close() {
        this.isRunning = false;

        layers.forEach(Layer::onDetach);

        Loader.getInstance().cleanup();
        Texture.cleanup();
        window.close();
    }

    public boolean isRunning() {
        return isRunning;
    }
}
