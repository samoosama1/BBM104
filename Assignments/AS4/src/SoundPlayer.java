import javafx.scene.media.AudioClip;

public class SoundPlayer {
    public static void playSound(AudioClip sound) {
        if (!sound.isPlaying())
            sound.play();
    }

    public static void stopSound(AudioClip sound) {
        if (sound.isPlaying())
            sound.stop();
    }
}
