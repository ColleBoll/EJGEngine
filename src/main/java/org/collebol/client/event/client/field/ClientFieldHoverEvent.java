package org.collebol.client.event.client.field;

import org.collebol.client.EJGEngine;
import org.collebol.client.gui.graphics.ui.component.Field;
import org.collebol.shared.event.Event;
import org.collebol.shared.event.EventListener;
import org.collebol.shared.math.Vector2D;

public class ClientFieldHoverEvent implements Event<ClientFieldHoverEvent.Listener, EJGEngine> {

    public static Listener Listener;
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

    public interface Listener extends EventListener {
        void onFieldHover(ClientFieldHoverEvent event, EJGEngine engine);
    }
}
