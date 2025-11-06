package org.collebol.client.event.client;

import org.collebol.client.EJGEngine;
import org.collebol.client.event.ClientEvent;
import org.collebol.client.event.ClientListener;
import org.collebol.client.input.KeyType;

import java.util.Map;

/**
 * The ClientKeyClickEvent class represents an event triggered by a key click.
 * It contains information about the key type and whether the key was pressed or released.
 *
 * @author ColleBol - <a href="mailto:contact@collebol.org">contact@collebol.org</a>
 * @since 1.0-dev
 */
public class ClientKeyClickEvent implements ClientEvent<ClientKeyClickEvent.Listener> {

    private final KeyType keyType;
    private final boolean press;

    public ClientKeyClickEvent(KeyType keyType, boolean press) {
        this.keyType = keyType;
        this.press = press;
    }

    public boolean isPressed(){
        return press;
    }

    public boolean isReleased(){
        return !press;
    }

    public KeyType getKeyType() {
        return keyType;
    }

    @Override
    public void dispatch(Listener listener, EJGEngine engine) {
        listener.onKeyClick(this, engine);
    }

    public interface Listener extends ClientListener {
        void onKeyClick(ClientKeyClickEvent event, EJGEngine engine);
    }

}
