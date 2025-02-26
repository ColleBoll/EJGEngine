package org.collebol.engine.event.client.button;

import org.collebol.engine.event.Event;
import org.collebol.engine.gui.graphics.ui.Component;
import org.collebol.engine.gui.graphics.ui.component.Button;
import org.collebol.engine.math.Vector2D;

import java.util.Map;

public class ClientButtonHoverEvent extends Event {

    private Vector2D position;
    private Button button;
    private boolean entered;

    public ClientButtonHoverEvent() {
    }

    public ClientButtonHoverEvent(Vector2D position, Button button, boolean entered) {
        this.position = position;
        this.button = button;
        this.entered = entered;
    }

    public Vector2D getPosition() {
        return position;
    }

    public Button getButton() {
        return button;
    }

    public boolean isEnter() {
        return entered;
    }

    public boolean isExit() {
        return !entered;
    }

    @Override
    public void setValues(Map<Class<?>, Object> params) {
        position = (Vector2D) params.get(Vector2D.class);
        button = (Button) params.get(Component.class);
        entered = (boolean) params.get(Boolean.class);
    }
}
