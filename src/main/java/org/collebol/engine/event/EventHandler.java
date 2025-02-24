package org.collebol.engine.event;

import org.collebol.engine.EJGEngine;
import org.collebol.engine.event.client.ClientKeyClickEvent;
import org.collebol.engine.event.client.ClientLeftClickEvent;
import org.collebol.engine.event.client.ClientRightClickEvent;
import org.collebol.engine.event.client.field.ClientFieldClickEvent;
import org.collebol.engine.event.client.field.ClientFieldHoverEvent;
import org.collebol.engine.event.client.field.ClientFieldSubHoverEvent;

import java.lang.reflect.Method;

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
public class EventHandler {

    private EJGEngine engine;

    //Client events
    private ClientRightClickEvent clientRightClickEvent;
    private ClientLeftClickEvent clientLeftClickEvent;
    private ClientKeyClickEvent clientKeyClickEvent;

    private ClientFieldClickEvent clientFieldClickEvent;
    private ClientFieldHoverEvent clientFieldHoverEvent;
    private ClientFieldSubHoverEvent clientFieldSubHoverEvent;

    public EventHandler(EJGEngine e) {
        this.engine = e;

        this.clientRightClickEvent = new ClientRightClickEvent();
        this.clientLeftClickEvent = new ClientLeftClickEvent();
        this.clientKeyClickEvent = new ClientKeyClickEvent();

        this.clientFieldClickEvent = new ClientFieldClickEvent();
        this.clientFieldHoverEvent = new ClientFieldHoverEvent();
        this.clientFieldSubHoverEvent = new ClientFieldSubHoverEvent();
    }

    /**
     * Registers a client event listener by checking if the listener has implemented specific event handling methods.
     *
     * @param event The client listener to be registered.
     */
    public void registerClientEvent(ClientListener event) {
        try {
            Method onRightClickMethod = event.getClass().getMethod("onRightClick", ClientRightClickEvent.class);
            if (onRightClickMethod.getDeclaringClass() != ClientListener.class) {
                this.clientRightClickEvent.registerEvent(event);
            }
            Method onLeftClickMethod = event.getClass().getMethod("onLeftClick", ClientLeftClickEvent.class);
            if (onLeftClickMethod.getDeclaringClass() != ClientListener.class) {
                this.clientLeftClickEvent.registerEvent(event);
            }
            Method onKeyClickMethod = event.getClass().getMethod("onKeyClick", ClientKeyClickEvent.class);
            if (onKeyClickMethod.getDeclaringClass() != ClientListener.class) {
                this.clientKeyClickEvent.registerEvent(event);
            }
            Method onFieldClickMethod = event.getClass().getMethod("onFieldClick", ClientFieldClickEvent.class);
            if (onFieldClickMethod.getDeclaringClass() != ClientListener.class) {
                this.clientFieldClickEvent.registerEvent(event);
            }
            Method onFieldHoverMethod = event.getClass().getMethod("onFieldHover", ClientFieldHoverEvent.class);
            if (onFieldHoverMethod.getDeclaringClass() != ClientListener.class) {
                this.clientFieldHoverEvent.registerEvent(event);
            }
            Method onFieldSubHoverMethod = event.getClass().getMethod("onFieldSubHover", ClientFieldSubHoverEvent.class);
            if (onFieldSubHoverMethod.getDeclaringClass() != ClientListener.class) {
                this.clientFieldSubHoverEvent.registerEvent(event);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the client event instance based on the provided event class.
     *
     * @param eventClass The class of the event to be returned.
     * @return The instance of the event if it matches one of the known client events, otherwise null.
     */
    public Event callClientEvent(Class<? extends Event> eventClass) {
        if (eventClass.equals(ClientRightClickEvent.class)) {
            return this.clientRightClickEvent;
        }
        if (eventClass.equals(ClientLeftClickEvent.class)) {
            return this.clientLeftClickEvent;
        }
        if (eventClass.equals(ClientKeyClickEvent.class)) {
            return this.clientKeyClickEvent;
        }
        if (eventClass.equals(ClientFieldClickEvent.class)) {
            return this.clientFieldClickEvent;
        }
        if (eventClass.equals(ClientFieldHoverEvent.class)) {
            return this.clientFieldHoverEvent;
        }
        if (eventClass.equals(ClientFieldSubHoverEvent.class)) {
            return this.clientFieldSubHoverEvent;
        }
        return null;
    }
}
