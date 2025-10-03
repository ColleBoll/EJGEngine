package org.collebol.shared.physics.collision;

import org.collebol.shared.objects.GameObject;

public class BoxCollider extends Collider{

    private double width, height;

    public BoxCollider(GameObject owner, double width, double height) {
        super(owner);
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean intersects(Collider other) {
        if (other instanceof BoxCollider) {
            BoxCollider o = (BoxCollider) other;

            double ax = this.getLocation().getX();
            double ay = this.getLocation().getY();
            double bx = o.getLocation().getX();
            double by = o.getLocation().getY();

            return ax < bx + o.width &&
                    ax + this.width > bx &&
                    ay < by + o.height &&
                    ay + this.height > by;
        }

        return false;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}
