import javafx.scene.ImageCursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class CursorManager {
    private static final List<ImageCursor> imageCursorList = new ArrayList<>();
    private static final List<ImageView> imageViewList = new ArrayList<>();
    private static ListIterator<ImageCursor> cursorIterator;
    private static ListIterator<ImageView> imageIterator;
    private static ImageCursor currentCursor;
    private static ImageView currentImage;

    static {
        for (int i = 0; i < 7; i++) {
            Image image = new Image(String.format("assets/crosshair/%d.png", i+1));
            ImageView imageView = new ImageView(image);
            ImageCursor imageCursor = new ImageCursor(image);
            imageViewList.add(imageView);
            imageCursorList.add(imageCursor);
        }
        cursorIterator = imageCursorList.listIterator();
        imageIterator = imageViewList.listIterator();
        currentImage = imageViewList.get(0);
        currentCursor = imageCursorList.get(0);
    }

    public static void setNextCursor(Pane pane) {
        if (cursorIterator.hasNext()) {
            ImageCursor imageCursor = cursorIterator.next();
            ImageView imageView = imageIterator.next();
            if (currentCursor == imageCursor) {
                if (cursorIterator.hasNext()) {
                    imageCursor = cursorIterator.next();
                    imageView = imageIterator.next();
                    pane.setCursor(imageCursor);

                    currentCursor = imageCursor;
                    return;
                }
            } else {
                pane.setCursor(imageCursor);
                currentCursor = imageCursor;
                return;
            }
        }
        while (cursorIterator.hasPrevious()) {
            cursorIterator.previous();
        }
        currentCursor = cursorIterator.next();
        pane.setCursor(currentCursor);
    }


    public static void setInitialCursor(Pane pane) {
    }

    public static void setInitialImage(Pane pane) {

    }
}
