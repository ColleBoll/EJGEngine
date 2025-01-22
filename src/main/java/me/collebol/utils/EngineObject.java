package me.collebol.utils;

import me.collebol.gui.graphics.Texture;
import me.collebol.math.Vector2D;

/**
 * EngineObject represents an object in the game engine,
 * holding its position (as a Vector2D) and texture (for visual representation).
 */
public class EngineObject {

    private Vector2D vector;
    private int textureIndex;

    /**
     * Position relative to the screen.
     * @return position as vector
     */
    public Vector2D getVector() {
        return vector;
    }

    /**
     * Position relative to the screen.
     * @param vector set the screen position of the object.
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
