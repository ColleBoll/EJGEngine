package org.collebol.game.world;

import org.collebol.game.objects.solids.Tile;

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
