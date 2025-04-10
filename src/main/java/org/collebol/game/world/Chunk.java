package org.collebol.game.world;

import org.collebol.shared.objects.GameObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Chunk class represents a segment of the game world, containing tiles ad entities.
 * It is used to manage and organize game objects within a specific area, defined by its size and position.
 * <p>Each chunk holds a collection of tiles and entities, allowing for efficient rendering and updating the game world.</p>
 *
 * <p>Usage:</p>
 * <blockquote><pre>
 *     MyChunk chunk = new MyChunk(chunkSize, chunkX, chunkY);
 *     chunk.addTile(tile);
 * </pre></blockquote>
 *
 * @author ColleBol - <a href="mailto:contact@collebol.org">contact@collebol.org</a>
 * @since 1.0-dev
 */
public abstract class Chunk {

    private int chunkSize;

    private int x;
    private int y;

    private Map<Integer, List<GameObject>> tiles;
    private List<GameObject> entities;

    /**
     * Chunk constructor.
     *
     * @param chunkSize the size of the chunk. Example: 16 (16x16)
     * @param x         the x-coordinate of the chunk.
     * @param y         the y-coordinate of the chunk.
     */
    public Chunk(int chunkSize, int x, int y) {
        this.chunkSize = chunkSize;
        this.x = x;
        this.y = y;
        this.tiles = new HashMap<>();
        this.entities = new ArrayList<>();
    }

    public int getChunkSize() {
        return chunkSize;
    }

    public void setChunkSize(int chunkSize) {
        this.chunkSize = chunkSize;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    /**
     * Adds a tile to the chunk.
     *
     * @param tile tile to be added.
     * @throws RuntimeException if the tile's GameLocation is null.
     */
    public void addTile(GameObject tile) {
        if (tile.getGameLocation() == null)
            throw new RuntimeException("Tile GameLocation is null. Please, set a location.");
        this.tiles.computeIfAbsent(tile.getTexture(), k -> new ArrayList<>()).add(tile);
    }

    public void addEntity(GameObject entity) {
        if (entity.getGameLocation() == null)
            throw new RuntimeException("Entity GameLocation is null. Please, set a location.");
        this.entities.add(entity);
    }

    /**
     *
     * @return a list of all tiles in the Chunk.
     */
    public List<GameObject> getTiles() {
        List<GameObject> allTiles = new ArrayList<>();
        for (List<GameObject> tileList : this.tiles.values()) {
            allTiles.addAll(tileList);
        }
        return allTiles;
    }

    /**
     *
     * @return a map of tiles grouped by their texture id.
     */
    public Map<Integer, List<GameObject>> getTilesAsMap() {
        return tiles;
    }

    public List<GameObject> getEntities() {
        return entities;
    }
}
