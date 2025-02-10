package org.collebol.audio;

import org.collebol.EJGEngine;
import org.collebol.gui.graphics.Camera;
import org.collebol.math.Vector2D;
import org.collebol.utils.GameLocation;

/**
 * The CameraSoundPlayer class is responsible for playing sounds relative to the camera's position.
 *
 * <p>Usage:</p>
 * <blockquote><pre>
 *     CameraSoundPlayer soundPlayer = new CameraSoundPlayer("default", engine);
 *     soundPlayer.playSound(soundId, gameLocation);
 * </pre></blockquote>
 *
 * @author ColleBol - <a href="mailto:contact@collebol.org">contact@collebol.org</a>
 * @since 1.0-dev
 */
public class CameraSoundPlayer implements AudioPlayer {

    private String name;
    private EJGEngine engine;

    public EJGEngine getEngine() {
        return engine;
    }

    /**
     * CameraSoundPlayer constructor.
     *
     * @param name the name of the sound player.
     * @param e    engine instance.
     */
    public CameraSoundPlayer(String name, EJGEngine e) {
        this.name = name;
        this.engine = e;
    }

    /**
     * Plays a sound at the specified game location relative to the camera position
     *
     * @param id       the id of the sound to be played.
     * @param location the game location where the sound should be played.
     */
    public void playSound(int id, GameLocation location) {
        Camera camera = this.engine.getWindow().getCurrentPanel().getCamera();
        float x = (float) (location.getX() - camera.getGameLocation().getX()) * this.engine.getWindow().getTileSize();
        float y = (float) (location.getY() - camera.getGameLocation().getY()) * this.engine.getWindow().getTileSize();
        System.out.println(x + " / " + y);
        SoundPlayer player = this.engine.getSoundHandler().getSoundPlayer("default");
        player.playSound(id, new Vector2D(x, y));
    }

    public String getName() {
        return name;
    }
}
