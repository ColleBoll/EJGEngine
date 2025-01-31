package org.collebol.gui.graphics.renderer;

import org.collebol.EJGEngine;
import org.collebol.gui.graphics.Light;
import org.collebol.gui.graphics.Texture;
import org.collebol.math.Vector2D;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class contains everything you need to render Textures on a Panel.
 */
public class TextureRenderer implements Renderer {

    private String name;

    private EJGEngine engine;
    private float width;
    private float height;
    private Map<Integer, Texture> textures = new HashMap<>();
    private List<Light> lights = new ArrayList<>();

    private EJGEngine getEngine() {
        return this.engine;
    }

    public TextureRenderer(String name, float width, float height, EJGEngine e) {
        this.name = name;
        this.engine = e;
        this.width = width;
        this.height = height;
    }

    public TextureRenderer(String name, EJGEngine e) {
        this.name = name;
        this.engine = e;
        this.width = e.getWindow().getTileSize();
        this.height = e.getWindow().getTileSize();
    }

    /**
     * Render a texture from a registered index.
     *
     * @param index    register a texture first!
     * @param position
     * @param scale
     * @param rotation
     * @param origin
     */
    public void render(int index, Vector2D position, float scale, float rotation, Vector2D origin, boolean lighting) {
        if (lighting) {
            GL11.glEnable(GL11.GL_LIGHTING);
        } else {
            GL11.glDisable(GL11.GL_LIGHTING);
        }
        Texture texture = getTexture(index);
        texture.bind();

        float startX = position.getX();
        float startY = position.getY();

        float tWidth = this.width * scale;
        float tHeight = this.height * scale;

        GL11.glPushMatrix();

        GL11.glTranslatef(origin.getX(), origin.getY(), 0);

        GL11.glRotatef(rotation, 0.0f, 0.0f, 1.0f); // Rotatie rond de Z-as

        GL11.glTranslatef(-origin.getX(), -origin.getY(), 0);

        GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS);
        GL11.glEnable(GL11.GL_TEXTURE_2D);

        GL11.glBegin(GL11.GL_QUADS);

        GL11.glColor3f(1.0f, 1.0f, 1.0f);

        GL11.glTexCoord2f(0, 0);
        GL11.glVertex2f(startX, startY);

        GL11.glTexCoord2f(0, 1);
        GL11.glVertex2f(startX, startY + tHeight);

        GL11.glTexCoord2f(1, 1);
        GL11.glVertex2f(startX + tWidth, startY + tHeight);

        GL11.glTexCoord2f(1, 0);
        GL11.glVertex2f(startX + tWidth, startY);

        GL11.glEnd();

        GL11.glPopAttrib();

        GL11.glPopMatrix();
    }

    public void applyLight(int index, Light light, float scale, float[] ambientColor) {
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_LIGHT0 + index);

        float lightX = light.getPosition().getX();
        float lightY = light.getPosition().getY();

        FloatBuffer lightPos = BufferUtils.createFloatBuffer(4);
        lightPos.put(new float[]{lightX, lightY, light.getRadius() * scale, 1.0f});
        lightPos.flip();
        GL11.glLightfv(GL11.GL_LIGHT0 + index, GL11.GL_POSITION, lightPos);

        FloatBuffer lightColor = BufferUtils.createFloatBuffer(4);
        lightColor.put(light.getColor());
        lightColor.flip();
        GL11.glLightfv(GL11.GL_LIGHT0 + index, GL11.GL_DIFFUSE, lightColor);

        FloatBuffer ambientCl = BufferUtils.createFloatBuffer(4);
        ambientCl.put(ambientColor);
        ambientCl.flip();
        GL11.glLightfv(GL11.GL_LIGHT0 + index, GL11.GL_AMBIENT, ambientCl);
    }

    public void registerTexture(Texture texture) {
        this.textures.put(texture.getIndex(), texture);
    }

    public Texture getTexture(int index) {
        if (this.textures.get(index) == null)
            throw new RuntimeException("Invalid Index of textures: " + index + ". Register Texture before using!");
        return this.textures.get(index);
    }

    public String getName() {
        return name;
    }
}
