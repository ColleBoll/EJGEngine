package me.collebol.gui;

import me.collebol.EJGEngine;
import me.collebol.gui.graphics.Camera;
import me.collebol.gui.graphics.TextRenderer;
import me.collebol.math.Vector2D;

import static org.lwjgl.nanovg.NanoVG.*;

public abstract class Panel {

    public int index;
    private Camera camera;
    private float DT;

    private EJGEngine engine;

    public Panel(int index, EJGEngine e){
        this.index = index;
        this.engine = e;
        this.camera = new Camera(new Vector2D(0,0), 3f, 0, engine);
    }

    public void showScreenDetails(){
        getEngine().getTextRenderer("default").render("Window width: " + getEngine().getWindow().getWidth() + "px",
                new Vector2D(getEngine().getWindow().getWidth() - 10f, 5f),
                13,
                1,
                TextRenderer.ALIGN_TOP_RIGHT,
                0);
        getEngine().getTextRenderer("default").render("Window height: " + getEngine().getWindow().getHeight() + "px",
                new Vector2D(getEngine().getWindow().getWidth() - 10f, 20f),
                13,
                1,
                TextRenderer.ALIGN_TOP_RIGHT,
                0);
        getEngine().getTextRenderer("default").render("FPS: " + Math.floor(1.0f / getDT()),
                new Vector2D(getEngine().getWindow().getWidth() - 10f, 50f),
                13,
                1,
                TextRenderer.ALIGN_TOP_RIGHT,
                0);
    }

    public abstract void update();

    public abstract void paint();

    public EJGEngine getEngine(){
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
