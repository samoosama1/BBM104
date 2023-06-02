import javafx.scene.media.AudioClip;

import java.io.File;

public class SoundPlayer {
    public static AudioClip titleTheme = new AudioClip(new File("assets/effects/Title.mp3").toURI().toString());
    public static AudioClip introTheme = new AudioClip(new File("assets/effects/Intro.mp3").toURI().toString());
    public static AudioClip duckFall = new AudioClip(new File("assets/effects/DuckFalls.mp3").toURI().toString());
    public static AudioClip levelComplete = new AudioClip(new File("assets/effects/LevelCompleted.mp3").toURI().toString());
    public static AudioClip gameOver = new AudioClip(new File("assets/effects/GameOver.mp3").toURI().toString());
    public static AudioClip gunshot = new AudioClip(new File("assets/effects/Gunshot.mp3").toURI().toString());

    public static void playSound(AudioClip sound) {
            sound.play();
    }

    public static void stopSound(AudioClip sound) {
            sound.stop();
    }
}
