package org.collebol.client.event;

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
