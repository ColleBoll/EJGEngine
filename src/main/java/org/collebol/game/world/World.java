package org.collebol.game.world;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class representing a game world in the EJGEngine.
 * This class is responsible for managing chunks, world loading, and world generation.
 *
 * <p>Usage:</p>
 * <blockquote><pre>
 *     World myWorld = new MyWorld("worldName", MyChunk.class);
 *     myWorld.setWorldLoader(new MyWorldLoader(myWorld));
 *     myWorld.registerWorldGenerator(new MyWorldGenerator(myWorld));
 * </pre></blockquote>
 *
 * @author ColleBol - <a href="mailto:contact@collebol.org">contact@collebol.org</a>
 * @since 1.0-dev
 */
public abstract class World {

    private String name;

    private List<Chunk> loadedChunks;
    private Class<? extends Chunk> chunkFormat;

    private WorldLoader worldLoader;
    private WorldGenerator worldGenerator;
    private WorldFileManager worldFileManager;

    private File worldFolder;

    /**
     * Constructs a World instance with the specified name and chunk format.
     *
     * @param name        the name of the world
     * @param chunkFormat the class representing the chunk format
     */
    public World(String name, Class<? extends Chunk> chunkFormat) {
        this.name = name;
        this.chunkFormat = chunkFormat;
        this.loadedChunks = new ArrayList<>();
        this.worldFolder = new File("EJGEngine/saves/" + name);
        this.worldLoader = new WorldLoader(this, 4);
        this.worldFileManager = new WorldFileManager(this);

        if (!this.worldFolder.exists()) {
            this.worldFolder.mkdirs();
            File c = new File(this.worldFolder, "chunks");
            c.mkdirs();
        }
    }

    /**
     * Constructs a World instance with the specified name, chunk format, and world folder.
     *
     * @param name        the name of the world
     * @param chunkFormat the class representing the chunk format
     * @param worldFolder the folder where the world data is stored
     */
    public World(String name, Class<? extends Chunk> chunkFormat, File worldFolder) {
        this.name = name;
        this.chunkFormat = chunkFormat;
        this.loadedChunks = new ArrayList<>();
        this.worldFolder = worldFolder;

        if (!this.worldFolder.exists()) {
            this.worldFolder.mkdirs();
        }
    }

    public String getName() {
        return name;
    }

    public Class<?> getChunkFormat() {
        return chunkFormat;
    }

    public void addChunk(Chunk chunk) {
        if (chunk == null) throw new RuntimeException("Chunk equals null. Please set data to the added chunk.");
        this.loadedChunks.add(chunk);
    }

    public List<Chunk> getChunks() {
        return loadedChunks;
    }

    /**
     * Returns the loaded chunk at the given chunk coordinates, or null if it is not loaded.
     *
     * @param chunkX the X coordinate of the chunk
     * @param chunkY the Y coordinate of the chunk
     * @return the loaded Chunk if present, otherwise null
     */
    public Chunk getChunk(int chunkX, int chunkY) {
        for (Chunk chunk : this.loadedChunks) {
            if (chunk.getX() == chunkX && chunk.getY() == chunkY) {
                return chunk;
            }
        }
        return null;
    }

    public void setChunks(List<Chunk> chunks) {
        if (chunks == null) return;
        this.loadedChunks = chunks;
    }

    public WorldLoader getWorldLoader() {
        if (this.worldLoader == null)
            throw new RuntimeException("You have not registered the WorldLoader of your World yet! Please, make sure to register a WorldLoader!");
        return worldLoader;
    }

    public WorldGenerator getWorldGenerator() {
        if (this.worldLoader == null)
            throw new RuntimeException("You have not registered the WorldGenerator of your World yet! Please, make sure to register a WorldGenerator!");
        return worldGenerator;
    }

    public void setWorldLoader(WorldLoader worldLoader) {
        this.worldLoader = worldLoader;
    }

    public void registerWorldGenerator(WorldGenerator worldGenerator) {
        this.worldGenerator = worldGenerator;
    }

    public File getWorldFolder() {
        return worldFolder;
    }

    public void setWorldFolder(File worldFolder) {
        this.worldFolder = worldFolder;
    }

    public WorldFileManager getWorldFileManager() {
        return worldFileManager;
    }
}
