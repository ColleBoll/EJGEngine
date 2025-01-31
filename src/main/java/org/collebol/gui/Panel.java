package org.collebol.gui;

import org.collebol.EJGEngine;
import org.collebol.gui.graphics.Camera;
import org.collebol.gui.graphics.renderer.TextRenderer;
import org.collebol.math.Vector2D;

public abstract class Panel {

    public int index;
    private Camera camera;
    private float DT;

    private EJGEngine engine;

    public Panel(int index, EJGEngine e) {
        this.index = index;
        this.engine = e;
        this.camera = new Camera(new Vector2D(0, 0), 1f, 0, engine);
    }

    public void showScreenDetails() {
        getEngine().getRenderers().getTextRenderer("default").render(new TextRenderer.TextBuilder()
                .text("Window width: " + getEngine().getWindow().getWidth() + "px")
                .position(new Vector2D(getEngine().getWindow().getWidth() - 10f, 5f))
                .size(13)
                .scale(1)
                .align(TextRenderer.ALIGN_TOP_RIGHT)
                .rotation(0)
        );
        getEngine().getRenderers().getTextRenderer("default").render(new TextRenderer.TextBuilder()
                .text("Window height: " + getEngine().getWindow().getHeight() + "px")
                .position(new Vector2D(getEngine().getWindow().getWidth() - 10f, 20f))
                .size(13)
                .scale(1)
                .align(TextRenderer.ALIGN_TOP_RIGHT)
                .rotation(0)
        );
        getEngine().getRenderers().getTextRenderer("default").render(new TextRenderer.TextBuilder()
                .text("FPS: " + Math.floor(1.0f / getDT()))
                .position(new Vector2D(getEngine().getWindow().getWidth() - 10f, 50f))
                .size(13)
                .scale(1)
                .align(TextRenderer.ALIGN_TOP_RIGHT)
                .rotation(0)
        );
    }

    public abstract void update();

    public abstract void paint();

    public EJGEngine getEngine() {
        return engine;
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public float getDT() {
        return DT;
    }

    public void setDT(float DT) {
        this.DT = DT;
    }
}
