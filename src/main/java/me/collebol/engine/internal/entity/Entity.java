package me.collebol.engine.internal.entity;

import me.collebol.engine.internal.Internal;
import me.collebol.engine.internal.utils.GameLocation;

@Internal
public abstract class Entity {

    public GameLocation gameLocation;

    public Entity(GameLocation gameLocation){
        this.gameLocation = gameLocation;
    }
}
