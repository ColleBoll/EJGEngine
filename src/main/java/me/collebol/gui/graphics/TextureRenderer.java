package me.collebol.gui.graphics;

import org.lwjgl.opengl.GL11;

public class TextureRenderer {

    public void render(Texture texture){
        texture.bind();

        GL11.glBegin(GL11.GL_QUADS);

        GL11.glTexCoord2f(0, 0);
        GL11.glVertex2f(-0.5f, 0.5f);

        GL11.glTexCoord2f(0, 1);
        GL11.glVertex2f(0.5f, 0.5f);

        GL11.glTexCoord2f(1, 1);
        GL11.glVertex2f(0.5f, -0.5f);

        GL11.glTexCoord2f(1, 0);
        GL11.glVertex2f(-0.5f, -0.5f);

        GL11.glEnd();
    }
}
