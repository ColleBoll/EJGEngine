package org.collebol.client.event.client.field;

import org.collebol.client.EJGEngine;
import org.collebol.client.event.ClientEvent;
import org.collebol.client.event.ClientListener;
import org.collebol.client.event.client.ClientLeftClickEvent;
import org.collebol.client.gui.graphics.ui.Component;
import org.collebol.client.gui.graphics.ui.component.Field;
import org.collebol.shared.math.Vector2D;

import java.util.Map;

public class ClientFieldSubHoverEvent implements ClientEvent<ClientFieldSubHoverEvent.Listener> {

    private final Vector2D position;
    private final Field parent;
    private final boolean entered;
    private final Component child;
    private final int childID;

    public ClientFieldSubHoverEvent(Vector2D position, Field parent, Component child, int childID, boolean entered) {
        this.position = position;
        this.parent = parent;
        this.entered = entered;
        this.child = child;
        this.childID = childID;
    }

    public Vector2D getPosition() {
        return position;
    }

    public Field getParent() {
        return parent;
    }

    public Component getChild() {
        return child;
    }

    public int getChildID() {
        return childID;
    }

    public boolean isEnter() {
        return entered;
    }

    public boolean isExit() {
        return !entered;
    }

    @Override
    public void dispatch(Listener listener, EJGEngine engine) {
        listener.onSubFieldHover(this, engine);
    }

    public interface Listener extends ClientListener {
        void onSubFieldHover(ClientFieldSubHoverEvent event, EJGEngine engine);
    }
}
