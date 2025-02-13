package org.collebol.engine.utils;

/**
 * The GameLocation class represents the location of GameObject's in the game.
 *
 * @author ColleBol - <a href="mailto:contact@collebol.org">contact@collebol.org</a>
 * @since 1.0-dev
 */
public class GameLocation {

    private double x;
    private double y;

    public GameLocation(double x, double y) {
        this.x = (double) Math.round(x * 100.0) / 100;
        this.y = (double) Math.round(y * 100.0) / 100;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
