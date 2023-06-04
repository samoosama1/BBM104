import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Level1 extends Level{
    public HorizontalDuck duck;
    private Scene level;
    private boolean enableMouseClick = true;
    private boolean enableEnter = false;

    public Level1(Stage window, int numOfBullets) {
        super(numOfBullets);
        Text levelText = new Text("Level 1/6");
        levelText.setFont(Font.font("Arial", FontWeight.BOLD, Main.SCALE * 8)); // Set the font and size as desired
        levelText.setTranslateY(Main.SCALE * 8);
        levelText.setTranslateX(Main.WIDTH * 0.42);
        levelText.setFill(Color.ORANGE);

        Text youWin = new Text("YOU WIN!");
        youWin.setFont(Font.font("Arial", FontWeight.BOLD, Main.SCALE * 15)); // Set the font and size as desired
        youWin.setTranslateY(Main.HEIGHT * 0.43);
        youWin.setTranslateX(Main.WIDTH * 0.35);
        youWin.setFill(Color.TRANSPARENT);
        youWin.setMouseTransparent(true);

        Text playNextLevel = new Text("Press ENTER to play next level");
        playNextLevel.setFont(Font.font("Arial", FontWeight.BOLD, Main.SCALE * 15)); // Set the font and size as desired
        playNextLevel.setTranslateY(Main.HEIGHT * 0.5);
        playNextLevel.setFill(Color.TRANSPARENT);
        playNextLevel.setTranslateX(Main.WIDTH * 0.088);
        playNextLevel.setMouseTransparent(true);

        Text ammoLeft = new Text(String.format("Ammo Left: %d", getNumOfBullets()));
        ammoLeft.setFont(Font.font("Arial", FontWeight.BOLD, Main.SCALE * 8)); // Set the font and size as desired
        ammoLeft.setTranslateY(Main.SCALE * 8);
        ammoLeft.setTranslateX(Main.WIDTH * 0.78);
        ammoLeft.setFill(Color.ORANGE);

        Pane root = new Pane();

        duck = new HorizontalDuck(66 * Main.SCALE, 33 * Main.SCALE, 1, 0, "blue");
        root.getChildren().add(duck.getHitbox());
        level = new Scene(root, Main.WIDTH, Main.HEIGHT);
        CursorManager.setCurrentCursor(level);
        BackgroundManager.setCurrentBackground(root);
        BackgroundManager.setCurrentForeground(root);
        root.getChildren().addAll(levelText, youWin, playNextLevel, ammoLeft);

        level.setOnMouseClicked(event -> {
            if (enableMouseClick) {
                SoundPlayer.playSound(SoundPlayer.gunshot);
                setNumOfBullets(getNumOfBullets() - 1);
                ammoLeft.setText(String.format("Ammo Left: %d", getNumOfBullets()));

                double clickX = event.getX();
                double clickY = event.getY();

                Pane hitbox = duck.getHitbox();
                if (clickX >= hitbox.getTranslateX() && clickX <= hitbox.getTranslateX() + DuckMaker.HITBOX_SIZE &&
                        clickY >= hitbox.getTranslateY() && clickY <= hitbox.getTranslateY() + DuckMaker.HITBOX_SIZE) {
                    enableMouseClick = false;
                    enableEnter = true;
                    youWin.setFill(Color.ORANGE);
                    playNextLevel.setFill(Color.ORANGE);
                    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.75 * 2), event2 -> playNextLevel.setFill(Color.ORANGE)),
                            new KeyFrame(Duration.seconds(0.75), event2 -> playNextLevel.setFill(Color.TRANSPARENT)));
                    timeline.setCycleCount(Timeline.INDEFINITE);
                    timeline.play();
                    SoundPlayer.playSound(SoundPlayer.duckFall);
                    SoundPlayer.playSound(SoundPlayer.levelComplete);
                    duck.die();
                } else if (getNumOfBullets() == 0) {
                    enableMouseClick = false;
                    SoundPlayer.playSound(SoundPlayer.gameOver);
                }
            }
        });

        level.setOnKeyPressed(event1 -> {
            if (enableEnter) {
                if (event1.getCode() == KeyCode.ENTER) {
                    SoundPlayer.stopSound(SoundPlayer.levelComplete);
                    Level2 level2 = new Level2(window, 3);
                    window.setScene(level2.getLevel());
                }
            }
        });
    }

    public Scene getLevel() {
        return level;
    }
}
