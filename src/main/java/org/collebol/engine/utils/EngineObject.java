package org.collebol.engine.utils;

import org.collebol.engine.math.Vector2D;

/**
 * EngineObject represents an object in the game engine,
 * holding its position (as a Vector2D) and texture (for visual representation).
 *
 * @author ColleBol - <a href="mailto:contact@collebol.org">contact@collebol.org</a>
 * @since 1.0-dev
 */
public class EngineObject {

    private Vector2D vector;
    private int textureid;

    /**
     * Position relative to the screen.
     *
     * @return position as vector
     */
    public Vector2D getVector() {
        return vector;
    }

    /**
     * Position relative to the screen.
     *
     * @param vector set the screen position of the object.
     */
    public void setVector(Vector2D vector) {
        this.vector = vector;
    }

    public int getTexture() {
        return this.textureid;
    }

    public void setTexture(int id) {
        this.textureid = id;
    }
}
