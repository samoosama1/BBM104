import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Scale;
import javafx.util.Duration;
import javafx.stage.Stage;

public class Level1 extends Level{
    public Duck duck;
    private Scene level;
    public Timeline flyingAnimation;
    public Timeline dyingAnimation;
    public Timeline flyingMovement;
    public Timeline fallingMovement;
    private int numOfBullets = 3;
    private boolean enableMouseClick = true;
    private boolean enableEnter = false;

    public Level1(Stage window) {
        Scale mirrorScale = new Scale(-1, 1);

        Pane root = new Pane();
        duck = new Duck(200, 100, 1, 0);

        flyingAnimation = new Timeline(
                new KeyFrame(Duration.seconds(0.2), event -> duck.getImageView().setImage(Duck.blackDuckImages.get(3))),
                new KeyFrame(Duration.seconds(0.4), event -> duck.getImageView().setImage(Duck.blackDuckImages.get(4))),
                new KeyFrame(Duration.seconds(0.6), event -> duck.getImageView().setImage(Duck.blackDuckImages.get(5))));
        flyingAnimation.setCycleCount(Timeline.INDEFINITE);
        flyingAnimation.play();

        flyingMovement = new Timeline(new KeyFrame(new Duration(16), event -> {
            duck.setDuckX(duck.getDuckX() + duck.getDeltaX());
            duck.setDuckY(duck.getDuckY() + duck.getFlyingDeltaY());

            if (duck.getDuckX() <= 0 || Main.WIDTH - duck.getDuckX() <= DuckMaker.HITBOX_SIZE) {
                duck.setDeltaX(duck.getDeltaX() * -1);
                if (duck.getImageView().getTransforms().isEmpty()) {
                    duck.getImageView().getTransforms().add(0, mirrorScale);
                    duck.getImageView().setTranslateX(Duck.DUCK_WIDTH);
                } else {
                    duck.getImageView().getTransforms().remove(0);
                    duck.getImageView().setTranslateX(0);
                }
            }
            duck.getHitbox().setTranslateX(duck.getDuckX());
        }));
        flyingMovement.setCycleCount(Timeline.INDEFINITE);
        flyingMovement.play();

        dyingAnimation = new Timeline(
                new KeyFrame(Duration.ZERO, event -> duck.getImageView().setImage(Duck.blackDuckImages.get(6))),
                new KeyFrame(Duration.seconds(0.3), event -> {
                    duck.getImageView().setImage(Duck.blackDuckImages.get(7));
                    fallingMovement.play();
                }));

        fallingMovement = new Timeline(
                new KeyFrame(Duration.millis(16), event -> {
                    duck.setDuckY(duck.getDuckY() + duck.getFallingDeltaY());
                    if (Main.HEIGHT - duck.getHitbox().getTranslateY() <= DuckMaker.HITBOX_SIZE) {
                        fallingMovement.stop();
                    }

                    duck.getHitbox().setTranslateY(duck.getDuckY());
                })
        );
        fallingMovement.setCycleCount(Timeline.INDEFINITE);

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
                    flyingAnimation.stop();
                    flyingMovement.stop();
                    dyingAnimation.play();
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
