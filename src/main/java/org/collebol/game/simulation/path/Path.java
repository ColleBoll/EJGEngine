package org.collebol.game.simulation.path;

import org.collebol.shared.GameLocation;
import org.collebol.shared.objects.entity.Entity;

import java.util.ArrayList;

public abstract class Path {

    private final Entity entity;

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

        this.pathSteps = pathInstructions();
        this.currentIndex = 0;

        this.endLoc = this.pathSteps.getLast();
    }

    public GameLocation getNextLocation() {
        if (this.pathSteps == null) return null;

        GameLocation l;
        if (this.pathSteps.size() > this.currentIndex) {
            l = this.pathSteps.get(this.currentIndex);
            this.currentIndex++;
            return l;
        } else {
            return this.endLoc;
        }
    }

    public GameLocation getCurrentLocation() {
        if (this.pathSteps == null) return null;
        return this.pathSteps.get(this.currentIndex);
    }

    public abstract ArrayList<GameLocation> pathInstructions();
}
