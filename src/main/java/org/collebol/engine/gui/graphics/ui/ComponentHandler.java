package org.collebol.engine.gui.graphics.ui;

import org.collebol.engine.gui.graphics.ui.component.Component;
import org.collebol.engine.gui.graphics.ui.component.Field;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComponentHandler {

    private final Map<Class<? extends Component>, Map<Integer, Component>> components;
    private final HashMap<Integer, List<Integer>> subComponents;

    public ComponentHandler() {
        this.components = new HashMap<>();
        this.subComponents = new HashMap<>();
    }

    public <T extends Component> void registerNewComponent(T component) {
        if (!this.components.containsKey(component.getClass())) {
            this.components.put(component.getClass(), new HashMap<>());
        }

        this.components.get(component.getClass()).put(component.getId(), component);

        if (component.getParentId() != 0) {
            if (!this.components.get(Field.class).containsKey(component.getParentId()))
                throw new RuntimeException("Only Fields can be a Parent of a Component. Parent id not found at the registered Fields: " + component.getParentId());
            if(!this.subComponents.containsKey(component.getParentId())){
                this.subComponents.put(component.getParentId(), new ArrayList<>());
            }
            this.subComponents.get(component.getParentId()).add(component.getId());
        }
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

    public List<Integer> getSubComponents(int parentId) {
        if (this.subComponents.get(parentId) == null) return null;
        if (!this.subComponents.containsKey(parentId))
            throw new RuntimeException("Parent ID not found: " + parentId + ". Be sure it is a registered Field ID. Only Fields can be a parent!");
        return this.subComponents.get(parentId);
    }

    public Map<Class<? extends Component>, Map<Integer, Component>> getComponentsMap() {
        return components;
    }
}
