import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.imageio.stream.ImageInputStream;

public class Level2 {
    public static Scale UP_LEFT = new Scale(-1, 1);
    public static Scale DOWN_LEFT = new Scale(-1, -1);
    public static Scale UP_RIGHT = new Scale(1, 1);
    public static Scale DOWN_RIGHT = new Scale(1, -1);
    private Duck duck;
    private Timeline flyingAnimation;
    private Timeline flyingMovement;
    private Timeline dyingAnimation;
    private Timeline fallingMovement;
    private Scene level;
    private int numOfBullets = 3;
    private boolean enableMouseClick = true;
    private boolean enableEnter = false;

    public Level2(Stage window) {
        UP_RIGHT.setPivotX(Duck.DUCK_WIDTH * 0.5);
        DOWN_LEFT.setPivotX(Duck.DUCK_WIDTH * 0.5);
        UP_LEFT.setPivotX(Duck.DUCK_WIDTH * 0.5);
        DOWN_RIGHT.setPivotX(Duck.DUCK_WIDTH * 0.5);

        UP_RIGHT.setPivotY(Duck.DUCK_HEIGHT * 0.5);
        DOWN_LEFT.setPivotY(Duck.DUCK_HEIGHT * 0.5);
        UP_LEFT.setPivotY(Duck.DUCK_HEIGHT * 0.5);
        DOWN_RIGHT.setPivotY(Duck.DUCK_HEIGHT * 0.5);

        Pane root = new Pane();

        duck = new Duck(Main.WIDTH * 0.5, Main.HEIGHT * 0.5, 1, -1);
        duck.getImageView().getTransforms().add(UP_RIGHT);

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
                if (duck.getDeltaX() > 0 && duck.getFlyingDeltaY() < 0) {
                    duck.getImageView().getTransforms().set(0, UP_RIGHT);
                } else if (duck.getDeltaX() > 0 && duck.getFlyingDeltaY() > 0) {
                    duck.getImageView().getTransforms().set(0, DOWN_RIGHT);
                } else if (duck.getDeltaX() < 0 && duck.getFlyingDeltaY() < 0) {
                    duck.getImageView().getTransforms().set(0, UP_LEFT);
                } else {
                    duck.getImageView().getTransforms().set(0, DOWN_LEFT);
                }
            }

            if (duck.getDuckY() <= 0 || Main.HEIGHT - duck.getDuckY() <= DuckMaker.HITBOX_SIZE) {
                duck.setFlyingDeltaY(duck.getFlyingDeltaY() * -1);
                if (duck.getDeltaX() > 0 && duck.getFlyingDeltaY() < 0) {
                    duck.getImageView().getTransforms().set(0, UP_RIGHT);
                } else if (duck.getDeltaX() > 0 && duck.getFlyingDeltaY() > 0) {
                    duck.getImageView().getTransforms().set(0, DOWN_RIGHT);
                } else if (duck.getDeltaX() < 0 && duck.getFlyingDeltaY() < 0) {
                    duck.getImageView().getTransforms().set(0, UP_LEFT);
                } else {
                    duck.getImageView().getTransforms().set(0, DOWN_LEFT);
                }
            }
            duck.getHitbox().setTranslateX(duck.getDuckX());
            duck.getHitbox().setTranslateY(duck.getDuckY());
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
                    duck.getImageView().getTransforms().remove(0);
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
