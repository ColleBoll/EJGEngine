package me.collebol.gui.graphics;

import me.collebol.EJGEngine;
import me.collebol.math.Vector2D;
import org.lwjgl.nanovg.NVGColor;
import org.lwjgl.nanovg.NanoVGGL2;
import org.lwjgl.system.MemoryUtil;

import static org.lwjgl.nanovg.NanoVG.*;

public class TextRenderer {
    private long vg;
    private EJGEngine engine;
    private String name;
    private String fontPath;

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

    public void render(String text, Vector2D position, float size) {
        nvgBeginFrame(this.vg, getEngine().getWindow().getWidth(), getEngine().getWindow().getHeight(), 20);

        NVGColor color = NVGColor.create();
        nvgRGBA((byte) 255, (byte) 255, (byte) 255, (byte) 255, color);

        nvgFontSize(this.vg, size);
        nvgFontFace(this.vg, this.name);
        nvgFillColor(this.vg, color);
        nvgTextAlign(this.vg, NVG_ALIGN_CENTER);
        nvgText(this.vg, position.getX(), position.getY(), text);

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
