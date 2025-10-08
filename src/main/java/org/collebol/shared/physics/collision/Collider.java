package org.collebol.shared.physics.collision;

import org.collebol.shared.GameLocation;
import org.collebol.shared.objects.GameObject;
import org.collebol.shared.physics.PhysicsComponent;

public abstract class Collider extends PhysicsComponent {

    private GameLocation location;
    private GameLocation originLocation;

    public Collider(GameObject owner, GameLocation originLocation) {
        super(owner);
        this.location = owner.getGameLocation();
        this.originLocation = originLocation;
    }

    public GameLocation getLocation() {
        return location;
    }

    public GameLocation getOriginLocation() {
        return originLocation;
    }

    public abstract boolean intersects(Collider other);
}
