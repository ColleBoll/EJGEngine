package org.collebol.engine.gui.graphics;

import org.collebol.engine.EJGEngine;
import org.collebol.engine.math.CameraCalculator;
import org.collebol.engine.math.Vector2D;
import org.collebol.engine.utils.GameLocation;

/**
 * The Camera class represents a camera in a 2D space.
 * It has a position ({@link Vector2D}), zoom level ({@code float}), origin-point ({@link Vector2D}), rotation ° ({@code float})
 * ambientLight (default light {@code float array}) and a {@code boolean} if you want to use lighting.
 *
 * <p>The class provides methods to move the camera by a given delta and to zoom in or out by a given factor.
 * Additionally, it includes getter and setter methods for the position and zoom level.</p>
 *
 * <p>Usage:</p>
 * <blockquote><pre>
 *     Camera camera = new Camera(
 *          new Vector(0, 0),
 *          3, //zoom = scale
 *          0, //rotation in °
 *          engine //EJGEngine instance
 *     );
 * </pre></blockquote>
 *
 * @author ColleBol - <a href="mailto:contact@collebol.org">contact@collebol.org</a>
 * @since 1.0-dev
 */
public class Camera {

    private Vector2D position;
    private float zoom;
    private Vector2D origin;
    private float rotation;
    private float[] ambientLight;
    private boolean lighting;

    private CameraCalculator calculator;

    private EJGEngine engine;

    public Camera(Vector2D position, float zoom, float rotation, EJGEngine e) {
        this.position = position;
        this.zoom = zoom;
        this.rotation = rotation;
        this.origin = new Vector2D(0, 0);
        this.calculator = new CameraCalculator(this, e);
        this.engine = e;
        this.ambientLight = new float[]{1.0f, 1.0f, 1.0f, 1.0f};
        this.lighting = false;
    }

    /**
     * The origin of the Camera represents the reference point from which
     * the camera's position and zoom are calculated.
     *
     * @return origin point as Vector
     */
    public Vector2D getOrigin() {
        return origin;
    }

    /**
     * The origin of the Camera represents the reference point from which
     * the camera's position and zoom are calculated.
     *
     * @param origin as vector
     */
    public void setOrigin(Vector2D origin) {
        this.origin = origin;
    }

    /**
     * Add a amount to the current camera position
     *
     * @param delta amount. X and Y
     */
    public void move(Vector2D delta) {
        this.position = this.position.add(delta);
    }

    /**
     * Zoom the current canvas.
     *
     * @param factor the amount will be added.
     */
    public void zoom(float factor) {
        GameLocation loc = this.getGameLocation();
        this.zoom += factor;
        this.setGameLocation(loc);
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

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public CameraCalculator calculate() {
        return this.calculator;
    }

    /**
     * This method will convert the position as Vector2D to the GameLocation where the origin point is pointed at.
     *
     * @return GameLocation from vector
     */
    public GameLocation getGameLocation() {
        if (this.position == null) throw new RuntimeException("Position is null. Set a position before using!");
        float x = ((this.position.getX() / this.engine.getWindow().getTileSize()) / this.zoom);
        float y = ((this.position.getY() / this.engine.getWindow().getTileSize()) / this.zoom);
        return new GameLocation(x, y);
    }

    /**
     * This method will set the GameLocation to a Panel (Vector2D) position where the origin point is pointed at.
     *
     * @param location the GameLocation the Camera must go to.
     */
    public void setGameLocation(GameLocation location) {
        float x = (float) ((location.getX() * this.engine.getWindow().getTileSize()) * this.zoom);
        float y = (float) ((location.getY() * this.engine.getWindow().getTileSize()) * this.zoom);
        this.position = new Vector2D(x, y);
    }

    public float[] getAmbientLight() {
        return ambientLight;
    }

    public void setAmbientLight(float[] ambientLight) {
        this.ambientLight = ambientLight;
    }

    public boolean isLighting() {
        return lighting;
    }

    public void setLighting(boolean lighting) {
        this.lighting = lighting;
    }
}
