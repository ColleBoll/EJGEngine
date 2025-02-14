package org.collebol.engine.event.client.field;

import org.collebol.engine.event.Event;
import org.collebol.engine.gui.graphics.ui.component.Component;
import org.collebol.engine.gui.graphics.ui.component.Field;
import org.collebol.engine.input.KeyType;
import org.collebol.engine.math.Vector2D;

import java.util.Map;

public class ClientFieldClickEvent extends Event {

    private Vector2D position;
    private KeyType keyType;
    private boolean press;
    private Field field;

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
        if(press){
            return true;
        }else{
            return false;
        }
    }

    public boolean isReleased(){
        if(press){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public void setValues(Map<Class<?>, Object> params) {
        position = (Vector2D) params.get(Vector2D.class);
        keyType = (KeyType) params.get(KeyType.class);
        press = (boolean) params.get(Boolean.class);
        field = (Field) params.get(Component.class);
    }
}
