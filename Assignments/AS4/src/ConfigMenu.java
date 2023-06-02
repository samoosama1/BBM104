import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ConfigMenu {
    public static Scene configMenu;

    static {
        VBox pane = new VBox(Main.SCALE * 0.1);
        pane.setAlignment(Pos.TOP_CENTER);
        BackgroundManager.setCurrentBackground(pane);

        Text navigateText = new Text("USE ARROW KEYS TO NAVIGATE");
        navigateText.setFont(Font.font("Arial", FontWeight.BOLD, Main.SCALE * 8)); // Set the font and size as desired
        navigateText.setFill(Color.ORANGE);

        Text startText = new Text("PRESS ENTER TO START");
        startText.setFont(Font.font("Arial", FontWeight.BOLD, Main.SCALE * 8)); // Set the font and size as desired
        startText.setFill(Color.ORANGE);

        Text exitText = new Text("PRESS ESC TO EXIT");
        exitText.setFont(Font.font("Arial", FontWeight.BOLD, Main.SCALE * 8)); // Set the font and size as desired
        exitText.setFill(Color.ORANGE);

        pane.getChildren().addAll(navigateText, startText, exitText);
        CursorManager.setCurrentImage(pane);

        configMenu = new Scene(pane, Main.SCALE * 256, Main.SCALE * 240);
    }

    public static void addEventHandler(Stage window) {
        configMenu.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case ENTER:
                    SoundPlayer.stopSound(SoundPlayer.titleTheme);
//                    SoundPlayer.playSound(SoundPlayer.introTheme);
//                    while (SoundPlayer.introTheme.isPlaying()) {
//                        assert true;
//                    }
                    window.setScene(Level1.level);
                    break;
                case ESCAPE:
                    window.setScene(MainMenu.mainMenu);
                    break;
                case UP:
                    CursorManager.setNextImage((Pane) configMenu.getRoot());
                    CursorManager.setNextCursor(Level1.level);
                    break;
                case DOWN:
                    CursorManager.setPrevImage((Pane) configMenu.getRoot());
                    CursorManager.setPrevCursor(Level1.level);
                    break;
                case LEFT:
                    BackgroundManager.setPrevBackground((Pane) configMenu.getRoot());
                    Level1.setBackground();
                    break;
                case RIGHT:
                    BackgroundManager.setNextBackground((Pane) configMenu.getRoot());
                    Level1.setBackground();
                    break;
            }
        });
    }
}
