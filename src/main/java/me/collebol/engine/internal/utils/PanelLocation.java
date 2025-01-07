package me.collebol.engine.internal.utils;

import me.collebol.EJGEngine;

public class PanelLocation {

    public int x;
    public int y;

    public PanelLocation(){
        this.x = 0;
        this.y = 0;
    }

    public PanelLocation(int x, int y){
        this.x = x;
        this.y = y;
    }

    public PanelLocation center(){
        int x = (EJGEngine.window.getCurrentPanel().getScreenWidth() / 2);
        int y = (EJGEngine.window.getCurrentPanel().getScreenHeight() / 2);
        return new PanelLocation(x, y);
    }
}
