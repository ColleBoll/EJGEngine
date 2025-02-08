package org.collebol.event;

import org.collebol.event.client.ClientKeyClickEvent;
import org.collebol.event.client.ClientLeftClickEvent;
import org.collebol.event.client.ClientRightClickEvent;

/**
 * The ClientListener class is an abstract class that implements the {@link Listener} interface.
 * It provides methods to handle different types of client events, such as key clicks and mouse clicks.
 * Subclasses should override these methods to define specific behaviors for each event type.
 *
 * <p>Usage:</p>
 * <blockquote><pre>
 *     public class MyEvent extends ClientListener {
 *         //methods
 *     }
 * </pre></blockquote>
 *
 * @author ColleBol - <a href="mailto:contact@collebol.org">contact@collebol.org</a>
 * @since 1.0-dev
 */
public abstract class ClientListener implements Listener {

    /**
     * Handles the event by determining its type and calling the appropriate method.
     *
     * @param event The event to be handled. This can be an instance of:
     *              <ul>
     *                  <li>{@link ClientRightClickEvent}</li>
     *                  <li>{@link ClientLeftClickEvent}</li>
     *                  <li>{@link ClientKeyClickEvent}</li>
     *              </ul>
     */
    public void handleEvent(Event event) {
        if (event instanceof ClientRightClickEvent) {
            onRightClick((ClientRightClickEvent) event);
        }
        if (event instanceof ClientLeftClickEvent) {
            onLeftClick((ClientLeftClickEvent) event);
        }
        if (event instanceof ClientKeyClickEvent) {
            onKeyClick((ClientKeyClickEvent) event);
        }
    }

    public void onRightClick(ClientRightClickEvent event) {
    }

    public void onLeftClick(ClientLeftClickEvent event) {
    }

    public void onKeyClick(ClientKeyClickEvent event) {
    }
}
