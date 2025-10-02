package org.collebol.shared;

import org.collebol.client.gui.graphics.renderer.TextureRenderer;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a sprite animation composed of multiple frames.
 * A sprite consists of one or more texture frames that are displayed sequentially
 * to create an animation. Each frame is identified by a texture ID previously
 * registered in the {@link TextureRenderer}. The sprite updates its current frame
 * automatically based on the elapsed time and the specified frame duration.
 *
 * <p>Sprites are designed to be attached to {@link org.collebol.shared.objects.GameObject}
 * instances. When a {@code GameObject} has a {@code Sprite}, the rendering system will
 * use the sprite's current frame instead of a static texture.</p>
 *
 * @author ColleBol - <a href="mailto:contact@collebol.org">contact@collebol.org</a>
 * @since 1.0-dev
 */
public class Sprite {

    private List<Integer> frameIds; // Texture ID's sorted with position ID's
    private float frameDuration;
    private int currentFrame;
    private float elapsedTime;

    public Sprite(SpriteBuilder builder) {
        this.frameIds = builder.frameIds;
        this.frameDuration = builder.frameDuration;
        this.currentFrame = builder.currentFrame;
        this.elapsedTime = builder.elapsedTime;
    }

    public static class SpriteBuilder {
        private List<Integer> frameIds;
        private float frameDuration = 1;
        private int currentFrame = 0;
        private float elapsedTime = 0;

        public SpriteBuilder frames(int[] IDs) {
            this.frameIds = new ArrayList<>();
            for (int id : IDs) {
                this.frameIds.add(id);
            }
            return this;
        }

        public SpriteBuilder duration(float duration) {
            this.frameDuration = duration;
            return this;
        }

        public SpriteBuilder current(int current){
            this.currentFrame = current;
            return this;
        }

        public SpriteBuilder elapsed(float elapsed){
            this.elapsedTime = elapsed;
            return this;
        }
    }

    public void update(float deltaTime) {
        this.elapsedTime += deltaTime;
        if (this.elapsedTime >= this.frameDuration) {
            this.elapsedTime -= this.frameDuration;
            this.currentFrame = (this.currentFrame + 1) % this.frameIds.size();
        }
    }

    public float getElapsedTime() {
        return elapsedTime;
    }

    public int getCurrentFrameId() {
        if (frameIds == null || frameIds.isEmpty()) {
            throw new IllegalStateException("Sprite has no frames! Call SpriteBuilder.frames(...) first.");
        }
        return frameIds.get(currentFrame % frameIds.size());
    }

    public float getFrameDuration() {
        return frameDuration;
    }

    public List<Integer> getFrameIds() {
        return frameIds;
    }
}
