package org.collebol.event.client;

import org.collebol.event.Event;
import org.collebol.input.KeyType;

import java.util.Map;

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
