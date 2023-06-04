import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class Level {
    private int numOfBullets;
    private int hitDucks = 0;
    private Scene level;

    public Level(int numOfBullets) {
        this.numOfBullets = numOfBullets;
    }

    public static void setBackground(Scene level) {
        Pane root = (Pane) level.getRoot();
        BackgroundManager.setCurrentBackground(root);
    }

    public static void setForeground(Scene level) {
        Pane root = (Pane) level.getRoot();
        BackgroundManager.setCurrentForeground(root);
    }

    public void incrementDuckHit() {
        hitDucks += 1;
    }

    public int getHitDucks() {
        return hitDucks;
    }

    public Scene getLevel() {
        return level;
    }

    public int getNumOfBullets() {
        return numOfBullets;
    }

    public void setNumOfBullets(int numOfBullets) {
        this.numOfBullets = numOfBullets;
    }

}
