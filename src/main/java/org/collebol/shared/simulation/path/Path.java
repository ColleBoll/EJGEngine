package org.collebol.shared.simulation.path;

import org.collebol.shared.GameLocation;
import org.collebol.shared.objects.entity.Entity;

import java.util.ArrayList;

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

    public abstract ArrayList<GameLocation> pathInstructions();

    public abstract void doWhenPathEnded();
}
