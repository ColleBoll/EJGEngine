package me.collebol.gui.graphics;

import me.collebol.EJGEngine;
import me.collebol.game.GameObject;
import me.collebol.math.Vector2D;
import me.collebol.utils.GameLocation;
import org.lwjgl.opengl.GL11;

import java.util.List;

import static org.lwjgl.nanovg.NanoVG.NVG_ALIGN_LEFT;
import static org.lwjgl.nanovg.NanoVG.NVG_ALIGN_TOP;

/**
 * Render objects within the canvas of the camera. Change the position of the camera and the view will change.
 */
public class CameraRenderer {

    private EJGEngine engine;

    public CameraRenderer(EJGEngine e){
        this.engine = e;
    }

    /**
     * Render GameObjects relative to the Camera.
     * @param gameObjects
     */
    public void renderObjects(List<GameObject> gameObjects){
        Camera camera = this.engine.getWindow().getCurrentPanel().getCamera();
        for(GameObject g : gameObjects){
            float x = (float) (((g.getGameLocation().x * (this.engine.getWindow().getTileSize() * camera.getZoom())) - camera.getPosition().getX()) + camera.getOrigin().getX());
            float y = (float) (((g.getGameLocation().y * (this.engine.getWindow().getTileSize() * camera.getZoom())) - camera.getPosition().getY()) + camera.getOrigin().getY());
            Vector2D v = new Vector2D(x, y);
            this.engine.getTextureRenderer().render(g.getTexture(), v, camera.getZoom(), camera.getRotation(), camera.getOrigin());
        }
    }

    /**
     * Render a GameObject relative to the Camera.
     * @param gameObject
     */
    public void renderObject(GameObject gameObject){
        Camera camera = this.engine.getWindow().getCurrentPanel().getCamera();
        float x = (float) (((gameObject.getGameLocation().x * (this.engine.getWindow().getTileSize() * camera.getZoom())) - camera.getPosition().getX()) + camera.getOrigin().getX());
        float y = (float) (((gameObject.getGameLocation().y * (this.engine.getWindow().getTileSize() * camera.getZoom())) - camera.getPosition().getY()) + camera.getOrigin().getY());
        Vector2D v = new Vector2D(x, y);
        this.engine.getTextureRenderer().render(gameObject.getTexture(), v, camera.getZoom(), camera.getRotation(), camera.getOrigin());
    }

    /**
     * Render text relative to the camera.
     * @param text
     * @param location
     * @param size
     * @param font
     * @param align
     */
    public void renderText(String text, GameLocation location, float size, String font, int align){
        Camera camera = this.engine.getWindow().getCurrentPanel().getCamera();
        float x = (float) (((location.x * (this.engine.getWindow().getTileSize() * camera.getZoom())) - camera.getPosition().getX()) + camera.getOrigin().getX());
        float y = (float) (((location.y * (this.engine.getWindow().getTileSize() * camera.getZoom())) - camera.getPosition().getY()) + camera.getOrigin().getY());
        Vector2D v = new Vector2D(x, y);

        this.engine.getTextRenderer(font).render(text, v, size, camera.getZoom(), align, camera.getRotation(), camera.getOrigin());
    }

    public void showOriginPoint(){
        Camera camera = this.engine.getWindow().getCurrentPanel().getCamera();
        Vector2D origin = camera.getOrigin();

        GL11.glColor3f(1.0f, 0.0f, 0.0f);
        GL11.glPointSize(5.0f);

        GL11.glBegin(GL11.GL_POINTS);
        GL11.glVertex2f((float) origin.getX(), (float) origin.getY());
        GL11.glEnd();

        GL11.glBegin(GL11.GL_LINE_LOOP);
        int numSegments = 100;
        float radius = 7.0f;
        for (int i = 0; i < numSegments; i++) {
            double angle = 2 * Math.PI * i / numSegments;
            float x = (float) (origin.getX() + radius * Math.cos(angle));
            float y = (float) (origin.getY() + radius * Math.sin(angle));
            GL11.glVertex2f(x, y);
        }
        GL11.glEnd();
    }

