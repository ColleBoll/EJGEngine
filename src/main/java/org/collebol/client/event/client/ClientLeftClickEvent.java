package org.collebol.client.event.client;

import org.collebol.client.event.Event;
import org.collebol.shared.math.Vector2D;

import java.util.Map;

/**
 * The ClientLeftClickEvent class represents an event triggered by a left mouse click.
 * It contains information about whether the key was pressed or released.
 *
 * @author ColleBol - <a href="mailto:contact@collebol.org">contact@collebol.org</a>
 * @since 1.0-dev
 */
public class ClientLeftClickEvent extends Event {

    private Vector2D position;
    private boolean press;

    public ClientLeftClickEvent(){

    }

    public ClientLeftClickEvent(Vector2D position, boolean press) {
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
