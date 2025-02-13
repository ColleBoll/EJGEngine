package org.collebol.engine.gui.graphics.renderer;

import org.collebol.engine.EJGEngine;
import org.collebol.engine.gui.graphics.Light;
import org.collebol.engine.gui.graphics.Texture;
import org.collebol.engine.math.Vector2D;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class contains everything you need to render Textures on a Panel.
 *
 * @author ColleBol - <a href="mailto:contact@collebol.org">contact@collebol.org</a>
 * @since 1.0-dev
 */
public class TextureRenderer implements Renderer {

    private String name;

    private float width;
    private float height;
    private Map<Integer, Texture> textures = new HashMap<>();
    private List<Light> lights = new ArrayList<>();

    public TextureRenderer(String name, float width, float height) {
        this.name = name;
        this.width = width;
        this.height = height;
    }

    public TextureRenderer(String name, EJGEngine e) {
        this.name = name;
        this.width = e.getWindow().getTileSize();
        this.height = e.getWindow().getTileSize();
    }

    /**
     * Render a texture from a registered id.
     *
     * @param id    register a texture first!
     * @param position
     * @param scale
     * @param rotation
     * @param origin
     */
    public void render(int id, Vector2D position, float scale, float rotation, Vector2D origin, boolean lighting) {
        if (lighting) {
            GL11.glEnable(GL11.GL_LIGHTING);
        } else {
            GL11.glDisable(GL11.GL_LIGHTING);
        }
        Texture texture = getTexture(id);
        texture.bind();

        float startX = position.getX();
        float startY = position.getY();

        float tWidth = this.width * scale;
        float tHeight = this.height * scale;

        GL11.glPushMatrix();

        GL11.glTranslatef(origin.getX(), origin.getY(), 0);

        GL11.glRotatef(rotation, 0.0f, 0.0f, 1.0f);

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

    /**
     * Renders a batch of items using a specified texture.
     * This method iterates through each item in the batch, applies transformations
     * (translation, rotation, scaling), and renders the item as a textured quad.
     *
     * @param batch    the batch of items to be rendered
     * @param lighting whether to enable lighting during rendering
     */
    public void renderBatch(Batch batch, boolean lighting) {
        if (lighting) {
            GL11.glEnable(GL11.GL_LIGHTING);
        } else {
            GL11.glDisable(GL11.GL_LIGHTING);
        }
        Texture texture = getTexture(batch.getTexture());
        texture.bind();

        GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glColor3f(1.0f, 1.0f, 1.0f);

        GL11.glBegin(GL11.GL_QUADS);

        for (Batch.BatchItem item : batch.getItems()) {
            float startX = item.position.getX();
            float startY = item.position.getY();
            float tWidth = this.width * item.scale;
            float tHeight = this.height * item.scale;

            double rad = Math.toRadians(item.rotation);
            float cos = (float) Math.cos(rad);
            float sin = (float) Math.sin(rad);

            float v0x = startX;
            float v0y = startY;

            float v1x = startX;
            float v1y = startY + tHeight;

            float v2x = startX + tWidth;
            float v2y = startY + tHeight;

            float v3x = startX + tWidth;
            float v3y = startY;

            float rv0x = item.origin.getX() + (v0x - item.origin.getX()) * cos - (v0y - item.origin.getY()) * sin;
            float rv0y = item.origin.getY() + (v0x - item.origin.getX()) * sin + (v0y - item.origin.getY()) * cos;

            float rv1x = item.origin.getX() + (v1x - item.origin.getX()) * cos - (v1y - item.origin.getY()) * sin;
            float rv1y = item.origin.getY() + (v1x - item.origin.getX()) * sin + (v1y - item.origin.getY()) * cos;

            float rv2x = item.origin.getX() + (v2x - item.origin.getX()) * cos - (v2y - item.origin.getY()) * sin;
            float rv2y = item.origin.getY() + (v2x - item.origin.getX()) * sin + (v2y - item.origin.getY()) * cos;

            float rv3x = item.origin.getX() + (v3x - item.origin.getX()) * cos - (v3y - item.origin.getY()) * sin;
            float rv3y = item.origin.getY() + (v3x - item.origin.getX()) * sin + (v3y - item.origin.getY()) * cos;

            GL11.glTexCoord2f(0, 0);
            GL11.glVertex2f(rv0x, rv0y);

            GL11.glTexCoord2f(0, 1);
            GL11.glVertex2f(rv1x, rv1y);

            GL11.glTexCoord2f(1, 1);
            GL11.glVertex2f(rv2x, rv2y);

            GL11.glTexCoord2f(1, 0);
            GL11.glVertex2f(rv3x, rv3y);
        }

        GL11.glEnd();
        GL11.glPopAttrib();
    }

    public void applyLight(int id, Light light, float scale, float[] ambientColor) {
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_LIGHT0 + id);

        float lightX = light.getPosition().getX();
        float lightY = light.getPosition().getY();

        FloatBuffer lightPos = BufferUtils.createFloatBuffer(4);
        lightPos.put(new float[]{lightX, lightY, light.getRadius() * scale, 1.0f});
        lightPos.flip();
        GL11.glLightfv(GL11.GL_LIGHT0 + id, GL11.GL_POSITION, lightPos);

        FloatBuffer lightColor = BufferUtils.createFloatBuffer(4);
        lightColor.put(light.getColor());
        lightColor.flip();
        GL11.glLightfv(GL11.GL_LIGHT0 + id, GL11.GL_DIFFUSE, lightColor);

        FloatBuffer ambientCl = BufferUtils.createFloatBuffer(4);
        ambientCl.put(ambientColor);
        ambientCl.flip();
        GL11.glLightfv(GL11.GL_LIGHT0 + id, GL11.GL_AMBIENT, ambientCl);
    }

    public void registerTexture(Texture texture) {
        this.textures.put(texture.getId(), texture);
    }

    public Texture getTexture(int id) {
        if (this.textures.get(id) == null)
            throw new RuntimeException("Invalid id of textures: " + id + ". Register Texture before using!");
        return this.textures.get(id);
    }

    public String getName() {
        return name;
    }
}
