package me.collebol.game.world;

import me.collebol.game.GameObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public abstract class WorldGenerator {

    private World world;

    public WorldGenerator(World world){
        this.world = world;
    }

    public void saveChunk(Chunk chunk){
        File chunkFile = new File(this.world.getWorldFolder(), "chunk_" + chunk.getX() + "_" + chunk.getY() + ".dat");
        if(chunkFile.exists()) return;

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(chunkFile))){
            for(GameObject tile : chunk.getTiles()){
                writer.write(tile.getGameLocation().getX() + "," + tile.getGameLocation().getY() + "," + tile.getTexture());
                writer.newLine();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public abstract Chunk generateChunk(Chunk chunk);
}
