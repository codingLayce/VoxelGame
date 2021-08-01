package fr.layce.engine.renderer;

import fr.layce.engine.entities.Camera;
import fr.layce.engine.entities.Entity;
import fr.layce.engine.models.Model;
import fr.layce.engine.shaders.StaticShader;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MasterRenderer {

    private static final float FOV = 90;
    private static final float NEAR_PLANE = 0.1f;
    private static final float FAR_PLANE = 1000;

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
        float aspectRatio = (float) Display.getWidth() / (float) Display.getHeight();
        float y_scale = 1f / (float) Math.tan(FOV / 2f);
        float x_scale = y_scale / aspectRatio;
        float zp = FAR_PLANE + NEAR_PLANE;
        float zm = FAR_PLANE - NEAR_PLANE;

        projectionMatrix = new Matrix4f();
        projectionMatrix.m00 = x_scale;
        projectionMatrix.m11 = y_scale;
        projectionMatrix.m22 = -zp / zm;
        projectionMatrix.m23 = -1;
        projectionMatrix.m32 = -(2 * FAR_PLANE * NEAR_PLANE) / zm;
        projectionMatrix.m33 = 0;
    }

}
