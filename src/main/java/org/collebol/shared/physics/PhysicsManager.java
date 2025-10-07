package org.collebol.shared.physics;

import org.collebol.shared.GameLocation;
import org.collebol.shared.objects.GameObject;
import org.collebol.shared.physics.collision.BoxCollider;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code PhysicsManager} handles registration, movement, and collision
 * detection for all {@link GameObject} instances that contain physics components.
 *
 * <p>This manager provides a simple, brute-force collision system based on
 * {@link BoxCollider} checks. When an object is moved using {@link #tryMove(GameObject, GameLocation)},
 * the system validates that the move does not cause the object to intersect with
 * any other registered object in the world.</p>
 *
 * <p>Usage:</p>
 * <blockquote><pre>
 *     // Create a new physics manager
 *     PhysicsManager physicsManager = new PhysicsManager();
 *
 *     // Register game objects
 *     GameObject player = new Player();
 *     player.addPhysicsComponent(new BoxCollider(player, 1, 1));
 *     physicsManager.register(player);
 *
 *     // Attempt to move the player to a new location
 *     GameLocation newLoc = new GameLocation(5, 3);
 *     boolean success = physicsManager.tryMove(player, newLoc);
 *
 *     if (!success) {
 *         System.out.println("Movement blocked by collision!");
 *     }
 * </pre></blockquote>
 *
 * <p>This class can be extended or replaced with more advanced physics systems
 * if needed, such as those involving velocity, gravity, or spatial partitioning.</p>
 *
 * @author ColleBol - <a href="mailto:contact@collebol.org">contact@collebol.org</a>
 * @since 1.0-dev
 */
public class PhysicsManager {

    /** The list of all registered {@link GameObject} instances in the physics world. */
    private final List<GameObject> objects;

    /**
     * Constructs a new {@code PhysicsManager} with an empty list of registered objects.
     */
    public PhysicsManager() {
        this.objects = new ArrayList<>();
    }

    /**
     * Registers a {@link GameObject} for collision detection and movement handling.
     * <p>If the object is already registered, this method has no effect.</p>
     *
     * @param object the game object to register
     */
    public void register(GameObject object) {
        if (!objects.contains(object)) {
            objects.add(object);
        }
    }

    /**
     * Attempts to move the specified {@link GameObject} to a new {@link GameLocation}.
     * <p>
     * The move will only succeed if no collision occurs with any other registered
     * object's {@link BoxCollider}. If a collision is detected, the object's position
     * is restored to its previous location and this method returns {@code false}.
     * </p>
     *
     * @param object the object to move
     * @param newLoc the desired new location
     * @return {@code true} if the move was successful (no collision),
     *         {@code false} if the object collided and its position was reverted
     */
    public boolean tryMove(GameObject object, GameLocation newLoc) {
        GameLocation loc = object.getGameLocation();
        double oldX = loc.getX();
        double oldY = loc.getY();

        loc.setX(newLoc.getX());
        loc.setY(newLoc.getY());

        for (GameObject other : this.objects) {
            if (other == object) continue;

            for (PhysicsComponent comp : object.getPhysicsComponentsList()) {
                if (!(comp instanceof BoxCollider)) continue;

                for (PhysicsComponent otherComp : other.getPhysicsComponentsList()) {
                    if (!(otherComp instanceof BoxCollider)) continue;

                    if (((BoxCollider) comp).intersects((BoxCollider) otherComp)) {
                        loc.setX(oldX);
                        loc.setY(oldY);
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
