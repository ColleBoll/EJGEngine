package me.collebol.utils;

import org.lwjgl.opengl.GL11;
import org.lwjgl.stb.STBTTAlignedQuad;
import org.lwjgl.stb.STBTTBakedChar;
import org.lwjgl.stb.STBTruetype;
import org.lwjgl.system.MemoryStack;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import static org.lwjgl.stb.STBTruetype.stbtt_BakeFontBitmap;
import static org.lwjgl.stb.STBTruetype.stbtt_GetPackedQuad;
import static org.lwjgl.system.MemoryUtil.memAlloc;
import static org.lwjgl.system.MemoryUtil.memFree;

public class TextRenderer {

    private int fontTextureID;
    private STBTTBakedChar.Buffer charData;

    public TextRenderer(String fontPath) {
        loadFont(fontPath);
    }

    private void loadFont(String fontPath) {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            ByteBuffer ttf = ioResourceToByteBuffer(fontPath, 512 * 1024);

            charData = STBTTBakedChar.malloc(96);
            ByteBuffer bitmap = memAlloc(512 * 512);

            stbtt_BakeFontBitmap(ttf, 32, bitmap, 512, 512, 32, charData);

            fontTextureID = GL11.glGenTextures();
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, fontTextureID);
            GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_ALPHA, 512, 512, 0, GL11.GL_ALPHA, GL11.GL_UNSIGNED_BYTE, bitmap);
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);

            memFree(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void drawText(String text, float x, float y) {
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, fontTextureID);

        try (MemoryStack stack = MemoryStack.stackPush()) {
            FloatBuffer xBuffer = stack.floats(x);
            FloatBuffer yBuffer = stack.floats(y);
            STBTTAlignedQuad quad = STBTTAlignedQuad.mallocStack(stack);

            GL11.glBegin(GL11.GL_QUADS);
            for (int i = 0; i < text.length(); i++) {
                char c = text.charAt(i);
                stbtt_GetPackedQuad(charData, 512, 512, c - 32, xBuffer, yBuffer, quad, 1);

                GL11.glTexCoord2f(quad.s0(), quad.t0());
                GL11.glVertex2f(quad.x0(), quad.y0());

                GL11.glTexCoord2f(quad.s1(), quad.t0());
                GL11.glVertex2f(quad.x1(), quad.y0());

                GL11.glTexCoord2f(quad.s1(), quad.t1());
                GL11.glVertex2f(quad.x1(), quad.y1());

                GL11.glTexCoord2f(quad.s0(), quad.t1());
                GL11.glVertex2f(quad.x0(), quad.y1());
            }
            GL11.glEnd();
        }
    }

    private static ByteBuffer ioResourceToByteBuffer(String resource, int bufferSize) throws Exception {
        ByteBuffer buffer;

        try (MemoryStack stack = MemoryStack.stackPush()) {
            ByteBuffer resourceBuffer = ByteBuffer.allocate(bufferSize);
            buffer = ByteBuffer.allocate(bufferSize);
            buffer.put(resourceBuffer);
            buffer.flip();
        }

        return buffer;
    }
}