package fr.layce.game;

import fr.layce.engine.DisplayManager;
import fr.layce.engine.Loader;
import fr.layce.engine.entities.DefaultCamera;
import fr.layce.engine.renderer.MasterRenderer;
import fr.layce.game.world.Chunk;
import fr.layce.game.world.World;
import fr.layce.game.world.blocks.Block;
import fr.layce.game.world.blocks.BlockRegistry;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import java.util.List;

public class MainGame {

    public void start() {
        DisplayManager.createDisplay();

        MasterRenderer renderer = new MasterRenderer();

        DefaultCamera camera = new DefaultCamera(new Vector3f(0, 2, 0));

        BlockRegistry.loadTexturesPath("src/main/resources/blocksRegistry.txt");

        World world = new World();
        world.generate(camera.getPosition());
        world.startGenerator();

        while (!Display.isCloseRequested()) {
            if (Mouse.isGrabbed()) {
                camera.move();
            }

            for (Chunk chunk : world.getChunksToRender(camera.getPosition())) {
                List<Block> blocks = chunk.getBlocks();
                for (int j = 0; j < blocks.size(); j++)
                    renderer.requestRender(blocks.get(j).getEntity());
            }

            renderer.executeRender(camera);

            DisplayManager.updateDisplay();
        }

        renderer.cleanup();
        Loader.getInstance().cleanup();
        DisplayManager.closeDisplay();
    }

    public static void main(String[] args) {
        MainGame app = new MainGame();
        app.start();
    }

}
