package org.collebol.client.event.client.field;

import org.collebol.client.EJGEngine;
import org.collebol.client.event.ClientEvent;
import org.collebol.client.event.ClientListener;
import org.collebol.client.gui.graphics.ui.component.Field;
import org.collebol.shared.math.Vector2D;

import java.util.Map;

public class ClientFieldHoverEvent implements ClientEvent<ClientFieldHoverEvent.Listener> {

    private final Vector2D position;
    private final Field field;
    private final boolean entered;

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
    public void dispatch(Listener listener, EJGEngine engine) {
        listener.onFieldHover(this, engine);
    }

    public interface Listener extends ClientListener {
        void onFieldHover(ClientFieldHoverEvent event, EJGEngine engine);
    }
}
