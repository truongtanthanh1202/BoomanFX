package booman_fx.engine;

import booman_fx.Enum.TypeSprite;
import booman_fx.game.App;
import booman_fx.game.GameState;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

import static booman_fx.Enum.TypeSprite.*;

public abstract class Sprite extends ImageView implements Comparable<Sprite> {
    protected final int SIZE = GameState.SIZE_A_SQUARE;
    protected int xInMap, yInMap;

    // type of sprite.
    private final TypeSprite typeSprite;
    public final Rectangle collisionBound;

    public TypeSprite getTypeSprite() {
        return typeSprite;
    }

    public int getXInMap() {
        return xInMap;
    }

    public int getYInMap() {
        return yInMap;
    }

    public void executeCollision() {}

    public void handleDeath() {
        App.gameAttribute.destroy(this);
    }

    protected Sprite(Image image, int xInMap, int yInMap, TypeSprite typeSprite) {
        this.xInMap = xInMap;
        this.yInMap = yInMap;
        double coordinateX = xInMap * SIZE;
        double coordinateY = yInMap * SIZE;

        if (typeSprite == ENEMY || typeSprite == PLAYER) {
            double approximateUp = 14;
            double approximateLeft = 10;
            double approximateDown = 10;
            double approximateRight = 10;
            collisionBound = new Rectangle(coordinateX + approximateLeft, coordinateY + approximateUp,
                    SIZE - approximateLeft - approximateRight, SIZE - approximateUp - approximateDown);
        } else {
            collisionBound = new Rectangle(coordinateX, coordinateY, SIZE, SIZE);
        }

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

    public boolean checkCollision(Sprite other) {
        return Math.max(collisionBound.getX(), other.collisionBound.getX())
                < Math.min(collisionBound.getX() + collisionBound.getWidth(), other.collisionBound.getX() + other.collisionBound.getWidth())
                && Math.max(collisionBound.getY(), other.collisionBound.getY())
                < Math.min(collisionBound.getY() + collisionBound.getHeight(), other.collisionBound.getY() + other.collisionBound.getHeight());
    }
}
