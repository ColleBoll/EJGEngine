package org.collebol.gui.graphics.renderer;

import org.collebol.EJGEngine;
import org.collebol.game.world.Chunk;
import org.collebol.game.world.World;

public class WorldRenderer implements Renderer {

    private World world;
    private EJGEngine engine;

    public WorldRenderer(World world, EJGEngine e) {
        this.world = world;
        this.engine = e;
    }

    public void renderWorldChunks() {
        for (Chunk chunk : this.world.getChunks()) {
            this.engine.getRenderers().getCameraRenderer().renderObjects(chunk.getTiles());
        }
    }

    public void renderChunkRelativeToCamera(Chunk chunk) {
        this.engine.getRenderers().getCameraRenderer().renderObjects(chunk.getTiles());
    }
}
