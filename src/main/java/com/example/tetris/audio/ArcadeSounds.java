package com.example.tetris.audio;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.SourceDataLine;

/**
 * Small synthesized 8-bit-style effects. No external sound files or dependencies required.
 */
public final class ArcadeSounds {
    private static final float SAMPLE_RATE = 22_050f;
    private static final AudioFormat FORMAT = new AudioFormat(SAMPLE_RATE, 8, 1, true, false);

    private ArcadeSounds() {
    }

    public static void move() {
        playAsync(new int[]{660}, new int[]{35});
    }

    public static void rotate() {
        playAsync(new int[]{520, 780}, new int[]{35, 45});
    }

    public static void drop() {
        playAsync(new int[]{180, 110}, new int[]{65, 85});
    }

    public static void lineClear(int lines) {
        int[] notes = switch (Math.min(lines, 4)) {
            case 1 -> new int[]{660, 880};
            case 2 -> new int[]{660, 880, 1040};
            case 3 -> new int[]{660, 880, 1040, 1320};
            default -> new int[]{660, 880, 1040, 1320, 1560};
        };
        int[] durations = new int[notes.length];
        java.util.Arrays.fill(durations, 65);
        playAsync(notes, durations);
    }

    public static void gameOver() {
        playAsync(new int[]{440, 330, 220, 165}, new int[]{90, 100, 120, 180});
    }

    private static void playAsync(int[] frequencies, int[] durationsMs) {
        Thread soundThread = new Thread(() -> {
            try (SourceDataLine line = AudioSystem.getSourceDataLine(FORMAT)) {
                line.open(FORMAT);
                line.start();
                for (int i = 0; i < frequencies.length; i++) {
                    byte[] samples = squareWave(frequencies[i], durationsMs[i]);
                    line.write(samples, 0, samples.length);
                }
                line.drain();
            } catch (Exception ignored) {
                // Sound is optional: systems without an audio device can still play the game.
            }
        }, "tetris-arcade-sound");
        soundThread.setDaemon(true);
        soundThread.start();
    }

    private static byte[] squareWave(int frequency, int durationMs) {
        int count = Math.max(1, (int) (SAMPLE_RATE * durationMs / 1000));
        byte[] samples = new byte[count];
        int period = Math.max(2, (int) (SAMPLE_RATE / frequency));
        for (int i = 0; i < count; i++) {
            // Short fade-in/out avoids clicks while keeping the crunchy arcade character.
            double edge = Math.min(1.0, Math.min(i / 100.0, (count - i) / 180.0));
            int amplitude = (int) (72 * Math.max(0, edge));
            samples[i] = (byte) ((i % period) < period / 2 ? amplitude : -amplitude);
        }
        return samples;
    }
}