    public void showCoordinates(){
        Camera camera = this.engine.getWindow().getCurrentPanel().getCamera();

        GameLocation pointerLoc = camera.getGameLocation();
        GameLocation mouseLoc = this.engine.getWindow().getMouseHandler().getGameLocation();

        Vector2D mousePos = this.engine.getWindow().getMouseHandler().getPosition();

        this.engine.getTextRenderer("default").render("Mouse GameLocation [ X: " + mouseLoc.x + " / Y: " + mouseLoc.y + "]",
                new Vector2D(10f, 5f),
                13,
                1,
                NVG_ALIGN_LEFT | NVG_ALIGN_TOP,
                0);
        this.engine.getTextRenderer("default").render("Camera origin GameLocation [ X: " + pointerLoc.x + " / Y: " + pointerLoc.y + "]",
                new Vector2D(10f, 20f),
                13,
                1,
                NVG_ALIGN_LEFT | NVG_ALIGN_TOP,
                0);
        this.engine.getTextRenderer("default").render("Mouse Panel position [ X: " + mousePos.getX() + " / Y: " + mousePos.getY() + "]",
                new Vector2D(10f, 50f),
                13,
                1,
                NVG_ALIGN_LEFT | NVG_ALIGN_TOP,
                0);
        this.engine.getTextRenderer("default").render("Camera origin position [ X: " + camera.getOrigin().getX() + " / Y: " + camera.getOrigin().getY() + "]",
                new Vector2D(10f, 65f),
                13,
                1,
                NVG_ALIGN_LEFT | NVG_ALIGN_TOP,
                0);
        this.engine.getTextRenderer("default").render("Camera zoom (scale): " + Math.floor(camera.getZoom()),
                new Vector2D(10f, 95f),
                13,
                1,
                NVG_ALIGN_LEFT | NVG_ALIGN_TOP,
                0);

        this.engine.getTextRenderer("default").render(mouseLoc.x + " / " + mouseLoc.y,
                new Vector2D(mousePos.getX(), mousePos.getY() - 15),
                13,
                1,
                NVG_ALIGN_LEFT | NVG_ALIGN_TOP,
                0);
    }

    public void showGridLines() {
        Camera camera = this.engine.getWindow().getCurrentPanel().getCamera();

        float tileSize = this.engine.getWindow().getTileSize() * camera.getZoom();

        float offsetX = camera.getPosition().getX();
        float offsetY = camera.getPosition().getY();
        float originX = camera.getOrigin().getX();
        float originY = camera.getOrigin().getY();

        float screenWidth = this.engine.getWindow().getWidth();
        float screenHeight = this.engine.getWindow().getHeight();

        float startX = -(offsetX % tileSize) + (originX % tileSize);
        float startY = -(offsetY % tileSize) + (originY % tileSize);

        if (startX > 0) {
            startX -= tileSize;
        }
        if (startY > 0) {
            startY -= tileSize;
        }

        GL11.glPushMatrix();

        GL11.glTranslatef(camera.getOrigin().getX(), camera.getOrigin().getY(), 0);

        GL11.glRotatef(camera.getRotation(), 0.0f, 0.0f, 1.0f); // Rotatie rond de Z-as

        GL11.glTranslatef(-camera.getOrigin().getX(), -camera.getOrigin().getY(), 0);

        GL11.glColor4f(1.0f, 1.0f, 0.0f, 1.0f);

        GL11.glBegin(GL11.GL_LINES);

        for (float x = startX; x <= screenWidth; x += tileSize) {
            GL11.glVertex2f(x, 0);
            GL11.glVertex2f(x, screenHeight);
        }

        for (float y = startY; y <= screenHeight; y += tileSize) {
            GL11.glVertex2f(0, y);
            GL11.glVertex2f(screenWidth, y);
        }

        GL11.glEnd();

        GL11.glPopMatrix();
    }
}
