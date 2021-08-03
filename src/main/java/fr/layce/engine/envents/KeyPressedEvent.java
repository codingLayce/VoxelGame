package fr.layce.engine.envents;

public record KeyPressedEvent(int keyCode) implements Event{

    public int getKeyCode() {
        return keyCode;
    }

    @Override
    public EventType getEventType() {
        return EventType.KeyPressed;
    }
}
