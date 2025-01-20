package me.collebol.gui.graphics;

import me.collebol.EJGEngine;
import me.collebol.game.GameObject;
import me.collebol.math.Vector2D;

import java.util.HashMap;
import java.util.List;

/**
 *
 */
public class ObjectRenderer {

    private EJGEngine engine;

    public ObjectRenderer(EJGEngine e){
        this.engine = e;
    }

    public void renderObjects(List<GameObject> gameObjects){
        for(GameObject g : gameObjects){
            float x = (float) (g.getGameLocation().x * (this.engine.getWindow().getTileSize() * this.engine.getWindow().getCurrentPanel().getCamera().getZoom()));
            float y = (float) (g.getGameLocation().y * (this.engine.getWindow().getTileSize() * this.engine.getWindow().getCurrentPanel().getCamera().getZoom()));
            Vector2D v = new Vector2D(x, y);
            this.engine.getTextureRenderer().render(g.getTexture(), v);
        }
    }
}
