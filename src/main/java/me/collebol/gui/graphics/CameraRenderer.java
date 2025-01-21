package me.collebol.gui.graphics;

import me.collebol.EJGEngine;
import me.collebol.game.GameObject;
import me.collebol.math.Vector2D;
import me.collebol.utils.GameLocation;

import java.util.List;

/**
 *
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
        this.engine.getTextRenderer(font).render(text, v, size);
    }
}
