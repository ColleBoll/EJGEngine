package org.collebol.gui.graphics.renderer;

import org.collebol.EJGEngine;
import org.collebol.game.GameObject;
import org.collebol.gui.graphics.Camera;
import org.collebol.gui.graphics.Light;
import org.collebol.math.Vector2D;
import org.collebol.utils.GameLocation;
import org.lwjgl.opengl.GL11;

import java.util.List;

/**
 * Render objects within the canvas of the camera. Change the position of the camera and the view will change.
 *
 * @author ColleBol - <a href="mailto:contact@collebol.org">contact@collebol.org</a>
 * @since < 1.0
 */
public class CameraRenderer implements Renderer {

    private EJGEngine engine;

    public CameraRenderer(EJGEngine e) {
        this.engine = e;
    }

    public static class Builder {
        private EJGEngine engine;

        public Builder engine(EJGEngine e) {
            this.engine = e;
            return this;
        }

    }

    /**
     * Render GameObjects relative to the Camera.
     *
     * @param gameObjects
     */
    public void renderObjects(List<GameObject> gameObjects) {
        Camera camera = this.engine.getWindow().getCurrentPanel().getCamera();
        for (GameObject g : gameObjects) {
            float x = (float) (((g.getGameLocation().getX() * (this.engine.getWindow().getTileSize() * camera.getZoom())) - camera.getPosition().getX()) + camera.getOrigin().getX());
            float y = (float) (((g.getGameLocation().getY() * (this.engine.getWindow().getTileSize() * camera.getZoom())) - camera.getPosition().getY()) + camera.getOrigin().getY());
            Vector2D v = new Vector2D(x, y);
            this.engine.getRenderers().getTextureRenderer("default").render(g.getTexture(), v, camera.getZoom(), camera.getRotation(), camera.getOrigin(), camera.isLighting());
        }
    }

    /**
     * Render a GameObject relative to the Camera.
     *
     * @param gameObject
     */
    public void renderObject(GameObject gameObject) {
        Camera camera = this.engine.getWindow().getCurrentPanel().getCamera();
        float x = (float) (((gameObject.getGameLocation().getX() * (this.engine.getWindow().getTileSize() * camera.getZoom())) - camera.getPosition().getX()) + camera.getOrigin().getX());
        float y = (float) (((gameObject.getGameLocation().getY() * (this.engine.getWindow().getTileSize() * camera.getZoom())) - camera.getPosition().getY()) + camera.getOrigin().getY());
        Vector2D v = new Vector2D(x, y);
        this.engine.getRenderers().getTextureRenderer("default").render(gameObject.getTexture(), v, camera.getZoom(), camera.getRotation(), camera.getOrigin(), camera.isLighting());
    }

    /**
     * Render text relative to the camera.
     *
     * @param textBuilder
     */
    public void renderText(TextBuilder textBuilder) {
        Camera camera = this.engine.getWindow().getCurrentPanel().getCamera();
        float x = (float) (((textBuilder.location.getX() * (this.engine.getWindow().getTileSize() * camera.getZoom())) - camera.getPosition().getX()) + camera.getOrigin().getX());
        float y = (float) (((textBuilder.location.getY() * (this.engine.getWindow().getTileSize() * camera.getZoom())) - camera.getPosition().getY()) + camera.getOrigin().getY());
        Vector2D v = new Vector2D(x, y);
        this.engine.getRenderers().getTextRenderer(textBuilder.font).render(
                new TextRenderer.TextBuilder()
                        .text(textBuilder.text)
                        .position(v)
                        .size(textBuilder.size)
                        .scale(camera.getZoom())
                        .align(textBuilder.align)
                        .rotation(camera.getRotation())
                        .origin(camera.getOrigin())
        );
    }

    /**
     * Render light relative to the camera.
     *
     * @param location the game location of the light
     * @param radius   radius of the light (how big)
     * @param color    the color of the light, example: new float[]{ 1.0f, 1.0f, 1.0f, 1.0f }
     */
    public void renderLight(int index, GameLocation location, float radius, float[] color) {
        Camera camera = this.engine.getWindow().getCurrentPanel().getCamera();
        float x = (float) (((location.getX() * (this.engine.getWindow().getTileSize() * camera.getZoom())) - camera.getPosition().getX()) + camera.getOrigin().getX());
        float y = (float) (((location.getY() * (this.engine.getWindow().getTileSize() * camera.getZoom())) - camera.getPosition().getY()) + camera.getOrigin().getY());
        Light light = new Light(new Light.LightBuilder()
                .intensity(1.0f)
                .color(color)
                .radius(radius)
                .position(new Vector2D(x, y)));
        this.engine.getRenderers().getTextureRenderer("default").applyLight(index, light, camera.getZoom(), camera.getAmbientLight());
    }

    public static class TextBuilder {
        private String text = "";
        private GameLocation location = new GameLocation(0, 0);
        private float size = 10;
        private String font = "default";
        private int align = TextRenderer.ALIGN_TOP_LEFT;

        public TextBuilder text(String text) {
            this.text = text;
            return this;
        }

        public TextBuilder location(GameLocation location) {
            this.location = location;
            return this;
        }

        public TextBuilder size(float size) {
            this.size = size;
            return this;
        }

