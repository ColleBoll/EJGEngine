package org.collebol.game.world;

import org.collebol.game.GameObject;

import java.util.ArrayList;
import java.util.List;

public abstract class Chunk {

    private int chunkSize;

    private int x;
    private int y;

    private List<GameObject> tiles;
    private List<GameObject> entities;

    public Chunk(int chunkSize, int x, int y) {
        this.chunkSize = chunkSize;
        this.x = x;
        this.y = y;
        this.tiles = new ArrayList<>();
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

    public void addTile(GameObject tile) {
        if (tile.getGameLocation() == null)
            throw new RuntimeException("Tile GameLocation is null. Please, set a location.");
        this.tiles.add(tile);
    }

    public void addEntity(GameObject entity) {
        if (entity.getGameLocation() == null)
            throw new RuntimeException("Entity GameLocation is null. Please, set a location.");
        this.entities.add(entity);
    }

    public List<GameObject> getTiles() {
        return tiles;
    }

    public List<GameObject> getEntities() {
        return entities;
    }
}
