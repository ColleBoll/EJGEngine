package me.collebol.gui;

import me.collebol.graphics.text.NanoVGExample;

public abstract class Panel {

    public int index;
    public NanoVGExample nanoVGExample;

    public Panel(int index, NanoVGExample n){
        this.index = index;
        this.nanoVGExample = n;
    }

    public abstract void update(float dt);

    public abstract void paint();

}
