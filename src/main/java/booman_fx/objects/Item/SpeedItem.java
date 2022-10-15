package booman_fx.objects.Item;

import booman_fx.game.Images;
import javafx.scene.image.Image;

import static booman_fx.Enum.TypeItem.SPEED_ITEM;

public class SpeedItem extends Item {
    private static final int MAX_POWER_SPEED = 10;
    protected SpeedItem(int xInMap, int yInMap) {
        super(Images.items[SPEED_ITEM.ordinal()][0].getImage(), xInMap, yInMap, Images.items[SPEED_ITEM.ordinal()]);
    }
}
