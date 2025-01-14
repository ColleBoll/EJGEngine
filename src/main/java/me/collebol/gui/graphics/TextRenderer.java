package me.collebol.gui.graphics;

import me.collebol.EJGEngine;
import me.collebol.utils.PanelLocation;
import org.lwjgl.nanovg.NVGColor;
import org.lwjgl.nanovg.NanoVGGL2;
import org.lwjgl.system.MemoryUtil;

import static org.lwjgl.nanovg.NanoVG.*;

public class TextRenderer {
    private long vg;
    private EJGEngine engine;
    private EJGEngine getEngine(){
        return engine;
    }

    public TextRenderer(EJGEngine e){
        this.engine = e;
    }

    public void setup() {
        this.vg = NanoVGGL2.nvgCreate(NanoVGGL2.NVG_ANTIALIAS | NanoVGGL2.NVG_STENCIL_STROKES);
        if (this.vg == MemoryUtil.NULL) {
            throw new RuntimeException("Could not initialize NanoVG.");
        }

        int font = nvgCreateFont(this.vg, "default", "src/main/resources/font.ttf");
        if (font == -1) {
            throw new RuntimeException("Could not add font.");
        }
    }

    public void render(String text, PanelLocation loc, float size) {
        nvgBeginFrame(this.vg, getEngine().getWindow().getWidth(), getEngine().getWindow().getHeight(), 20);

        NVGColor color = NVGColor.create();
        nvgRGBA((byte) 255, (byte) 255, (byte) 255, (byte) 255, color);

        nvgFontSize(this.vg, size);
        nvgFontFace(this.vg, "default");
        nvgFillColor(this.vg, color);
        nvgTextAlign(this.vg, NVG_ALIGN_CENTER);
        nvgText(this.vg, loc.x, loc.y, text);

        nvgEndFrame(this.vg);
    }

    public void cleanup() {
        NanoVGGL2.nvgDelete(vg);
    }
}
