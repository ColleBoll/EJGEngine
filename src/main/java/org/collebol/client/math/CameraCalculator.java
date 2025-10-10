package org.collebol.client.math;

import org.collebol.client.EJGEngine;
import org.collebol.client.gui.graphics.Camera;
import org.collebol.shared.GameLocation;
import org.collebol.shared.math.Vector2D;

/**
 * You can calculate camera data here.
 *
 * @author ColleBol - <a href="mailto:contact@collebol.org">contact@collebol.org</a>
 * @since 1.0-dev
 */
public class CameraCalculator {

    private final Camera camera;
    private final EJGEngine engine;

    public CameraCalculator(Camera camera, EJGEngine e) {
        this.camera = camera;
        this.engine = e;
    }

    /**
     * This calculates method will calculate the current GameLocation on the given Vector2D cords.
     * If the camera moves or zooms in or rotates, the GameLocation will change.
     *
     * @param position Panel position to calculate the current GameLocation on that Panel position.
     * @return The GameLocation on the given Panel position.
     */
    public GameLocation getGameLocationFromPanelPosition(Vector2D position) {
        float tileSize = this.engine.getWindow().getTileSize();
        float zoom = this.camera.getZoom();
        Vector2D cameraPos = this.camera.getPosition();
        Vector2D cameraOrigin = this.camera.getOrigin();

        float localX = position.getX() - cameraOrigin.getX();
        float localY = position.getY() - cameraOrigin.getY();

        float rotation = this.camera.getRotation();
        float radians = (float) Math.toRadians(-rotation);

        float rotatedX = (float) (localX * Math.cos(radians) - localY * Math.sin(radians));
        float rotatedY = (float) (localX * Math.sin(radians) + localY * Math.cos(radians));

        float worldX = (rotatedX + cameraPos.getX()) / (tileSize * zoom);
        float worldY = (rotatedY + cameraPos.getY()) / (tileSize * zoom);

        return new GameLocation(worldX, worldY);
    }

    /**
     * This method calculates the Panel position on screen from a given GameLocation.
     * It takes into account the camera's position, zoom, and rotation.
     *
     * @param gameLocation The GameLocation to convert to a Panel position.
     * @return The corresponding Panel position as a Vector2D.
     */
    public Vector2D getPanelPositionFromGameLocation(GameLocation gameLocation) {
        float tileSize = this.engine.getWindow().getTileSize();
        float zoom = this.camera.getZoom();
        Vector2D cameraPos = this.camera.getPosition();
        Vector2D cameraOrigin = this.camera.getOrigin();

        float worldX = (float) (gameLocation.getX() * tileSize * zoom - cameraPos.getX());
        float worldY = (float) (gameLocation.getY() * tileSize * zoom - cameraPos.getY());

        float rotation = this.camera.getRotation();
        float radians = (float) Math.toRadians(rotation);

        float rotatedX = (float) (worldX * Math.cos(radians) - worldY * Math.sin(radians));
        float rotatedY = (float) (worldX * Math.sin(radians) + worldY * Math.cos(radians));

        float panelX = rotatedX + cameraOrigin.getX();
        float panelY = rotatedY + cameraOrigin.getY();

        return new Vector2D(panelX, panelY);
    }

}
