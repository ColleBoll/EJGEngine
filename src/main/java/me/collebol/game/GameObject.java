package me.collebol.game;

import me.collebol.gui.graphics.Texture;
import me.collebol.math.Vector2D;
import me.collebol.utils.EngineObject;
import me.collebol.utils.GameLocation;

public class GameObject extends EngineObject {

    private Vector2D velocity;
    private GameLocation gameLocation;

    /**
     * Velocity is the speed in combination with the direction of motion of the object.
     * @return vector velocity
     */
    public Vector2D getVelocity() {
        return velocity;
    }

    /**
     * Velocity is the speed in combination with the direction of motion of the object.
     * @param velocity vector with x and y its moving in
     */
    public void setVelocity(Vector2D velocity) {
        this.velocity = velocity;
    }

    /**
     * GameLocation is the location in game.
     * @return game location
     */
    public GameLocation getGameLocation() {
        return gameLocation;
    }

    /**
     * GameLocation is the location in game.
     * @param gameLocation game location. not Vector2D.
     */
    public void setGameLocation(GameLocation gameLocation) {
        this.gameLocation = gameLocation;
    }
}
