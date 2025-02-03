package org.collebol.game;

import org.collebol.math.Vector2D;
import org.collebol.utils.EngineObject;
import org.collebol.utils.GameLocation;

public class GameObject extends EngineObject {

    private Vector2D velocity;
    private GameLocation gameLocation;

    /**
     * Velocity is the speed in combination with the direction of motion of the object.
     *
     * @return vector velocity
     * @author ColleBol - contact@collebol.org
     * @since < 1.0
     */
    public Vector2D getVelocity() {
        return velocity;
    }

    /**
     * Velocity is the speed in combination with the direction of motion of the object.
     *
     * @param velocity vector with x and y its moving in
     * @author ColleBol - contact@collebol.org
     * @since < 1.0
     */
    public void setVelocity(Vector2D velocity) {
        this.velocity = velocity;
    }

    /**
     * GameLocation is the location in game.
     *
     * @return game location
     * @author ColleBol - contact@collebol.org
     * @since < 1.0
     */
    public GameLocation getGameLocation() {
        return gameLocation;
    }

    /**
     * GameLocation is the location in game.
     *
     * @param gameLocation game location. not Vector2D.
     * @author ColleBol - contact@collebol.org
     * @since < 1.0
     */
    public void setGameLocation(GameLocation gameLocation) {
        this.gameLocation = gameLocation;
    }
}
