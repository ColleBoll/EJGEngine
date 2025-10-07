package org.collebol.shared.objects;

import org.collebol.shared.math.Vector2D;
import org.collebol.shared.EngineObject;
import org.collebol.shared.GameLocation;
import org.collebol.shared.physics.PhysicsComponent;

import java.util.ArrayList;
import java.util.List;

/**
 * The GameObject class represents a object in the game world.
 * It extends the {@link EngineObject} class and includes properties for velocity and {@link GameLocation}.
 *
 * <p>This class is used to manage the state and behavior of a object in the game.</p>
 *
 * <p>Usage:</p>
 * <blockquote><pre>
 *     GameObject object = new GameObject();
 * </pre></blockquote>
 *
 * @author ColleBol - <a href="mailto:contact@collebol.org">contact@collebol.org</a>
 * @since 1.0-dev
 */
public class GameObject extends EngineObject {

    private Vector2D velocity;
    private GameLocation gameLocation;
    private List<PhysicsComponent> physicsComponents = new ArrayList<>();

    /**
     * Velocity is the speed in combination with the direction of motion of the object.
     *
     * @return vector velocity
     */
    public Vector2D getVelocity() {
        return velocity;
    }

    /**
     * Velocity is the speed in combination with the direction of motion of the object.
     *
     * @param velocity vector with x and y its moving in
     */
    public void setVelocity(Vector2D velocity) {
        this.velocity = velocity;
    }

    /**
     * GameLocation is the location in game.
     *
     * @return game location
     */
    public GameLocation getGameLocation() {
        return gameLocation;
    }

    /**
     * GameLocation is the location in game.
     *
     * @param gameLocation game location. not Vector2D.
     */
    public void setGameLocation(GameLocation gameLocation) {
        this.gameLocation = gameLocation;
    }

    /**
     * Physics components are components added to a GameObject to do stuff like:
     * <p>If added Collider: The components cannot collide with other objects with colliders.</p>
     *
     * @return list of all components attached to Object
     */
    public List<PhysicsComponent> getPhysicsComponentsList() {
        return physicsComponents;
    }

    public void addPhysicsComponent(PhysicsComponent component) {
        if (component == null) return;
        this.physicsComponents.add(component);
    }

    /**
     * Sets the list of {@link PhysicsComponent} of this GameObject
     *
     * @param physicsComponents the list of components
     */
    public void setPhysicsComponents(List<PhysicsComponent> physicsComponents) {
        this.physicsComponents = physicsComponents;
    }
}
