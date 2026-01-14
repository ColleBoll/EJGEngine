package org.collebol.client.event.client;

import org.collebol.client.EJGEngine;
import org.collebol.client.event.ClientEvent;
import org.collebol.client.event.ClientListener;
import org.collebol.client.input.KeyType;
import org.collebol.shared.math.Vector2D;

/**
 * The ClientMouseScrollEvent class represents an event triggered by a mouse scroll.
 * It contains information about the direction of the scroll.
 *
 * @author ColleBol - <a href="mailto:contact@collebol.org">contact@collebol.org</a>
 * @since 1.0-dev
 */
public class ClientMouseScrollEvent implements ClientEvent<ClientMouseScrollEvent.Listener> {

    private final Vector2D offset;
    private final KeyType keyType;

    public ClientMouseScrollEvent(Vector2D offset) {
        this.offset = offset;

        // omdat de offset value van de muis ook hoger als 1 of -1 kan zijn (vraag mij niet waarom)
        // (bijv. x:0.0,y:-2.0) is er een hoger dan 0 gebruikt
        // > 0 = up (positief)
        // < 0 = down (negatief)
        keyType = switch (offset) {
            case Vector2D v when v.getY() > 0 -> KeyType.SCROLL_UP;
            case Vector2D v when v.getY() < 0 -> KeyType.SCROLL_DOWN;
            case Vector2D v when v.getX() > 0 -> KeyType.SCROLL_LEFT;
            case Vector2D v when v.getX() < 0 -> KeyType.SCROLL_RIGHT;
            default -> null;
        };
    }

    public Vector2D getOffset() {
        return offset;
    }

    public KeyType getKeyType() {
        return keyType;
    }

    @Override
    public void dispatch(Listener listener, EJGEngine engine) {
        listener.onMouseScroll(this, engine);
    }

    public interface Listener extends ClientListener {
        void onMouseScroll(ClientMouseScrollEvent event, EJGEngine engine);
    }
}
