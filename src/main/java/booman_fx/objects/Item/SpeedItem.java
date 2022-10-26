package booman_fx.objects.Item;

import booman_fx.game.GameResources.Images;
import booman_fx.objects.Character.Character;

import static booman_fx.Enum.TypeItem.SPEED_ITEM;

public class SpeedItem extends Item {
    private static final int MAX_POWER_SPEED = 8;
    protected SpeedItem(int realX, int realY) {
        super(Images.items[SPEED_ITEM.ordinal()][0].getImage(), realX, realY, Images.items[SPEED_ITEM.ordinal()]);
    }

    @Override
    public void powerUp(Character character) {
        if(character.powerSpeedProperty().getValue() < MAX_POWER_SPEED){
            character.increasePowerSpeed();
        }
    }
}
