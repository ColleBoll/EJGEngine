package org.collebol.audio;

import org.lwjgl.openal.AL10;
import org.lwjgl.stb.STBVorbis;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.libc.LibCStdlib;

import java.nio.IntBuffer;
import java.nio.ShortBuffer;

/**
 * The Sound class represents an audio object which you can play.
 * It handles loading sound files, setting audio properties, and managing OpenAL sources.
 *
 * <p>Usage:</p>
 * <blockquote><pre>
 *     Sound sound = new Sound(new Sound.SoundBuilder()
 *         .path("path/to/sound.ogg")
 *         .id(1)
 *         .volume(1.0f)
 *         .pitch(1.0f)
 *         .maxDistance(100.0f)
 *         .rollOffFactor(1.0f)
 *     );
 * </pre></blockquote>
 *
 * @author ColleBol - <a href="mailto:contact@collebol.org">contact@collebol.org</a>
 * @since 1.0-dev
 */
public class Sound {

    private int id;
    private int sourcePointer;
    private String path;
    private float volume;
    private float pitch;
    private float maxDistance;
    private float rollOffFactor;

    /**
     * Sound constructor.
     *
     * @param builder sound builder.
     */
    public Sound(SoundBuilder builder) {
        this.id = builder.id;
        this.path = builder.path;
        this.volume = builder.volume;
        this.pitch = builder.pitch;
        this.maxDistance = builder.maxDistance;
        this.rollOffFactor = builder.rollOffFactor;

        ShortBuffer rawAudioBuffer;

        int channels;
        int sampleRate;

        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer channelsBuffer = stack.mallocInt(1);
            IntBuffer sampleRateBuffer = stack.callocInt(1);

            rawAudioBuffer = STBVorbis.stb_vorbis_decode_filename(path, channelsBuffer, sampleRateBuffer);

            channels = channelsBuffer.get(0);
            sampleRate = sampleRateBuffer.get(0);
        }
        int format = -1;
        if (channels == 1) {
            format = AL10.AL_FORMAT_MONO16;
        } else if (channels == 2) {
            format = AL10.AL_FORMAT_STEREO16;
        }

        int bufferPointer = AL10.alGenBuffers();
        AL10.alBufferData(bufferPointer, format, rawAudioBuffer, sampleRate);
        LibCStdlib.free(rawAudioBuffer);
        this.sourcePointer = AL10.alGenSources();
        AL10.alSourcei(this.sourcePointer, AL10.AL_BUFFER, bufferPointer);
    }

    /**
     * The SoundBuilder class is used to construct {@code Sound} objects with specific properties.
     */
    public static class SoundBuilder {
        private String path = null;
        private int id;
        private float volume = 1.0f;
        private float pitch = 1.0f;
        private float maxDistance = 100.0f;
        private float rollOffFactor = 1.0f;

        /**
         * @param path the place where the sound file is located.
         * @return instance.
         */
        public SoundBuilder path(String path) {
            this.path = path;
            return this;
        }

        /**
         * @param id unique id of the sound.
         * @return instance.
         */
        public SoundBuilder id(int id) {
            this.id = id;
            return this;
        }

        /**
         * @param volume default volume of the source.
         * @return instance
         */
        public SoundBuilder volume(float volume) {
            this.volume = volume;
            return this;
        }

        /**
         * @param pitch default pitch of the source.
         * @return instance
         */
        public SoundBuilder pitch(float pitch) {
            this.pitch = pitch;
            return this;
        }

        /**
         * @param distance default max distance where you can hear the source.
         * @return instance
         */
        public SoundBuilder maxDistance(float distance) {
            this.maxDistance = distance;
            return this;
        }

        /**
         * @param factor default float how fast the volume decreases.
         * @return instance
         */
        public SoundBuilder rollOffFactor(float factor) {
            this.rollOffFactor = factor;
            return this;
        }
    }

    public int getSource() {
        return this.sourcePointer;
    }

    public int getid() {
        return this.id;
    }

    public String getPath() {
        return path;
    }

    public float getVolume() {
        return this.volume;
    }

    public float getPitch() {
        return this.pitch;
    }

    public float getMaxDistance() {
        return this.maxDistance;
    }

    public float getRollOffFactor() {
        return this.rollOffFactor;
    }
}
