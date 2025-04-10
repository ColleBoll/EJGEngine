package org.collebol.engine.audio;

import org.collebol.shared.math.Vector2D;
import org.collebol.engine.math.VolumeCalculator;
import org.lwjgl.openal.*;
import org.lwjgl.system.MemoryUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * The SoundPlayer class manages the playback of audio sounds in the game.
 * It handles the initialization of the OpenAL context, registration of sounds, and playing of sounds.
 *
 * <p>Usage:</p>
 * <blockquote><pre>
 *     SoundPlayer soundPlayer = new SoundPlayer();
 *     soundPlayer.registerSound(sound);
 *     soundPlayer.playSound(sound.getId());
 * </pre></blockquote>
 *
 * @author ColleBol - <a href="mailto:contact@collebol.org">contact@collebol.org</a>
 * @since 1.0-dev
 */
public class SoundPlayer implements AudioPlayer {

    private String name;
    private long device;
    private long context;

    private Map<Integer, Sound> sounds = new HashMap<>();

    /**
     * SoundPlayer constructor.
     */
    public SoundPlayer(String name) {
        this.name = name;

        String defaultDeviceName = ALC10.alcGetString(0, ALC10.ALC_DEFAULT_DEVICE_SPECIFIER);
        this.device = ALC11.alcOpenDevice(defaultDeviceName);
        if (device == MemoryUtil.NULL) {
            throw new IllegalStateException("Failed to open an OpenAL device.");
        }
        int[] attributes = {0};
        this.context = ALC10.alcCreateContext(device, attributes);
        ALC10.alcMakeContextCurrent(context);

        ALCCapabilities alcCapabilities = ALC.createCapabilities(device);
        ALCapabilities alCapabilities = AL.createCapabilities(alcCapabilities);
    }

    /**
     * Register a sound to the player.
     *
     * @param sound the sound to be registerd.
     */
    public void registerSound(Sound sound) {
        this.sounds.put(sound.getId(), sound);
    }

    /**
     * Play a registered sound by its id.
     *
     * @param id               the id of the sound to be played.
     * @param listenerPosition the position of the listener.
     */
    public void playSound(int id, Vector2D listenerPosition) {
        if (!this.sounds.containsKey(id))
            throw new RuntimeException("Invalid Sound id: " + id + ". Register Sound before using!");
        Sound sound = this.sounds.get(id);
        if (sound.getPath() == null)
            throw new RuntimeException("Sound path equals null. Please, set a sound path before using!");
        int pointer = sound.getSource();

        AL10.alSourcef(pointer, AL10.AL_GAIN, VolumeCalculator.calculate(sound, listenerPosition));
        AL10.alSourcef(pointer, AL10.AL_PITCH, sound.getPitch());

        AL10.alSourcePlay(pointer);
    }

    /**
     * Play a registered sound by its id.
     * <p>Listener position = 0,0</p>
     *
     * @param id the id of the sound to be played.
     */
    public void playSound(int id) {
        if (!this.sounds.containsKey(id))
            throw new RuntimeException("Invalid Sound id: " + id + ". Register Sound before using!");
        Sound sound = this.sounds.get(id);
        if (sound.getPath() == null)
            throw new RuntimeException("Sound path equals null. Please, set a sound path before using!");
        int pointer = sound.getSource();

        AL10.alSourcef(pointer, AL10.AL_GAIN, VolumeCalculator.calculate(sound, new Vector2D(0, 0)));
        AL10.alSourcef(pointer, AL10.AL_PITCH, sound.getPitch());

        AL10.alSourcePlay(pointer);
    }

    /**
     * Play a modified sound with custom properties to an already existing sound.
     *
     * @param sound            the modified sound.
     * @param listenerPosition the position of the listener.
     */
    public void playModifiedSound(ModifySound sound, Vector2D listenerPosition) {
        if (!this.sounds.containsKey(sound.getId()))
            throw new RuntimeException("Invalid Sound id: " + sound.getId() + ". Register Sound before using!");
        if (this.sounds.get(sound.getId()).getPath() == null)
            throw new RuntimeException("Sound path equals null. Please, set a sound path before using!");
        int pointer = this.sounds.get(sound.getId()).getSource();

        AL10.alSourcef(pointer, AL10.AL_GAIN, VolumeCalculator.calculate(sound, listenerPosition));
        AL10.alSourcef(pointer, AL10.AL_PITCH, sound.getPitch());

        AL10.alSourcePlay(pointer);
    }

    /**
     * Play a modified sound with custom properties to an already existing sound.
     * <p>Listener position = 0,0</p>
     *
     * @param sound the modified sound.
     */
    public void playModifiedSound(ModifySound sound) {
        if (!this.sounds.containsKey(sound.getId()))
            throw new RuntimeException("Invalid Sound id: " + sound.getId() + ". Register Sound before using!");
        if (this.sounds.get(sound.getId()).getPath() == null)
            throw new RuntimeException("Sound path equals null. Please, set a sound path before using!");
        int pointer = this.sounds.get(sound.getId()).getSource();

        AL10.alSourcef(pointer, AL10.AL_GAIN, VolumeCalculator.calculate(sound, new Vector2D(0, 0)));
        AL10.alSourcef(pointer, AL10.AL_PITCH, sound.getPitch());

        AL10.alSourcePlay(pointer);
    }

    /**
     * The ModifySound class is used to modify sound properties before playing.
     */
    public static class ModifySound extends Sound {

        public ModifySound(int id, SoundPlayer soundPlayer) {
            super(new SoundBuilder(id, soundPlayer.sounds.get(id).getPath())
                    .position(soundPlayer.sounds.get(id).getPosition())
                    .volume(soundPlayer.sounds.get(id).getVolume())
                    .pitch(soundPlayer.sounds.get(id).getPitch())
                    .refDistance(soundPlayer.sounds.get(id).getRefDistance())
                    .rollOffFactor(soundPlayer.sounds.get(id).getRollOffFactor())
            );
        }

        public ModifySound position(Vector2D position) {
            setPosition(position);
            return this;
        }

        /**
         * @param volume modified volume of the source.
         * @return instance
         */
        public ModifySound volume(float volume) {
            setVolume(volume);
            return this;
        }

        /**
         * @param pitch modified pitch of the source.
         * @return instance
         */
        public ModifySound pitch(float pitch) {
            setPitch(pitch);
            return this;
        }

        /**
         * @param distance modified max distance where you can hear the source.
         * @return instance
         */
        public ModifySound refDistance(float distance) {
            setRefDistance(distance);
            return this;
        }

        /**
         * @param factor modified float how fast the volume decreases.
         * @return instance
         */
        public ModifySound rollOffFactor(float factor) {
            setRollOffFactor(factor);
            return this;
        }
    }

    public void disable() {
        for (Sound s : this.sounds.values()) {
            AL10.alDeleteSources(s.getSource());
        }
        ALC10.alcDestroyContext(this.context);
        ALC10.alcCloseDevice(this.device);
    }

    public String getName() {
        return name;
    }
}
