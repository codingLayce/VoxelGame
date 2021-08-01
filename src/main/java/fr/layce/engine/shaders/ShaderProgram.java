package fr.layce.engine.shaders;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import java.io.IOException;
import java.io.InputStream;
import java.nio.FloatBuffer;
import java.nio.charset.StandardCharsets;

public abstract class ShaderProgram {

    protected int programID;
    protected int vertexShaderID;
    protected int fragmentShaderID;

    public ShaderProgram(String vertexShaderFile, String fragmentShaderFile) {
        this.programID = GL20.glCreateProgram();
        this.vertexShaderID = loadShader(vertexShaderFile, GL20.GL_VERTEX_SHADER);
        this.fragmentShaderID = loadShader(fragmentShaderFile, GL20.GL_FRAGMENT_SHADER);

        GL20.glAttachShader(programID, vertexShaderID);
        GL20.glAttachShader(programID, fragmentShaderID);
        bindAttributes();
        GL20.glLinkProgram(programID);
        GL20.glValidateProgram(programID);

        getAllUniformLocations();
    }

    public void start() {
        GL20.glUseProgram(programID);
    }

    public void stop() {
        GL20.glUseProgram(0);
    }

    public void cleanup() {
        stop();
        GL20.glDetachShader(programID, vertexShaderID);
        GL20.glDetachShader(programID, fragmentShaderID);
        GL20.glDeleteShader(vertexShaderID);
        GL20.glDeleteShader(fragmentShaderID);
        GL20.glDeleteProgram(programID);
    }

    protected abstract void getAllUniformLocations();

    protected int getUniformocation(String name) {
        return GL20.glGetUniformLocation(programID, name);
    }

    protected void bindAttribute(String varName, int attribute) {
        GL20.glBindAttribLocation(programID, attribute, varName);
    }

    protected abstract void bindAttributes();

    protected void loadFloat(int location, float value) {
        GL20.glUniform1f(location, value);
    }

    protected void load2DVector(int location, Vector2f value) {
        GL20.glUniform2f(location, value.x, value.y);
    }

    protected void load3DVector(int location, Vector3f value) {
        GL20.glUniform3f(location, value.x, value.y, value.z);
    }

    protected void loadMatrix(int location, Matrix4f matrix) {
        FloatBuffer buffer = BufferUtils.createFloatBuffer(16);
        matrix.store(buffer);
        buffer.flip();

        GL20.glUniformMatrix4(location, false, buffer);
    }

    protected void loadBoolean(int location, boolean value) {
        int toLoad = 0;
        if (value)
            toLoad = 1;
        GL20.glUniform1i(location, toLoad);
    }

    private int loadShader(String file, int type) {
        String source = null;
        InputStream in = this.getClass().getResourceAsStream(file);

        assert in != null;

        try {
            source = new String(in.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert source != null;

        int shaderID = GL20.glCreateShader(type);

        GL20.glShaderSource(shaderID, source);
        GL20.glCompileShader(shaderID);

        if (GL20.glGetShaderi(shaderID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
            System.out.println(GL20.glGetShaderInfoLog(shaderID, 1000));
            System.err.println("Cannot compile shader: " + file);
            System.exit(-1);
        }

        return shaderID;
    }

}
