package org.collebol.client.gui.graphics.ui;

import java.util.HashMap;
import java.util.Map;

public class ComponentHandler {

    private final Map<Class<? extends Component>, Map<Integer, Component>> components;

    public ComponentHandler() {
        this.components = new HashMap<>();
    }

    public <T extends Component> void registerNewComponent(T component) {
        if (!this.components.containsKey(component.getClass())) {
            this.components.put(component.getClass(), new HashMap<>());
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
