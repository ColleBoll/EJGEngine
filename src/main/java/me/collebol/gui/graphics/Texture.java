package me.collebol.gui.graphics;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;

public class Texture {

    private int INDEX;
    private int TEXTURE_OBJECT;
    private int WIDTH;
    private int HEIGHT;

    public Texture(String filePath, int index){
        this.INDEX = index;
        BufferedImage bufferedImage;
        try{
            bufferedImage = ImageIO.read(getClass().getResourceAsStream(filePath));
            WIDTH = bufferedImage.getWidth();
            HEIGHT = bufferedImage.getHeight();

            int[] pixels_raw = new int[WIDTH * HEIGHT * 4];
            pixels_raw = bufferedImage.getRGB(0, 0, WIDTH, HEIGHT, null, 0, WIDTH);

            ByteBuffer pixels = BufferUtils.createByteBuffer(WIDTH * HEIGHT * 4);
            for (int i = 0; i < WIDTH; i++) {
                for (int j = 0; j < HEIGHT; j++) {
                    int pixel = pixels_raw[i * WIDTH + j];
                    pixels.put((byte) ((pixel >> 16) & 0xFF));//red
                    pixels.put((byte) ((pixel >> 8) & 0xFF));//green
                    pixels.put((byte) (pixel & 0xFF));//blue
                    pixels.put((byte) ((pixel >> 24) & 0xFF));//alpha
                }
            }
            pixels.flip();
            TEXTURE_OBJECT = GL11.glGenTextures();

            GL11.glBindTexture(GL11.GL_TEXTURE_2D, TEXTURE_OBJECT);

            GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
            GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);

            GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, WIDTH, HEIGHT, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, pixels);
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    public void bind(){
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, TEXTURE_OBJECT);
    }
}
