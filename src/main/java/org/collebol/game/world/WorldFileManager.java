package org.collebol.game.world;

import org.collebol.game.GameObject;
import org.collebol.utils.GameLocation;

import java.io.*;

/**
 * The WorldFileManager class is responsible for saving and loading {@link Chunk} in the game {@link World}.
 * It provides methods to serialize ad deserialize chunk data to and from binary files.
 *
 * <p>Usage:</p>
 * <blockquote><pre>
 *     WorldFileManager fileManager = new WorldFileManager(world);
 *     fileManager.saveChunk(chunk); //save a chunk to binary file in worldfolder
 *     Chunk loadedChunk = fileManager.loadChunk(chunkX, chunkY); //get chunk from binary file in worldfolder
 * </pre></blockquote>
 *
 * @author ColleBol - <a href="mailto:contact@collebol.org">contact@collebol.org</a>
 * @since 1.0-dev
 */
public class WorldFileManager {

    private World world;

    public WorldFileManager(World world) {
        this.world = world;
    }

    /**
     * Saves the specified chunk to a binary file.
     *
     * @param chunk the chunk to be saved.
     */
    public void saveChunk(Chunk chunk) {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(this.world.getWorldFolder().getPath() + "/chunks/chunk_" + chunk.getX() + "_" + chunk.getY() + ".bin"))) {
            dos.writeInt(chunk.getX());
            dos.writeInt(chunk.getY());

            dos.writeInt(chunk.getTiles().size());
            for (GameObject tile : chunk.getTiles()) {
                dos.writeInt((int) tile.getGameLocation().getX());
                dos.writeInt((int) tile.getGameLocation().getY());
                dos.writeInt(tile.getTexture());
            }

            dos.writeInt(chunk.getEntities().size());
            for (GameObject entity : chunk.getEntities()) {

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Loads a chunk from binary file.
     *
     * @param chunkX the x-coordinate of the chunk.
     * @param chunkY the y-coordinate of the chunk.
     * @return the loaded chunk, or null if the file does not exist.
     */
    public Chunk loadChunk(int chunkX, int chunkY) {
        File file = new File(this.world.getWorldFolder().getPath() + "/chunks/chunk_" + chunkX + "_" + chunkY + ".bin");
        if (!file.exists()) return null;

        try (DataInputStream dis = new DataInputStream(new FileInputStream(file))) {
            Chunk chunk = (Chunk) this.world.getChunkFormat().getConstructor(int.class, int.class).newInstance(chunkX, chunkY);

            chunk.setX(dis.readInt());
            chunk.setY(dis.readInt());

            int tileCount = dis.readInt();
            for (int i = 0; i < tileCount; i++) {
                int x = dis.readInt();
                int y = dis.readInt();
                int id = dis.readInt();
                GameObject tile = new GameObject();
                tile.setGameLocation(new GameLocation(x, y));
                tile.setTexture(id);

                chunk.addTile(tile);
            }

            int entityCount = dis.readInt();
            for (int i = 0; i < entityCount; i++) {

            }

            return chunk;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
