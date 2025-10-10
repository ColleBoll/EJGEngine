package org.collebol.client.event;

import org.collebol.client.EJGEngine;
import org.collebol.client.event.client.ClientKeyClickEvent;
import org.collebol.client.event.client.ClientLeftClickEvent;
import org.collebol.client.event.client.ClientRightClickEvent;
import org.collebol.client.event.client.button.ClientButtonClickEvent;
import org.collebol.client.event.client.button.ClientButtonHoverEvent;
import org.collebol.client.event.client.field.ClientFieldClickEvent;
import org.collebol.client.event.client.field.ClientFieldHoverEvent;
import org.collebol.client.event.client.field.ClientFieldSubHoverEvent;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Class responsible for handling and registering client events in the EJGEngine.
 * This class manages the registration of event listeners and the invocation of client events.
 *
 * <p>Usage:</p>
 * <blockquote><pre>
 *     EventHandler eventHandler = new EventHandler(engine);
 * </pre></blockquote>
 * <p>Register event:</p>
 * <blockquote><pre>
 *     eventHandler.registerClientEvent(myEvent);
 * </pre></blockquote>
 * <p>Call event:</p>
 * <blockquote><pre>
 *     eventHandler.callClientEvent(Event.class);
 * </pre></blockquote>
 *
 * @author ColleBol - <a href="mailto:contact@collebol.org">contact@collebol.org</a>
 * @since 1.0-dev
 */
public class ClientEventHandler {

    private EJGEngine engine;
    private final Map<Class<? extends ClientEvent>, ClientEvent> eventMap = new HashMap<>();


    public ClientEventHandler(EJGEngine e) {
        this.engine = e;
        registerDefaultEvents();
    }

    private void registerDefaultEvents() {
        registerEvent(new ClientRightClickEvent());
        registerEvent(new ClientLeftClickEvent());
        registerEvent(new ClientKeyClickEvent());
        registerEvent(new ClientFieldClickEvent());
        registerEvent(new ClientFieldHoverEvent());
        registerEvent(new ClientFieldSubHoverEvent());
        registerEvent(new ClientButtonClickEvent());
        registerEvent(new ClientButtonHoverEvent());
    }

    public <E extends ClientEvent> void registerEvent(E event) {
        eventMap.put(event.getClass(), event);
    }

    /**
     * Registers a client event listener by checking if the listener has implemented specific event handling methods.
     *
     * @param listener The client listener to be registered.
     */
    public void registerListener(ClientListener listener) {
        for (ClientEvent event : eventMap.values()) {
            event.registerEvent(listener);
        }
    }

    @SuppressWarnings("unchecked")
    public <E extends ClientEvent> E getEvent(Class<E> eventClass) {
        return (E) eventMap.get(eventClass);
    }
}
