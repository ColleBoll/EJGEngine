package org.collebol.client.event.client.field;

import org.collebol.client.event.ClientEvent;
import org.collebol.client.gui.graphics.ui.Component;
import org.collebol.client.gui.graphics.ui.component.Field;
import org.collebol.shared.math.Vector2D;

import java.util.Map;

public class ClientFieldSubHoverEvent extends ClientEvent {

    private Vector2D position;
    private Field parent;
    private boolean entered;
    private Component child;
    private int childID;

    public ClientFieldSubHoverEvent() {
    }

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
    public <T> void setValues(Map<Class<?>, T> params) {
        position = (Vector2D) params.get(Vector2D.class);
        parent = (Field) params.get(Component.class);
        entered = (boolean) params.get(Boolean.class);
        childID = (int) params.get(Integer.class);
        child = parent.subComponents().getComponents().get(childID);
    }
}
