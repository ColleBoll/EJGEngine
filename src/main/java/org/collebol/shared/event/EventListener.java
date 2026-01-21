package org.collebol.shared.event;

/**
 * Marker interface for event listeners.
 * <p>
 * An {@code EventListener} represents a component that can respond to one or
 * more events dispatched by an {@link EventHandler}. Concrete listener
 * interfaces are typically defined per event and may be implemented using
 * lambda expressions or concrete classes.
 * <p>
 * This interface does not define any methods and exists solely to provide
 * type safety and a common supertype for all event listeners.
 *
 * @author ColleBol - <a href="mailto:contact@collebol.org">contact@collebol.org</a>
 * @since 1.0-dev
 */
public interface EventListener {
}
