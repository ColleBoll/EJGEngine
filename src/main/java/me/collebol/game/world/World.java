package me.collebol.game.world;

import me.collebol.EJGEngine;
import me.collebol.gui.graphics.WorldRenderer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public abstract class World {

    private String name;
    private EJGEngine engine;

    private List<Chunk> chunks;
    private Chunk chunkFormat;

    private WorldLoader worldLoader;
    private WorldGenerator worldGenerator;
    private WorldRenderer worldRenderer;

    private File worldFolder;

    public World(String name, Chunk chunkFormat, EJGEngine e){
        this.name = name;
        this.chunkFormat = chunkFormat;
        this.chunks = new ArrayList<>();
        this.engine = e;
        this.worldFolder = new File(e.getWindow().getTitle() + "/saves/" + name);
        this.worldRenderer = new WorldRenderer(this, e);

        if(!this.worldFolder.exists()){
            this.worldFolder.mkdirs();
        }
    }

    public World(String name, Chunk chunkFormat, File worldFolder, EJGEngine e){
        this.name = name;
        this.chunkFormat = chunkFormat;
        this.chunks = new ArrayList<>();
        this.engine = e;
        this.worldFolder = worldFolder;
        this.worldRenderer = new WorldRenderer(this, e);

        if(!this.worldFolder.exists()){
            this.worldFolder.mkdirs();
        }
    }

    public String getName() {
        return name;
    }

    public Chunk getChunkFormat() {
        return chunkFormat;
    }

    public void addChunk(Chunk chunk){
        if(chunk == null) throw new RuntimeException("Chunk equals null. Please set data to the added chunk.");
        this.chunks.add(chunk);
    }

    public List<Chunk> getChunks() {
        return chunks;
    }

    public WorldLoader getWorldLoader() {
        if(this.worldLoader == null) throw new RuntimeException("You have not registered the WorldLoader of your World yet! Please, make sure to register a WorldLoader!");
        return worldLoader;
    }

    public WorldGenerator getWorldGenerator() {
        if(this.worldLoader == null) throw new RuntimeException("You have not registered the WorldGenerator of your World yet! Please, make sure to register a WorldGenerator!");
        return worldGenerator;
    }

    public WorldRenderer getWorldRenderer() {
        return worldRenderer;
    }

    public void registerWorldLoader(WorldLoader worldLoader) {
        this.worldLoader = worldLoader;
    }

    public void registerWorldGenerator(WorldGenerator worldGenerator){
        this.worldGenerator = worldGenerator;
    }

    public File getWorldFolder() {
        return worldFolder;
    }

    public void setWorldFolder(File worldFolder) {
        this.worldFolder = worldFolder;
    }
}
