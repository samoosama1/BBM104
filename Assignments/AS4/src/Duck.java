import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Duck {
    public static final int DUCK_WIDTH = 27 * Main.SCALE;
    public static final int DUCK_HEIGHT = 31 * Main.SCALE;
    public static Map<String, Image[]> listMap = new HashMap<>();;

    private double duckX;
    private double duckY;
    private double flyingDeltaX;
    private double flyingDeltaY;
    private final double fallingDeltaY = 1 * Main.SCALE;
    private ImageView imageView = DuckMaker.makeImageView();
    private final Pane hitbox = DuckMaker.makeHitbox(imageView);

    public double getFlyingDeltaX() {
        return flyingDeltaX;
    }

    public void setFlyingDeltaX(double flyingDeltaX) {
        this.flyingDeltaX = flyingDeltaX;
    }

    public Timeline getFlyingAnimation() {
        return flyingAnimation;
    }

    public void setFlyingAnimation(Timeline flyingAnimation) {
        this.flyingAnimation = flyingAnimation;
    }

    public Timeline getFlyingMovement() {
        return flyingMovement;
    }

    public void setFlyingMovement(Timeline flyingMovement) {
        this.flyingMovement = flyingMovement;
    }

    public Timeline getDyingAnimation() {
        return dyingAnimation;
    }

    public void setDyingAnimation(Timeline dyingAnimation) {
        this.dyingAnimation = dyingAnimation;
    }

    public Timeline getFallingMovement() {
        return fallingMovement;
    }

    public void setFallingMovement(Timeline fallingMovement) {
        this.fallingMovement = fallingMovement;
    }

    private Timeline flyingAnimation;
    private Timeline flyingMovement;
    private Timeline dyingAnimation;
    private Timeline fallingMovement;

    static {
        Image[] blackDuckImages = new Image[8];
        Image[] redDuckImages = new Image[8];
        Image[] blueDuckImages = new Image[8];


        for (int i = 0; i < 8; i++) {
            Image image = new Image(String.format("assets/duck_black/%d.png", i+1));
            blackDuckImages[i] = image;
        }

        for (int i = 0; i < 8; i++) {
            Image image = new Image(String.format("assets/duck_blue/%d.png", i+1));
            blueDuckImages[i] = image;
        }

        for (int i = 0; i < 8; i++) {
            Image image = new Image(String.format("assets/duck_red/%d.png", i+1));
            redDuckImages[i] = image;
        }

        listMap.put("black", blackDuckImages);
        listMap.put("red", redDuckImages);
        listMap.put("blue", blueDuckImages);
    }

    public Duck(double duckX, double duckY, double deltaX, double deltaY, String color) {
        this.duckX = duckX;
        this.duckY = duckY;
        this. flyingDeltaX = deltaX * Main.SCALE;
        this.flyingDeltaY = deltaY * Main.SCALE;
        hitbox.setTranslateX(duckX);
        hitbox.setTranslateY(duckY);

        setDyingAnimation(new Timeline(
                new KeyFrame(Duration.ZERO, event -> getImageView().setImage(Duck.listMap.get(color)[6])),
                new KeyFrame(Duration.seconds(0.3), event -> {
                    getImageView().setImage(Duck.listMap.get(color)[7]);
                    getFallingMovement().play();})
        ));

        setFallingMovement(new Timeline(
                new KeyFrame(Duration.millis(16), event -> {
                    setDuckY(getDuckY() + getFallingDeltaY());
                    if (Main.HEIGHT - getHitbox().getTranslateY() <= DuckMaker.HITBOX_SIZE) {
                        getFallingMovement().stop();
                    }
                    getHitbox().setTranslateY(getDuckY());
                })
        ));
        getFallingMovement().setCycleCount(Timeline.INDEFINITE);
    }

    public void die() {
        flyingAnimation.stop();
        flyingMovement.stop();
        dyingAnimation.play();
    }

    public double getFallingDeltaY() {
        return fallingDeltaY;
    }

    public double getDuckX() {
        return duckX;
    }

    public void setDuckX(double duckX) {
        this.duckX = duckX;
    }

    public double getDuckY() {
        return duckY;
    }

    public void setDuckY(double duckY) {
        this.duckY = duckY;
    }

    public Pane getHitbox() {
        return hitbox;
    }

    public double getDeltaX() {
        return  flyingDeltaX;
    }

    public void setDeltaX(double deltaX) {
        this. flyingDeltaX = deltaX;
    }

    public double getFlyingDeltaY() {
        return flyingDeltaY;
    }

    public void setFlyingDeltaY(double flyingDeltaY) {
        this.flyingDeltaY = flyingDeltaY;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }
}
