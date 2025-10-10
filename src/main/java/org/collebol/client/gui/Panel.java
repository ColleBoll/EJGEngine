package org.collebol.client.gui;

import org.collebol.client.EJGEngine;
import org.collebol.client.gui.graphics.Camera;
import org.collebol.client.gui.graphics.Text;
import org.collebol.shared.math.Vector2D;

/**
 * The Panel class represents a user interface panel in a window.
 * It is an abstract class that provides the basic structure and functionality for a Panel.
 *
 * <p>Each panel has an id, a camera and references to a window.</p>
 *
 * <p>Usage:</p>
 * <blockquote><pre>
 *     MyPanel panel = new MyPanel(id, engine);
 *     engine.getWindow().registerPanel(panel); //register panel at the window.
 * </pre></blockquote>
 *
 * @author ColleBol - <a href="mailto:contact@collebol.org">contact@collebol.org</a>
 * @since 1.0-dev
 */
public abstract class Panel {

    public int id;
    private Camera camera;
    private float DT;
    private int currentFPS;

    private final EJGEngine engine;

    /**
     * Panel constructor.
     *
     * @param id the id of the panel.
     * @param e  the engine instance.
     */
    public Panel(int id, EJGEngine e) {
        this.id = id;
        this.engine = e;
        this.camera = new Camera(new Vector2D(0, 0), 1f, 0, engine);
        this.currentFPS = (int) Math.floor(1.0f / getDT());
    }

    /**
     * Displays screen details, a tool for developing.
     */
    public void showScreenDetails(float size) {
        getEngine().getRenderers().getTextRenderer("default").render(new Text(new Text.TextBuilder()
                .text("EJGEngine version: " + getEngine().getEngineInfo().getVersion())
                .position(new Vector2D( 10f, -35f + (size * 2f)))
                .size(size)
                .scale(1)
                .align(Text.ALIGN_TOP_LEFT)
                .rotation(0)
        ));

        getEngine().getRenderers().getTextRenderer("default").render(new Text(new Text.TextBuilder()
                .text("Window width: " + getEngine().getWindow().getWidth() + "px")
                .position(new Vector2D(getEngine().getWindow().getWidth() - 10f, -10f + (size * 2f)))
                .size(size)
                .scale(1)
                .align(Text.ALIGN_TOP_RIGHT)
                .rotation(0)
        ));
        getEngine().getRenderers().getTextRenderer("default").render(new Text(new Text.TextBuilder()
                .text("Window height: " + getEngine().getWindow().getHeight() + "px")
                .position(new Vector2D(getEngine().getWindow().getWidth() - 10f, 10f + (size * 2f)))
                .size(size)
                .scale(1)
                .align(Text.ALIGN_TOP_RIGHT)
                .rotation(0)
        ));
        getEngine().getRenderers().getTextRenderer("default").render(new Text(new Text.TextBuilder()
                .text("FPS: " + getCurrentFPS())
                .position(new Vector2D(getEngine().getWindow().getWidth() - 10f, 35f + (size * 2f)))
                .size(size)
                .scale(1)
                .align(Text.ALIGN_TOP_RIGHT)
                .rotation(0)
        ));
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
     * @return delta tile
     */
    public float getDT() {
        return DT;
    }

    public void setDT(float DT) {
        this.DT = DT;
    }

    public int getCurrentFPS() {
        return currentFPS;
    }

    public void setCurrentFPS(int currentFPS) {
        this.currentFPS = currentFPS;
    }
}
