package me.collebol.gui.graphics;

import me.collebol.EJGEngine;
import me.collebol.utils.PanelLocation;
import org.lwjgl.opengl.GL11;

public class TextureRenderer {

    public void render(Texture texture, PanelLocation loc, EJGEngine engine){
        texture.bind();

        float startX = loc.x;
        float startY = loc.y;
        float width = engine.getWindow().getTileSize();
        float height = engine.getWindow().getTileSize();

        GL11.glBegin(GL11.GL_QUADS);

        GL11.glTexCoord2f(0, 0);
        GL11.glVertex2f(startX, startY);

        GL11.glTexCoord2f(0, 1);
        GL11.glVertex2f(startX, startY + height);

        GL11.glTexCoord2f(1, 1);
        GL11.glVertex2f(startX + width, startY + height);

        GL11.glTexCoord2f(1, 0);
        GL11.glVertex2f(startX + width, startY);

        GL11.glEnd();
    }
}
