import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Level3 extends Level {
    public Level3(Stage window, int numOfDucks) {
        super(numOfDucks, window);

        Text levelText = new Text("Level 3/6");
        levelText.setFont(Font.font("Arial", FontWeight.BOLD, Main.SCALE * 8)); // Set the font and size as desired
        levelText.setTranslateY(Main.SCALE * 8);
        levelText.setTranslateX(Main.WIDTH * 0.42);
        levelText.setFill(Color.ORANGE);

        Duck duck1 = new HorizontalDuck(100 * Main.SCALE, 16.6 * Main.SCALE, 2, 0, "black");
        Duck duck2 = new HorizontalDuck(66 * Main.SCALE, 66 * Main.SCALE, -2, 0, "red");

        getDuckList().add(duck1);
        getDuckList().add(duck2);

        getRoot().getChildren().add(0, duck1.getHitbox());
        getRoot().getChildren().add(0, duck2.getHitbox());
        getRoot().getChildren().add(levelText);

        getLevel().setOnKeyPressed(event1 -> {
            if (isEnableEnter()) {
                if (isLevelPassed()) {
                    if (event1.getCode() == KeyCode.ENTER) {
                        SoundPlayer.stopSound(SoundPlayer.levelComplete);
                        window.setScene(new Level4(window, 3).getLevel());
                    }
                } else {
                    if (event1.getCode() == KeyCode.ENTER) {
                        playAgain();
                    } else if (event1.getCode() == KeyCode.ESCAPE) {
                        returnToMainMenu();
                    }
                }
            }
        });
    }

}
