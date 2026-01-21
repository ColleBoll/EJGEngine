package org.collebol.shared.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Abstract base class for handling and dispatching events using a shared context.
 * <p>
 * An {@code EventHandler} manages the registration of {@link EventListener}s
 * and is responsible for dispatching events to all listeners of a given type.
 * Each dispatched event receives a shared {@link EventContext}, which provides
 * access to the environment in which the event occurs.
 * <p>
 * This class is a generalized replacement for more specialized event handlers
 * (such as client- or server-specific handlers), where previously a concrete
 * object (e.g. an engine instance) was passed directly to listeners. That object
 * is now represented by the context.
 *
 * <p><b>Usage:</b></p>
 * A concrete implementation is expected to extend this class and provide
 * a specific context implementation.
 *
 * <blockquote><pre>
 * public class ClientEventHandler extends EventHandler&lt;EJGEngine&gt; {
 *
 *     public ClientEventHandler(EJGEngine context) {
 *         super(context);
 *     }
 * }
 * </pre></blockquote>
 *
 * <p>Listeners can then be registered and will be invoked when an event is called:</p>
 *
 * <blockquote><pre>
 * eventHandler.registerListener(MyEvent.Listener.class, (event, context) -> {
 *     // handle the event using the shared context
 * });
 * </pre></blockquote>
 *
 * @param <C> the type of {@link EventContext} shared with all events and listeners
 * @author ColleBol - <a href="mailto:contact@collebol.org">contact@collebol.org</a>
 * @since 1.0-dev
 */
public abstract class EventHandler<C extends EventContext> {

    private final Map<Class<?>, List<EventListener>> listeners = new HashMap<>();
    private final C context;

    public EventHandler(C context) {
        this.context = context;
    }

    /**
     * Registers a listener for the specified listener type.
     * <p>
     * The listener will be invoked whenever an event associated with the given
     * listener type is dispatched.
     *
     * @param <L>      the listener type
     * @param type     the class representing the listener type
     * @param listener the listener instance to register
     */
    public <L extends EventListener> void registerListener(Class<L> type, L listener) {
        listeners.computeIfAbsent(type, k -> new ArrayList<>()).add(listener);
    }

    /**
     * Dispatches the given event to all registered listeners of the specified type.
     * <p>
     * Each listener will receive both the event instance and the shared context.
     *
     * @param <E>          the event type
     * @param <L>          the listener type
     * @param event        the event to dispatch
     * @param listenerType the class representing the listener type
     */
    public <E extends Event<L, C>, L extends EventListener> void call(E event, Class<L> listenerType) {
        List<EventListener> list = listeners.get(listenerType);
        if (list != null) {
            for (EventListener listener : list) {
                event.dispatch((L) listener, context);
            }
        }
    }
}
