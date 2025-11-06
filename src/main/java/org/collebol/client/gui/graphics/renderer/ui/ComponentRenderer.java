package org.collebol.client.gui.graphics.renderer.ui;

import org.collebol.client.EJGEngine;
import org.collebol.client.gui.graphics.renderer.Renderer;
import org.collebol.client.gui.graphics.ui.Component;
import org.collebol.client.gui.graphics.ui.component.Field;

import java.lang.reflect.ParameterizedType;

/**
 * This ComponentRenderer class is responsible for rendering {@code <T>} components in the UI.
 * It handles the rendering of both standalone components and sub-components within a parent component.
 *
 * <p>
 *     This class only contains the super classes: {@link #render(int)} and {@link #renderSub(int, int)}.<br>
 *     Sub-classes contain {@link #renderInternal(Component, float, float)} with for every component custom OpenGL render data.
 * </p>
 *
 * <p>Usage:</p>
 * <blockquote><pre>
 *     MyComponentRenderer buttonRenderer = new MyComponentRenderer(engine);
 *     MyComponentRenderer.render(buttonId);
 *     MyComponentRenderer.renderSub(subButtonId, parentId);
 * </pre></blockquote>
 *
 * @param <T> the type of component this renderer handles
 * @author ColleBol - <a href="mailto:contact@collebol.org">contact@collebol.org</a>
 * @since 1.0-dev
 */
public abstract class ComponentRenderer<T extends Component> extends Renderer {

    public final EJGEngine engine;
    private final Class<T> componentClass;

    public ComponentRenderer(EJGEngine engine) {
        this.engine = engine;

        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        this.componentClass = (Class<T>) type.getActualTypeArguments()[0];
    }

    /**
     * This method renders a specified UI {@link Component}.<br>
     *
     * It calls the {@link #renderInternal(Component, float, float)}.
     *
     * @param id id of the registered component you want to render.
     */
    public void render(int id) {
        T input = (T) this.engine.getComponentHandler().getComponent(this.componentClass, id);
        renderInternal(input, 0f, 0f);
    }

    /**
     * This method renders a specified UI of a parent component.<br>
     *
     * It calls the {@link #renderInternal(Component, float, float)} with changed offset
     * matching the X and Y of the parent.
     *
     * @param id the id of the registered component you want to render.
     * @param parentId the id of the parent where the component is registered you want to render. (Must be a {@link Field})
     */
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
