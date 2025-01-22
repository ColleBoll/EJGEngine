package me.collebol.gui.graphics;

import me.collebol.EJGEngine;
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
    private Vector2D origin;

    private EJGEngine engine;

    public Camera(Vector2D position, float zoom, EJGEngine e){
        this.position = position;
        this.zoom = zoom;
        this.origin = new Vector2D(0, 0);
        this.engine = e;
    }

    /**
     * The origin of the Camera represents the reference point from which
     * the camera's position and zoom are calculated.
     * @return origin point as Vector
     */
    public Vector2D getOrigin() {
        return origin;
    }

    /**
     * The origin of the Camera represents the reference point from which
     * the camera's position and zoom are calculated.
     * @param origin as vector
     */
    public void setOrigin(Vector2D origin) {
        this.origin = origin;
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

    /**
     * This method will convert the position as Vector2D to the GameLocation where the origin point is pointed at.
     * @return GameLocation from vector
     */
    public GameLocation getGameLocation(){
        if(this.position == null) throw new RuntimeException("Position is null. Set a position before using!");
        float x = ((this.position.getX() / this.engine.getWindow().getTileSize()) / this.zoom);
        float y = ((this.position.getY() / this.engine.getWindow().getTileSize()) / this.zoom);
        return new GameLocation(x, y);
    }

    public void setGameLocation(GameLocation location){
        float x = (float) ((location.x * this.engine.getWindow().getTileSize()) * this.zoom);
        float y = (float) ((location.y * this.engine.getWindow().getTileSize()) * this.zoom);
        this.position = new Vector2D(x, y);
    }
}
