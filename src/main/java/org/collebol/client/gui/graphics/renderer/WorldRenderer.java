package org.collebol.client.gui.graphics.renderer;

import org.collebol.client.EJGEngine;
import org.collebol.game.world.Chunk;
import org.collebol.game.world.World;

/**
 * The WorldRenderer class contains several methods to simply render world and chunks.
 * <p>WorldRenderer is relative to a {@link CameraRenderer}.</p>
 *
 * <p>Usage:</p>
 * <blockquote><pre>
 *     WorldRenderer worldRenderer = new WorldRenderer(world, engine);
 *     engine.getRenderers().registerNewRenderer(worldRenderer); //register the worldRenderer
 * </pre></blockquote>
 *
 * @author ColleBol - <a href="mailto:contact@collebol.org">contact@collebol.org</a>
 * @since 1.0-dev
 */
public class WorldRenderer extends Renderer {

    private World world;
    private EJGEngine engine;

    /**
     * WorldRenderer constructor
     *
     * @param world the world to be rendered.
     * @param e     the engine instance.
     */
    public WorldRenderer(World world, EJGEngine e) {
        this.world = world;
        this.engine = e;
    }

    /**
     * Renders all chunks in {@link World#getChunks()} loaded in the world.
     * <p>Relative to a {@link CameraRenderer}</p>
     */
    public void renderWorldChunks() {
        for (Chunk chunk : this.world.getChunks()) {
            this.engine.getRenderers().getCameraRenderer().renderObjects(chunk.getTiles());
        }
    }

    /**
     * Renders all chunks in {@link World#getChunks()} loaded in the world as Batch.
     * This method iterates through each chunk and uses the {@link CameraRenderer} to render the objects within each chunk as a batch.
     */
    public void renderWorldChunksAsBatch() {
        for (Chunk chunk : this.world.getChunks()) {
            this.engine.getRenderers().getCameraRenderer().renderBatchObjects(chunk.getTilesAsMap());
        }
    }

    public void renderChunkRelativeToCamera(Chunk chunk) {
        this.engine.getRenderers().getCameraRenderer().renderObjects(chunk.getTiles());
    }
}
