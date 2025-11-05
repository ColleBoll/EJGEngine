package org.collebol.client.gui.graphics.renderer.ui;

import org.collebol.client.EJGEngine;
import org.collebol.client.gui.graphics.renderer.Renderer;
import org.collebol.client.gui.graphics.ui.Component;

public abstract class ComponentRenderer<T extends Component> extends Renderer {

    public final EJGEngine engine;

    public ComponentRenderer(EJGEngine engine) {
        this.engine = engine;
    }

    public void render(int id) {
        T input = this.engine.getComponentHandler().getComponent(new Class<T>)
    }

    abstract void renderInternal(T input, float offsetX, float offsetY);
}
