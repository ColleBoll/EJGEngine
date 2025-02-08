package org.collebol.gui;

import org.collebol.EJGEngine;
import org.collebol.gui.graphics.Camera;
import org.collebol.gui.graphics.renderer.TextRenderer;
import org.collebol.math.Vector2D;

/**
 * The Panel class represents a user interface panel in a window.
 * It is an abstract class that provides the basic structure and functionality for a Panel.
 *
 * <p>Each panel has an index, a camera and references to a window.</p>
 *
 * <p>Usage:</p>
 * <blockquote><pre>
 *     MyPanel panel = new MyPanel(index, engine);
 *     engine.getWindow().registerPanel(panel); //register panel at the window.
 * </pre></blockquote>
 * @author ColleBol - <a href="mailto:contact@collebol.org">contact@collebol.org</a>
 * @since 1.0-dev
 */
public abstract class Panel {

    public int index;
    private Camera camera;
    private float DT;

    private EJGEngine engine;

    /**
     * Panel constructor.
     * @param index the index of the panel.
     * @param e the engine instance.
     */
    public Panel(int index, EJGEngine e) {
        this.index = index;
        this.engine = e;
        this.camera = new Camera(new Vector2D(0, 0), 1f, 0, engine);
    }

    /**
     * Displays screen details, a tool for developing.
     */
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

    /**
     *
     * @return delta tile
     */
    public float getDT() {
        return DT;
    }

    public void setDT(float DT) {
        this.DT = DT;
    }
}
