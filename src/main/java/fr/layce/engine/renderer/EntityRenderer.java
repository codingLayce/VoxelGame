package fr.layce.engine.renderer;

import fr.layce.engine.entities.Entity;
import fr.layce.engine.models.Model;
import fr.layce.engine.shaders.StaticShader;
import fr.layce.engine.utils.Matrix;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import java.util.List;
import java.util.Map;

public class EntityRenderer {

    public void render(Map<Model, List<Entity>> entities, StaticShader staticShader) {
        for (Model model : entities.keySet()) {
            GL30.glBindVertexArray(model.getRawModel().getVaoID());
            GL20.glEnableVertexAttribArray(0);
            GL20.glEnableVertexAttribArray(1);

            GL13.glActiveTexture(GL13.GL_TEXTURE0);
            model.getTexture().bind();

            List<Entity> list = entities.get(model);
            for (int i = 0; i < list.size(); i++) {
                Entity entity = list.get(i);
                Matrix4f transformationMatrix = Matrix.transform(entity.getPosition(), entity.getRotation(), entity.getScale());
                staticShader.loadTransformationMatrix(transformationMatrix);
                GL11.glDrawElements(GL11.GL_TRIANGLES, model.getRawModel().getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
            }

            GL20.glDisableVertexAttribArray(0);
            GL20.glDisableVertexAttribArray(1);
            GL30.glBindVertexArray(0);
        }
    }

}
