package org.collebol.shared.event;

import org.collebol.shared.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventHandler<C extends Context> {

    private final Map<Class<?>, List<EventListener>> listeners = new HashMap<>();
    private final C context;

    public EventHandler(C context) {
        this.context = context;
    }

    public <L extends EventListener> void registerListener(Class<L> type, L listener) {
        listeners.computeIfAbsent(type, k -> new ArrayList<>()).add(listener);
    }

    public <E extends Event<L, C>, L extends EventListener> void call(E event, L listenerType) {
        List<EventListener> list = listeners.get(listenerType.getClass());
        if (list != null) {
            for (EventListener listener : list) {
                event.dispatch((L) listener, context);
            }
        }
    }
}
