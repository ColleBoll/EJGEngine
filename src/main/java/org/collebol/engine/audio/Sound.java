package org.collebol.engine.audio;

import org.collebol.shared.math.Vector2D;
import org.lwjgl.openal.AL10;
import org.lwjgl.stb.STBVorbis;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.libc.LibCStdlib;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import java.nio.file.Files;

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
    private Vector2D position;
    private float volume;
    private float pitch;
    private float refDistance;
    private float rollOffFactor;

    /**
     * Sound constructor.
     *
     * @param builder sound builder.
     */
    public Sound(SoundBuilder builder) {
        this.id = builder.id;
        this.path = builder.path;
        this.position = builder.position;
        this.volume = builder.volume;
        this.pitch = builder.pitch;
        this.refDistance = builder.refDistance;
        this.rollOffFactor = builder.rollOffFactor;

        ShortBuffer rawAudioBuffer;

        int channels;
        int sampleRate;

        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer channelsBuffer = stack.mallocInt(1);
            IntBuffer sampleRateBuffer = stack.callocInt(1);

            rawAudioBuffer = STBVorbis.stb_vorbis_decode_filename(extractResourceToTempFile(this.path), channelsBuffer, sampleRateBuffer);

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
        private Vector2D position = new Vector2D(0, 0);
        private float volume = 1.0f;
        private float pitch = 1.0f;
        private float refDistance = 1.0f;
        private float rollOffFactor = 1.0f;

        /**
         * SoundBuilder constructor.
         *
         * @param path the path to the sound file.
         * @param id   the unique id of the sound.
         */
        public SoundBuilder(int id, String path) {
            this.path = path;
            this.id = id;
        }

        /**
         * @param position position where the sound must play from.
         * @return instance.
         */
        public SoundBuilder position(Vector2D position) {
            this.position = position;
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
         * @param refDistance default the distance where you can hear the sound at full volume.
         * @return instance
         */
        public SoundBuilder refDistance(float refDistance) {
            this.refDistance = refDistance;
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

    private String extractResourceToTempFile(String resourcePath) {
        try (InputStream inputStream = getClass().getResourceAsStream(resourcePath)) {
            if (inputStream == null) {
                throw new IllegalArgumentException("Resource not found: " + resourcePath);
            }

            File tempFile = Files.createTempFile("sound", ".ogg").toFile();
            tempFile.deleteOnExit();

            try (FileOutputStream outputStream = new FileOutputStream(tempFile)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            }

            return tempFile.getAbsolutePath();
        } catch (IOException e) {
            throw new RuntimeException("Failed to load resource: " + resourcePath, e);
        }
    }

    public int getSource() {
        return this.sourcePointer;
    }

    public int getId() {
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

    public float getRefDistance() {
        return this.refDistance;
    }

    public float getRollOffFactor() {
        return this.rollOffFactor;
    }

    public Vector2D getPosition() {
        return position;
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public void setRefDistance(float refDistance) {
        this.refDistance = refDistance;
    }

    public void setRollOffFactor(float rollOffFactor) {
        this.rollOffFactor = rollOffFactor;
    }
}
