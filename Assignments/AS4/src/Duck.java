import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class Duck {
    public static final int DUCK_WIDTH = 27 * Main.SCALE;
    public static final int DUCK_HEIGHT = 31 * Main.SCALE;
    public static ArrayList<Image> blackDuckImages = new ArrayList<>();
    public static ArrayList<Image> blueDuckImages = new ArrayList<>();
    public static ArrayList<Image> redDuckImages = new ArrayList<>();

    private double duckX;
    private double duckY;
    private double flyingDeltaX;
    private double flyingDeltaY;
    private final double fallingDeltaY = 1 * Main.SCALE;
    private ImageView imageView = DuckMaker.makeImageView();
    private final Pane hitbox = DuckMaker.makeHitbox(imageView);

    static {
        for (int i = 0; i < 8; i++) {
            Image image = new Image(String.format("assets/duck_black/%d.png", i+1));
            blackDuckImages.add(image);
        }

        for (int i = 0; i < 8; i++) {
            Image image = new Image(String.format("assets/duck_blue/%d.png", i+1));
            blueDuckImages.add(image);
        }

        for (int i = 0; i < 8; i++) {
            Image image = new Image(String.format("assets/duck_red/%d.png", i+1));
            redDuckImages.add(image);
        }
    }

    public Duck(double duckX, double duckY, double deltaX, double deltaY) {
        this.duckX = duckX;
        this.duckY = duckY;
        this. flyingDeltaX = deltaX * Main.SCALE;
        this.flyingDeltaY = deltaY * Main.SCALE;
        hitbox.setTranslateX(duckX);
        hitbox.setTranslateY(duckY);
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
