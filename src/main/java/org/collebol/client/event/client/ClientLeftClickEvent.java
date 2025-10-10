package org.collebol.client.event.client;

import org.collebol.client.EJGEngine;
import org.collebol.client.event.ClientEvent;
import org.collebol.client.event.ClientListener;
import org.collebol.shared.math.Vector2D;

import java.util.Map;

/**
 * The ClientLeftClickEvent class represents an event triggered by a left mouse click.
 * It contains information about whether the key was pressed or released.
 *
 * @author ColleBol - <a href="mailto:contact@collebol.org">contact@collebol.org</a>
 * @since 1.0-dev
 */
public class ClientLeftClickEvent implements ClientEvent<ClientLeftClickEvent.Listener> {

    private final Vector2D position;
    private final boolean press;

    public ClientLeftClickEvent(Vector2D position, boolean press) {
        this.position = position;
        this.press = press;
    }

    public Vector2D getPosition() {
        return position;
    }

    public boolean isPressed(){
        return press;
    }

    public boolean isReleased(){
        return !press;
    }

    @Override
    public void dispatch(Listener listener, EJGEngine engine) {
        listener.onKeyLeftClick(this, engine);
    }

    public interface Listener extends ClientListener {
        void onKeyLeftClick(ClientLeftClickEvent event, EJGEngine engine);
    }
}
