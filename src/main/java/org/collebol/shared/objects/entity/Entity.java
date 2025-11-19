package org.collebol.shared.objects.entity;

import org.collebol.shared.objects.GameObject;
import org.collebol.shared.GameLocation;
import org.collebol.shared.simulation.path.Path;

import java.util.UUID;

public abstract class Entity extends GameObject {

    private final UUID uuid;
    private Path path;

    public Entity(UUID uuid){
        this.uuid = uuid;
        this.path = null;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public abstract void update(float DT);
}
