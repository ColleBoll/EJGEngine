package me.collebol.gui;

import me.collebol.EJGEngine;
import me.collebol.graphics.TextRenderer;

public abstract class Panel {

    public int index;

    private EJGEngine engine;
    public EJGEngine getEngine(){
        return engine;
    }

    public Panel(int index, EJGEngine e){
        this.index = index;
        this.engine = e;
    }

    public abstract void update(float dt);

    public abstract void paint();

}
