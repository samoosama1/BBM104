import javafx.application.Application;
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
        Level1.addEventHandlers();

        window.setScene(MainMenu.mainMenu);
        window.show();
    }

}