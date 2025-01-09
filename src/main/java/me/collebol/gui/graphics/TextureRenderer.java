package me.collebol.gui.graphics;

import me.collebol.utils.PanelLocation;
import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.HashMap;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.glGenerateMipmap;

public class TextureRenderer {

    private HashMap<Integer, Integer> TEXTURES = new HashMap<>();

    public HashMap<Integer, Integer> getTextures() {
        return this.TEXTURES;
    }

    public void loadTexture(String path, int index){
        int TEXTURE;
        try(MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer w = stack.mallocInt(1);
            IntBuffer h = stack.mallocInt(1);
            IntBuffer c = stack.mallocInt(1);
            ByteBuffer img = STBImage.stbi_load(path, w, h, c, 4);
            if(img == null){
                throw new RuntimeException("Failed to load texture: " + path);
            }

            System.out.println("Loaded texture: " + path + " with width: " + w.get(0) + " and height: " + h.get(0));

            TEXTURE = glGenTextures();
            glBindTexture(GL_TEXTURE_2D, TEXTURE);

            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, w.get(), h.get(), 0, GL_RGBA, GL_UNSIGNED_BYTE, img);
            glGenerateMipmap(GL_TEXTURE_2D);

            STBImage.stbi_image_free(img);
            this.TEXTURES.put(index, TEXTURE);
        }catch (RuntimeException e){
            e.getStackTrace();
        }
    }

    public void renderTexture(int index, PanelLocation loc, float width, float height){
        glEnable(GL_TEXTURE_2D);
        glBindTexture(GL_TEXTURE_2D, index);

        glBegin(GL_QUADS);
        glTexCoord2f(0, 0);
        glVertex2f(loc.x, loc.y);
        glTexCoord2f(1, 0);
        glVertex2f(loc.x + width, loc.y);
        glTexCoord2f(1, 1);
        glVertex2f(loc.x + width, loc.y + height);
        glTexCoord2f(0, 1);
        glVertex2f(loc.x, loc.y + height);
        glEnd();

        glDisable(GL_TEXTURE_2D);
    }
}
