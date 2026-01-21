package org.collebol.shared.event;

/**
 * Represents a dispatchable event.
 * <p>
 * An {@code Event} encapsulates event-specific data and defines how the event
 * is delivered to a corresponding {@link EventListener}. When an event is
 * dispatched, it receives both the listener instance and the shared
 * {@link EventContext}.
 *
 * <p>Implementations of this interface are expected to define a nested
 * listener interface extending {@link EventListener}, which determines the
 * type of listeners that can handle the event.</p>
 *
 * <p><b>Usage:</b></p>
 * <blockquote><pre>
 * public class MyEvent implements Event&lt;MyEvent.Listener, ClientContext&gt; {
 *
 *     public interface Listener extends EventListener {
 *         void onEvent(MyEvent event, ClientContext context);
 *     }
 *
 *     {@literal @}Override
 *     public void dispatch(Listener listener, ClientContext context) {
 *         listener.onSomeEvent(this, context);
 *     }
 * }
 * </pre></blockquote>
 *
 * @param <L> the type of {@link EventListener} that can handle this event
 * @param <C> the type of {@link EventContext} provided during dispatch
 * @author ColleBol - <a href="mailto:contact@collebol.org">contact@collebol.org</a>
 * @since 1.0-dev
 */
public interface Event<L extends EventListener, C extends EventContext> {

    void dispatch(L listener, C context);
}
