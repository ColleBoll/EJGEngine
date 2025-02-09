package org.collebol.audio;

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
 *     soundPlayer.playSound(sound.getid());
 * </pre></blockquote>
 *
 * @author ColleBol - <a href="mailto:contact@collebol.org">contact@collebol.org</a>
 * @since 1.0-dev
 */
public class SoundPlayer {

    private long device;
    private long context;

    private Map<Integer, Sound> sounds = new HashMap<>();

    /**
     * SoundPlayer constructor.
     */
    public SoundPlayer() {
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
        this.sounds.put(sound.getid(), sound);
    }

    /**
     * Play a registered sound by its id.
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
        AL10.alSourcef(pointer, AL10.AL_GAIN, sound.getVolume());
        AL10.alSourcef(pointer, AL10.AL_PITCH, sound.getPitch());
        AL10.alSourcef(pointer, AL10.AL_MAX_DISTANCE, sound.getMaxDistance());
        AL10.alSourcef(pointer, AL10.AL_ROLLOFF_FACTOR, sound.getRollOffFactor());
        AL10.alSourcePlay(pointer);
    }

    /**
     * Play a modified sound with custom properties to an already existing sound.
     *
     * @param sound the modified sound.
     */
    public void playModifiedSound(ModifySound sound) {
        if (!this.sounds.containsKey(sound.id))
            throw new RuntimeException("Invalid Sound id: " + sound.id + ". Register Sound before using!");
        if (this.sounds.get(sound.id).getPath() == null)
            throw new RuntimeException("Sound path equals null. Please, set a sound path before using!");
        int pointer = this.sounds.get(sound.id).getSource();
        AL10.alSourcef(pointer, AL10.AL_GAIN, sound.volume);
        AL10.alSourcef(pointer, AL10.AL_PITCH, sound.pitch);
        AL10.alSourcef(pointer, AL10.AL_MAX_DISTANCE, sound.maxDistance);
        AL10.alSourcef(pointer, AL10.AL_ROLLOFF_FACTOR, sound.rollOffFactor);
        AL10.alSourcePlay(pointer);
    }

    /**
     * The ModifySound class is used to modify sound properties before playing.
     */
    public static class ModifySound {
        private int id;
        private float volume = 1.0f;
        private float pitch = 1.0f;
        private float maxDistance = 100.0f;
        private float rollOffFactor = 1.0f;

        /**
         * @param id the id of the sound you want to modify.
         * @return
         */
        public ModifySound id(int id) {
            this.id = id;
            return this;
        }

        /**
         * @param volume modified volume of the source.
         * @return instance
         */
        public ModifySound volume(float volume) {
            this.volume = volume;
            return this;
        }

        /**
         * @param pitch modified pitch of the source.
         * @return instance
         */
        public ModifySound pitch(float pitch) {
            this.pitch = pitch;
            return this;
        }

        /**
         * @param distance modified max distance where you can hear the source.
         * @return instance
         */
        public ModifySound maxDistance(float distance) {
            this.maxDistance = distance;
            return this;
        }

        /**
         * @param factor modified float how fast the volume decreases.
         * @return instance
         */
        public ModifySound rollOffFactor(float factor) {
            this.rollOffFactor = factor;
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
}
