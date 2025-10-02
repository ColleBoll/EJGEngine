package org.collebol.client.event;

import org.collebol.client.event.client.ClientKeyClickEvent;
import org.collebol.client.event.client.ClientLeftClickEvent;
import org.collebol.client.event.client.ClientRightClickEvent;
import org.collebol.client.event.client.field.ClientFieldClickEvent;
import org.collebol.client.event.client.field.ClientFieldHoverEvent;
import org.collebol.client.event.client.field.ClientFieldSubHoverEvent;
import org.collebol.client.event.client.button.ClientButtonClickEvent;
import org.collebol.client.event.client.button.ClientButtonHoverEvent;

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
     *                  <li>{@link ClientFieldClickEvent}</li>
     *                  <li>{@link ClientFieldHoverEvent}</li>
     *                  <li>{@link ClientFieldSubHoverEvent}</li>
     *                  <li>{@link ClientButtonClickEvent}</li>
     *                  <li>{@link ClientButtonHoverEvent}</li>
     *              </ul>
     */
    public void handleEvent(ClientEvent event) {
        if (event instanceof ClientRightClickEvent) {
            onRightClick((ClientRightClickEvent) event);
        }
        if (event instanceof ClientLeftClickEvent) {
            onLeftClick((ClientLeftClickEvent) event);
        }
        if (event instanceof ClientKeyClickEvent) {
            onKeyClick((ClientKeyClickEvent) event);
        }
        if (event instanceof ClientFieldClickEvent) {
            onFieldClick((ClientFieldClickEvent) event);
        }
        if (event instanceof ClientFieldHoverEvent) {
            onFieldHover((ClientFieldHoverEvent) event);
        }
        if (event instanceof ClientFieldSubHoverEvent) {
            onFieldSubHover((ClientFieldSubHoverEvent) event);
        }
        if (event instanceof ClientButtonClickEvent) {
            onButtonClick((ClientButtonClickEvent) event);
        }
        if (event instanceof ClientButtonHoverEvent) {
            onButtonHover((ClientButtonHoverEvent) event);
        }
    }

    public void onRightClick(ClientRightClickEvent event) {
    }

    public void onLeftClick(ClientLeftClickEvent event) {
    }

    public void onKeyClick(ClientKeyClickEvent event) {
    }

    public void onFieldClick(ClientFieldClickEvent event) {
    }

    public void onFieldHover(ClientFieldHoverEvent event) {
    }

    public void onFieldSubHover(ClientFieldSubHoverEvent event) {
    }

    public void onButtonClick(ClientButtonClickEvent event) {
    }

    public void onButtonHover(ClientButtonHoverEvent event) {
    }
}
