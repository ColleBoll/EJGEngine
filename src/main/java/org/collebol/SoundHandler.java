package org.collebol;

import org.collebol.audio.AudioPlayer;
import org.collebol.audio.CameraSoundPlayer;
import org.collebol.audio.SoundPlayer;

import java.util.HashMap;
import java.util.Map;

/**
 * The SoundHandler class is responsible for managing sound players and camera sound players in the game.
 * It allows registering new players and retrieving them by name.
 *
 * <p>Usage:</p>
 * <blockquote><pre>
 *     SoundHandler soundHandler = new SoundHandler(engine);
 *     soundHandler.registerNewPlayer(new SoundPlayer("name"));
 *     SoundPlayer player = soundHandler.getSoundPlayer("name");
 * </pre></blockquote>
 *
 * @author ColleBol - <a href="mailto:contact@collebol.org">contact@collebol.org</a>
 * @since 1.0-dev
 */
public class SoundHandler {

    private EJGEngine engine;

    private Map<String, SoundPlayer> soundPlayers;
    private Map<String, CameraSoundPlayer> cameraPlayers;

    /**
     * SoundHandler constructor.
     * @param e engine instance.
     */
    public SoundHandler(EJGEngine e) {
        this.engine = e;
        this.soundPlayers = new HashMap<>();
        this.cameraPlayers = new HashMap<>();

        this.soundPlayers.put("default", new SoundPlayer("default"));
        this.cameraPlayers.put("default", new CameraSoundPlayer("default", this.engine));
    }

    /**
     * Registers a new audio player.
     *
     * @param audioPlayer the audio player to be registered
     * @param <T> the type of the audio player
     */
    public <T extends AudioPlayer> void registerNewPlayer(T audioPlayer) {
        if (audioPlayer instanceof SoundPlayer) {
            soundPlayers.put(((SoundPlayer) audioPlayer).getName(), (SoundPlayer) audioPlayer);
        }
        if (audioPlayer instanceof CameraSoundPlayer) {
            cameraPlayers.put(((CameraSoundPlayer) audioPlayer).getName(), (CameraSoundPlayer) audioPlayer);
        }
    }

    /**
     * Retrieves a sound player by name.
     *
     * @param name the name of the sound player
     * @return the sound player
     */
    public SoundPlayer getSoundPlayer(String name) {
        if (this.soundPlayers.isEmpty())
            throw new RuntimeException("You are trying to play sound with no SoundPlayer('s) registered! Please, register a SoundPlayer before using!");
        if (name == "default" && !this.soundPlayers.containsKey(name))
            throw new RuntimeException("Please, register a Default SoundPlayer in the register() method with the name 'default'.");
        if (this.soundPlayers.get(name) != null) {
            return this.soundPlayers.get(name);
        } else {
            throw new RuntimeException("SoundPlayer not found: " + name);
        }
    }

    /**
     * Retrieves a camera sound player by name.
     *
     * @param name the name of the camera sound player
     * @return the camera sound player
     */
    public CameraSoundPlayer getCameraPlayer(String name) {
        if (this.cameraPlayers.isEmpty())
            throw new RuntimeException("You are trying to play sound with no CameraSoundPlayer('s) registered! Please, register a CameraSoundPlayer before using!");
        if (name == "default" && !this.cameraPlayers.containsKey("default"))
            throw new RuntimeException("Please, register a Default CameraSoundPlayer in the register() method with the name 'default'.");
        if (this.cameraPlayers.get(name) != null) {
            return this.cameraPlayers.get(name);
        } else {
            throw new RuntimeException("CameraSoundPlayer not found: " + name);
        }
    }
}
