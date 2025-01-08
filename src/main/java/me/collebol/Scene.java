package me.collebol;

import org.lwjgl.opengl.GL11;

public abstract class Scene {

    public Scene(){

    }

    public int i;

    public abstract void update(float dt);

    public abstract void paint();

}
