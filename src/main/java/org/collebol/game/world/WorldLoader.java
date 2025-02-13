package org.collebol.game.world;

import org.collebol.game.GameObject;
import org.collebol.engine.utils.GameLocation;

import java.util.ArrayList;
import java.util.List;

/**
 * Class responsible for loading chunks in the EJGEngine.
 * This class handles loading chunks from files based on the render distance.
 *
 * <p>Usage:</p>
 * <pre>
 * {@code
 * WorldLoader loader = new WorldLoader(myWorld, 4);
 * List<Chunk> chunks = loader.loadRenderDistanceChunkFileFromLocation(myLocation);
 * }
 * </pre>
 *
 * @author ColleBol - <a href="mailto:contact@collebol.org">contact@collebol.org</a>
 * @see World
 * @see Chunk
 * @see GameLocation
 * @see GameObject
 * @since 1.0-dev
 */
public class WorldLoader {

    private World world;

    private int renderDistance;

    public WorldLoader(World world, int renderDistance) {
        this.world = world;
        this.renderDistance = renderDistance;
    }

    public List<Chunk> loadRenderDistanceChunkFileFromLocation(GameLocation gameLocation) {
        Chunk chunk = null;

        try {
            chunk = (Chunk) this.world.getChunkFormat().getConstructor(int.class, int.class).newInstance(0, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        int minChunkX = (int) gameLocation.getX() / chunk.getChunkSize() - this.renderDistance;
        int maxChunkX = (int) gameLocation.getX() / chunk.getChunkSize() + this.renderDistance;
        int minChunkY = (int) gameLocation.getY() / chunk.getChunkSize() - this.renderDistance;
        int maxChunkY = (int) gameLocation.getY() / chunk.getChunkSize() + this.renderDistance;

        List<Chunk> chunkList = new ArrayList<>();

        for (int chunkX = minChunkX; chunkX <= maxChunkX; chunkX++) {
            for (int chunkY = minChunkY; chunkY <= maxChunkY; chunkY++) {
                if (!(this.world.getWorldFileManager().loadChunk(chunkX, chunkY) == null)) {
                    Chunk cChunk = this.world.getWorldFileManager().loadChunk(chunkX, chunkY);
                    chunkList.add(cChunk);
                }
            }
        }
        return chunkList;
    }

    public int getRenderDistance() {
        return renderDistance;
    }

    public void setRenderDistance(int renderDistance) {
        this.renderDistance = renderDistance;
    }
}
