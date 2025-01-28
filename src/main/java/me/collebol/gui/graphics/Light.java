package me.collebol.gui.graphics;

import me.collebol.math.Vector2D;

public class Light {

    private Vector2D position;
    private float intensity;
    private float radius;
    private float[] color;

    public Light(LightBuilder builder){
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
        private Vector2D position = new Vector2D(0,0);
        private float intensity = 1.0f;
        private float radius = 10.0f;
        private float[] color = new float[]{ 1.0f, 1.0f, 1.0f, 1.0f };

        public LightBuilder position(Vector2D position){
            this.position = position;
            return this;
        }

        public LightBuilder intensity(float intensity){
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
