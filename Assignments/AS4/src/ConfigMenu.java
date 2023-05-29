//import javafx.geometry.Pos;
//import javafx.scene.Scene;
//import javafx.scene.image.Image;
//import javafx.scene.input.KeyCode;
//import javafx.scene.input.KeyEvent;
//import javafx.scene.layout.*;
//import javafx.scene.media.AudioClip;
//import javafx.scene.paint.Color;
//import javafx.scene.text.Font;
//import javafx.scene.text.FontWeight;
//import javafx.scene.text.Text;
//import javafx.stage.Stage;
//
//import java.io.File;
//
//public class ConfigMenu {
//    public static void display(AudioClip titleTheme, int SCALE, Stage window) {
//        // Create a background image object
//        BackgroundImage background = new BackgroundImage(new Image("assets/background/1.png"),
//                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
//                BackgroundPosition.DEFAULT, new BackgroundSize(SCALE * 256, SCALE * 240,
//                false, false, false, false));
//
//        VBox pane = new VBox(SCALE * 0.1);
//        pane.setAlignment(Pos.TOP_CENTER);
//        pane.setBackground(new Background(background));
//
//        Text navigateText = new Text("USE ARROW KEYS TO NAVIGATE");
//        navigateText.setFont(Font.font("Arial", FontWeight.BOLD, SCALE * 8)); // Set the font and size as desired
//        navigateText.setFill(Color.ORANGE);
//
//        Text startText = new Text("PRESS ENTER TO START");
//        startText.setFont(Font.font("Arial", FontWeight.BOLD, SCALE * 8)); // Set the font and size as desired
//        startText.setFill(Color.ORANGE);
//
//        Text exitText = new Text("PRESS ESC TO EXIT");
//        exitText.setFont(Font.font("Arial", FontWeight.BOLD, SCALE * 8)); // Set the font and size as desired
//        exitText.setFill(Color.ORANGE);
//
//        // Add the text node to the pane
//        pane.getChildren().addAll(navigateText, startText, exitText);
//
//        AudioClip introTheme = new AudioClip(new File("assets/effects/Intro.mp3").toURI().toString());
//
//        // Create the scene and set the pane as its root
//        Scene scene = new Scene(pane, SCALE * 256, SCALE * 240);
//        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
//            if (key.getCode() == KeyCode.ENTER){
//                titleTheme.stop();
//                introTheme.play();
//            } else if (key.getCode() == KeyCode.ESCAPE) {
//                MainMenu.display(SCALE, window);
//            }
//        });
//
//        window.setScene(scene);
//    }
//}
