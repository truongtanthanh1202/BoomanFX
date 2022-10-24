package booman_fx.objects.Item;

import booman_fx.game.GameResources.Images;
import booman_fx.objects.Character.Character;

import static booman_fx.Enum.TypeItem.BOMB_ITEM;

public class BombItem extends Item{
    private static final int MAX_BOMBS = 10;
    protected BombItem(int xInMap, int yInMap) {
        super(Images.items[BOMB_ITEM.ordinal()][0].getImage(), xInMap, yInMap, Images.items[BOMB_ITEM.ordinal()]);
    }

    @Override
    public void powerUp(Character character) {
        character.increaseNumBomb();
    }
}
