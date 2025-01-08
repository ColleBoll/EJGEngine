package me.collebol.graphics.text;

import org.lwjgl.nanovg.NanoVG;
import org.lwjgl.nanovg.NVGColor;
import org.lwjgl.system.MemoryUtil;

import static org.lwjgl.nanovg.NanoVG.*;
import static org.lwjgl.nanovg.NanoVGGL3.*;

public class NanoVGExample {
    private long vg;

    public void init() {
        vg = nvgCreate(NVG_ANTIALIAS | NVG_STENCIL_STROKES);
        if (vg == MemoryUtil.NULL) {
            throw new RuntimeException("Could not initialize NanoVG.");
        }

        int font = nvgCreateFont(vg, "default", "src/test/resources/font.ttf");
        if (font == -1) {
            throw new RuntimeException("Could not add font.");
        }
    }

    public void render() {
        nvgBeginFrame(vg, 800, 600, 1);

        NVGColor color = NVGColor.create();
        nvgRGBA((byte) 255, (byte) 255, (byte) 255, (byte) 255, color);

        nvgFontSize(vg, 48.0f);
        nvgFontFace(vg, "default");
        nvgFillColor(vg, color);
        nvgTextAlign(vg, NanoVG.NVG_ALIGN_LEFT | NanoVG.NVG_ALIGN_TOP);
        nvgText(vg, 100, 100, "Hello, NanoVG!");

        nvgEndFrame(vg);
    }

    public void cleanup() {
        nvgDelete(vg);
    }
}
