package me.collebol.graphics;

import me.collebol.EJGEngine;
import me.collebol.utils.PanelLocation;
import org.lwjgl.nanovg.NanoVG;
import org.lwjgl.nanovg.NVGColor;
import org.lwjgl.system.MemoryUtil;

import static org.lwjgl.nanovg.NanoVG.*;
import static org.lwjgl.nanovg.NanoVGGL3.*;

public class TextRenderer {
    private long vg;
    private EJGEngine engine;
    private EJGEngine getEngine(){
        return engine;
    }

    public TextRenderer(EJGEngine i){
        this.engine = i;
    }

    public void setup() {
        vg = nvgCreate(NVG_ANTIALIAS | NVG_STENCIL_STROKES);
        if (vg == MemoryUtil.NULL) {
            throw new RuntimeException("Could not initialize NanoVG.");
        }

        int font = nvgCreateFont(vg, "default", "src/main/resources/font.ttf");
        if (font == -1) {
            throw new RuntimeException("Could not add font.");
        }
    }

    public void render(String text, PanelLocation loc, float size) {
        nvgBeginFrame(vg, getEngine().WINDOW_WIDTH, getEngine().WINDOW_HEIGHT, 20);

        NVGColor color = NVGColor.create();
        nvgRGBA((byte) 255, (byte) 255, (byte) 255, (byte) 255, color);

        nvgFontSize(vg, size);
        nvgFontFace(vg, "default");
        nvgFillColor(vg, color);
        nvgTextAlign(vg, NVG_ALIGN_CENTER);
        nvgText(vg, loc.x, loc.y, text);

        nvgEndFrame(vg);
    }

    public void cleanup() {
        nvgDelete(vg);
    }
}
