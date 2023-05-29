import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.File;


public class Main extends Application {
    private final int SCALE = 3;

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

        // Load the background image
        Image welcome = new Image("assets/welcome/1.png");

        // Create a background image object
        BackgroundImage background = new BackgroundImage(welcome,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT, new BackgroundSize(SCALE * 256, SCALE * 240,
                false, false, false, false));

        // Create a pane and set the background
        VBox pane = new VBox(SCALE * 3.3);
        pane.setAlignment(Pos.CENTER);
        pane.setBackground(new Background(background));

        // Create the text node

        Text startText = new Text("PRESS ENTER TO START");
        startText.setFont(Font.font("Arial", FontWeight.BOLD, SCALE * 16)); // Set the font and size as desired
        startText.setFill(Color.ORANGE);
        startText.setTranslateY(SCALE * 48);

        Text exitText = new Text("PRESS ESC TO EXIT");
        exitText.setFont(Font.font("Arial", FontWeight.BOLD, SCALE * 16)); // Set the font and size as desired
        exitText.setFill(Color.ORANGE);
        exitText.setTranslateY(SCALE * 48);

        // Add the text node to the pane
        pane.getChildren().addAll(startText, exitText);


        // Create the scene and set the pane as its root
        Scene mainMenu = new Scene(pane, SCALE * 256, SCALE * 240);


        // Create a background image object
        BackgroundImage background1 = new BackgroundImage(new Image("assets/background/1.png"),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT, new BackgroundSize(SCALE * 256, SCALE * 240,
                false, false, false, false));

        VBox pane1 = new VBox(SCALE * 0.1);
        pane1.setAlignment(Pos.TOP_CENTER);
        pane1.setBackground(new Background(background1));

        Text navigateText = new Text("USE ARROW KEYS TO NAVIGATE");
        navigateText.setFont(Font.font("Arial", FontWeight.BOLD, SCALE * 8)); // Set the font and size as desired
        navigateText.setFill(Color.ORANGE);

        Text startText1 = new Text("PRESS ENTER TO START");
        startText1.setFont(Font.font("Arial", FontWeight.BOLD, SCALE * 8)); // Set the font and size as desired
        startText1.setFill(Color.ORANGE);

        Text exitText1 = new Text("PRESS ESC TO EXIT");
        exitText1.setFont(Font.font("Arial", FontWeight.BOLD, SCALE * 8)); // Set the font and size as desired
        exitText1.setFill(Color.ORANGE);

        // Add the text node to the pane
        pane1.getChildren().addAll(navigateText, startText1, exitText1);


        Scene configMenu = new Scene(pane1, SCALE * 256, SCALE * 240);

        // Create the scene and set the pane as its root
        configMenu.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if (key.getCode() == KeyCode.ENTER){
                SoundPlayer.stopSound(titleTheme);
                SoundPlayer.playSound(introTheme);
            } else if (key.getCode() == KeyCode.ESCAPE) {
                window.setScene(mainMenu);
            }
        });

        mainMenu.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if (key.getCode() == KeyCode.ENTER){
                window.setScene(configMenu);
            } else if (key.getCode() == KeyCode.ESCAPE) {
                System.exit(1);
            }
        });

        window.setScene(mainMenu);
        window.show();
    }
}