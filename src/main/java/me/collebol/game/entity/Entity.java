package me.collebol.game.entity;

import me.collebol.utils.GameLocation;

public abstract class Entity {

    private GameLocation GAME_LOCATION;
    public GameLocation getGameLocation() {
        return GAME_LOCATION;
    }
    public void setGameLocation(GameLocation location) {
        this.GAME_LOCATION = location;
    }

    public abstract void update();
}
