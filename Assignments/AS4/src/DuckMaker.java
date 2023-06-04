import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;


public class DuckMaker {
    public static final int  HITBOX_SIZE = 33 * Main.SCALE;
    public static Pane makeHitbox(ImageView duckImageView) {
        Pane hitbox = new StackPane(duckImageView);
        hitbox.setPrefSize(HITBOX_SIZE, HITBOX_SIZE);
        hitbox.setStyle("-fx-background-color: transparent");
        return hitbox;
    }

    public static ImageView makeImageView() {
        ImageView duckImageView = new ImageView();
        duckImageView.setFitWidth(Duck.DUCK_WIDTH);
        duckImageView.setFitHeight(Duck.DUCK_HEIGHT);
        return duckImageView;
    }
}
