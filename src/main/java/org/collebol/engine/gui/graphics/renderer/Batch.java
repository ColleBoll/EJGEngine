package org.collebol.engine.gui.graphics.renderer;

import org.collebol.engine.math.Vector2D;

import java.util.ArrayList;
import java.util.List;

/**
 * The Batch class represents a collection of items to be rendered with a specific texture;
 * Its allows adding items with specific positions, scales rotation and origin point.
 * <p>This class is useful for batching multiple renderable items together to optimize rendering performance.</p>
 *
 * <p>Usage:</p>
 * <blockquote><pre>
 *     Batch batch = new Batch(textureid);
 *     batch.addItem(position, zoom, rotation, origin);
 * </pre></blockquote>
 *
 * @author ColleBol - <a href="mailto:contact@collebol.org">contact@collebol.org</a>
 * @since 1.0-dev
 */
public class Batch {

    private int texture;
    private List<BatchItem> items;

    public Batch(int textureid) {
        this.texture = textureid;
        this.items = new ArrayList<>();
    }

    public void addItem(Vector2D position, float scale, float rotation, Vector2D origin) {
        items.add(new BatchItem(position, scale, rotation, origin));
    }

    public int getTexture() {
        return texture;
    }

    public List<BatchItem> getItems() {
        return items;
    }

    public static class BatchItem {
        public Vector2D position;
        public float scale;
        public float rotation;
        public Vector2D origin;

        public BatchItem(Vector2D position, float scale, float rotation, Vector2D origin) {
            this.position = position;
            this.scale = scale;
            this.rotation = rotation;
            this.origin = origin;
        }
    }
}
