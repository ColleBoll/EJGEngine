package org.collebol.shared.physics;

import org.collebol.shared.objects.GameObject;

public abstract class PhysicsComponent {

    protected GameObject owner;

    public PhysicsComponent(GameObject owner) {
        this.owner = owner;
    }
}
