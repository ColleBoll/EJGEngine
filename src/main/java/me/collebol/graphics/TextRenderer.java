package me.collebol.graphics;

import me.collebol.utils.FileUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.stb.STBTTBakedChar;
import org.lwjgl.stb.STBTTFontinfo;
import org.lwjgl.stb.STBTruetype;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.stb.STBTruetype.*;
import static org.lwjgl.system.MemoryStack.stackMalloc;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.*;

public class TextRenderer {
    private static final int BITMAP_W = 512; // Breedte van de font texture
    private static final int BITMAP_H = 512; // Hoogte van de font texture

    private final int fontTextureId;
    private final STBTTBakedChar.Buffer charData;
    private final float scale;

    public TextRenderer(String fontFilePath, int fontSize) {
        // Laad het font-bestand in
        ByteBuffer fontBuffer = FileUtils.loadFileToByteBuffer(fontFilePath);

        // Buffer voor character data
        charData = STBTTBakedChar.malloc(96); // Voor ASCII-karakters 32-126

        // Maak een font texture
        fontTextureId = createFontTexture(fontBuffer, fontSize);
        scale = stbtt_ScaleForPixelHeight(STBTTFontinfo.create(), fontSize);
    }

    private int createFontTexture(ByteBuffer fontBuffer, int fontSize) {
        // Maak een bitmapbuffer voor het font
        ByteBuffer bitmap = memAlloc(BITMAP_W * BITMAP_H);

        // Bak het font in de bitmap
        stbtt_BakeFontBitmap(fontBuffer, fontSize, bitmap, BITMAP_W, BITMAP_H, 32, charData);

        // Maak een OpenGL-texture
        int textureId = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, textureId);

        // Upload de bitmap naar OpenGL
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RED, BITMAP_W, BITMAP_H, 0, GL_RED, GL_UNSIGNED_BYTE, bitmap);

        // Stel texture parameters in
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);

        memFree(bitmap);
        return textureId;
    }

    public void renderText(String text, float x, float y) {
        glBindTexture(GL_TEXTURE_2D, fontTextureId);

        try (var stack = stackPush()) {
            FloatBuffer xPos = stack.floats(x); // Startpositie
            for (int i = 0; i < text.length(); i++) {
                char c = text.charAt(i);
                if (c < 32 || c > 126) continue; // Overslaan van niet-ASCII tekens

                // Haal de quad gegevens op voor het karakter
                STBTTBakedChar charInfo = charData.get(c - 32);

                // Bereken de coördinaten voor de quad
                float x0 = xPos.get(0) + charInfo.xoff();
                float y0 = y - charInfo.yoff();
                float x1 = x0 + charInfo.xadvance();
                float y1 = y0 + (charInfo.y1() - charInfo.y0());

                // Bereken de textuurcoördinaten
                float s0 = charInfo.x0() / (float) BITMAP_W;
                float t0 = charInfo.y0() / (float) BITMAP_H;
                float s1 = charInfo.x1() / (float) BITMAP_W;
                float t1 = charInfo.y1() / (float) BITMAP_H;

                // Render de quad voor het karakter
                renderQuad(x0, y0, x1, y1, s0, t0, s1, t1);

                // Update de cursorpositie
                xPos.put(0, xPos.get(0) + charInfo.xadvance());
            }
        }
        glBindTexture(GL_TEXTURE_2D, 0);
    }

    private void renderQuad(float x0, float y0, float x1, float y1, float s0, float t0, float s1, float t1) {
        glBegin(GL_QUADS);

        glTexCoord2f(s0, t0); glVertex2f(x0, y0); // Linksboven
        glTexCoord2f(s1, t0); glVertex2f(x1, y0); // Rechtsboven
        glTexCoord2f(s1, t1); glVertex2f(x1, y1); // Rechtsonder
        glTexCoord2f(s0, t1); glVertex2f(x0, y1); // Linksonder

        glEnd();
    }

    public void cleanup() {
        glDeleteTextures(fontTextureId);
        charData.free();
    }
}
