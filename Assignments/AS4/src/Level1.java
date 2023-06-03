import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Scale;
import javafx.util.Duration;
import javafx.stage.Stage;

public class Level1 extends Level{
    public HorizontalDuck duck;
    private Scene level;
    private int numOfBullets = 3;
    private boolean enableMouseClick = true;
    private boolean enableEnter = false;

    public Level1(Stage window) {

        Pane root = new Pane();
        duck = new HorizontalDuck(200, 100, 1, 0, "blue");
        root.getChildren().add(duck.getHitbox());
        level = new Scene(root, Main.WIDTH, Main.HEIGHT);
        CursorManager.setCurrentCursor(level);
        BackgroundManager.setCurrentBackground(root);
        BackgroundManager.setCurrentForeground(root);

        level.setOnMouseClicked(event -> {
            if (enableMouseClick) {
                SoundPlayer.playSound(SoundPlayer.gunshot);
                numOfBullets--;

                double clickX = event.getX();
                double clickY = event.getY();

                Pane hitbox = duck.getHitbox();
                if (clickX >= hitbox.getTranslateX() && clickX <= hitbox.getTranslateX() + DuckMaker.HITBOX_SIZE &&
                        clickY >= hitbox.getTranslateY() && clickY <= hitbox.getTranslateY() + DuckMaker.HITBOX_SIZE) {
                    enableMouseClick = false;
                    enableEnter = true;
                    SoundPlayer.playSound(SoundPlayer.duckFall);
                    SoundPlayer.playSound(SoundPlayer.levelComplete);
                    duck.die();
                } else if (numOfBullets == 0) {
                    enableMouseClick = false;
                    SoundPlayer.playSound(SoundPlayer.gameOver);
                }
            }
        });

        level.setOnKeyPressed(event1 -> {
            if (enableEnter) {
                if (event1.getCode() == KeyCode.ENTER) {
                    SoundPlayer.stopSound(SoundPlayer.levelComplete);
                    Level2 level2 = new Level2(window);
                    window.setScene(level2.getLevel());
                }
            }
        });
    }

    public Scene getLevel() {
        return level;
    }
}
