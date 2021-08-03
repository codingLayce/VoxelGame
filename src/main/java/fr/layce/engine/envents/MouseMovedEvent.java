package fr.layce.engine.envents;

public record MouseMovedEvent(float mouseX, float mouseY) implements Event {

    public float getMouseX() {
        return mouseX;
    }

    public float getMouseY() {
        return mouseY;
    }

    @Override
    public EventType getEventType() {
        return EventType.MouseMoved;
    }
}
