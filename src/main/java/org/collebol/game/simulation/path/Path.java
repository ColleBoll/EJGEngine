package org.collebol.game.simulation.path;

import org.collebol.shared.GameLocation;
import org.collebol.shared.math.Vector2D;

import java.util.ArrayList;

public abstract class Path {

    private Vector2D oldLoc;
    private int currentLoc;
    private Vector2D newLoc;

    private ArrayList<GameLocation> path;

    public Path() {

    }

    public abstract ArrayList<GameLocation> pathInstructions();
}
