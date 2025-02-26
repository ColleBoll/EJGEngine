package org.collebol.game.objects.entity;

import org.collebol.game.GameObject;
import org.collebol.engine.utils.GameLocation;

import java.util.UUID;

public abstract class Entity extends GameObject {

    private GameLocation gameLocation;
    private UUID uuid;

    public Entity(UUID uuid){
        this.uuid = uuid;
    }

    public GameLocation getGameLocation() {
        return this.gameLocation;
    }

    public void setGameLocation(GameLocation location) {
        this.gameLocation = location;
    }

    public UUID getUuid() {
        return uuid;
    }

    public abstract void update();
}
