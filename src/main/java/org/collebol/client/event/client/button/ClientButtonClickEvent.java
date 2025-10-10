package org.collebol.client.event.client.button;

import org.collebol.client.event.ClientEvent;
import org.collebol.client.gui.graphics.ui.Component;
import org.collebol.client.gui.graphics.ui.component.Button;
import org.collebol.client.input.KeyType;
import org.collebol.shared.math.Vector2D;

import java.util.Map;

public class ClientButtonClickEvent extends ClientEvent {

    private Vector2D position;
    private KeyType keyType;
    private boolean press;
    private Button button;

    public ClientButtonClickEvent() {
    }

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
    public <T> void setValues(Map<Class<?>, T> params) {
        position = (Vector2D) params.get(Vector2D.class);
        keyType = (KeyType) params.get(KeyType.class);
        press = (boolean) params.get(Boolean.class);
        button = (Button) params.get(Component.class);
    }
}
