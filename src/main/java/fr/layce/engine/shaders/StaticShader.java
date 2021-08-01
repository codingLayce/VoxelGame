package fr.layce.engine.shaders;

import fr.layce.engine.entities.Camera;
import fr.layce.engine.utils.Maths;
import org.lwjgl.util.vector.Matrix4f;

public class StaticShader extends ShaderProgram {

    private static final String VERTEX_FILE = "vertexShader.glsl";
    private static final String FRAGMENT_FILE = "fragmentShader.glsl";

    private int location_transformationMatrix;
    private int location_projectionMatrix;
    private int location_viewMatrix;

    public StaticShader() {
        super(VERTEX_FILE, FRAGMENT_FILE);
    }

    @Override
    protected void getAllUniformLocations() {
        this.location_transformationMatrix = super.getUniformocation("transformationMatrix");
        this.location_projectionMatrix = super.getUniformocation("projectionMatrix");
        this.location_viewMatrix = super.getUniformocation("viewMatrix");
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute("position", 0);
        super.bindAttribute("texCoords", 1);
    }

    public void loadTransformationMatrix(Matrix4f matrix) {
        super.loadMatrix(location_transformationMatrix, matrix);
    }

    public void loadProjectionMatrix(Matrix4f matrix) {
        super.loadMatrix(location_projectionMatrix, matrix);
    }

    public void loadViewMatrix(Camera camera) {
        super.loadMatrix(location_viewMatrix, Maths.createViewMatrix(camera));
    }
}
