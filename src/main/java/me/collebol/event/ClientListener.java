package me.collebol.event;

import me.collebol.EJGEngine;
import me.collebol.event.Listener;
import me.collebol.event.client.ClientKeyClickEvent;
import me.collebol.event.client.ClientLeftClickEvent;
import me.collebol.event.client.ClientRightClickEvent;

public abstract class ClientListener implements Listener {

    public void onRightClick(ClientRightClickEvent event, EJGEngine engine){}

    public void onLeftClick(ClientLeftClickEvent event, EJGEngine engine){}

    public void onKeyClick(ClientKeyClickEvent event, EJGEngine engine){}
}
