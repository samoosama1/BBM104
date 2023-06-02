import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;


public class DuckMaker {
    public static final int  HITBOX_SIZE = 33 * Main.SCALE;
    public static Pane makeHitbox(ImageView duckImageView) {
        Pane hitbox = new StackPane(duckImageView);
        hitbox.setPrefSize(HITBOX_SIZE, HITBOX_SIZE);
        hitbox.setStyle("-fx-background-color: cyan");
        return hitbox;
    }

    public static ImageView makeImageView() {
        ImageView duckImageView = new ImageView();
        duckImageView.setFitWidth(27 * Main.SCALE);
        duckImageView.setFitHeight(31 * Main.SCALE);
        return duckImageView;
    }
}
