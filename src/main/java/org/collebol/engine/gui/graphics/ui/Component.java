package org.collebol.engine.gui.graphics.ui;

import org.collebol.shared.math.Vector2D;

/**
 * The Component class is an abstract class that represents a UI component in the game.
 * It contains properties for the component's ID, position, width, and height, along with their respective getters and setters.
 *
 * @author ColleBol - <a href="mailto:contact@collebol.org">contact@collebol.org</a>
 * @since 1.0-dev
 */
public abstract class Component {

    private int id;
    private Vector2D position;
    private float width;
    private float height;

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public Vector2D getPosition() {
        return position;
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
