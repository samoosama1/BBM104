import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Level4 extends Level{
    public Level4(Stage window, int numOfDucks) {
        super(numOfDucks, window);

        Text levelText = new Text("Level 4/6");
        levelText.setFont(Font.font("Arial", FontWeight.BOLD, Main.SCALE * 8)); // Set the font and size as desired
        levelText.setTranslateY(Main.SCALE * 8);
        levelText.setTranslateX(Main.WIDTH * 0.42);
        levelText.setFill(Color.ORANGE);

        Duck duck1 = new DiagonalDuck(Main.WIDTH * 0.2, Main.HEIGHT * 0.8, 2, -2, "black");
        Duck duck2 = new DiagonalDuck(Main.WIDTH * 0.8, Main.HEIGHT * 0.2, -2, 2, "blue");
        Duck duck3 = new HorizontalDuck(Main.WIDTH * 0.3, Main.HEIGHT * 0.3, -3, 3, "red");

        getDuckList().add(duck1);
        getDuckList().add(duck2);
        getDuckList().add(duck3);

        getRoot().getChildren().add(0, duck1.getHitbox());
        getRoot().getChildren().add(0, duck2.getHitbox());
        getRoot().getChildren().add(0, duck3.getHitbox());
        getRoot().getChildren().add(levelText);

        getLevel().setOnKeyPressed(event1 -> {
            if (isEnableEnter()) {
                if (isLevelPassed()) {
                    if (event1.getCode() == KeyCode.ENTER) {
                        SoundPlayer.stopSound(SoundPlayer.levelComplete);
                        window.setScene(new Level5(window, 4).getLevel());
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
