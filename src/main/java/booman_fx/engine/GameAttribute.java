package booman_fx.engine;

import booman_fx.game.Map;
import booman_fx.objects.Character.Player.Player;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Group;

import java.util.List;
import java.util.stream.Collectors;

public abstract class GameAttribute {
    // where contain all objects
    protected final Group sceneSprites;
    private final int framesPerSecond;

    protected static Map spritesMap;
    protected static int level = 1;
    protected boolean isNextLevel;
    protected final IntegerProperty timeLeft = new SimpleIntegerProperty(18000);
    protected static Audio musicGame;
    protected static Audio soundEffectGame;

    public GameAttribute(int framesPerSecond) {
        this.framesPerSecond = framesPerSecond;
        sceneSprites = new Group();
    }

    public Audio getMusicGame() {
        return musicGame;
    }

    public Audio getSoundEffectGame() {
        return soundEffectGame;
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

    public List<Sprite> sprites() {
        return sceneSprites.getChildren().stream().map(image -> (Sprite) image).collect(Collectors.toList());
    }
}
