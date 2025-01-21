package me.collebol.gui.graphics;

import me.collebol.EJGEngine;
import me.collebol.game.GameObject;
import me.collebol.math.Vector2D;
import me.collebol.utils.GameLocation;
import org.lwjgl.opengl.GL11;

import java.util.List;

/**
 * Render objects within the canvas of the camera. Change the position of the camera and the view will change.
 */
public class CameraRenderer {

    private EJGEngine engine;

    public CameraRenderer(EJGEngine e){
        this.engine = e;
    }

    public void renderObjects(List<GameObject> gameObjects){
        Camera camera = this.engine.getWindow().getCurrentPanel().getCamera();
        for(GameObject g : gameObjects){
            float x = (float) (((g.getGameLocation().x * (this.engine.getWindow().getTileSize() * camera.getZoom())) - camera.getPosition().getX()) + camera.getOrigin().getX());
            float y = (float) (((g.getGameLocation().y * (this.engine.getWindow().getTileSize() * camera.getZoom())) - camera.getPosition().getY()) + camera.getOrigin().getY());
            Vector2D v = new Vector2D(x, y);
            this.engine.getTextureRenderer().render(g.getTexture(), v, camera.getZoom());
        }
    }

    public void renderObject(GameObject gameObject){
        Camera camera = this.engine.getWindow().getCurrentPanel().getCamera();
        float x = (float) (((gameObject.getGameLocation().x * (this.engine.getWindow().getTileSize() * camera.getZoom())) - camera.getPosition().getX()) + camera.getOrigin().getX());
        float y = (float) (((gameObject.getGameLocation().y * (this.engine.getWindow().getTileSize() * camera.getZoom())) - camera.getPosition().getY()) + camera.getOrigin().getY());
        Vector2D v = new Vector2D(x, y);
        this.engine.getTextureRenderer().render(gameObject.getTexture(), v, camera.getZoom());
    }

    public void renderText(String text, GameLocation location, float size, String font){
        Camera camera = this.engine.getWindow().getCurrentPanel().getCamera();
        float x = (float) (((location.x * (this.engine.getWindow().getTileSize() * camera.getZoom())) - camera.getPosition().getX()) + camera.getOrigin().getX());
        float y = (float) (((location.y * (this.engine.getWindow().getTileSize() * camera.getZoom())) - camera.getPosition().getY()) + camera.getOrigin().getY());
        Vector2D v = new Vector2D(x, y);
        this.engine.getTextRenderer(font).render(text, v, size, camera.getZoom());
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
}
