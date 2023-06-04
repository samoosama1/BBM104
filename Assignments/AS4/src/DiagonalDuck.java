import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.util.Duration;

public class DiagonalDuck extends Duck{
    public static Rotate ROTATE_90_DEGREES = new Rotate();

    static {
        ROTATE_90_DEGREES.setAngle(90);
        ROTATE_90_DEGREES.setPivotX(DUCK_WIDTH * 0.5);
        ROTATE_90_DEGREES.setPivotY(DUCK_HEIGHT * 0.5);
    }

    public DiagonalDuck(double duckX, double duckY, double deltaX, double deltaY, String color) {
        super(duckX, duckY, deltaX, deltaY, color);

        if (getFlyingDeltaY() > 0) {
            getImageView().getTransforms().add(ROTATE_90_DEGREES);
        }

        setFlyingAnimation(new Timeline(
            new KeyFrame(Duration.seconds(0.2), event -> getImageView().setImage(Duck.listMap.get(color)[0])),
            new KeyFrame(Duration.seconds(0.4), event -> getImageView().setImage(Duck.listMap.get(color)[1])),
            new KeyFrame(Duration.seconds(0.6), event -> getImageView().setImage(Duck.listMap.get(color)[2]))));
        getFlyingAnimation().setCycleCount(Timeline.INDEFINITE);
        getFlyingAnimation().play();

        setFlyingMovement(new Timeline(new KeyFrame(new Duration(16), event -> {
            setDuckX(getDuckX() + getDeltaX());
            setDuckY(getDuckY() + getFlyingDeltaY());

            if (getDuckX() <= 0 || Main.WIDTH - getDuckX() <= DuckMaker.HITBOX_SIZE) {
                setDeltaX(getDeltaX() * -1);
                if (getDeltaX() > 0 && getFlyingDeltaY() < 0) {
                    getImageView().getTransforms().set(0, UP_RIGHT);
                    getImageView().getTransforms().remove(ROTATE_90_DEGREES);
                } else if (getDeltaX() > 0 && getFlyingDeltaY() > 0) {
                    getImageView().getTransforms().set(0, ROTATE_90_DEGREES);
                } else if (getDeltaX() < 0 && getFlyingDeltaY() < 0) {
                    getImageView().getTransforms().set(0, UP_LEFT);
                    getImageView().getTransforms().remove(ROTATE_90_DEGREES);
                } else {
                    getImageView().getTransforms().set(0, UP_LEFT);
                    getImageView().getTransforms().add(ROTATE_90_DEGREES);
                }
            }

            if (getDuckY() <= 0 || Main.HEIGHT - getDuckY() <= DuckMaker.HITBOX_SIZE) {
                setFlyingDeltaY(getFlyingDeltaY() * -1);
                if (getDeltaX() > 0 && getFlyingDeltaY() < 0) {
                    getImageView().getTransforms().set(0, UP_RIGHT);
                    getImageView().getTransforms().remove(ROTATE_90_DEGREES);
                } else if (getDeltaX() > 0 && getFlyingDeltaY() > 0) {
                    getImageView().getTransforms().set(0, ROTATE_90_DEGREES);
                } else if (getDeltaX() < 0 && getFlyingDeltaY() < 0) {
                    getImageView().getTransforms().set(0, UP_LEFT);
                    getImageView().getTransforms().remove(ROTATE_90_DEGREES);
                } else {
                    getImageView().getTransforms().set(0, UP_LEFT);
                    getImageView().getTransforms().add(ROTATE_90_DEGREES);
                }
            }
            getHitbox().setTranslateX(getDuckX());
            getHitbox().setTranslateY(getDuckY());
        })));
        getFlyingMovement().setCycleCount(Timeline.INDEFINITE);
        getFlyingMovement().play();
    }
}
