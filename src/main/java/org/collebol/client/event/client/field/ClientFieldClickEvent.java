package org.collebol.client.event.client.field;

import org.collebol.client.event.Event;
import org.collebol.client.gui.graphics.ui.Component;
import org.collebol.client.gui.graphics.ui.component.Field;
import org.collebol.client.input.KeyType;
import org.collebol.client.math.ComponentCalculator;
import org.collebol.shared.math.Vector2D;

import java.util.List;
import java.util.Map;

public class ClientFieldClickEvent extends Event {

    private Vector2D position;
    private KeyType keyType;
    private boolean press;
    private Field field;
    private List<Component> actionSubComponent;

    public ClientFieldClickEvent() {
    }

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

    public boolean isSubCommandClicked(){
        return actionSubComponent != null;
    }

    public List<Component> getClickedSubComponents() {
        return actionSubComponent;
    }

    @Override
    public void setValues(Map<Class<?>, Object> params) {
        position = (Vector2D) params.get(Vector2D.class);
        keyType = (KeyType) params.get(KeyType.class);
        press = (boolean) params.get(Boolean.class);
        field = (Field) params.get(Component.class);
        List<Component> l = ComponentCalculator.checkIfSubComponent(field, position);
        actionSubComponent = l;
    }
}
