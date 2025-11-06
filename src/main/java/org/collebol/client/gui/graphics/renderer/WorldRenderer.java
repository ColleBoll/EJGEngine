package org.collebol.client.gui.graphics.renderer;

import org.collebol.client.EJGEngine;
import org.collebol.client.gui.graphics.Camera;
import org.collebol.game.GameManager;
import org.collebol.game.world.Chunk;
import org.collebol.game.world.World;
import org.collebol.shared.objects.GameObject;
import org.collebol.shared.objects.entity.Entity;
import org.collebol.shared.objects.entity.Player;
import org.collebol.shared.physics.PhysicsComponent;
import org.collebol.shared.physics.collision.BoxCollider;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

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

    private final World world;
    private final GameManager gameManager;
    private final EJGEngine engine;

    /**
     * WorldRenderer constructor
     *
     * @param world the world to be rendered.
     * @param e     the engine instance.
     */
    public WorldRenderer(GameManager manager, String world, EJGEngine e) {
        this.world = manager.getGameRegister().getWorld(world);
        this.gameManager = manager;
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
        List<Chunk> chunksSnapshot = new ArrayList<>(this.world.getChunks());
        for (Chunk chunk : chunksSnapshot) {
            // render chunks
            this.engine.getRenderers().getCameraRenderer().renderBatchObjects(chunk.getTilesAsMap());

            // render players
            if (this.gameManager.getGameRegister().getPlayers() == null) return;
            for (Player player : this.gameManager.getGameRegister().getPlayers()) {
                GameObject obj = player;
                this.engine.getRenderers().getCameraRenderer().renderObject(obj);
            }
            for (Entity entity : this.gameManager.getGameRegister().getEntitys()) {
                GameObject obj = entity;
                this.engine.getRenderers().getCameraRenderer().renderObject(obj);
            }
        }
    }

    public void renderChunkRelativeToCamera(Chunk chunk) {
        this.engine.getRenderers().getCameraRenderer().renderObjects(chunk.getTiles());
    }
}
