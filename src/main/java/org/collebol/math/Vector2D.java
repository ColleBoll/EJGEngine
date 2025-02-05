package org.collebol.math;

/**
 * This 2D game Vector represents a quantity with both direction and magnitude (length),
 * typically used for movement, velocity, and position. It is described by two components:
 * x (horizontal) and y (vertical).
 *
 * @author ColleBol - <a href="mailto:contact@collebol.org">contact@collebol.org</a>
 * @since 1.0-dev
 */
public class Vector2D {

    private float x;
    private float y;

    public Vector2D(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    /**
     * Add up two vectors.
     *
     * @param other the other vector you want to add up.
     * @return new vector with the combined vector values.
     */
    public Vector2D add(Vector2D other) {
        return new Vector2D(this.x + other.x, this.y + other.y);
    }

    /**
     * Subtract two vectors.
     *
     * @param other vector you want to subtract.
     * @return new vector. ( x - other ).
     */
    public Vector2D subtract(Vector2D other) {
        return new Vector2D(this.x - other.x, this.y - other.y);
    }

    /**
     * Multiply vector values with scalar.
     *
     * @param scalar the value you want to multiply with
     * @return new vector with multiplied values.
     */
    public Vector2D scale(float scalar) {
        return new Vector2D(this.x * scalar, this.y * scalar);
    }

    /**
     * Calculate the length (magnitude) of the vector.
     *
     * @return magnitude
     */
    public float length() {
        return (float) Math.sqrt(this.x * this.x + this.y * this.y);
    }
}
