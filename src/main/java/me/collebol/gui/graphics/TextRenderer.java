package me.collebol.gui.graphics;

import me.collebol.EJGEngine;
import me.collebol.math.Vector2D;
import org.lwjgl.nanovg.NVGColor;
import org.lwjgl.nanovg.NanoVGGL2;
import org.lwjgl.system.MemoryUtil;

import static org.lwjgl.nanovg.NanoVG.*;

/**
 * This class contains all the methods to render text on a Panel.
 */
public class TextRenderer {
    private long vg;
    private EJGEngine engine;
    private String name;
    private String fontPath;

    public static int ALIGN_CENTER = NVG_ALIGN_CENTER;
    public static int ALIGN_TOP_LEFT = NVG_ALIGN_LEFT | NVG_ALIGN_TOP;
    public static int ALIGN_TOP_RIGHT = NVG_ALIGN_RIGHT | NVG_ALIGN_TOP;
    public static int ALIGN_BOTTOM_LEFT = NVG_ALIGN_LEFT | NVG_ALIGN_BOTTOM;
    public static int ALIGN_BOTTOM_RIGHT = NVG_ALIGN_RIGHT | NVG_ALIGN_BOTTOM;

    private EJGEngine getEngine(){
        return engine;
    }

    public TextRenderer(String name, String fontPath, EJGEngine e){
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
     * Render text on the Panel without origin-point
     * @param text
     * @param position the panel position
     * @param size
     * @param scale
     * @param align
     * @param rotation
     */
    public void render(String text, Vector2D position, float size, float scale, int align, float rotation) {
        nvgBeginFrame(this.vg, getEngine().getWindow().getWidth(), getEngine().getWindow().getHeight(), 20);

        NVGColor color = NVGColor.create();
        nvgRGBA((byte) 255, (byte) 255, (byte) 255, (byte) 255, color);

        nvgSave(this.vg);

        nvgTranslate(this.vg, position.getX(), position.getY());

        nvgRotate(this.vg, (float) Math.toRadians(rotation));

        nvgFontSize(this.vg, (size * scale));
        nvgFontFace(this.vg, this.name);
        nvgFillColor(this.vg, color);
        nvgTextAlign(this.vg, align);

        nvgText(this.vg, 0, 0, text);

        nvgRestore(this.vg);

        nvgEndFrame(this.vg);
    }

    /**
     * Render text on the Panel with origin-point
     * @param text
     * @param position
     * @param size
     * @param scale
     * @param align
     * @param rotation
     * @param origin the point where the rotation is pointed at.
     */
    public void render(String text, Vector2D position, float size, float scale, int align, float rotation, Vector2D origin) {
        nvgBeginFrame(this.vg, getEngine().getWindow().getWidth(), getEngine().getWindow().getHeight(), 20);

        NVGColor color = NVGColor.create();
        nvgRGBA((byte) 255, (byte) 255, (byte) 255, (byte) 255, color);

        nvgSave(this.vg);

        nvgTranslate(this.vg, origin.getX(), origin.getY());

        nvgRotate(this.vg, (float) Math.toRadians(rotation));

        nvgTranslate(this.vg, position.getX() - origin.getX(), position.getY() - origin.getY());

        nvgFontSize(this.vg, (size * scale));
        nvgFontFace(this.vg, this.name);
        nvgFillColor(this.vg, color);
        nvgTextAlign(this.vg, align);

        nvgText(this.vg, 0, 0, text);

        nvgRestore(this.vg);

        nvgEndFrame(this.vg);
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
