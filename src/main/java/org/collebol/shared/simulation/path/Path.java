package org.collebol.shared.simulation.path;

import org.collebol.shared.GameLocation;
import org.collebol.shared.objects.entity.Entity;

import java.util.ArrayList;

/**
 * This abstract class represents a movement path for an {@link Entity} within the simulation.
 * <p>
 * A {@link Path} is responsible for generating a sequence of {@link GameLocation}
 * steps that an entity will follow over time. Subclasses must define how the path
 * is generated and what should happen when the path ends.
 * </p>
 *
 * <p>
 * A path must be initialized using {@link #generateNewPath()} before it can be used.
 * The current location can then be retrieved step-by-step using
 * {@link #getCurrentLocation()} and advanced using {@link #nextLocation()}.
 * </p>
 *
 * @author ColleBol - <a href="mailto:contact@collebol.org">contact@collebol.org</a>
 * @since 1.0-dev
 */
public abstract class Path {

    private final Entity entity;

    private boolean enabled;
    private boolean pathEnded;

    private GameLocation startLoc;
    private GameLocation endLoc;

    private int currentIndex;
    private ArrayList<GameLocation> pathSteps;

    public Path(Entity entity) {
        this.entity = entity;
    }

    /**
     * Generates and initializes a new path for the entity.
     * <p>
     * This method resets the path state, stores the starting location,
     * generates the path steps using {@link #pathInstructions()},
     * and sets the current position to the first step.
     * </p>
     */
    public void generateNewPath() {

        this.startLoc = new GameLocation(
                entity.getGameLocation().getX(),
                entity.getGameLocation().getY()
        );

        this.enabled = true;
        this.pathEnded = false;

        this.pathSteps = pathInstructions();
        this.currentIndex = 1;

        this.endLoc = this.pathSteps.getLast();
    }

    public void nextLocation() {
        if (this.pathSteps == null) return;

        if (this.pathSteps.size() >= this.currentIndex) {
            this.currentIndex++;
        } else {
            this.pathEnded = true;
            doWhenPathEnded();
        }
    }

    public GameLocation getCurrentLocation() {
        if (this.pathSteps == null) return null;

        if (this.pathSteps.size() >= this.currentIndex) {
            return this.pathSteps.get(this.currentIndex - 1);
        } else {
            this.pathEnded = true;
            doWhenPathEnded();
            return null;
        }
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Entity getEntity() {
        return entity;
    }

    public boolean isPathEnded() {
        return pathEnded;
    }

    /**
     * Generates the list of locations that make up this path.
     * <p>
     * Implementations must return an ordered list of {@link GameLocation}
     * instances representing each step of the path.
     * </p>
     *
     * @return a list of path steps
     */
    public abstract ArrayList<GameLocation> pathInstructions();

    /**
     * Called when the path has reached its end.
     * <p>
     * Implementations can use this method to trigger follow-up behavior,
     * such as generating a new path or changing the entity state.
     * </p>
     */
    public abstract void doWhenPathEnded();
}
