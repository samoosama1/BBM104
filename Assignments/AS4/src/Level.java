import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class Level {
    public static void setBackground(Scene level) {
        Pane root = (Pane) level.getRoot();
        BackgroundManager.setCurrentBackground(root);
    }

    public static void setForeground(Scene level) {
        Pane root = (Pane) level.getRoot();
        BackgroundManager.setCurrentForeground(root);
    }
}
