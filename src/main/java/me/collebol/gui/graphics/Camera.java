package me.collebol.gui.graphics;

import me.collebol.math.Vector2D;
import me.collebol.utils.GameLocation;

/**
 * The Camera class represents a camera in a 2D space.
 * It has a position (Vector2D) and a zoom level (float).
 * The class provides methods to move the camera by a given delta and to zoom in or out by a given factor.
 * Additionally, it includes getter and setter methods for the position and zoom level.
 */
public class Camera {

    private Vector2D position;
    private float zoom;

    public Camera(Vector2D position, float zoom){
        this.position = position;
        this.zoom = zoom;
    }

    /**
     * Add a amount to the current camera position
     * @param delta amount. X and Y
     */
    public void move(Vector2D delta){
        this.position = this.position.add(delta);
    }

    /**
     * Zoom the current canvas.
     * @param factor the amount will be multiplied.
     */
    public void zoom(float factor){
        this.zoom *= factor;
    }

    /**
     * @return current vector position of the camera.
     */
    public Vector2D getPosition() {
        return position;
    }

    /**
     * @param position as vector x and y.
     */
    public void setPosition(Vector2D position) {
        this.position = position;
    }

    /**
     * @return current zoom factor.
     */
    public float getZoom() {
        return zoom;
    }

    /**
     * @param zoom set zoom factor (float).
     */
    public void setZoom(float zoom) {
        this.zoom = zoom;
    }
}
