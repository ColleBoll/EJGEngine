package org.collebol.shared.physics.collision;

import org.collebol.shared.GameLocation;
import org.collebol.shared.objects.GameObject;
import org.collebol.shared.physics.PhysicsComponent;

public abstract class Collider extends PhysicsComponent {

    private GameLocation location;

    public Collider(GameObject owner) {
        super(owner);
        this.location = owner.getGameLocation();
    }

    public GameLocation getLocation() {
        return location;
    }

    public abstract boolean intersects(Collider other);
}
