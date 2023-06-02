import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Scale;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class Level1 {
    public static List<Duck> duckList = new ArrayList<>();
    public static Scene level;
    public static int numOfBullets = 6;
    static {
        Scale mirrorScale = new Scale(-1, 1);

        Pane root = new Pane();
        Duck duck1 = new Duck(200, 100, 2, 0, "Walter White");
        Duck duck2 = new Duck(200, 150, -2, 0, "Jesse Pinkman");

        duck2.getImageView().getTransforms().add(mirrorScale);
        duck2.getImageView().setTranslateX(31 * Main.SCALE);

        duckList.add(duck1);
        duckList.add(duck2);

        Timeline animation = new Timeline(new KeyFrame(Duration.seconds(0.2), event -> {
            for (Duck duck : duckList) {
                duck.getImageView().setImage(Duck.blackDuckImages.get(3));
            }
        }),
                new KeyFrame(Duration.seconds(0.4), event -> {
                    for (Duck duck : duckList) {
                        duck.getImageView().setImage(Duck.blackDuckImages.get(4));
                    }
                }),
                new KeyFrame(Duration.seconds(0.6), event -> {
                    for (Duck duck : duckList) {
                        duck.getImageView().setImage(Duck.blackDuckImages.get(5));
                    }
                }));

        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();


        Timeline movement = new Timeline(new KeyFrame(new Duration(16), event -> {
            for (Duck duck : duckList) {
                duck.setDuckX(duck.getDuckX() + duck.getDeltaX());
                duck.setDuckY(duck.getDuckY() + duck.getDeltaY());

                if (duck.getDuckX() <= 0 || Main.WIDTH - duck.getDuckX() <= Duck.DUCK_WIDTH) {
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
            }
        }));
        movement.setCycleCount(Timeline.INDEFINITE);
        movement.play();

        for (Duck duck : duckList) {
            root.getChildren().add(duck.getHitbox());
        }

        level = new Scene(root, Main.WIDTH, Main.HEIGHT);
        CursorManager.setCurrentCursor(level);
    }

    public static void addEventHandlers() {
        level.setOnMouseClicked(event -> {
            SoundPlayer.playSound(SoundPlayer.gunshot);
            numOfBullets--;

            double clickX = event.getX();
            double clickY = event.getY();

            Pane pane = (Pane) level.getRoot();
            for (Duck duck : duckList) {
                Pane hitbox = duck.getHitbox();
                if (clickX >= hitbox.getTranslateX() && clickX <= hitbox.getTranslateX() + DuckMaker.HITBOX_SIZE &&
                clickY >= hitbox.getTranslateY() && clickY <= hitbox.getTranslateY() + DuckMaker.HITBOX_SIZE) {
                    pane.getChildren().remove(duck.getHitbox());
                }
            }
        });
    }

    public static void setBackground() {
        Pane root = (Pane) level.getRoot();
        BackgroundManager.setCurrentBackground(root);
    }

    public static void setForeground() {
        Pane root = (Pane) level.getRoot();
        BackgroundManager.setCurrentForeground(root);
    }
}
