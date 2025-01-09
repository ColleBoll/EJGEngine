package me.collebol.game.entity;

import me.collebol.game.GameObject;
import me.collebol.utils.GameLocation;

public abstract class Entity extends GameObject {

    private GameLocation GAME_LOCATION;
    public GameLocation getGameLocation() {
        return this.GAME_LOCATION;
    }
    public void setGameLocation(GameLocation location) {
        this.GAME_LOCATION = location;
    }

    public abstract void update();
}
