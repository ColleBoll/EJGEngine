package org.collebol.game.world;

import org.collebol.game.GameObject;
import org.collebol.utils.GameLocation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
                if (!(getChunkFile(chunkX, chunkY) == null)) {
                    Chunk cChunk = loadChunkTilesFromFile(chunkX, chunkY);
                    chunkList.add(cChunk);
                }
            }
        }
        return chunkList;
    }


    /**
     * @param chunkX
     * @param chunkY
     * @return the given chunk with tiles from file.
     */
    public Chunk loadChunkTilesFromFile(int chunkX, int chunkY) {
        File chunkFile = getChunkFile(chunkX, chunkY);
        if (chunkFile == null) return null;
        try (BufferedReader reader = new BufferedReader(new FileReader(chunkFile))) {
            Chunk chunk = (Chunk) this.world.getChunkFormat().getConstructor(int.class, int.class).newInstance(chunkX, chunkY);
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");

                double tileX = Double.parseDouble(tokens[0]);
                double tileY = Double.parseDouble(tokens[1]);
                int texture = Integer.parseInt(tokens[2]);

                GameObject tile = new GameObject();
                tile.setGameLocation(new GameLocation(tileX, tileY));
                tile.setTexture(texture);

                chunk.addTile(tile);
            }
            return chunk;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private File getChunkFile(int chunkX, int chunkY) {
        File worldFoler = this.world.getWorldFolder();
        if (!worldFoler.exists())
            throw new RuntimeException("Chunk world folder path does not exists. Please, make sure you give the right path to your chunk files. Given path: " + worldFoler.getAbsolutePath());

        File chunkFile = new File(worldFoler, "chunk_" + chunkX + "_" + chunkY + ".dat");
        if (!chunkFile.exists()) return null;
        return chunkFile;
    }

    public int getRenderDistance() {
        return renderDistance;
    }

    public void setRenderDistance(int renderDistance) {
        this.renderDistance = renderDistance;
    }
}