        public TextBuilder font(String font) {
            this.font = font;
            return this;
        }

        public TextBuilder align(int align) {
            this.align = align;
            return this;
        }
    }

    /**
     * Renders the origin point of the camera on the screen.
     * <p>
     * This method uses OpenGL to draw a red point at the camera's origin and a circle around it.
     */
    public void showOriginPoint() {
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

    /**
     * Displays various coordinates and camera information on the screen.
     * <p>
     * This method retrieves the current camera and mouse positions, and renders text on the screen
     * showing the mouse game location, camera-origin game location, mouse panel position, camera-origin panel position,
     * camera zoom level, and camera rotation.
     */
    public void showCoordinates() {
        Camera camera = this.engine.getWindow().getCurrentPanel().getCamera();

        GameLocation pointerLoc = camera.getGameLocation();
        GameLocation mouseLoc = this.engine.getWindow().getMouseHandler().getGameLocation();

        Vector2D mousePos = this.engine.getWindow().getMouseHandler().getPosition();

        this.engine.getRenderers().getTextRenderer("default").render(new TextRenderer.TextBuilder()
                .text("Mouse GameLocation [ X: " + mouseLoc.getX() + " / Y: " + mouseLoc.getY() + "]")
                .position(new Vector2D(10f, 5f))
                .size(13)
                .scale(1)
                .align(TextRenderer.ALIGN_TOP_LEFT)
                .rotation(0)
        );

        this.engine.getRenderers().getTextRenderer("default").render(new TextRenderer.TextBuilder()
                .text("Camera-origin GameLocation [ X: " + pointerLoc.getX() + " / Y: " + pointerLoc.getY() + "]")
                .position(new Vector2D(10f, 20f))
                .size(13)
                .scale(1)
                .align(TextRenderer.ALIGN_TOP_LEFT)
                .rotation(0)
        );

        this.engine.getRenderers().getTextRenderer("default").render(new TextRenderer.TextBuilder()
                .text("Mouse Panel position [ X: " + mousePos.getX() + " / Y: " + mousePos.getY() + "]")
                .position(new Vector2D(10f, 50f))
                .size(13)
                .scale(1)
                .align(TextRenderer.ALIGN_TOP_LEFT)
                .rotation(0)
        );

        this.engine.getRenderers().getTextRenderer("default").render(new TextRenderer.TextBuilder()
                .text("Camera-origin Panel position [ X: " + camera.getOrigin().getX() + " / Y: " + camera.getOrigin().getY() + "]")
                .position(new Vector2D(10f, 65f))
                .size(13)
                .scale(1)
                .align(TextRenderer.ALIGN_TOP_LEFT)
                .rotation(0)
        );

        this.engine.getRenderers().getTextRenderer("default").render(new TextRenderer.TextBuilder()
                .text("Camera zoom (scale): " + Math.round(camera.getZoom() * 100.0f) / 100.0f)
                .position(new Vector2D(10f, 95f))
                .size(13)
                .scale(1)
                .align(TextRenderer.ALIGN_TOP_LEFT)
                .rotation(0)
        );

        this.engine.getRenderers().getTextRenderer("default").render(new TextRenderer.TextBuilder()
                .text("Camera rotation: " + Math.round(camera.getRotation() * 100.0f) / 100.0f)
                .position(new Vector2D(10f, 110f))
                .size(13)
                .scale(1)
                .align(TextRenderer.ALIGN_TOP_LEFT)
                .rotation(0)
        );

        this.engine.getRenderers().getTextRenderer("default").render(new TextRenderer.TextBuilder()
                .text(mouseLoc.getX() + " / " + mouseLoc.getY())
                .position(new Vector2D(mousePos.getX(), mousePos.getY() - 15))
                .size(13)
                .scale(1)
                .align(TextRenderer.ALIGN_TOP_LEFT)
                .rotation(0)
        );
    }

    /**
     * Renders grid lines on the screen based on the camera's position and zoom level.
     * <p>
     * This method calculates the starting positions for the grid lines based on the camera's offset and origin.
     * It then uses OpenGL to draw yellow grid lines across the screen and blue diagonal lines forming a square.
     */
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

        GL11.glDisable(GL11.GL_LIGHTING);

        GL11.glTranslatef(camera.getOrigin().getX(), camera.getOrigin().getY(), 0);

        GL11.glRotatef(camera.getRotation(), 0.0f, 0.0f, 1.0f);

        GL11.glTranslatef(-camera.getOrigin().getX(), -camera.getOrigin().getY(), 0);

        //Yellow grid
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

        //Blue lines
        GL11.glColor4f(0.0f, 0.0f, 1.0f, 1.0f);

        float squareSize = Math.min(screenWidth, screenHeight);

        float halfSquare = squareSize / 2;
        float squareLeft = originX - halfSquare;
        float squareRight = originX + halfSquare;
        float squareTop = originY - halfSquare;
        float squareBottom = originY + halfSquare;

        GL11.glBegin(GL11.GL_LINES);

        GL11.glVertex2f(squareLeft, squareTop);
        GL11.glVertex2f(squareRight, squareBottom);

        GL11.glVertex2f(squareLeft, squareBottom);
        GL11.glVertex2f(squareRight, squareTop);

        GL11.glEnd();

        GL11.glPopMatrix();
    }
}
