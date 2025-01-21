package me.collebol.gui.graphics;

import me.collebol.EJGEngine;
import me.collebol.math.Vector2D;
import org.lwjgl.opengl.GL11;

import java.util.HashMap;

public class TextureRenderer {

    private EJGEngine engine;
    private float width;
    private float height;
    private HashMap<Integer, Texture> textures = new HashMap<>();

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

    public void render(int index, Vector2D position, float scale){
        Texture texture = getTexture(index);
        texture.bind();

        float startX = position.getX();
        float startY = position.getY();

        float tWidth = this.width * scale;
        float tHeight = this.height * scale;

        GL11.glEnable(GL11.GL_TEXTURE_2D);

        GL11.glBegin(GL11.GL_QUADS);

        GL11.glTexCoord2f(0, 0);
        GL11.glVertex2f(startX, startY);

        GL11.glTexCoord2f(0, 1);
        GL11.glVertex2f(startX, startY + tHeight);

        GL11.glTexCoord2f(1, 1);
        GL11.glVertex2f(startX + tWidth, startY + tHeight);

        GL11.glTexCoord2f(1, 0);
        GL11.glVertex2f(startX + tWidth, startY);

        GL11.glEnd();
    }

    public void registerTexture(Texture texture){
        this.textures.put(texture.getIndex(), texture);
    }

    public Texture getTexture(int index){
        if(this.textures.get(index) == null) throw new RuntimeException("Invalid Index of textures: " + index + ". Register Texture before using!");
        return this.textures.get(index);
    }
}
