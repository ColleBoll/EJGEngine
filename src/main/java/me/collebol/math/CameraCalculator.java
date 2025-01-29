package me.collebol.math;

import me.collebol.EJGEngine;
import me.collebol.gui.graphics.Camera;
import me.collebol.utils.GameLocation;

/**
 * You can calculate camera data in here.
 */
public class CameraCalculator {

    private Camera camera;
    private EJGEngine engine;

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
    public GameLocation getGameLocationFromVector2D(Vector2D position) {
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
}
