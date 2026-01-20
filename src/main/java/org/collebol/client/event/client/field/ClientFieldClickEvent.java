package org.collebol.client.event.client.field;

import org.collebol.client.EJGEngine;
import org.collebol.client.gui.graphics.ui.Component;
import org.collebol.client.gui.graphics.ui.component.Field;
import org.collebol.client.input.KeyType;
import org.collebol.client.math.ComponentCalculator;
import org.collebol.shared.event.Event;
import org.collebol.shared.event.EventListener;
import org.collebol.shared.math.Vector2D;

import java.util.List;

public class ClientFieldClickEvent implements Event<ClientFieldClickEvent.Listener, EJGEngine> {

    public static Listener Listener;
    private final Vector2D position;
    private final KeyType keyType;
    private final boolean press;
    private final Field field;
    private List<Component> actionSubComponent;

    public ClientFieldClickEvent(Vector2D position, KeyType keyType, boolean press, Field field) {
        this.position = position;
        this.keyType = keyType;
        this.press = press;
        this.field = field;
    }

    public Vector2D getPosition() {
        return position;
    }

    public Field getField() {
        return field;
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

    public boolean isSubComponentClicked(){
        return actionSubComponent != null;
    }

    public List<Component> getClickedSubComponents() {
        return actionSubComponent;
    }

    @Override
    public void dispatch(Listener listener, EJGEngine engine) {
        listener.onFieldClick(this, engine);

        actionSubComponent = ComponentCalculator.checkIfSubComponent(field, position);
    }

    public interface Listener extends EventListener {
        void onFieldClick(ClientFieldClickEvent event, EJGEngine engine);
    }
}
