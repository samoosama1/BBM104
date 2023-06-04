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

public class Level {
    private int numOfBullets;
    private int hitDucks = 0;
    private Scene level;
    private Pane root;
    private boolean enableMouseClick = true;
    private Stage window;
    private boolean enableEnter = false;
    private boolean levelPassed = false;
    private List<Duck> duckList = new ArrayList<>();

    public Level(int numOfDucks, Stage window) {
        this.window = window;
        this.numOfBullets = numOfDucks * 3;

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

        Text gameOver = new Text("GAME OVER!");
        gameOver.setFont(Font.font("Arial", FontWeight.BOLD, Main.SCALE * 15)); // Set the font and size as desired
        gameOver.setTranslateY(Main.HEIGHT * 0.43);
        gameOver.setTranslateX(Main.WIDTH * 0.35);
        gameOver.setFill(Color.TRANSPARENT);
        gameOver.setMouseTransparent(true);

        Text playAgain = new Text("Press ENTER to play again\n");
        playAgain.setFont(Font.font("Arial", FontWeight.BOLD, Main.SCALE * 15)); // Set the font and size as desired
        playAgain.setTranslateY(Main.HEIGHT * 0.5);
        playAgain.setFill(Color.TRANSPARENT);
        playAgain.setTranslateX(Main.WIDTH * 0.13);
        playAgain.setMouseTransparent(true);

        Text exit = new Text("Press ESC to exit");
        exit.setFont(Font.font("Arial", FontWeight.BOLD, Main.SCALE * 15)); // Set the font and size as desired
        exit.setTranslateY(Main.HEIGHT * 0.57);
        exit.setFill(Color.TRANSPARENT);
        exit.setTranslateX(Main.WIDTH * 0.26);
        exit.setMouseTransparent(true);

        root = new Pane();

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
                    if (getHitDucks() == numOfDucks) {
                        enableMouseClick = false;
                        enableEnter = true;
                        setLevelPassed(true);
                        youWin.setFill(Color.ORANGE);
                        playNextLevel.setFill(Color.ORANGE);
                        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.75 * 2), event2 -> playNextLevel.setFill(Color.ORANGE)),
                                new KeyFrame(Duration.seconds(0.75), event2 -> playNextLevel.setFill(Color.TRANSPARENT)));
                        timeline.setCycleCount(Timeline.INDEFINITE);
                        timeline.play();
                        SoundPlayer.playSound(SoundPlayer.levelComplete);
                    } else if (getNumOfBullets() == 0) {
                        enableMouseClick = false;
                        setEnableEnter(true);
                        gameOver.setFill(Color.ORANGE);
                        playAgain.setFill(Color.ORANGE);
                        exit.setFill(Color.ORANGE);
                        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.75 * 2), event3 -> {
                            playAgain.setFill(Color.ORANGE);
                            exit.setFill(Color.ORANGE);
                        }),
                                new KeyFrame(Duration.seconds(0.75), event3 -> {
                                    playAgain.setFill(Color.TRANSPARENT);
                                    exit.setFill(Color.TRANSPARENT);
                                }));
                        timeline.setCycleCount(Timeline.INDEFINITE);
                        timeline.play();
                        SoundPlayer.playSound(SoundPlayer.gameOver);
                    }
                }
            }
        });


        BackgroundManager.setCurrentBackground(root);
//        BackgroundManager.setCurrentForeground(root);
        CursorManager.setCurrentCursor(level);
        root.getChildren().addAll(youWin, playNextLevel, ammoLeft, gameOver, playAgain, exit);
    }

    public void playAgain() {
        SoundPlayer.stopSound(SoundPlayer.gameOver);
        Level1 level1 = new Level1(window, 1);
        window.setScene(level1.getLevel());
    }

    public void returnToMainMenu() {
        SoundPlayer.stopSound(SoundPlayer.gameOver);
        MainMenu mainMenu = new MainMenu(window);
        window.setScene(mainMenu.getMainMenu());
    }

    public boolean isEnableEnter() {
        return enableEnter;
    }

    public void setEnableEnter(boolean enableEnter) {
        this.enableEnter = enableEnter;
    }

    public boolean isLevelPassed() {
        return levelPassed;
    }

    public void setLevelPassed(boolean levelPassed) {
        this.levelPassed = levelPassed;
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

    public Pane getRoot() {
        return root;
    }

    public List<Duck> getDuckList() {
        return duckList;
    }
}
