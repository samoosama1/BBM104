import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;


public class HitboxMaker {
    public static Pane makeHitbox(ImageView duckImageView) {
        Pane hitbox = new StackPane(duckImageView);
        hitbox.setPrefSize(36 * Main.SCALE , 36 * Main.SCALE);
        hitbox.setStyle("-fx-background-color: cyan");
        return hitbox;
    }
}
