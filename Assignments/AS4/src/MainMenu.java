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

public class MainMenu {
    public static Scene mainMenu;

    static {
        SoundPlayer.titleTheme.setCycleCount(AudioClip.INDEFINITE);
        SoundPlayer.playSound(SoundPlayer.titleTheme);

        Image welcome = new Image("assets/welcome/1.png");

        // Create a background image object
        BackgroundImage background = new BackgroundImage(welcome,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT, new BackgroundSize(Main.SCALE * 256, Main.SCALE * 240,
                false, false, false, false));

        // Create a root and set the background
        VBox root = new VBox(Main.SCALE * 3.3);
        root.setAlignment(Pos.CENTER);
        root.setBackground(new Background(background));

        // Create the text node

        Text startText = new Text("PRESS ENTER TO START");
        startText.setFont(Font.font("Arial", FontWeight.BOLD, Main.SCALE * 16)); // Set the font and size as desired
        startText.setFill(Color.ORANGE);
        startText.setTranslateY(Main.SCALE * 48);

        Text exitText = new Text("PRESS ESC TO EXIT");
        exitText.setFont(Font.font("Arial", FontWeight.BOLD, Main.SCALE * 16)); // Set the font and size as desired
        exitText.setFill(Color.ORANGE);
        exitText.setTranslateY(Main.SCALE * 48);

        // Add the text node to the root
        root.getChildren().addAll(startText, exitText);

        mainMenu = new Scene(root, Main.SCALE * 256, Main.SCALE * 240);
    }

    public static void addEventHandler(Stage window) {
        mainMenu.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if (key.getCode() == KeyCode.ENTER){
                window.setScene(ConfigMenu.configMenu);
            } else if (key.getCode() == KeyCode.ESCAPE) {
                System.exit(1);
            }
        });
    }
}
