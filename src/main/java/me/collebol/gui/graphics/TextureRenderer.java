package me.collebol.gui.graphics;

import me.collebol.EJGEngine;
import me.collebol.utils.PanelLocation;
import org.lwjgl.opengl.GL11;

public class TextureRenderer {

    private EJGEngine engine;
    private float width;
    private float height;

    private EJGEngine getEngine(){
        return this.engine;
    }

    public TextureRenderer(float width, float height, EJGEngine e){
        this.engine = e;
        this.width = width;
        this.height = height;
    }

    public TextureRenderer(EJGEngine e){
        this.engine = e;
        this.width = e.getWindow().getTileSize();
        this.height = e.getWindow().getTileSize();
    }

    public void render(Texture texture, PanelLocation loc){
        texture.bind();

        float startX = loc.x;
        float startY = loc.y;

        GL11.glEnable(GL11.GL_TEXTURE_2D);

        GL11.glBegin(GL11.GL_QUADS);

        GL11.glTexCoord2f(0, 0);
        GL11.glVertex2f(startX, startY);

        GL11.glTexCoord2f(0, 1);
        GL11.glVertex2f(startX, startY + this.height);

        GL11.glTexCoord2f(1, 1);
        GL11.glVertex2f(startX + this.width, startY + this.height);

        GL11.glTexCoord2f(1, 0);
        GL11.glVertex2f(startX + this.width, startY);

        GL11.glEnd();
    }
}
