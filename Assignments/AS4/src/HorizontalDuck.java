import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.transform.Scale;
import javafx.util.Duration;

public class HorizontalDuck extends Duck{
    public HorizontalDuck(double duckX, double duckY, double deltaX, double deltaY, String color) {
        super(duckX, duckY, deltaX, deltaY, color);

        Scale mirrorScale = new Scale(-1, 1);
        mirrorScale.setPivotX(DUCK_WIDTH * 0.5);
        mirrorScale.setPivotX(DUCK_HEIGHT * 0.5);

        setFlyingAnimation(new Timeline(
            new KeyFrame(Duration.seconds(0.2), event -> getImageView().setImage(Duck.listMap.get(color)[3])),
            new KeyFrame(Duration.seconds(0.4), event -> getImageView().setImage(Duck.listMap.get(color)[4])),
            new KeyFrame(Duration.seconds(0.6), event -> getImageView().setImage(Duck.listMap.get(color)[5]))));
        getFlyingAnimation().setCycleCount(Timeline.INDEFINITE);
        getFlyingAnimation().play();

        setFlyingMovement(new Timeline(new KeyFrame(new Duration(16), event -> {
            setDuckX(getDuckX() + getDeltaX());
            setDuckY(getDuckY() + getFlyingDeltaY());

            if (getDuckX() <= 0 || Main.WIDTH - getDuckX() <= DuckMaker.HITBOX_SIZE) {
                setDeltaX(getDeltaX() * -1);
                if (getDeltaX() > 0) {
                    getImageView().getTransforms().remove(UP_LEFT);
                } else {
                    getImageView().getTransforms().add(UP_LEFT);
                }
            }
            getHitbox().setTranslateX(getDuckX()); })
        ));
        getFlyingMovement().setCycleCount(Timeline.INDEFINITE);
        getFlyingMovement().play();
    }
}
