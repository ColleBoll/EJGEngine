package org.collebol.engine.math;

import org.collebol.engine.EJGEngine;
import org.collebol.engine.gui.graphics.ui.Component;
import org.collebol.engine.gui.graphics.ui.component.Field;

import java.util.ArrayList;
import java.util.List;

public class ComponentCalculator {

    /**
     * Checks if there is a component below of the mouse.
     *
     * @return component list if there is a component else null.
     */
    public static List<Component> getComponentUnderMouse(Vector2D position, EJGEngine instance) {
        float mouseX = position.getX();
        float mouseY = position.getY();

        List<Component> componentList = new ArrayList<>();
        List<Class<? extends Component>> clses = instance.getComponentHandler().getComponentsMap().keySet().stream().toList();
        for (Class<? extends Component> cls : clses) {
            for (Component field : instance.getComponentHandler().getComponents(cls).values()) {
                float fieldX = field.getPosition().getX();
                float fieldY = field.getPosition().getY();
                float width = field.getWidth();
                float height = field.getHeight();

                if (mouseX >= fieldX && mouseX <= (fieldX + width) &&
                        mouseY >= fieldY && mouseY <= (fieldY + height)) {

                    componentList.add(field);
                }
            }
        }
        return componentList;
    }

    /**
     * Checks if there is a subcomponent of the given parent below the mouse.
     *
     * @param parent   the given parent of the subcomponents. (Field)
     * @param position
     * @return list of subcomponents.
     */
    public static List<Component> checkIfSubComponent(Field parent, Vector2D position) {
        float mouseX = position.getX();
        float mouseY = position.getY();
        List<Component> l = new ArrayList<>();
        if (parent.subComponents().getComponents() == null) return null;
        if (parent.subComponents().getComponents().isEmpty()) return null;
        for (Component c : parent.subComponents().getComponents().values()) {
            float cX = c.getPosition().getX() + parent.getPosition().getX();
            float cY = c.getPosition().getY() + parent.getPosition().getY();
            float width = c.getWidth();
            float height = c.getHeight();
            if (mouseX >= cX && mouseX <= (cX + width) &&
                    mouseY >= cY && mouseY <= (cY + height)) {
                l.add(c);
            }
        }
        if (!l.isEmpty()) {
            return l;
        } else {
            return null;
        }
    }
}
