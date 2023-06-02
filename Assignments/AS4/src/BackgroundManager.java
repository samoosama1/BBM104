import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class BackgroundManager {
    private static final List<ImageView> foregroundList = new LinkedList<>();
    private static final List<Background> backgroundList = new LinkedList<>();
    private static final ListIterator<Background> backgroundIterator;
    private static final ListIterator<ImageView> foregroundIterator;
    private static Background currentBackground;
    private static ImageView currentForeground;
    static {
        for (int i = 0; i < 6; i++) {
            BackgroundImage background = new BackgroundImage(new Image(String.format("assets/background/%d.png", i+1)),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT, new BackgroundSize(Main.SCALE * 256, Main.SCALE * 240,
                    false, false, false, false));
            backgroundList.add(new Background(background));
        }
        backgroundIterator = backgroundList.listIterator();
        currentBackground = backgroundIterator.next();

        for (int i = 0; i < 6; i++) {
            ImageView imageView = new ImageView(new Image(String.format("assets/foreground/%d.png", i+1)));
            imageView.setFitWidth(Main.WIDTH);
            imageView.setFitHeight(Main.HEIGHT);
            foregroundList.add(imageView);
        }
        foregroundIterator = foregroundList.listIterator();
        currentForeground = foregroundIterator.next();

    }

    public static void setNextBackground(Pane pane) {
        currentBackground = IteratorHelper.getNext(backgroundIterator, currentBackground);
        currentForeground = IteratorHelper.getNext(foregroundIterator, currentForeground);
        pane.setBackground(currentBackground);
    }

    public static void setPrevBackground(Pane pane) {
        currentBackground = IteratorHelper.getPrev(backgroundIterator, currentBackground);
        currentForeground = IteratorHelper.getPrev(foregroundIterator, currentForeground);
        pane.setBackground(currentBackground);
    }

    public static void setCurrentBackground(Pane pane) {
        pane.setBackground(currentBackground);
    }

    public static void setCurrentForeground(Pane pane) {
        pane.getChildren().add(currentForeground);
    }
}
