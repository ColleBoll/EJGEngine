package org.collebol.event;

import org.collebol.EJGEngine;
import org.collebol.input.KeyType;
import org.collebol.math.Vector2D;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Event {

    private EJGEngine engine;

    private List<ClientListener> listeners = new ArrayList<>();

    public void registerEvent(ClientListener listener){
        listeners.add(listener);
    }

    public List<ClientListener> getListeners() {
        return listeners;
    }

    /**
     * Triggers the event and notifies all registered listeners.
     *
     * @param engine The engine instance that is triggering the event.
     * @param params The parameters associated with the event. These can be of various types such as Vector2D, Boolean, or KeyType.
     */
    public void call(EJGEngine engine, Object... params){
        Map<Class<?>, Object> paramMap = new HashMap<>();
        for(Object param : params){
            if(param instanceof Vector2D){
                paramMap.put(Vector2D.class, param);
            }else if(param instanceof Boolean){
                paramMap.put(Boolean.class, param);
            }else if(param instanceof KeyType){
                paramMap.put(KeyType.class, param);
            }
        }
        this.engine = engine;
        setValues(paramMap);
        for(ClientListener listener : this.listeners){
            listener.handleEvent(this);
        }
    }

    public abstract void setValues(Map<Class<?>, Object> params);

    public EJGEngine getEngine(){
        return this.engine;
    }
}
