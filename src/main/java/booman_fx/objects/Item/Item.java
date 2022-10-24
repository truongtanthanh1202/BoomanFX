package booman_fx.objects.Item;

import booman_fx.Enum.TypeItem;
import booman_fx.game.GameResources.Images;
import booman_fx.engine.Sprite;
import booman_fx.game.App;
import booman_fx.objects.Character.Character;
import javafx.scene.image.Image;
import static booman_fx.Enum.TypeSprite.*;
import java.util.Date;
import java.util.Random;

public abstract class Item extends Sprite{
    private Images[] imageItem;
    private static final double DEATH_TIME = 6;
    private double deathTime = DEATH_TIME;
    private static final Images[][] ITEM_IMAGE = Images.items;

    public static void createItem(int xInMap, int yInMap) {
        int random = Math.abs(new Random().nextInt()) % 20;
        int index = (random < 1) ? 1 : (random < 5) ? 0 : (random < 9) ? 2 : (random < 13) ? 3 : 4;
        if(index < 4) {
            switch (TypeItem.values()[index]) {
                case HEART_ITEM -> App.gameAttribute.spawn(new HeartItem(xInMap, yInMap));
                case FLAME_ITEM -> App.gameAttribute.spawn(new PowerItem(xInMap, yInMap));
                case BOMB_ITEM -> App.gameAttribute.spawn(new BombItem(xInMap, yInMap));
                case SPEED_ITEM -> App.gameAttribute.spawn(new SpeedItem(xInMap, yInMap));
            }
        }
    }

    // constructor
    protected Item(Image image, int xInMap, int yInMap, Images[] imageItem) {
        super(image, xInMap, yInMap, ITEM);
        this.imageItem = imageItem;
    }

    @Override
    public void update() {
        setImage(imageItem[(int) (new Date().getTime() / 400) % 2 + 1].getImage());
        deathTime -= 1.0 / App.gameAttribute.getFramesPerSecond();
        if (deathTime < 2) {
            if (this instanceof BombItem) {
                setImage(ITEM_IMAGE[4][(int) (new Date().getTime() / 100) % 2 + 1].getImage());
            } else if (this instanceof HeartItem) {
                setImage(ITEM_IMAGE[5][(int) (new Date().getTime() / 100) % 2 + 1].getImage());
            } else if (this instanceof PowerItem) {
                setImage(ITEM_IMAGE[6][(int) (new Date().getTime() / 100) % 2 + 1].getImage());
            } else {
                setImage(ITEM_IMAGE[7][(int) (new Date().getTime() / 100) % 2 + 1].getImage());
            }
        }
        if (deathTime < 0) {
            handleDeath();
        }
    }

    public abstract void powerUp(Character character);
}
