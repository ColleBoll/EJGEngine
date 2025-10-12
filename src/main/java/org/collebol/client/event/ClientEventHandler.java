package org.collebol.client.event;

import org.collebol.client.EJGEngine;
import org.collebol.client.event.client.ClientKeyClickEvent;
import org.collebol.client.event.client.ClientRightClickEvent;
import org.collebol.client.event.client.listeners.DefaultKeyClickTextInputListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The ClientEventHandler handels the events on the client. For example, if you right-click on your window.
 * All the Listeners registered with the {@link ClientRightClickEvent} listener will be called.
 *
 * <p>Usage:</p>
 * The EJGEngine already contains a {@link ClientEventHandler}. You can call it using {@link EJGEngine#getEventHandler()}.
 * <blockquote><pre>
 *     // in register() method
 *     getEventHandler().registerListener(ClientRightClickEvent.Listener.class, ((event, engine) -> {
 *         // what hapens when right click.
 *     }));
 * </pre></blockquote>
 * <p>You can also create a separate class implementing the {@link ClientRightClickEvent.Listener} instead of using a lambda.</p>
 *
 * @author ColleBol - <a href="mailto:contact@collebol.org">contact@collebol.org</a>
 * @since 1.0-dev
 */
public class ClientEventHandler {

    private final Map<Class<?>, List<ClientListener>> listeners = new HashMap<>();
    private final EJGEngine engine;
    private final ClientObserverManager observer;

    public ClientEventHandler(EJGEngine engine) {
        this.engine = engine;
        this.observer = new ClientObserverManager(engine);

        registerDefaultListeners();
    }

    /**
     * Use this to register a Listener extending {@link ClientListener}.<br>
     * This listener will be executed when the selected Event happens.
     *
     * <p>Usage (example):</p>
     * You can use every Event implementing {@link ClientEvent}.
     * <blockquote><pre>
     *     registerListener(ClientRightClickEvent.Listener.class, ((event, engine) -> {
     *         // what hapens when right click.
     *     }));
     * </pre></blockquote>
     * <p>You can also create a separate class implementing the {@link ClientRightClickEvent.Listener} instead of using a lambda.</p>
     *
     * @param type The class of the Event listener.
     * @param listener The listener itself.
     * @param <L> The kind of listener you use.
     */
    public <L extends ClientListener> void registerListener(Class<L> type, L listener) {
        listeners.computeIfAbsent(type, k -> new ArrayList<>()).add(listener);
    }

    /**
     * Use this to call a specific event. Every Listener associated with the given event will be executed.
     *
     * <p>Usage (example):</p>
     * <blockquote><pre>
     *     call(new ClientRightClickEvent(
     *         new Vector2D(10, 10), // position of the event
     *         true // is pressed
     *     ), ClientRightClickEvent.Listener.class);
     * </pre></blockquote>
     *
     * @param event The event you want to send
     * @param listenerType The event you want to call
     * @param <E> {@link ClientEvent}
     * @param <L> {@link ClientListener}
     */
    @SuppressWarnings("unchecked")
    public <E extends ClientEvent<L>, L extends ClientListener> void call(E event, Class<L> listenerType) {
        List<ClientListener> list = listeners.get(listenerType);
        if (list != null) {
            for (ClientListener listener : list) {
                event.dispatch((L) listener, engine);
            }
        }
    }

    /**
     * The {@link ClientObserverManager} is used to observe events that could happen.
     * Like, when data changes it calls a specific event.
     *
     * @return the observer manager.
     */
    public ClientObserverManager getObserver() {
        return observer;
    }

    private void registerDefaultListeners() {
        registerListener(ClientKeyClickEvent.Listener.class, new DefaultKeyClickTextInputListener());
    }
}
