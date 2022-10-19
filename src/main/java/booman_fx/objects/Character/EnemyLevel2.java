package booman_fx.objects.Character;

import booman_fx.game.Manager.EnemyLevel2Manager;
import booman_fx.objects.Item.HeartItem;
import booman_fx.objects.Item.Item;

public class EnemyLevel2 extends Enemy {
    public EnemyLevel2(int xInMap, int yInMap) {
        super(xInMap, yInMap);
        controller = new EnemyLevel2Manager(this);
    }

    @Override
    protected void collisionBox() {
    }

    @Override
    protected void collisionItem(Item item) {
        if(!(item instanceof HeartItem)){
            item.powerUp(this);
        }
        item.handleDeath();
    }
}
