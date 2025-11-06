package org.collebol.client.gui.graphics.renderer.ui;

import org.collebol.client.EJGEngine;
import org.collebol.client.gui.graphics.renderer.Renderer;
import org.collebol.client.gui.graphics.renderer.TextRenderer;
import org.collebol.client.gui.graphics.ui.Component;
import org.collebol.client.gui.graphics.ui.component.Button;
import org.collebol.client.gui.graphics.ui.component.Field;
import org.collebol.client.gui.graphics.ui.component.Label;
import org.collebol.client.gui.graphics.ui.component.TextInput;

import java.lang.invoke.TypeDescriptor;
import java.util.HashMap;
import java.util.Map;

/**
 * The UIRenderer class is responsible for managing UI renderers.
 * It maintains a collection of specific renderers for different component types and delegates rendering tasks to them.
 * This class uses a map to store and retrieve renderers based on the component type.
 *
 * <p>Usage:</p>
 * <blockquote><pre>
 *     UIRenderer uiRenderer = new UIRenderer(engine);
 *     uiRenderer.renderComponent(Field.class, fieldId);
 *     uiRenderer.renderSubComponent(Button.class, buttonId, fieldId);
 * </pre></blockquote>
 *
 * @author ColleBol - <a href="mailto:contact@collebol.org">contact@collebol.org</a>
 * @since 1.0-dev
 */
public class UIRenderer extends Renderer {

    private final EJGEngine engine;

    private final Map<Class<? extends Component>, ComponentRenderer<? extends Component>> UIRenderers;

    public void addUIRenderers() {
        this.UIRenderers.put(Field.class, new FieldRenderer(engine));
        this.UIRenderers.put(Button.class, new ButtonRenderer(engine));
        this.UIRenderers.put(TextInput.class, new TextInputRenderer(engine));
        this.UIRenderers.put(Label.class, new LabelRenderer(engine));
    }

    /**
     * UIRenderer constructor.
     * @param e instance
     */
    public UIRenderer(EJGEngine e) {
        this.engine = e;
        this.UIRenderers = new HashMap<>();

        addUIRenderers();
    }

    public <T extends Component> void renderComponent(Class<T> cls, int id) {
        ComponentRenderer<T> renderer = (ComponentRenderer<T>) this.UIRenderers.get(cls);
        renderer.render(id);
    }

    public <T extends Component> void renderSubComponent(Class<T> cls, int id, int parentId){
        ComponentRenderer<T> renderer = (ComponentRenderer<T>) this.UIRenderers.get(cls);
        renderer.renderSub(id, parentId);
    }
}
