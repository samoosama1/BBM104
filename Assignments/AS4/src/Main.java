import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    public static final int SCALE = 3;
    public static final int WIDTH = SCALE * 256;
    public static final int HEIGHT = SCALE * 240;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage window) throws Exception {

        MainMenu.addEventHandler(window);
        ConfigMenu.addEventHandler(window);
        Level1.addEventHandlers(window);
        Level2.addEventHandlers(window);

        window.setScene(MainMenu.mainMenu);
        window.setTitle("HUBMM DuckHunt");
        window.getIcons().add(new Image("assets/favicon/1.png"));
        window.show();
    }

}