import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class CursorManager {
    private static final List<ImageCursor> imageCursorList = new ArrayList<>();
    private static final List<ImageView> imageViewList = new ArrayList<>();
    private static final ListIterator<ImageCursor> cursorIterator;
    private static final ListIterator<ImageView> imageIterator;
    private static ImageCursor currentCursor;
    private static ImageView currentImage;

    static {
        for (int i = 0; i < 7; i++) {
            Image image = new Image(String.format("assets/crosshair/%d.png", i+1));
            ImageView imageView = new ImageView(image);
            imageView.setTranslateY((double) 180 * Main.SCALE / 2);
            imageView.setFitWidth(10 * Main.SCALE);
            imageView.setFitHeight(10 * Main.SCALE);
            ImageCursor imageCursor = new ImageCursor(image);
            imageViewList.add(imageView);
            imageCursorList.add(imageCursor);
        }
        cursorIterator = imageCursorList.listIterator();
        imageIterator = imageViewList.listIterator();
        currentImage = imageViewList.get(0);
        currentCursor = imageCursorList.get(0);
    }

    public static void setNextCursor(Scene scene) {
        currentCursor = IteratorHelper.getNext(cursorIterator, currentCursor);
        scene.setCursor(currentCursor);
    }

    public static void setPrevCursor(Scene scene) {
        currentCursor = IteratorHelper.getPrev(cursorIterator, currentCursor);
        scene.setCursor(currentCursor);
    }

    public static void setCurrentCursor(Scene scene) {
        scene.setCursor(currentCursor);
    }

    public static void setCurrentImage(Pane pane) {
        pane.getChildren().add(currentImage);
    }

    public static void setNextImage(Pane pane) {
        currentImage = IteratorHelper.getNext(imageIterator, currentImage);
        pane.getChildren().set(pane.getChildren().size() - 1, currentImage);
    }

    public static void setPrevImage(Pane pane) {
        currentImage = IteratorHelper.getPrev(imageIterator, currentImage);
        pane.getChildren().set(pane.getChildren().size() - 1, currentImage);
    }
}
