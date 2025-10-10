package org.collebol.client.event.client.field;

import org.collebol.client.event.ClientEvent;
import org.collebol.client.gui.graphics.ui.Component;
import org.collebol.client.gui.graphics.ui.component.Field;
import org.collebol.shared.math.Vector2D;

import java.util.Map;

public class ClientFieldHoverEvent extends ClientEvent {

    private Vector2D position;
    private Field field;
    private boolean entered;

    public ClientFieldHoverEvent() {
    }

    public ClientFieldHoverEvent(Vector2D position, Field field, boolean entered) {
        this.position = position;
        this.field = field;
        this.entered = entered;
    }

    public Vector2D getPosition() {
        return position;
    }

    public Field getField() {
        return field;
    }

    public boolean isEnter() {
        return entered;
    }

    public boolean isExit() {
        return !entered;
    }

    @Override
    public <T> void setValues(Map<Class<?>, T> params) {
        position = (Vector2D) params.get(Vector2D.class);
        field = (Field) params.get(Component.class);
        entered = (boolean) params.get(Boolean.class);
    }
}
