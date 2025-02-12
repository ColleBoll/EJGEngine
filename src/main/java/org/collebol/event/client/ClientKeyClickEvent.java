package org.collebol.event.client;

import org.collebol.event.Event;
import org.collebol.input.KeyType;

import java.util.Map;

/**
 * The ClientKeyClickEvent class represents an event triggered by a key click.
 * It contains information about the key type and whether the key was pressed or released.
 *
 * @author ColleBol - <a href="mailto:contact@collebol.org">contact@collebol.org</a>
 * @since 1.0-dev
 */
public class ClientKeyClickEvent extends Event {

    private KeyType keyType;
    private boolean press;

    public ClientKeyClickEvent(){

    }

    public ClientKeyClickEvent(KeyType keyType, boolean press) {
        this.keyType = keyType;
        this.press = press;
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

    public KeyType getKeyType() {
        return keyType;
    }

    @Override
    public void setValues(Map<Class<?>, Object> params) {
        keyType = (KeyType) params.get(KeyType.class);
        press = (boolean) params.get(Boolean.class);
    }
}
