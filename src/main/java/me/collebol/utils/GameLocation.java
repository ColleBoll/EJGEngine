package me.collebol.utils;

public class GameLocation {

    public double x;
    public double y;

    public GameLocation(double x, double y){
        this.x = (double) Math.round(x * 100.0) / 100;
        this.y = (double) Math.round(y * 100.0) / 100;
    }
}
