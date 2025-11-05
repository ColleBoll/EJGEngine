package org.collebol.client.gui.graphics.renderer.ui;

import org.collebol.client.EJGEngine;
import org.collebol.client.gui.graphics.renderer.Renderer;
import org.collebol.client.gui.graphics.ui.Component;
import org.collebol.client.gui.graphics.ui.component.Field;

import java.lang.reflect.ParameterizedType;

public abstract class ComponentRenderer<T extends Component> extends Renderer {

    public final EJGEngine engine;
    private final Class<T> componentClass;

    public ComponentRenderer(EJGEngine engine) {
        this.engine = engine;

        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        this.componentClass = (Class<T>) type.getActualTypeArguments()[0];
    }

    public void render(int id) {
        T input = (T) this.engine.getComponentHandler().getComponent(this.componentClass, id);
        renderInternal(input, 0f, 0f);
    }

    public void renderSub(int id, int parentId) {
        Field parent;
        try {
            parent = (Field) this.engine.getComponentHandler().getComponent(Field.class, parentId);
        } catch (RuntimeException e) {
            throw new RuntimeException("The given parentId is not found! Make sure your parent is a Field component.");
        }
        T input = (T) parent.getSubComponentsHandler().getComponent(this.componentClass, id);
        renderInternal(input, parent.getPosition().getX(), parent.getPosition().getY());
    }

    abstract void renderInternal(T input, float offsetX, float offsetY);
}
