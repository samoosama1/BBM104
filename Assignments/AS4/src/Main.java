import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import java.io.File;

public class Main extends Application {
    public static final int SCALE = 3;
    AudioClip titleTheme = new AudioClip(new File("assets/effects/Title.mp3").toURI().toString());
    AudioClip introTheme = new AudioClip(new File("assets/effects/Intro.mp3").toURI().toString());
    AudioClip duckFall = new AudioClip(new File("assets/effects/DuckFalls.mp3").toURI().toString());
    AudioClip levelComplete = new AudioClip(new File("assets/effects/LevelCompleted.mp3").toURI().toString());
    AudioClip gameOver = new AudioClip(new File("assets/effects/GameOver.mp3").toURI().toString());
    AudioClip gunshot = new AudioClip(new File("assets/effects/Gunshot.mp3").toURI().toString());

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage window) throws Exception {
        titleTheme.setCycleCount(AudioClip.INDEFINITE);
        SoundPlayer.playSound(titleTheme);

        Scene mainMenu = new Scene(MainMenu.mainMenuMaker(SCALE), SCALE * 256, SCALE * 240);
        Scene configMenu = new Scene(ConfigMenu.configMenuMaker(SCALE), SCALE * 256, SCALE * 240);


        mainMenu.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if (key.getCode() == KeyCode.ENTER){
                window.setScene(configMenu);
            } else if (key.getCode() == KeyCode.ESCAPE) {
                System.exit(1);
            }
        });

        // Create the scene and set the pane as its root
        configMenu.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case ENTER:
                    SoundPlayer.stopSound(titleTheme);
                    SoundPlayer.playSound(introTheme);
                    while (introTheme.isPlaying()) {
                        assert true;
                    }
                    System.out.println("yes.");
                    break;
                case ESCAPE:
                    window.setScene(mainMenu);
                    break;
                case UP:
                    CursorManager.setNextImage((Pane) configMenu.getRoot());
                    break;
                case DOWN:
                    CursorManager.setPrevImage((Pane) configMenu.getRoot());
                    break;
                case LEFT:
                    BackgroundManager.setPrevBackground((Pane) configMenu.getRoot());
                    break;
                case RIGHT:
                    BackgroundManager.setNextBackground((Pane) configMenu.getRoot());
                    break;
            }
        });

        window.setScene(mainMenu);
        window.show();
    }

}