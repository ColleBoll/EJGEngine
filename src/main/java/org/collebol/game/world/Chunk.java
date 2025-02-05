package org.collebol.game.world;

import org.collebol.game.GameObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Chunk {

    private int chunkSize;

    private int x;
    private int y;

    private Map<Integer, List<GameObject>> tiles;
    private List<GameObject> entities;

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

    public List<GameObject> getTiles() {
        List<GameObject> allTiles = new ArrayList<>();
        for(List<GameObject> tileList : this.tiles.values()){
            allTiles.addAll(tileList);
        }
        return allTiles;
    }

    public Map<Integer, List<GameObject>> getTilesAsMap(){
        return tiles;
    }

    public List<GameObject> getEntities() {
        return entities;
    }
}
