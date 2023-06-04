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

import java.util.ArrayList;
import java.util.List;

public class Level3 extends Level {
    private List<Duck> duckList = new ArrayList<>();
    private boolean enableMouseClick = true;
    private boolean enableEnter = false;

    public Level3(Stage window, int numOfBullets) {
        super(numOfBullets);

        Text levelText = new Text("Level 3/6");
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
        Duck duck1 = new HorizontalDuck(100 * Main.SCALE, 16.6 * Main.SCALE, 2, 0, "black");

        Duck duck2 = new HorizontalDuck(66 * Main.SCALE, 66 * Main.SCALE, -2, 0, "red");
        duckList.add(duck1);
        duckList.add(duck2);
        root.getChildren().add(duck1.getHitbox());
        root.getChildren().add(duck2.getHitbox());

        level = new Scene(root, Main.WIDTH, Main.HEIGHT);

        level.setOnMouseClicked(event -> {
            if (enableMouseClick) {
                SoundPlayer.playSound(SoundPlayer.gunshot);
                setNumOfBullets(getNumOfBullets() - 1);
                ammoLeft.setText(String.format("Ammo Left: %d", getNumOfBullets()));

                double clickX = event.getX();
                double clickY = event.getY();

                for (Duck duck : duckList) {
                    Pane hitbox = duck.getHitbox();
                    if (clickX >= hitbox.getTranslateX() && clickX <= hitbox.getTranslateX() + DuckMaker.HITBOX_SIZE &&
                            clickY >= hitbox.getTranslateY() && clickY <= hitbox.getTranslateY() + DuckMaker.HITBOX_SIZE ) {
                        if (!duck.isHit()) {
                            incrementDuckHit();
                            duck.setHit(true);
                            duck.die();
                        }
                    }
                    if (getHitDucks() == duckList.size()) {
                        enableMouseClick = false;
                        enableEnter = true;
                        youWin.setFill(Color.ORANGE);
                        playNextLevel.setFill(Color.ORANGE);
                        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.75 * 2), event2 -> playNextLevel.setFill(Color.ORANGE)),
                                new KeyFrame(Duration.seconds(0.75), event2 -> playNextLevel.setFill(Color.TRANSPARENT)));
                        timeline.setCycleCount(Timeline.INDEFINITE);
                        timeline.play();
                        SoundPlayer.playSound(SoundPlayer.duckFall);
                        duck.die();
                        SoundPlayer.playSound(SoundPlayer.levelComplete);
                    } else if (getNumOfBullets() == 0) {
                        enableMouseClick = false;
                        SoundPlayer.playSound(SoundPlayer.gameOver);
                    }
                }
            }
        });

        level.setOnKeyPressed(event1 -> {
            if (enableEnter) {
                if (event1.getCode() == KeyCode.ENTER) {
                    SoundPlayer.stopSound(SoundPlayer.levelComplete);
                    Level3 level3 = new Level3(window, 6);
                    window.setScene(level3.getLevel());
                }
            }
        });

        BackgroundManager.setCurrentBackground(root);
        BackgroundManager.setCurrentForeground(root);
        CursorManager.setCurrentCursor(level);
        root.getChildren().addAll(levelText, youWin, playNextLevel, ammoLeft);
    }

}
