import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Level1 {
    public static List<Duck> duckList = new ArrayList<>();
    public static Scene level;
    public static ArrayList<Image> duckImages = new ArrayList<>();
    static {
        for (int i = 0; i < 8; i++) {
            Image image = new Image(String.format("assets/duck_black/%d.png", i+1));
            duckImages.add(image);
        }

        Pane root = new Pane();

        ImageView duckImageView = new ImageView();
        duckImageView.setFitWidth(27 * Main.SCALE);
        duckImageView.setFitHeight(31 * Main.SCALE);

        Pane hitbox = HitboxMaker.makeHitbox(duckImageView);

        Timeline animation = new Timeline(new KeyFrame(Duration.seconds(0.2), event -> duckImageView.setImage(duckImages.get(3))),
                new KeyFrame(Duration.seconds(0.4), event -> duckImageView.setImage(duckImages.get(4))),
                new KeyFrame(Duration.seconds(0.6), event -> duckImageView.setImage(duckImages.get(5))));

        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();

        Timeline movement = new Timeline(new KeyFrame(new Duration(16), event -> {
            duckX += deltaX;

            if (Math.abs(duckX) <= 25 || Math.abs(duckX - 400) <= 25) {
                deltaX *= -1;
                if (imageView.getTransforms().isEmpty()) {
                    imageView.getTransforms().add(0, mirrorScale);
                    imageView.setTranslateX(31 * 1.5);
                } else {
                    imageView.getTransforms().remove(0);
                    imageView.setTranslateX(0);

                }
            }

            hitbox.setTranslateX(duckX);
        }));
        movement.setCycleCount(Timeline.INDEFINITE);
        movement.play();

        root.getChildren().add(hitbox);

        level = new Scene(root, Main.SCALE * 256, Main.SCALE * 240);
        BackgroundManager.setCurrentBackground(root);
        CursorManager.setCurrentCursor(level);
    }

    public static void addEventHandlers() {
        level.setOnMouseClicked(event -> {
            double clickX = event.getX();
            double clickY = event.getY();

            Pane pane = (Pane) level.getRoot();
            for (Duck duck : duckList) {

            }
        });
    }

    public static void setBackground() {
        Pane root = (Pane) level.getRoot();
        BackgroundManager.setCurrentBackground(root);
    }
}
