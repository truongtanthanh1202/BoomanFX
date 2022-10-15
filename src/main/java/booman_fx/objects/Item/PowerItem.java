package booman_fx.objects.Item;

import booman_fx.game.Images;

import static booman_fx.Enum.TypeItem.FLAME_ITEM;

public class PowerItem extends Item{
    private static final int MAX_POWER_ITEM = 10;

    protected PowerItem(int xInMap, int yInMap) {
        super(Images.items[FLAME_ITEM.ordinal()][0].getImage(), xInMap, yInMap, Images.items[FLAME_ITEM.ordinal()]);
    }
}
