package org.collebol.gui.graphics;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class Texture {

    private int index;
    private int textureObject;
    private int width;
    private int height;

    public Texture(String filePath, int index) {
        this.index = index;
        BufferedImage bufferedImage;
        try (InputStream inputStream = getClass().getResourceAsStream(filePath)) {
            if (inputStream == null) {
                throw new IOException("Resource not found: " + filePath);
            }
            bufferedImage = ImageIO.read(inputStream);
            this.width = bufferedImage.getWidth();
            this.height = bufferedImage.getHeight();

            int[] pixels_raw = new int[this.width * this.height * 4];
            pixels_raw = bufferedImage.getRGB(0, 0, this.width, this.height, null, 0, this.width);

            ByteBuffer pixels = BufferUtils.createByteBuffer(this.width * this.height * 4);
            for (int i = 0; i < this.width; i++) {
                for (int j = 0; j < this.height; j++) {
                    int pixel = pixels_raw[i * this.width + j];
                    pixels.put((byte) ((pixel >> 16) & 0xFF));//red
                    pixels.put((byte) ((pixel >> 8) & 0xFF));//green
                    pixels.put((byte) (pixel & 0xFF));//blue
                    pixels.put((byte) ((pixel >> 24) & 0xFF));//alpha
                }
            }
            pixels.flip();
            this.textureObject = GL11.glGenTextures();

            GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.textureObject);

            GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
            GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);

            GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, this.width, this.height, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, pixels);
        } catch (IOException e) {
            throw new RuntimeException("Something went wrong making texture with index " + this.index + ", see:" + e);
        }
    }

    public void bind() {
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.textureObject);
    }

    public int getIndex() {
        return index;
    }
}
