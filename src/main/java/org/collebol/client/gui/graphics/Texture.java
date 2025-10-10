package org.collebol.client.gui.graphics;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/**
 * The Texture class represents a texture in the EJGEngine.
 * It is responsible for loading a texture file ({@code .png}) and creating a {@code OpenGL} texture from it.
 * <p>Usage:</p>
 * <blockquote><pre>
 *     Texture texture = new Texture(
 *         "path/to/texture.png", //from resources
 *         0 //id = unique texture number/id
 *     );
 * </pre></blockquote>
 *
 * @author ColleBol - <a href="mailto:contact@collebol.org">contact@collebol.org</a>
 * @since 1.0-dev
 */
public class Texture {

    private final int id;
    private final int textureObject;
    private final int width;
    private final int height;

    /**
     * This method you can construct your Texture object.
     *
     * @param filePath path to your {@code .png} file.
     * @param id    unique texture number/ID.
     */
    public Texture(String filePath, int id) {
        this.id = id;
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
            throw new RuntimeException("Something went wrong making texture with id " + this.id + ", see:" + e);
        }
    }

    public void bind() {
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.textureObject);
    }

    public int getId() {
        return id;
    }
}
