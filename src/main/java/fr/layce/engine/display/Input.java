package fr.layce.engine.display;

import fr.layce.engine.Application;
import org.lwjgl.glfw.GLFW;

public class Input {

    public static boolean isKeyPressed(int keycode) {
        int state = Application.getInstance().getWindow().getKey(keycode);
        return (state == GLFW.GLFW_PRESS || state == GLFW.GLFW_REPEAT);
    }

}
