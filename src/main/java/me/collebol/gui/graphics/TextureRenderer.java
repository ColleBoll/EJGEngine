package me.collebol.gui.graphics;

import me.collebol.EJGEngine;
import me.collebol.math.Vector2D;
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
        this.width = e.getWindow().getTileSize() * this.getEngine().getWindow().getCurrentPanel().getCamera().getZoom();
        this.height = e.getWindow().getTileSize() * this.getEngine().getWindow().getCurrentPanel().getCamera().getZoom();;
    }

    public void render(Texture texture, Vector2D position){
        texture.bind();

        float startX = position.getX() + this.getEngine().getWindow().getCurrentPanel().getCamera().getPosition().getX();
        float startY = position.getY() + this.getEngine().getWindow().getCurrentPanel().getCamera().getPosition().getY();;

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
