package me.collebol.gui.graphics.renderer;

import me.collebol.EJGEngine;
import me.collebol.game.world.Chunk;
import me.collebol.game.world.World;

public class WorldRenderer implements Renderer {

    private World world;
    private EJGEngine engine;

    public WorldRenderer(World world, EJGEngine e){
        this.world = world;
        this.engine = e;
    }

    public void renderWorldChunks(){
        for(Chunk chunk : this.world.getChunks()){
            this.engine.getRenderRegister().getCameraRenderer().renderObjects(chunk.getTiles());
        }
    }

    public void renderChunkRelativeToCamera(Chunk chunk){
        this.engine.getRenderRegister().getCameraRenderer().renderObjects(chunk.getTiles());
    }
}
