package booman_fx.objects.Item;

import booman_fx.Enum.TypeItem;
import booman_fx.game.Images;
import booman_fx.engine.Sprite;
import booman_fx.game.App;
import booman_fx.objects.Character.Character;
import javafx.scene.image.Image;
import static booman_fx.Enum.TypeSprite.*;
import java.util.Date;
import java.util.Random;

public abstract class Item extends Sprite{
    private Images[] imageItem;

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
    }
    public abstract void powerUp(Character character);
}
