package org.collebol.shared.simulation;

import org.collebol.game.GameRegister;
import org.collebol.shared.objects.entity.Entity;

import java.util.List;

public class SimulationManager {

    private final GameRegister gameRegister;

    public SimulationManager(GameRegister gameManager) {
        this.gameRegister = gameManager;
    }

    public void updatePath()  {
        List<Entity> entities = this.gameRegister.getEntitys();
        for (Entity e : entities) {
            if (e.getPath() == null) return;
            if (!e.getPath().isEnabled()) return;
            if (e.getPath().isPathEnded()) return;
            if (e.getPath().getCurrentLocation() == null) return;
            boolean move = this.gameRegister.getPhysicsManager().tryMove(e, e.getPath().getCurrentLocation());
            if (move) {
                e.getPath().nextLocation();
            }
        }
    }
}
