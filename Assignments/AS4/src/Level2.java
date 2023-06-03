import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Level2 {
    private Duck duck;
    private Scene level;
    private int numOfBullets = 3;
    private boolean enableMouseClick = true;
    private boolean enableEnter = false;

    public Level2(Stage window) {

        Pane root = new Pane();

        duck = new DiagonalDuck(Main.WIDTH * 0.5, Main.HEIGHT * 0.5, 1, -1, "red");
        duck.getImageView().getTransforms().add(DiagonalDuck.UP_RIGHT);
        root.getChildren().add(duck.getHitbox());

        level = new Scene(root, Main.WIDTH, Main.HEIGHT);

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
                    duck.die();
                    SoundPlayer.playSound(SoundPlayer.levelComplete);
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
                    MainMenu mainMenu = new MainMenu(window);
                    window.setScene(mainMenu.getMainMenu());
                }
            }
        });

        CursorManager.setCurrentCursor(level);
        BackgroundManager.setCurrentBackground(root);
        BackgroundManager.setCurrentForeground(root);
    }

    public Scene getLevel() {
        return level;
    }
}
