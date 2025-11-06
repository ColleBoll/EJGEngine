package org.collebol.client.gui.graphics.ui;

import org.collebol.client.EJGEngine;
import org.collebol.client.event.client.button.ClientButtonClickEvent;
import org.collebol.client.event.client.button.ClientButtonHoverEvent;
import org.collebol.client.gui.graphics.ui.component.Button;

import java.util.HashMap;
import java.util.Map;

public class ComponentHandler {

    private final EJGEngine engine;
    private final Map<Class<? extends Component>, Map<Integer, Component>> components;

    public ComponentHandler(EJGEngine engine) {
        this.engine = engine;
        this.components = new HashMap<>();
    }

    public <T extends Component> void registerNewComponent(T component) {
        if (!this.components.containsKey(component.getClass())) {
            this.components.put(component.getClass(), new HashMap<>());

            // Register listeners of button
            if (component instanceof Button) {
                if (((Button) component).getClickEvent() != null)
                    this.engine.getEventHandler().registerListener(ClientButtonClickEvent.Listener.class, ((Button) component).getClickEvent());
                if (((Button) component).getHoverEvent() != null)
                    this.engine.getEventHandler().registerListener(ClientButtonHoverEvent.Listener.class, ((Button) component).getHoverEvent());
            }
        }
        if (getComponents().containsKey(component.getId()))
            throw new RuntimeException("You are trying to register a component with a ID that already is been used! ID: " + component.getId());
        this.components.get(component.getClass()).put(component.getId(), component);
    }

    public Component getComponent(Class<? extends Component> cls, int id) {
        if (this.components.isEmpty())
            return null;
        if (this.components.get(cls).isEmpty())
            throw new RuntimeException("You are trying to get a " + cls.getName() + " but you have not set one yet! Please, make sure to register a " + cls.getName() + " before using!");
        if (this.components.get(cls).get(id) == null)
            throw new RuntimeException(cls.getName() + " Component not found: " + id);
        return this.components.get(cls).get(id);
    }

    public <T extends Class<? extends Component>> Map<Integer, Component> getComponents(T cls) {
        if (this.components.isEmpty()) return null;
        return this.components.get(cls);
    }

    public Map<Integer, Component> getComponents() {
        if (this.components.isEmpty()) return null;
        Map<Integer, Component> c = new HashMap<>();
        for (Map<Integer, Component> cc : this.components.values()) {
            c.putAll(cc);
        }
        return c;
    }

    public Map<Class<? extends Component>, Map<Integer, Component>> getComponentsMap() {
        return components;
    }
}
