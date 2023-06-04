import javafx.application.Application;
import javafx.stage.Stage;

public class DuckHunt extends Application {
    public static final int SCALE = 3;
    public static final int WIDTH = SCALE * 256;
    public static final int HEIGHT = SCALE * 240;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage window) throws Exception {
        Game game = new Game();
        game.startGame(window);
    }
}