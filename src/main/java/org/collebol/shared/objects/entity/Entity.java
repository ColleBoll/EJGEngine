package org.collebol.shared.objects.entity;

import org.collebol.shared.objects.GameObject;
import org.collebol.shared.GameLocation;

import java.util.UUID;

public abstract class Entity extends GameObject {

    private GameLocation gameLocation;
    private final UUID uuid;

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

    public abstract void update(float DT);
}
