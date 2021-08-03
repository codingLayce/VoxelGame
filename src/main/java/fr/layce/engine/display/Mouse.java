package fr.layce.engine.display;

import fr.layce.engine.Application;
import org.joml.Vector2f;

public class Mouse {

    public static Vector2f getMousePosition() {
        return Application.getInstance().getWindow().getMousePosition();
    }

    public static boolean isCursorGrabbed() {
        return Application.getInstance().getWindow().isCursorGrabbed();
    }

    public static void setCursorGrabbed(boolean value) {
        if (value)
            Application.getInstance().getWindow().grabCursor();
        else
            Application.getInstance().getWindow().ungrabCursor();
    }

}
