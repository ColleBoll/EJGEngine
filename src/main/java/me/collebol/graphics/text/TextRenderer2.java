package me.collebol.graphics.text;

import org.lwjgl.stb.STBTTAlignedQuad;
import org.lwjgl.stb.STBTTBakedChar;
import org.lwjgl.system.MemoryStack;

import java.io.IOException;
import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.stb.STBTruetype.stbtt_BakeFontBitmap;
import static org.lwjgl.stb.STBTruetype.stbtt_GetBakedQuad;
import static org.lwjgl.system.MemoryUtil.memAlloc;
import static org.lwjgl.system.MemoryUtil.memFree;

public class TextRenderer2 {

    private static final int BITMAP_W = 227; // Breedte van de bitmap
    private static final int BITMAP_H = 227; // Hoogte van de bitmap

    private final int fontTexture; // OpenGL-textuur-ID
    private final STBTTBakedChar.Buffer charData; // Lettergegevens (ASCII 32-126)

    public TextRenderer2(String fontFile, float fontSize) {
        System.out.println("Creating TextRenderer");
        charData = STBTTBakedChar.malloc(96); // ASCII 32-126
        fontTexture = glGenTextures();

        try (MemoryStack stack = MemoryStack.stackPush()) {
            System.out.println("Creating TextRenderer 2");
            ByteBuffer fontBuffer = loadFont(fontFile);
            System.out.println("Creating TextRenderer 3");

            ByteBuffer bitmap = memAlloc(BITMAP_W * BITMAP_H); // Bitmap voor font
            System.out.println("Creating TextRenderer 4");
            stbtt_BakeFontBitmap(fontBuffer, fontSize, bitmap, BITMAP_W, BITMAP_H, 32, charData);
            System.out.println("Creating TextRenderer 5");

            // OpenGL: Laad bitmap in een tekstuur
            System.out.println("Creating TextRenderer 3");
            glBindTexture(GL_TEXTURE_2D, fontTexture);
            glTexImage2D(GL_TEXTURE_2D, 0, GL_RED, BITMAP_W, BITMAP_H, 0, GL_RED, GL_UNSIGNED_BYTE, bitmap);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);

            memFree(bitmap);
            System.out.println("Creating TextRenderer ");
        } catch (IOException e) {
            throw new RuntimeException("Failed to load font file", e);
        }
    }

    public void renderText(String text, float x, float y) {
        glEnable(GL_TEXTURE_2D);
        glBindTexture(GL_TEXTURE_2D, fontTexture);

        glBegin(GL_QUADS);
        for (char c : text.toCharArray()) {
            if (c < 32 || c > 126) continue; // Sla niet-ASCII over

            STBTTAlignedQuad quad = STBTTAlignedQuad.malloc();
            float[] xpos = {x};
            float[] ypos = {y};

            stbtt_GetBakedQuad(charData, BITMAP_W, BITMAP_H, c - 32, xpos, ypos, quad, true);

            glTexCoord2f(quad.s0(), quad.t0());
            glVertex2f(quad.x0(), quad.y0());

            glTexCoord2f(quad.s1(), quad.t0());
            glVertex2f(quad.x1(), quad.y0());

            glTexCoord2f(quad.s1(), quad.t1());
            glVertex2f(quad.x1(), quad.y1());

            glTexCoord2f(quad.s0(), quad.t1());
            glVertex2f(quad.x0(), quad.y1());

            x = xpos[0]; // Update x-positie
        }
        glEnd();
    }

    private ByteBuffer loadFont(String path) throws IOException {
        try (var stream = TextRenderer2.class.getResourceAsStream(path)) {
            if (stream == null) throw new IOException("Font file not found: " + path);
            return ByteBuffer.wrap(stream.readAllBytes());
        }
    }
}
