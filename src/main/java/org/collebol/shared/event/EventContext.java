package org.collebol.shared.event;

/**
 * Marker interface representing a shared event context.
 * <p>
 * An {@code EventContext} provides access to the environment in which events
 * are dispatched. It is shared across all events and listeners handled by
 * a specific {@link EventHandler}.
 * <p>
 * Typical implementations may contain engine instances,
 * service managers, configuration objects, or other data required during
 * event handling.
 *
 * @author ColleBol - <a href="mailto:contact@collebol.org">contact@collebol.org</a>
 * @since 1.0-dev
 */
public interface EventContext {
}
