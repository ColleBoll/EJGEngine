package org.collebol.client.physics.collision;

import org.collebol.shared.math.Vector2D;
import org.collebol.client.physics.PhysicsComponent;

public abstract class Collider extends PhysicsComponent {

    private Vector2D origin;
    private float width, height;

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

    public Vector2D getOrigin() {
        return origin;
    }

    public void setOrigin(Vector2D origin) {
        this.origin = origin;
    }
}
