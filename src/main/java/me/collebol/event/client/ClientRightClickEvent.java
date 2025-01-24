package me.collebol.event.client;

import me.collebol.EJGEngine;
import me.collebol.event.ClientListener;
import me.collebol.event.Event;
import me.collebol.math.Vector2D;
import me.collebol.utils.Client;

import java.util.Map;

public class ClientRightClickEvent extends Event {

    private Vector2D position;
    private boolean press;

    public ClientRightClickEvent(){

    }

    public ClientRightClickEvent(Vector2D position, boolean press) {
        this.position = position;
        this.press = press;
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

    @Override
    public void setValues(Map<Class<?>, Object> params) {
        position = (Vector2D) params.get(Vector2D.class);
        press = (boolean) params.get(Boolean.class);
    }
}
