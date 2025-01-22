package me.collebol.gui;

import me.collebol.EJGEngine;
import me.collebol.gui.graphics.Camera;
import me.collebol.math.Vector2D;

public abstract class Panel {

    public int index;
    private Camera camera;

    private EJGEngine engine;

    public Panel(int index, EJGEngine e){
        this.index = index;
        this.engine = e;
        this.camera = new Camera(new Vector2D(0,0), 3f, engine);
    }

    public abstract void update(float dt);

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
}
