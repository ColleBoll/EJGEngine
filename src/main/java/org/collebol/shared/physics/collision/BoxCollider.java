package org.collebol.shared.physics.collision;

import org.collebol.shared.objects.GameObject;

/**
 * A rectangular collider used for detecting collisions between box-shaped
 * {@link GameObject} instances. This collider uses axis-aligned bounding box (AABB)
 * intersection logic to determine overlaps with other {@code BoxCollider} instances.
 *
 * <p>Usage:</p>
 * <blockquote><pre>
 *     MyGameObject gameObj = new MyGameObject();
 *     gameObj.addPhysicsComponent(new BoxCollider(gameObj, 1, 1));
 * </pre></blockquote>
 *
 * <p>You can also use GameManager to register and move Entity's:</p>
 * <blockquote><pre>
 *     MyEntity entity = new MyEntity();
 *     entity.addPhysicsComponent(new BoxCollider(entity, 1, 1));
 *     gameManager.getGameRegister().registerEntity(entity);
 *
 *     // update to new location.
 *     gameManager.getGameRegister().getPhysicsManager().tryMove(entity, newLoc);
 * </pre></blockquote>
 *
 * @author ColleBol - <a href="mailto:contact@collebol.org">contact@collebol.org</a>
 * @since 1.0-dev
 */
public class BoxCollider extends Collider {

    private double width, height;

    /**
     * Constructs a new {@code BoxCollider} for the given game object with the specified
     * width and height.
     *
     * @param owner  the {@link GameObject} that owns this collider
     * @param width  the width of the collider in world units (tileSize)
     * @param height the height of the collider in world units (tileSize)
     */
    public BoxCollider(GameObject owner, double width, double height) {
        super(owner);
        this.width = width;
        this.height = height;
    }

    /**
     * Checks whether this collider intersects with another collider.
     * <p>If the other collider is also a {@code BoxCollider}, an axis-aligned
     * bounding box (AABB) intersection test is performed. If the other collider
     * type is unsupported, this method returns {@code false}.</p>
     *
     * @param other the other collider to check intersection with
     * @return {@code true} if the colliders intersect, {@code false} otherwise
     */
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
