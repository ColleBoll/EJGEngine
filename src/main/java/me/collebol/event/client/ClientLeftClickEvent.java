package me.collebol.event.client;

import me.collebol.EJGEngine;
import me.collebol.event.ClientListener;
import me.collebol.event.Event;
import me.collebol.math.Vector2D;
import me.collebol.utils.Client;

public class ClientLeftClickEvent extends Event {

    private EJGEngine engine;

    private Client client;
    private Vector2D position;
    private boolean press;

    public ClientLeftClickEvent(){

    }

    public ClientLeftClickEvent(Vector2D position, boolean press) {
        this.client = client;
        this.position = position;
        this.press = press;
    }

    @Override
    public void call(Vector2D position, boolean press, EJGEngine engine) {
        for(ClientListener listener : getListeners()){
            ClientLeftClickEvent event = new ClientLeftClickEvent(position, press);
            listener.onLeftClick(event, this.engine);
        }
    }

    public Client getClient() {
        return client;
    }

    public Vector2D getPosition() {
        return position;
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
}
