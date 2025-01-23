package me.collebol.game.world;

import me.collebol.game.GameObject;
import me.collebol.game.objects.solids.Tile;
import me.collebol.utils.GameLocation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class WorldLoader {

    private World world;

    private int renderDistance;

    public WorldLoader(World world, int renderDistance){
        this.world = world;
        this.renderDistance = renderDistance;
    }

    /**
     * @param chunk
     * @return the given chunk with tiles from file.
     */
    public Chunk loadChunkTilesFromFile(Chunk chunk){
        File chunkFile = getChunkFile(chunk);

        try(BufferedReader reader = new BufferedReader(new FileReader(chunkFile))){
            String line;
            while((line = reader.readLine()) != null){
                String[] tokens = line.split(",");

                double tileX = Double.parseDouble(tokens[0]);
                double tileY = Double.parseDouble(tokens[1]);
                int texture = Integer.parseInt(tokens[2]);

                GameObject tile = new GameObject();
                tile.setGameLocation(new GameLocation(tileX, tileY));
                tile.setTexture(texture);

                chunk.addTile(tile);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return chunk;
    }

    private File getChunkFile(Chunk chunk) {
        File worldFoler = this.world.getWorldFolder();
        if(!worldFoler.exists()) throw new RuntimeException("Chunk world folder path does not exists. Please, make sure you give the right path to your chunk files. Given path: " + worldFoler.getAbsolutePath());

        File chunkFile = new File(worldFoler, "chunk_" + chunk.getX() + "_" + chunk.getY() + ".dat");
        if(!chunkFile.exists()) throw new RuntimeException("Chunk-file does not exists in the given world folder! Please, make sure to give a existing chunk-file! Given path: " + chunkFile.getAbsolutePath());
        return chunkFile;
    }

    public int getRenderDistance() {
        return renderDistance;
    }

    public void setRenderDistance(int renderDistance) {
        this.renderDistance = renderDistance;
    }
}
