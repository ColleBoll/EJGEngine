package me.collebol.gui.graphics;

import me.collebol.EJGEngine;
import me.collebol.game.GameObject;
import me.collebol.game.world.Chunk;
import me.collebol.game.world.World;

public class WorldRenderer {

    private World world;
    private EJGEngine engine;

    public WorldRenderer(World world, EJGEngine e){
        this.world = world;
        this.engine = e;
    }

    public void renderChunkRelativeToCamera(Chunk chunk){
        this.engine.getCameraRenderer().renderObjects(chunk.getTiles());
    }
}
