package me.collebol.gui;

public abstract class Panel {

    public int index;

    public Panel(int index){
        this.index = index;
    }

    public abstract void update(float dt);

    public abstract void paint();

}
