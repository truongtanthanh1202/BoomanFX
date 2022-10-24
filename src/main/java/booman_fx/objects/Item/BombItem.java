package booman_fx.objects.Item;

import booman_fx.game.GameResources.Images;
import booman_fx.objects.Character.Character;

import static booman_fx.Enum.TypeItem.BOMB_ITEM;

public class BombItem extends Item{
    private static final int MAX_BOMBS = 8;
    protected BombItem(int realX, int realY) {
        super(Images.items[BOMB_ITEM.ordinal()][0].getImage(), realX, realY, Images.items[BOMB_ITEM.ordinal()]);
    }

    @Override
    public void powerUp(Character character) {
        character.increaseNumBomb();
    }
}
