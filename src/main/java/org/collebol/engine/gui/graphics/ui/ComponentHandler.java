package org.collebol.engine.gui.graphics.ui;

import org.collebol.engine.EJGEngine;
import org.collebol.engine.gui.graphics.ui.component.Button;
import org.collebol.engine.gui.graphics.ui.component.Component;

import java.util.HashMap;
import java.util.Map;

public class ComponentHandler {

    private EJGEngine engine;

    private Map<Integer, Field> fields;

    private Map<Integer, Button> buttons;

    public ComponentHandler(EJGEngine e) {
        this.engine = engine;
        this.fields = new HashMap<>();
        this.buttons = new HashMap<>();
    }

    public <T extends Component> void registerNewComponent(T component) {
        if (component instanceof Button) {
            if (this.buttons.containsKey(((Button) component).getId()))
                throw new RuntimeException("Button with the id: " + ((Button) component).getId() + " already exists. Please, make sure to give it a original id!");
            buttons.put(((Button) component).getId(), (Button) component);
        }
    }

    public void registerNewField(Field field) {
        if (field == null) throw new RuntimeException("Field equals null. Please, register a valid Field.");
        if (this.fields.containsKey(field.getId()))
            throw new RuntimeException("Field with the id: " + field.getId() + " already exists. Please, make sure to give it a original id!");
        fields.put(field.getId(), field);
    }

    public Field getField(int id){
        if (this.fields.isEmpty())
            throw new RuntimeException("You are trying to get a Field but you have not set one yet! Please, make sure to register a Field before using!");
        if (this.fields.get(id) != null) {
            return this.fields.get(id);
        } else {
            throw new RuntimeException("Field not found: " + id);
        }
    }

    public Map<Integer, Field> getFields() {
        return fields;
    }
}
