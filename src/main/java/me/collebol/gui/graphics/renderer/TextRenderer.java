package me.collebol.gui.graphics.renderer;

import me.collebol.EJGEngine;
import me.collebol.math.Vector2D;
import org.lwjgl.nanovg.NVGColor;
import org.lwjgl.nanovg.NanoVGGL2;
import org.lwjgl.system.MemoryUtil;

import static org.lwjgl.nanovg.NanoVG.*;

/**
 * This class contains all the methods to render text on a Panel.
 */
public class TextRenderer implements Renderer {
    private long vg;
    private EJGEngine engine;
    private String name;
    private String fontPath;

    public static int ALIGN_CENTER = NVG_ALIGN_CENTER;
    public static int ALIGN_TOP_LEFT = NVG_ALIGN_LEFT | NVG_ALIGN_TOP;
    public static int ALIGN_TOP_RIGHT = NVG_ALIGN_RIGHT | NVG_ALIGN_TOP;
    public static int ALIGN_BOTTOM_LEFT = NVG_ALIGN_LEFT | NVG_ALIGN_BOTTOM;
    public static int ALIGN_BOTTOM_RIGHT = NVG_ALIGN_RIGHT | NVG_ALIGN_BOTTOM;

    private EJGEngine getEngine() {
        return engine;
    }

    public TextRenderer(String name, String fontPath, EJGEngine e) {
        this.engine = e;
        this.name = name;
        this.fontPath = fontPath;
    }

    public void setup() {
        this.vg = NanoVGGL2.nvgCreate(NanoVGGL2.NVG_ANTIALIAS | NanoVGGL2.NVG_STENCIL_STROKES);
        if (this.vg == MemoryUtil.NULL) {
            throw new RuntimeException("Could not initialize NanoVG.");
        }

        int font = nvgCreateFont(this.vg, this.name, this.fontPath);
        if (font == -1) {
            throw new RuntimeException("Could not add font.");
        }
    }

    /**
     * Render text on the Panel with origin-point
     *
     * @param textBuilder
     */
    public void render(TextBuilder textBuilder) {
        nvgBeginFrame(this.vg, getEngine().getWindow().getWidth(), getEngine().getWindow().getHeight(), 20);

        NVGColor color = NVGColor.create();
        nvgRGBA((byte) 255, (byte) 255, (byte) 255, (byte) 255, color);

        nvgSave(this.vg);

        nvgTranslate(this.vg, textBuilder.origin.getX(), textBuilder.origin.getY());

        nvgRotate(this.vg, (float) Math.toRadians(textBuilder.rotation));

        nvgTranslate(this.vg, textBuilder.position.getX() - textBuilder.origin.getX(), textBuilder.position.getY() - textBuilder.origin.getY());

        nvgFontSize(this.vg, (textBuilder.size * textBuilder.scale));
        nvgFontFace(this.vg, this.name);
        nvgFillColor(this.vg, color);
        nvgTextAlign(this.vg, textBuilder.align);

        nvgText(this.vg, 0, 0, textBuilder.text);

        nvgRestore(this.vg);

        nvgEndFrame(this.vg);
    }

    public static class TextBuilder {
        private String text = "";
        private Vector2D position = new Vector2D(0, 0);
        private float size = 10;
        private float scale = 1;
        private int align = TextRenderer.ALIGN_TOP_LEFT;
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

    public void cleanup() {
        NanoVGGL2.nvgDelete(vg);
    }

    public String getName() {
        return name;
    }

    public String getFontPath() {
        return fontPath;
    }
}
