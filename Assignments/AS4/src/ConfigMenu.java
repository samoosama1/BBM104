import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class ConfigMenu {
    public static Pane configMenuMaker(int SCALE) {
        VBox pane = new VBox(SCALE * 0.1);
        pane.setAlignment(Pos.TOP_CENTER);
        BackgroundManager.setInitialBackground(pane);

        Text navigateText = new Text("USE ARROW KEYS TO NAVIGATE");
        navigateText.setFont(Font.font("Arial", FontWeight.BOLD, SCALE * 8)); // Set the font and size as desired
        navigateText.setFill(Color.ORANGE);

        Text startText = new Text("PRESS ENTER TO START");
        startText.setFont(Font.font("Arial", FontWeight.BOLD, SCALE * 8)); // Set the font and size as desired
        startText.setFill(Color.ORANGE);

        Text exitText = new Text("PRESS ESC TO EXIT");
        exitText.setFont(Font.font("Arial", FontWeight.BOLD, SCALE * 8)); // Set the font and size as desired
        exitText.setFill(Color.ORANGE);

        CursorManager.setInitialImage(pane);

        // Add the text node to the pane
        pane.getChildren().addAll(navigateText, startText, exitText);
        return pane;
    }
}
