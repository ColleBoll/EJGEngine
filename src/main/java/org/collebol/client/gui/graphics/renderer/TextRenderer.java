package org.collebol.client.gui.graphics.renderer;

import org.collebol.client.EJGEngine;
import org.collebol.client.gui.graphics.Text;
import org.lwjgl.nanovg.NVGColor;
import org.lwjgl.nanovg.NanoVGGL2;
import org.lwjgl.system.MemoryUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import static org.lwjgl.nanovg.NanoVG.*;

/**
 * This class contains all the methods to render text on a Panel.
 *
 * @author ColleBol - <a href="mailto:contact@collebol.org">contact@collebol.org</a>
 * @since 1.0-dev
 */
public class TextRenderer extends Renderer {

    private long vg;
    private EJGEngine engine;
    private String name;
    private String fontPath;

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

        int font = nvgCreateFont(this.vg, this.name, extractResourceToTempFile(this.fontPath));
        if (font == -1) {
            throw new RuntimeException("Could not add font.");
        }
    }

    /**
     * Render text on the Panel with origin-point
     *
     * @param text
     */
    public void render(Text text) {
        nvgBeginFrame(this.vg, getEngine().getWindow().getWidth(), getEngine().getWindow().getHeight(), 20);

        NVGColor color = NVGColor.create();
        nvgRGBA((byte) 255, (byte) 255, (byte) 255, (byte) 255, color);

        nvgSave(this.vg);

        nvgTranslate(this.vg, text.getOrigin().getX(), text.getOrigin().getY());

        nvgRotate(this.vg, (float) Math.toRadians(text.getRotation()));

        nvgTranslate(this.vg, text.getPosition().getX() - text.getOrigin().getX(), text.getPosition().getY() - text.getOrigin().getY());

        nvgFontSize(this.vg, (text.getSize() * text.getScale()));
        nvgFontFace(this.vg, this.name);
        nvgFillColor(this.vg, color);
        nvgTextAlign(this.vg, text.getAlign());

        nvgText(this.vg, 0, 0, text.getText());

        nvgRestore(this.vg);

        nvgEndFrame(this.vg);
    }

    private String extractResourceToTempFile(String resourcePath) {
        try (InputStream inputStream = getClass().getResourceAsStream(resourcePath)) {
            if (inputStream == null) {
                throw new IllegalArgumentException("Resource not found: " + resourcePath);
            }

            File tempFile = Files.createTempFile("font", ".ttf").toFile();
            tempFile.deleteOnExit();

            try (FileOutputStream outputStream = new FileOutputStream(tempFile)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            }

            return tempFile.getAbsolutePath();
        } catch (IOException e) {
            throw new RuntimeException("Failed to load resource: " + resourcePath, e);
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
