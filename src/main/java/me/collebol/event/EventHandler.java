package me.collebol.event;

import me.collebol.EJGEngine;
import me.collebol.event.client.ClientKeyClickEvent;
import me.collebol.event.client.ClientLeftClickEvent;
import me.collebol.event.client.ClientRightClickEvent;

import java.lang.reflect.Method;

public class EventHandler {

    private EJGEngine engine;

    //Client events
    private ClientRightClickEvent clientRightClickEvent;
    private ClientLeftClickEvent clientLeftClickEvent;
    private ClientKeyClickEvent clientKeyClickEvent;

    public EventHandler(EJGEngine e){
        this.engine = e;

        this.clientRightClickEvent = new ClientRightClickEvent();
        this.clientLeftClickEvent = new ClientLeftClickEvent();
        this.clientKeyClickEvent = new ClientKeyClickEvent();
    }

    public void registerClientEvent(ClientListener event) {
        try {
            Method onRightClickMethod = event.getClass().getMethod("onRightClick", ClientRightClickEvent.class, EJGEngine.class);
            if(onRightClickMethod.getDeclaringClass() != ClientListener.class){
                this.clientRightClickEvent.registerEvent(event);

            }
            Method onLeftClickMethod = event.getClass().getMethod("onLeftClick", ClientLeftClickEvent.class, EJGEngine.class);
            if(onLeftClickMethod.getDeclaringClass() != ClientListener.class){
                this.clientLeftClickEvent.registerEvent(event);
            }
            Method onKeyClickMethod = event.getClass().getMethod("onKeyClick", ClientKeyClickEvent.class, EJGEngine.class);
            if(onKeyClickMethod.getDeclaringClass() != ClientListener.class){
                this.clientKeyClickEvent.registerEvent(event);
            }
        }catch (NoSuchMethodException e){
            e.printStackTrace();
        }
    }

    public Event callClientEvent(Class<? extends Event> eventClass){
        if(eventClass.equals(ClientRightClickEvent.class)){
            return this.clientRightClickEvent;
        }
        if(eventClass.equals(ClientLeftClickEvent.class)){
            return this.clientLeftClickEvent;
        }
        if(eventClass.equals(ClientKeyClickEvent.class)){
            return this.clientKeyClickEvent;
        }
        return null;
    }
}
