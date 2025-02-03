package org.collebol.game.world;

import org.collebol.game.GameObject;
import org.collebol.game.objects.solids.Tile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Abstract class representing a world generator in the EJGEngine.
 * This class is responsible for generating and saving chunks in the game world.
 *
 * <p>Usage:</p>
 * <pre>
 * {@code
 * WorldGenerator generator = new MyWorldGenerator(myWorld);
 * }
 * </pre>
 *
 * <p>You have to create your own {@link WorldGenerator#generateChunk(Chunk)} method before using.</p>
 *
 * <p>For more information, please refer to the <a href="https://github.com/ColleBoll/EJGEngine/wiki">EJGEngine Wiki</a>.</p>
 *
 * @author ColleBol - <a href="mailto:contact@collebol.org">contact@collebol.org</a>
 * @since 1.0-dev
 */
public abstract class WorldGenerator {

    private World world;

    public WorldGenerator(World world) {
        this.world = world;
    }

    /**
     * Save a {@link Chunk} in the given World directory: {@link World#getWorldFolder()}
     *
     * @param chunk The chunk you want to save
     */
    public void saveChunk(Chunk chunk) {
        File chunkFile = new File(this.world.getWorldFolder(), "chunk_" + chunk.getX() + "_" + chunk.getY() + ".dat");
        if (chunkFile.exists()) return;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(chunkFile))) {
            for (GameObject tile : chunk.getTiles()) {
                writer.write(tile.getGameLocation().getX() + "," + tile.getGameLocation().getY() + "," + tile.getTexture());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Generate chunk method.
     * <p>Here you have to create your own {@link Chunk}. </p>
     *
     * <p>Example chunk generator:</p>
     * <pre>
     * {@code
     * for (int i = 0; i < chunk.getChunkSize(); i++) {
     *     for (int j = 0; j < chunk.getChunkSize(); j++) {
     *         GameLocation location = new GameLocation(i + (chunk.getX() * chunk.getChunkSize()), j + (chunk.getY() * chunk.getChunkSize()));
     *         float chance = new Random().nextFloat();
     *         MyTile tile = new MyTile();
     *         tile.setGameLocation(location);
     *         chunk.addTile(tile);
     *     }
     * }
     *         saveChunk(chunk);
     *         return chunk;
     * }
     * </pre>
     *
     * @param chunk Chunk base.
     * @return chunk with chunk data, like: {@link Tile}'s
     */
    public abstract Chunk generateChunk(Chunk chunk);
}
