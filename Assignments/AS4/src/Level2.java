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
import javafx.util.Duration;

public class Level2 extends Level{
    public Level2(Stage window, int numOfDucks) {
        super(numOfDucks, window);

        Text levelText = new Text("Level 2/6");
        levelText.setFont(Font.font("Arial", FontWeight.BOLD, Main.SCALE * 8)); // Set the font and size as desired
        levelText.setTranslateY(Main.SCALE * 8);
        levelText.setTranslateX(Main.WIDTH * 0.42);
        levelText.setFill(Color.ORANGE);

        Duck duck = new DiagonalDuck(Main.WIDTH * 0.5, Main.HEIGHT * 0.5, 1, -1, "red");
        getDuckList().add(duck);

        getRoot().getChildren().add(0, duck.getHitbox());
        getRoot().getChildren().add(levelText);

        getLevel().setOnKeyPressed(event1 -> {
            if (isEnableEnter()) {
                if (isLevelPassed()) {
                    if (event1.getCode() == KeyCode.ENTER) {
                        SoundPlayer.stopSound(SoundPlayer.levelComplete);
                        window.setScene(new Level3(window, 2).getLevel());
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
