package org.collebol.client.event;

import org.collebol.client.EJGEngine;
import org.collebol.client.event.client.ClientKeyClickEvent;
import org.collebol.client.event.client.listeners.DefaultKeyClickTextInputListener;
import org.collebol.shared.event.EventHandler;

public class ClientEventHandler extends EventHandler<EJGEngine> {

    public ClientEventHandler(EJGEngine engine) {
        super(engine);

        registerDefaultListeners();
    }

    private void registerDefaultListeners() {
        registerListener(ClientKeyClickEvent.Listener.class, new DefaultKeyClickTextInputListener());
    }
}
