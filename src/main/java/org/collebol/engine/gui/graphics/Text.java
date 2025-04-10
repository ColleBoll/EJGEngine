package org.collebol.engine.gui.graphics;

import org.collebol.shared.math.Vector2D;

import static org.lwjgl.nanovg.NanoVG.*;

/**
 * The Text class represents a text element that can be rendered on the screen.
 * It contains properties such as the text content, position, size, scale, alignment, rotation, and origin.
 * This class uses the Builder pattern to facilitate the creation of Text objects with various configurations.
 *
 * <p>Usage:</p>
 * <blockquote><pre>
 *     Text text = new Text(new Text.TextBuilder()
 *         .text("Hello, World!")
 *         .position(new Vector2D(100, 200))
 *         .size(24)
 *         .scale(1.5f)
 *         .align(Text.ALIGN_CENTER)
 *         .rotation(45)
 *         .origin(new Vector2D(50, 50))
 *     );
 * </pre></blockquote>
 *
 * <p>The Text class also provides several predefined alignment constants for convenience:</p>
 * <ul>
 *     <li>{@link #ALIGN_CENTER}</li>
 *     <li>{@link #ALIGN_TOP_LEFT}</li>
 *     <li>{@link #ALIGN_TOP_RIGHT}</li>
 *     <li>{@link #ALIGN_BOTTOM_LEFT}</li>
 *     <li>{@link #ALIGN_BOTTOM_RIGHT}</li>
 * </ul>
 *
 * @author ColleBol - <a href="mailto:contact@collebol.org">contact@collebol.org</a>
 * @since 1.0-dev
 */
public class Text {

    private String text;
    private Vector2D position;
    private float size;
    private float scale;
    private int align;
    private float rotation;
    private Vector2D origin;

    public static int ALIGN_CENTER = NVG_ALIGN_CENTER;
    public static int ALIGN_TOP_LEFT = NVG_ALIGN_LEFT | NVG_ALIGN_TOP;
    public static int ALIGN_TOP_RIGHT = NVG_ALIGN_RIGHT | NVG_ALIGN_TOP;
    public static int ALIGN_BOTTOM_LEFT = NVG_ALIGN_LEFT | NVG_ALIGN_BOTTOM;
    public static int ALIGN_BOTTOM_RIGHT = NVG_ALIGN_RIGHT | NVG_ALIGN_BOTTOM;

    public Text(TextBuilder builder) {
        this.text = builder.text;
        this.position = builder.position;
        this.size = builder.size;
        this.scale = builder.scale;
        this.align = builder.align;
        this.rotation = builder.rotation;
        this.origin = builder.origin;
    }

    public static class TextBuilder {
        private String text = "-";
        private Vector2D position = new Vector2D(0, 0);
        private float size = 10;
        private float scale = 1;
        private int align = ALIGN_TOP_LEFT;
        private float rotation = 0;
        private Vector2D origin = new Vector2D(0, 0);

        public TextBuilder text(String text) {
            this.text = text;
            return this;
        }

        public TextBuilder position(Vector2D position) {
            this.position = position;
            return this;
        }

        public TextBuilder size(float size) {
            this.size = size;
            return this;
        }

        public TextBuilder scale(float scale) {
            this.scale = scale;
            return this;
        }

        public TextBuilder align(int align) {
            this.align = align;
            return this;
        }

        public TextBuilder rotation(float rotation) {
            this.rotation = rotation;
            return this;
        }

        public TextBuilder origin(Vector2D origin) {
            this.origin = origin;
            return this;
        }
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Vector2D getPosition() {
        return position;
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public int getAlign() {
        return align;
    }

    public void setAlign(int align) {
        this.align = align;
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public Vector2D getOrigin() {
        return origin;
    }

    public void setOrigin(Vector2D origin) {
        this.origin = origin;
    }
}
