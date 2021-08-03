package fr.layce.engine.renderer;

import fr.layce.engine.Application;
import fr.layce.engine.entities.Camera;
import fr.layce.engine.entities.Entity;
import fr.layce.engine.models.Model;
import fr.layce.engine.shaders.StaticShader;
import fr.layce.engine.utils.Matrix;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MasterRenderer {

    private static final float FOV = 70;
    private static final float NEAR_PLANE = 0.1f;
    private static final float FAR_PLANE = 100f;

    private Matrix4f projectionMatrix;
    private final Map<Model, List<Entity>> renderQueue;
    private final StaticShader shader;
    private final EntityRenderer entityRenderer;

    public MasterRenderer() {
        renderQueue = new HashMap<>();
        shader = new StaticShader();
        entityRenderer = new EntityRenderer();

        createProjectionMatrix();
        shader.start();
        shader.loadProjectionMatrix(projectionMatrix);
        shader.stop();
    }

    private void prepare() {
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glClearColor(0.4f, 0.7f, 1, 1);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
    }

    public void requestRender(Entity entity) {
        Model model = entity.getModel();

        if (!renderQueue.containsKey(model)) {
            List<Entity> entities = new ArrayList<>();
            entities.add(entity);
            renderQueue.put(model, entities);
        } else {
            renderQueue.get(model).add(entity);
        }
    }

    public void executeRender(Camera camera) {
        prepare();

        shader.start();
        shader.loadViewMatrix(camera);

        entityRenderer.render(renderQueue, shader);

        shader.stop();
        renderQueue.clear();
    }

    public void cleanup() {
        shader.cleanup();
    }

    public void createProjectionMatrix() {
        projectionMatrix = Matrix.projection(FOV, Application.getInstance().getWindow().getWidth(), Application.getInstance().getWindow().getHeight(), NEAR_PLANE, FAR_PLANE);
    }

}
