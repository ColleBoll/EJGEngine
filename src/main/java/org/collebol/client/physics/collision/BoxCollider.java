package org.collebol.client.physics.collision;

import org.collebol.shared.math.Vector2D;

public class BoxCollider extends Collider {

    public BoxCollider() {
    }

    public BoxCollider(float width, float height, Vector2D origin) {
        setWidth(width);
        setHeight(height);
        setOrigin(origin);
    }

    public BoxCollider(BoxColliderBuilder builder) {
        setWidth(builder.width);
        setHeight(builder.height);
        setOrigin(builder.origin);
    }
    
    public static class BoxColliderBuilder {
        private Vector2D origin = new Vector2D(0, 0);
        private float width = 0, height = 0;
        
        public BoxColliderBuilder origin(Vector2D origin){
            this.origin = origin;
            return this;
        }
        
        public BoxColliderBuilder width(float width){
            this.width = width;
            return this;
        }
        
        public BoxColliderBuilder height(float height){
            this.height = height;
            return this;
        }
    }

    public boolean collidesWith(Collider other) {
        return getOrigin().getX() < other.getOrigin().getX() + other.getWidth() &&
                getOrigin().getX() + getWidth() > other.getOrigin().getX() &&
                getOrigin().getY() < other.getOrigin().getY() + other.getHeight() &&
                getOrigin().getY() + getHeight() > other.getOrigin().getY();
    }
}
