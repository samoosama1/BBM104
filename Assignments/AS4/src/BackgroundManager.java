import javafx.scene.image.Image;
import javafx.scene.layout.*;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class BackgroundManager {
    private static List<Background> backgroundList = new LinkedList<>();
    private static final ListIterator<Background> listIterator;
    private static Background currentBackground;
    static {
        for (int i = 0; i < 6; i++) {
            BackgroundImage background = new BackgroundImage(new Image(String.format("assets/background/%d.png", i+1)),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT, new BackgroundSize(Main.SCALE * 256, Main.SCALE * 240,
                    false, false, false, false));
            backgroundList.add(new Background(background));
        }
        listIterator = backgroundList.listIterator();
        currentBackground = listIterator.next();
    }

    public static void setNextBackground(Pane pane) {
        if (listIterator.hasNext()) {
            Background background = listIterator.next();
            if (currentBackground == background) {
                if (listIterator.hasNext()) {
                    background = listIterator.next();
                    pane.setBackground(background);
                    currentBackground = background;
                    return;
                }
            } else {
                pane.setBackground(background);
                currentBackground = background;
                return;
            }
        }
        while (listIterator.hasPrevious()) {
            listIterator.previous();
        }
        currentBackground = listIterator.next();
        pane.setBackground(currentBackground);
    }

    public static void setPrevBackground(Pane pane) {
        if (listIterator.hasPrevious()) {
            Background background = listIterator.previous();
            if (currentBackground == background) {
                if (listIterator.hasPrevious()) {
                    background = listIterator.previous();
                    pane.setBackground(background);
                    currentBackground = background;
                    return;
                }
            } else {
                pane.setBackground(background);
                currentBackground = background;
                return;
            }
        }
        while (listIterator.hasNext()) {
            listIterator.next();
        }
        currentBackground = listIterator.previous();
        pane.setBackground(currentBackground);
    }

    public static void setInitialBackground(Pane pane) {
        pane.setBackground(currentBackground);
    }
}
