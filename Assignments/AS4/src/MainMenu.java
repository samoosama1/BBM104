import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainMenu {
    private Scene mainMenu;

    public MainMenu(Stage window) {
        SoundPlayer.titleTheme.setCycleCount(AudioClip.INDEFINITE);
        SoundPlayer.playSound(SoundPlayer.titleTheme);

        Image welcome = new Image("assets/welcome/1.png");

        // Create a background image object
        BackgroundImage background = new BackgroundImage(welcome,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT, new BackgroundSize(DuckHunt.SCALE * 256, DuckHunt.SCALE * 240,
                false, false, false, false));

        // Create a root and set the background
        VBox root = new VBox(DuckHunt.SCALE * 3.3);
        root.setAlignment(Pos.CENTER);
        root.setBackground(new Background(background));

        // Create the text node

        Text startText = new Text("PRESS ENTER TO START");
        startText.setFont(Font.font("Arial", FontWeight.BOLD, DuckHunt.SCALE * 16)); // Set the font and size as desired
        startText.setTranslateY(DuckHunt.SCALE * 48);
        startText.setFill(Color.ORANGE);

        Text exitText = new Text("PRESS ESC TO EXIT");
        exitText.setFont(Font.font("Arial", FontWeight.BOLD, DuckHunt.SCALE * 16)); // Set the font and size as desired
        exitText.setFill(Color.ORANGE);
        exitText.setTranslateY(DuckHunt.SCALE * 48);

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.75 * 2), event -> startText.setFill(Color.ORANGE)),
                new KeyFrame(Duration.seconds(0.75), event -> { startText.setFill(Color.TRANSPARENT); }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        // Add the text node to the root
        root.getChildren().addAll(startText, exitText);

        mainMenu = new Scene(root, DuckHunt.SCALE * 256, DuckHunt.SCALE * 240);

        mainMenu.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if (key.getCode() == KeyCode.ENTER){
                ConfigMenu configMenu = new ConfigMenu(window);
                window.setScene(configMenu.getConfigMenu());
            } else if (key.getCode() == KeyCode.ESCAPE) {
                System.exit(1);
            }
        });

        CursorManager.reset();
        BackgroundManager.reset();
    }

    public Scene getMainMenu() {
        return mainMenu;
    }
}
