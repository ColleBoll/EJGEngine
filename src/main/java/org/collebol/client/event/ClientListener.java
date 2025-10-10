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
public interface ClientListener extends Listener {

}
