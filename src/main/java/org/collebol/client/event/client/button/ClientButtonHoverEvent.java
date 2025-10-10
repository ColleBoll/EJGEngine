package org.collebol.client.event.client.button;

import org.collebol.client.EJGEngine;
import org.collebol.client.event.ClientEvent;
import org.collebol.client.event.ClientListener;
import org.collebol.client.event.client.ClientLeftClickEvent;
import org.collebol.client.gui.graphics.ui.Component;
import org.collebol.client.gui.graphics.ui.component.Button;
import org.collebol.shared.math.Vector2D;

import java.util.Map;

public class ClientButtonHoverEvent implements ClientEvent<ClientButtonHoverEvent.Listener> {

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

    public interface Listener extends ClientListener {
        void onButtonHover(ClientButtonHoverEvent event, EJGEngine engine);
    }
}
