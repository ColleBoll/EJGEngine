package org.collebol.shared.simulation.path;

import org.collebol.shared.GameLocation;
import org.collebol.shared.objects.entity.Entity;

import java.util.ArrayList;

public class DefaultPath extends Path{

    public DefaultPath(Entity entity) {
        super(entity);
    }

    @Override
    public ArrayList<GameLocation> pathInstructions() {

        ArrayList<GameLocation> path = new ArrayList<>();

        double startX = getEntity().getGameLocation().getX();
        double startY = getEntity().getGameLocation().getY();

        double radius = 2;
        double targetX = startX + (Math.random() * (radius * 2 + 1)) - radius;
        double targetY = startY + (Math.random() * (radius * 2 + 1)) - radius;

        double vx = targetX - startX;
        double vy = targetY - startY;

        double length = Math.sqrt(vx * vx + vy * vy);
        if (length == 0) return path;

        double dx = vx / length;
        double dy = vy / length;

        double speed = 0.05;

        double distance = 0;

        while (distance <= length) {

            double px = startX + dx * distance;
            double py = startY + dy * distance;

            path.add(new GameLocation(px, py));

            distance += speed;
        }

        path.add(new GameLocation(targetX, targetY));

        return path;
    }

    @Override
    public void doWhenPathEnded() {
        generateNewPath();
    }

}
