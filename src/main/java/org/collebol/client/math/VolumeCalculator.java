package org.collebol.client.math;

import org.collebol.client.audio.Sound;
import org.collebol.shared.math.Vector2D;

/**
 * The VolumeCalculator class is responsible for calculating the volume of a sound based on its position relative to the listener's position.
 * It uses the reference distance and roll-off factor of the sound to determine how the volume decreases with distance.
 *
 * @author ColleBol - <a href="mailto:contact@collebol.org">contact@collebol.org</a>
 * @since 1.0-dev
 */
public class VolumeCalculator {

    /**
     * Calculates the volume of a sound based on its position relative to the listener's position.
     *
     * @param sound the sound whose volume is to be calculated
     * @param listenerPosition the position of the listener
     * @return the calculated volume
     */
    public static float calculate(Sound sound, Vector2D listenerPosition){
        float distance = sound.getPosition().distance(listenerPosition);

        if(distance < sound.getRefDistance()){
            return 1.0f;
        }

        float volume = sound.getVolume() / (1.0f + sound.getRollOffFactor() * (distance - sound.getRefDistance()));

        return Math.max(0.0f, volume);
    }
}
