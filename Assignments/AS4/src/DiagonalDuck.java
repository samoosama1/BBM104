import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.transform.Scale;
import javafx.util.Duration;

public class DiagonalDuck extends Duck{
    public static Scale UP_LEFT = new Scale(-1, 1);
    public static Scale DOWN_LEFT = new Scale(-1, -1);
    public static Scale UP_RIGHT = new Scale(1, 1);
    public static Scale DOWN_RIGHT = new Scale(1, -1);

    static {
        UP_RIGHT.setPivotX(DUCK_WIDTH * 0.5);
        DOWN_LEFT.setPivotX(DUCK_WIDTH * 0.5);
        UP_LEFT.setPivotX(DUCK_WIDTH * 0.5);
        DOWN_RIGHT.setPivotX(DUCK_WIDTH * 0.5);

        UP_RIGHT.setPivotY(DUCK_HEIGHT * 0.5);
        DOWN_LEFT.setPivotY(DUCK_HEIGHT * 0.5);
        UP_LEFT.setPivotY(DUCK_HEIGHT * 0.5);
        DOWN_RIGHT.setPivotY(DUCK_HEIGHT * 0.5);
    }

    public DiagonalDuck(double duckX, double duckY, double deltaX, double deltaY, String color) {
        super(duckX, duckY, deltaX, deltaY, color);

        setFlyingAnimation(new Timeline(
                new KeyFrame(Duration.seconds(0.2), event -> getImageView().setImage(Duck.listMap.get(color)[1])),
                new KeyFrame(Duration.seconds(0.4), event -> getImageView().setImage(Duck.listMap.get(color)[2])),
                new KeyFrame(Duration.seconds(0.6), event -> getImageView().setImage(Duck.listMap.get(color)[3]))));
        getFlyingAnimation().setCycleCount(Timeline.INDEFINITE);
        getFlyingAnimation().play();

        setFlyingMovement(new Timeline(new KeyFrame(new Duration(16), event -> {
            setDuckX(getDuckX() + getDeltaX());
            setDuckY(getDuckY() + getFlyingDeltaY());

            if (getDuckX() <= 0 || Main.WIDTH - getDuckX() <= DuckMaker.HITBOX_SIZE) {
                setDeltaX(getDeltaX() * -1);
                if (getDeltaX() > 0 && getFlyingDeltaY() < 0) {
                    getImageView().getTransforms().set(0, UP_RIGHT);
                } else if (getDeltaX() > 0 && getFlyingDeltaY() > 0) {
                    getImageView().getTransforms().set(0, DOWN_RIGHT);
                } else if (getDeltaX() < 0 && getFlyingDeltaY() < 0) {
                    getImageView().getTransforms().set(0, UP_LEFT);
                } else {
                    getImageView().getTransforms().set(0, DOWN_LEFT);
                }
            }

            if (getDuckY() <= 0 || Main.HEIGHT - getDuckY() <= DuckMaker.HITBOX_SIZE) {
                setFlyingDeltaY(getFlyingDeltaY() * -1);
                if (getDeltaX() > 0 && getFlyingDeltaY() < 0) {
                    getImageView().getTransforms().set(0, UP_RIGHT);
                } else if (getDeltaX() > 0 && getFlyingDeltaY() > 0) {
                    getImageView().getTransforms().set(0, DOWN_RIGHT);
                } else if (getDeltaX() < 0 && getFlyingDeltaY() < 0) {
                    getImageView().getTransforms().set(0, UP_LEFT);
                } else {
                    getImageView().getTransforms().set(0, DOWN_LEFT);
                }
            }
            getHitbox().setTranslateX(getDuckX());
            getHitbox().setTranslateY(getDuckY());
        })));
        getFlyingMovement().setCycleCount(Timeline.INDEFINITE);
        getFlyingMovement().play();


    }
}