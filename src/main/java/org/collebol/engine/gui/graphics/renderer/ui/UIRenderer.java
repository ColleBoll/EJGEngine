package org.collebol.engine.gui.graphics.renderer.ui;

import org.collebol.engine.EJGEngine;
import org.collebol.engine.gui.graphics.renderer.Renderer;
import org.collebol.engine.gui.graphics.ui.Component;
import org.collebol.engine.gui.graphics.ui.component.Field;

import java.util.HashMap;
import java.util.Map;

public class UIRenderer extends Renderer {

    private final EJGEngine engine;

    private Map<Class<? extends Renderer>, Renderer> UIRenderers;

    public void addUIRenderers() {
        this.UIRenderers.put(FieldRenderer.class, new FieldRenderer(engine));
    }

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
    }

    public <T extends Class<? extends Component>> void renderSubComponent(T cls, int id, int fieldId){
        if (cls == Field.class) {
            FieldRenderer renderer = (FieldRenderer) this.UIRenderers.get(FieldRenderer.class);
            renderer.renderSubField(id, fieldId);
        }
    }
}
