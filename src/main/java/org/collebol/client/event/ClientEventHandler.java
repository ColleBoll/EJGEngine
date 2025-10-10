package org.collebol.client.event;

import org.collebol.client.EJGEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientEventHandler {

    private final Map<Class<?>, List<ClientListener>> listeners = new HashMap<>();
    private final EJGEngine engine;

    public ClientEventHandler(EJGEngine engine) {
        this.engine = engine;
    }

    public <L extends ClientListener> void registerListener(Class<L> type, L listener) {
        listeners.computeIfAbsent(type, k -> new ArrayList<>()).add(listener);
    }

    @SuppressWarnings("unchecked")
    public <E extends ClientEvent<L>, L extends ClientListener> void call(E event, Class<L> listenerType) {
        List<ClientListener> list = listeners.get(listenerType);
        if (list != null) {
            for (ClientListener listener : list) {
                event.dispatch((L) listener, engine);
            }
        }
    }
}
