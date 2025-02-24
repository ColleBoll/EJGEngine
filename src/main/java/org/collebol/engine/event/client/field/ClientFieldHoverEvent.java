package org.collebol.engine.event.client.field;

import org.collebol.engine.event.Event;
import org.collebol.engine.gui.graphics.ui.Component;
import org.collebol.engine.gui.graphics.ui.component.Field;
import org.collebol.engine.math.ComponentCalculator;
import org.collebol.engine.math.Vector2D;

import java.util.List;
import java.util.Map;

public class ClientFieldHoverEvent extends Event {

    private Vector2D position;
    private Field field;
    private boolean entered;
    private List<Component> actionSubComponent;

    public ClientFieldHoverEvent() {
    }

    public ClientFieldHoverEvent(Vector2D position, Field field, boolean entered) {
        this.position = position;
        this.field = field;
        this.entered = entered;
    }

    public Vector2D getPosition() {
        return position;
    }

    public Field getField() {
        return field;
    }

    public boolean isEnter() {
        return entered;
    }

    public boolean isExit() {
        return !entered;
    }

    public boolean isSubCommandHovered(){
        return actionSubComponent != null;
    }

    public List<Component> getHoveredSubComponents() {
        return actionSubComponent;
    }

    @Override
    public void setValues(Map<Class<?>, Object> params) {
        position = (Vector2D) params.get(Vector2D.class);
        field = (Field) params.get(Component.class);
        entered = (boolean) params.get(Boolean.class);
        List<Component> l = ComponentCalculator.checkIfSubComponent(field, position);
        actionSubComponent = l;
    }
}
