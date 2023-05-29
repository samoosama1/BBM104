//import javafx.geometry.Pos;
//import javafx.scene.Scene;
//import javafx.scene.image.Image;
//import javafx.scene.input.KeyCode;
//import javafx.scene.input.KeyEvent;
//import javafx.scene.media.AudioClip;
//import javafx.scene.paint.Color;
//import javafx.scene.layout.*;
//import javafx.scene.text.Font;
//import javafx.scene.text.FontWeight;
//import javafx.scene.text.Text;
//import javafx.stage.Stage;
//
//import java.io.File;
//
//public class MainMenu {
//    public static void display(int SCALE, Stage window) {
//
//        // Load the background image
//        Image backgroundImage = new Image("assets/welcome/1.png");
//
//        // Create a background image object
//        BackgroundImage background = new BackgroundImage(backgroundImage,
//                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
//                BackgroundPosition.DEFAULT, new BackgroundSize(SCALE * 256, SCALE * 240,
//                false, false, false, false));
//
//        // Create a pane and set the background
//        VBox pane = new VBox(SCALE * 3.3);
//        pane.setAlignment(Pos.CENTER);
//        pane.setBackground(new Background(background));
//
//        // Create the text node
//
//        Text startText = new Text("PRESS ENTER TO START");
//        startText.setFont(Font.font("Arial", FontWeight.BOLD, SCALE * 16)); // Set the font and size as desired
//        startText.setFill(Color.ORANGE);
//        startText.setTranslateY(SCALE * 48);
//
//        Text exitText = new Text("PRESS ESC TO EXIT");
//        exitText.setFont(Font.font("Arial", FontWeight.BOLD, SCALE * 16)); // Set the font and size as desired
//        exitText.setFill(Color.ORANGE);
//        exitText.setTranslateY(SCALE * 48);
//
//        // Add the text node to the pane
//        pane.getChildren().addAll(startText, exitText);
//
//        AudioClip titleTheme = new AudioClip(new File("assets/effects/Title.mp3").toURI().toString());
//        titleTheme.setCycleCount(AudioClip.INDEFINITE);
//
//        // Create the scene and set the pane as its root
//        Scene scene = new Scene(pane, SCALE * 256, SCALE * 240);
//        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
//            if (key.getCode() == KeyCode.ENTER){
//                ConfigMenu.display(titleTheme ,SCALE, window);
//            } else if (key.getCode() == KeyCode.ESCAPE) {
//                System.exit(1);
//            }
//        });
//
//        window.setScene(scene);
//        window.show();
//    }
//}
