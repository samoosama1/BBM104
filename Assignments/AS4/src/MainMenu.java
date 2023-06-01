import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class MainMenu {
    public static Pane mainMenuMaker(int SCALE) {
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
        return pane;
    }
}
