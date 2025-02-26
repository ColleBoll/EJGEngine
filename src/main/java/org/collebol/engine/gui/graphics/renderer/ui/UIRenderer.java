package org.collebol.engine.gui.graphics.renderer.ui;

import org.collebol.engine.EJGEngine;
import org.collebol.engine.gui.graphics.renderer.Renderer;
import org.collebol.engine.gui.graphics.ui.Component;
import org.collebol.engine.gui.graphics.ui.component.Button;
import org.collebol.engine.gui.graphics.ui.component.Field;

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

    private Map<Class<? extends Renderer>, Renderer> UIRenderers;

    public void addUIRenderers() {
        this.UIRenderers.put(FieldRenderer.class, new FieldRenderer(engine));
        this.UIRenderers.put(ButtonRenderer.class, new ButtonRenderer(engine));
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

    public <T extends Class<? extends Component>> void renderComponent(T cls, int id) {
        if (cls == Field.class) {
            FieldRenderer renderer = (FieldRenderer) this.UIRenderers.get(FieldRenderer.class);
            renderer.renderField(id);
        }
        if(cls == Button.class) {
            ButtonRenderer renderer = (ButtonRenderer) this.UIRenderers.get(ButtonRenderer.class);
            renderer.renderButton(id);
        }
    }

    public <T extends Class<? extends Component>> void renderSubComponent(T cls, int id, int fieldId){
        if (cls == Field.class) {
            FieldRenderer renderer = (FieldRenderer) this.UIRenderers.get(FieldRenderer.class);
            renderer.renderSubField(id, fieldId);
        }
        if (cls == Button.class) {
            ButtonRenderer renderer = (ButtonRenderer) this.UIRenderers.get(ButtonRenderer.class);
            renderer.renderSubButton(id, fieldId);
        }
    }
}
