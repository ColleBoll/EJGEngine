package me.collebol.event;

import me.collebol.EJGEngine;
import me.collebol.input.KeyType;
import me.collebol.math.Vector2D;
import me.collebol.utils.Client;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.List;

public abstract class Event {

    private List<ClientListener> listeners = new ArrayList<>();

    public void registerEvent(ClientListener listener){
        listeners.add(listener);
    }

    public List<ClientListener> getListeners() {
        return listeners;
    }

    public void call(Vector2D position, boolean press, EJGEngine engine){}

    public void call(Client client, Vector2D position, EJGEngine engine){}

    public void call(KeyType keyType, boolean press, EJGEngine engine){}
}
