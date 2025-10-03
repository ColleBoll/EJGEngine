package org.collebol.shared.physics;

import org.collebol.shared.GameLocation;
import org.collebol.shared.objects.GameObject;
import org.collebol.shared.physics.collision.BoxCollider;

import java.util.ArrayList;
import java.util.List;

public class PhysicsManager {

    private final List<GameObject> objects;

    public PhysicsManager() {
        this.objects = new ArrayList<>();
    }

    public void register(GameObject object) {
        if (!objects.contains(object)) {
            objects.add(object);
        }
    }

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
