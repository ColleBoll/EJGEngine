package me.collebol.event.client;

import me.collebol.EJGEngine;
import me.collebol.event.ClientListener;
import me.collebol.event.Event;
import me.collebol.input.KeyType;

public class ClientKeyClickEvent extends Event {

    private EJGEngine engine;

    private KeyType keyType;
    private boolean press;

    public ClientKeyClickEvent(){

    }

    public ClientKeyClickEvent(KeyType keyType, boolean press) {
        this.keyType = keyType;
        this.press = press;
    }

    @Override
    public void call(KeyType keyType, boolean press, EJGEngine engine) {
        for(ClientListener listener : getListeners()){
            ClientKeyClickEvent event = new ClientKeyClickEvent(keyType, press);
            listener.onKeyClick(event, engine);
        }
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
}
