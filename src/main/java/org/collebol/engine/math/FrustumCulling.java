package org.collebol.engine.math;

import org.collebol.engine.EJGEngine;
import org.collebol.engine.gui.graphics.Camera;
import org.collebol.shared.math.Vector2D;

/**
 * The FrustumCulling class provides a method to determine if a given position is within the camera's frustum.
 * This is useful for optimizing rendering by culling objects that are outside the camera's view.
 *
 * <p>Usage:</p>
 * <blockquote><pre>
 *     boolean isVisible = FrustumCulling.isInFrustum(camera, position, engine);
 * </pre></blockquote>
 *
 * @author ColleBol - <a href="mailto:contact@collebol.org">contact@collebol.org</a>
 * @see Camera
 * @since 1.0-dev
 */
public class FrustumCulling {

    /**
     * Checks if a given position is within the camera's frustum.
     * <p>Relative to screen position.</p>
     *
     * @param camera   The camera instance.
     * @param position The position to check.
     * @param e        EJGEngine instance.
     * @return
     */
    public static boolean isInFrustum(Camera camera, Vector2D position, EJGEngine e) {
        float camX = (0 - (e.getWindow().getTileSize() * camera.getZoom() / 2)) - (e.getWindow().getTileSize() * camera.getZoom());
        float camY = (0 - (e.getWindow().getTileSize() * camera.getZoom() / 2)) - (e.getWindow().getTileSize() * camera.getZoom());
        float screenWidth = (e.getWindow().getWidth() + (e.getWindow().getTileSize() * camera.getZoom() / 2)) + (e.getWindow().getTileSize() * camera.getZoom());
        float screenHeight = (e.getWindow().getHeight() + (e.getWindow().getTileSize() * camera.getZoom() / 2)) + (e.getWindow().getTileSize() * camera.getZoom());

        float left = camX;
        float right = camX + screenWidth;
        float bottom = camY;
        float top = camY + screenHeight;

        return (position.getX() > left && position.getX() < right) &&
                (position.getY() > bottom && position.getY() < top);
    }
}
