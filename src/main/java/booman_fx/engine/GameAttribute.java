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
    protected final Group sceneSprites;
    private final int framesPerSecond;

    private Timeline gameLoop;

    protected static Map spritesMap;
    protected static int level;

    protected static Player player;

    protected boolean isNextLevel;
    protected final IntegerProperty timeLeft = new SimpleIntegerProperty(60*300); //60 mean fps of game
    protected static Audio musicGame;
    protected static Audio soundEffectGame;
    protected static final IntegerProperty status = new SimpleIntegerProperty(PLAY.ordinal());

    public IntegerProperty statusProperty() {
        return status;
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
            checkCollision();
            checkEndGame();
            checkNextLevel();
            checkWinGame();

            nextLevel();
            checkNextLevel();
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

    protected void checkCollision() {
        sprites().forEach(Sprite::executeCollision);
    }


    public void spawn(Sprite sprite) {
        sceneSprites.getChildren().add(sprite);
    }

    public void destroy(Sprite sprite) {
        sceneSprites.getChildren().remove(sprite);
    }

    protected abstract void showSprites();

    protected abstract void nextLevel();

    protected abstract void checkNextLevel();

    protected abstract void checkEndGame();

    protected abstract boolean checkWinGame();

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

    public abstract void sleep(int second);
}
