package booman_fx.objects.Item;

import booman_fx.game.GameResources.Images;
import booman_fx.objects.Character.Character;

import static booman_fx.Enum.TypeItem.HEART_ITEM;

public class HeartItem extends Item{
    private static final int MAX_LIVES = 8;
    public HeartItem(int realX, int realY) {
        super(Images.items[HEART_ITEM.ordinal()][0].getImage(), realX, realY, Images.items[HEART_ITEM.ordinal()]);
    }

    @Override
    public void powerUp(Character character) {
        character.increaseLives();
    }
}
