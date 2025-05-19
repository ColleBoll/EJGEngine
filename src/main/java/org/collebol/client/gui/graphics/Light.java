package org.collebol.client.gui.graphics;

import org.collebol.shared.math.Vector2D;

/**
 * The Light class represents a light source in a 2D space.
 * It has a position ({@link Vector2D}), intensity ({@code float}), radius ({@code float}) and color ({@code float array}).
 * This class provides getter methods for these properties, and it includes a LightBuilder class for the construction of a Light instance.
 *
 * <p>Building light:</p>
 * <blockquote><pre>
 *     Light light = new Light(Light.LightBuilder()
 *         .position(new Vector2D(0, 0))
 *         .intensity(1f)
 *         .radius(10f)
 *         .color(new float[]{ 1.0f, 1.0f, 1.0f, 1.0f })
 *         //                   R     G     B
 *     );
 * </pre></blockquote>
 *
 * @author ColleBol - <a href="mailto:contact@collebol.org">contact@collebol.org</a>
 * @since 1.0-dev
 */
public class Light {

    private Vector2D position;
    private float intensity;
    private float radius;
    private float[] color;

    public Light(LightBuilder builder) {
        this.position = builder.position;
        this.intensity = builder.intensity;
        this.radius = builder.radius;
        this.color = builder.color;
    }

    public Vector2D getPosition() {
        return position;
    }

    public float getIntensity() {
        return intensity;
    }

    public float getRadius() {
        return radius;
    }

    public float[] getColor() {
        return color;
    }

    public static class LightBuilder {
        private Vector2D position = new Vector2D(0, 0);
        private float intensity = 1.0f;
        private float radius = 10.0f;
        private float[] color = new float[]{1.0f, 1.0f, 1.0f, 1.0f};

        public LightBuilder position(Vector2D position) {
            this.position = position;
            return this;
        }

        public LightBuilder intensity(float intensity) {
            this.intensity = intensity;
            return this;
        }

        public LightBuilder radius(float radius) {
            this.radius = radius;
            return this;
        }

        public LightBuilder color(float[] color) {
            this.color = color;
            return this;
        }
    }
}
