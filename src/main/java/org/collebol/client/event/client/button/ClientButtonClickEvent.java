package org.collebol.client.event.client.button;

import org.collebol.client.EJGEngine;
import org.collebol.client.event.ClientEvent;
import org.collebol.client.event.ClientListener;
import org.collebol.client.event.client.ClientLeftClickEvent;
import org.collebol.client.gui.graphics.ui.Component;
import org.collebol.client.gui.graphics.ui.component.Button;
import org.collebol.client.input.KeyType;
import org.collebol.shared.math.Vector2D;

import java.util.Map;

public class ClientButtonClickEvent implements ClientEvent<ClientButtonClickEvent.Listener> {

    private final Vector2D position;
    private final KeyType keyType;
    private final boolean press;
    private final Button button;

    public ClientButtonClickEvent(Vector2D position, KeyType keyType, boolean press, Button button) {
        this.position = position;
        this.keyType = keyType;
        this.press = press;
        this.button = button;
    }

    public Vector2D getPosition() {
        return position;
    }

    public Button getButton() {
        return button;
    }

    public KeyType getKeyType() {
        return keyType;
    }

    public boolean isPressed(){
        return press;
    }

    public boolean isReleased(){
        return !press;
    }

    @Override
    public void dispatch(Listener listener, EJGEngine engine) {
        listener.onButtonClick(this, engine);
    }

    public interface Listener extends ClientListener {
        void onButtonClick(ClientButtonClickEvent event, EJGEngine engine);
    }
}
