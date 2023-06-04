import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Level5 extends Level{
    public Level5(Stage window, int numOfDucks) {
        super(numOfDucks, window);

        Text levelText = new Text("Level 5/6");
        levelText.setFont(Font.font("Arial", FontWeight.BOLD, DuckHunt.SCALE * 8)); // Set the font and size as desired
        levelText.setTranslateY(DuckHunt.SCALE * 8);
        levelText.setTranslateX(DuckHunt.WIDTH * 0.42);
        levelText.setFill(Color.ORANGE);

        Duck duck1 = new DiagonalDuck(DuckHunt.WIDTH * 0.2, DuckHunt.HEIGHT * 0.8, 2, -1, "black");
        Duck duck2 = new DiagonalDuck(DuckHunt.WIDTH * 0.8, DuckHunt.HEIGHT * 0.2, -2, 2, "blue");
        Duck duck3 = new HorizontalDuck(DuckHunt.WIDTH * 0.33, DuckHunt.HEIGHT * 0.3, -4, 0, "red");
        Duck duck4 = new DiagonalDuck(DuckHunt.WIDTH * 0.5, DuckHunt.HEIGHT * 0.5, 2.5, -2.5, "red");

        getDuckList().add(duck1);
        getDuckList().add(duck2);
        getDuckList().add(duck3);
        getDuckList().add(duck4);

        getRoot().getChildren().add(0, duck1.getHitbox());
        getRoot().getChildren().add(0, duck2.getHitbox());
        getRoot().getChildren().add(0, duck3.getHitbox());
        getRoot().getChildren().add(0, duck4.getHitbox());
        getRoot().getChildren().add(levelText);

        getLevel().setOnKeyPressed(event1 -> {
            if (isEnableEnter()) {
                if (isLevelPassed()) {
                    if (event1.getCode() == KeyCode.ENTER) {
                        SoundPlayer.stopSound(SoundPlayer.levelComplete);
                        window.setScene(new Level6(window, 5).getLevel());
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
