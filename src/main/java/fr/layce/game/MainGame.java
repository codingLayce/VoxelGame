package fr.layce.game;

import fr.layce.engine.Application;
import fr.layce.engine.Layer;
import fr.layce.engine.display.Input;
import fr.layce.engine.display.Mouse;
import fr.layce.engine.renderer.MasterRenderer;
import fr.layce.game.world.Chunk;
import fr.layce.game.world.World;
import fr.layce.game.world.blocks.Block;
import fr.layce.game.world.blocks.BlockRegistry;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import java.util.List;

public class MainGame implements Layer {

    private MasterRenderer renderer;
    private DefaultCamera camera;
    private World world;

    public static void main(String[] args) {
        Application app = Application.createApplication();
        MainGame game = new MainGame();
        app.pushLayer(game);
        app.start();
    }

    @Override
    public void onUpdate(float timeStep) {
        camera.update(timeStep);

        for (Chunk chunk : world.getChunksToRender(camera.getPosition())) {
            List<Block> blocks = chunk.getBlocks();
            for (int j = 0; j < blocks.size(); j++)
                renderer.requestRender(blocks.get(j).getEntity());
        }

        world.updatePlayerPosition(camera.getPosition());

        if (Input.isKeyPressed(GLFW.GLFW_KEY_ESCAPE)) {
            Application.getInstance().close();
        }

        renderer.executeRender(camera);
    }

    @Override
    public void onAttach() {
        this.renderer = new MasterRenderer();
        this.camera = new DefaultCamera(new Vector3f(0, 2, 0));

        BlockRegistry.loadTexturesPath("/blocksRegistry.txt");

        world = new World();
        world.generate(camera.getPosition());
        world.startGenerator();

        Mouse.setCursorGrabbed(true);
    }

    @Override
    public void onDetach() {
        renderer.cleanup();
    }
}
