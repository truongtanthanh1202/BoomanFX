package booman_fx.engine;

import booman_fx.Enum.TypeSprite;
import booman_fx.game.Images;
import booman_fx.game.GameState;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

import static booman_fx.Enum.TypeSprite.*;

public class Sprite extends ImageView implements Comparable<Sprite> {
    protected final int SIZE = GameState.SIZE_A_SQUARE;

    // type of sprite.
    private final TypeSprite typeSprite;

    public TypeSprite getTypeSprite() {
        return typeSprite;
    }

    protected int xInMap, yInMap;

    public int getXInMap() {
        return xInMap;
    }

    public int getYInMap() {
        return yInMap;
    }

    protected Sprite(Image image, int xInMap, int yInMap, TypeSprite typeSprite) {
        this.xInMap = xInMap;
        this.yInMap = yInMap;
        double coordinateX = xInMap * SIZE;
        double coordinateY = yInMap * SIZE;

        this.typeSprite = typeSprite;
        setImage(image);
        setTranslateX(coordinateX + SIZE - image.getWidth());
        setTranslateY(coordinateY + SIZE - image.getHeight());
    }

    public void update() {
    }

    @Override
    public int compareTo(Sprite other) {
        return (getYInMap() < other.getYInMap()) ? -1 : (getYInMap() > other.getYInMap()) ? 1
                : (typeSprite == PLAYER) ? 1 : (other.typeSprite == PLAYER) ? -1
                : (typeSprite == ENEMY) ? 1 : (other.typeSprite == ENEMY) ? -1 : 0;
    }
}
