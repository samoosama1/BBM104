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
    private int duckX;
    private int duckY;
    private int deltaX;
    private int deltaY;
    private ImageView imageView = DuckMaker.makeImageView();
    private final Pane hitbox = DuckMaker.makeHitbox(imageView);
    private final String name;

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

    public Duck(int duckX, int duckY, int deltaX, int deltaY, String name) {
        this.name = name;
        this.duckX = duckX;
        this.duckY = duckY;
        this.deltaX = deltaX;
        this.deltaY = deltaY;
        hitbox.setTranslateX(duckX);
        hitbox.setTranslateY(duckY);
    }

    public int getDuckX() {
        return duckX;
    }

    public void setDuckX(int duckX) {
        this.duckX = duckX;
    }

    public int getDuckY() {
        return duckY;
    }

    public void setDuckY(int duckY) {
        this.duckY = duckY;
    }

    public Pane getHitbox() {
        return hitbox;
    }

    public int getDeltaX() {
        return deltaX;
    }

    public void setDeltaX(int deltaX) {
        this.deltaX = deltaX;
    }

    public int getDeltaY() {
        return deltaY;
    }

    public void setDeltaY(int deltaY) {
        this.deltaY = deltaY;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public String toString() {
        return name;
    }
}
