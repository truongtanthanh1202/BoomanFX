package booman_fx.engine;

import booman_fx.game.Map;
import booman_fx.objects.Character.Player.Player;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Group;
import javafx.util.Duration;

import java.util.List;
import java.util.stream.Collectors;

import static booman_fx.Enum.StatusGame.PLAY;

public abstract class GameAttribute {
    // where contain all objects
    public static int TIME_LWP = 2;
    protected final Group sceneSprites;
    private final int framesPerSecond;

    private Timeline gameLoop;

    protected static Map spritesMap;
    protected static int level = 1;

    protected static Player player;

    protected boolean isNextLevel;
    protected final IntegerProperty timeLeft = new SimpleIntegerProperty(60*20); //60 mean fps of game
    protected static Audio musicGame;
    protected static Audio soundEffectGame;
    protected final IntegerProperty status = new SimpleIntegerProperty(PLAY.ordinal());
    protected int timeLWP;

    public int getTimeLWP() {
        return timeLWP;
    }

    public void setTimeLWP(int timeLWP) {
        this.timeLWP = timeLWP;
    }

    public void setStatus(int status) {
        this.status.setValue(status);
    }


    public GameAttribute(int framesPerSecond) {
        this.framesPerSecond = framesPerSecond;
        sceneSprites = new Group();
        buildGameLoop();
    }

    private void buildGameLoop() {
        final Duration oneFrameAmt = Duration.millis(1000 / (float) framesPerSecond);
        final KeyFrame oneFrame = new KeyFrame(oneFrameAmt, actionEvent -> {
            updateSprites();
            showSprites();
            checkEndGame();
            checkNextLevel();
            checkWinGame();
        });

        gameLoop = new Timeline();
        gameLoop.setCycleCount(Animation.INDEFINITE);
        gameLoop.getKeyFrames().add(oneFrame);
    }

    public Audio getMusicGame() {
        return musicGame;
    }

    public Audio getSoundEffectGame() {
        return soundEffectGame;
    }

    public IntegerProperty getTimeLeft() {
        return timeLeft;
    }

    public int getFramesPerSecond() {
        return framesPerSecond;
    }

    public Map getSpritesMap() {
        return spritesMap;
    }

    public Group getSceneSprites() {
        return sceneSprites;
    }

    public int getLevel() {
        return level;
    }

    public Player getPlayer() {
        return player;
    }

    // update for all sprites in group sceneSprites
    protected void updateSprites() {
        sprites().forEach(Sprite::update);
    }

    public void spawn(Sprite sprite) {
        sceneSprites.getChildren().add(sprite);
    }

    public void destroy(Sprite sprite) {
        sceneSprites.getChildren().remove(sprite);
    }

    protected abstract void showSprites();

    protected abstract void checkNextLevel();

    protected abstract void checkEndGame();

    protected abstract void checkWinGame();

    // control game loop
    public void begin() {
        gameLoop.play();
    }

    public void pause() {
        gameLoop.pause();
    }

    public void shutdown() {
        musicGame.pause();
        gameLoop.stop();
    }

    public List<Sprite> sprites() {
        return sceneSprites.getChildren().stream().map(image -> (Sprite) image).collect(Collectors.toList());
    }
}
