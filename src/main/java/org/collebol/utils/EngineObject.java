package org.collebol.utils;

import org.collebol.math.Vector2D;

/**
 * EngineObject represents an object in the game engine,
 * holding its position (as a Vector2D) and texture (for visual representation).
 *
 * @author ColleBol - contact@collebol.org
 * @since < 1.0
 */
public class EngineObject {

    private Vector2D vector;
    private int textureIndex;

    /**
     * Position relative to the screen.
     *
     * @return position as vector
     * @author ColleBol - contact@collebol.org
     * @since < 1.0
     */
    public Vector2D getVector() {
        return vector;
    }

    /**
     * Position relative to the screen.
     *
     * @param vector set the screen position of the object.
     * @author ColleBol - contact@collebol.org
     * @since < 1.0
     */
    public void setVector(Vector2D vector) {
        this.vector = vector;
    }

    public int getTexture() {
        return this.textureIndex;
    }

    public void setTexture(int index) {
        this.textureIndex = index;
    }
}
