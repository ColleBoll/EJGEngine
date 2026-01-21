package org.collebol.client.event.client.button;

import org.collebol.client.EJGEngine;
import org.collebol.client.gui.graphics.ui.component.Button;
import org.collebol.shared.event.Event;
import org.collebol.shared.event.EventListener;
import org.collebol.shared.math.Vector2D;

public class ClientButtonHoverEvent implements Event<ClientButtonHoverEvent.Listener, EJGEngine> {

    public static Listener Listener;
    private final Vector2D position;
    private final Button button;
    private final boolean entered;

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
    public void dispatch(Listener listener, EJGEngine engine) {
        listener.onButtonHover(this, engine);
    }

    public interface Listener extends EventListener {
        void onButtonHover(ClientButtonHoverEvent event, EJGEngine engine);
    }
}
