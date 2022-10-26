package booman_fx.objects.Character.Enemy;

import booman_fx.game.Manager.EnemyLevelManager;
import booman_fx.objects.Item.HeartItem;
import booman_fx.objects.Item.Item;

public class EnemyLevel2 extends Enemy {
    public EnemyLevel2(int realX, int realY) {
        super(realX, realY);
        enemyManager = new EnemyLevelManager(this);
    }

    @Override
    protected void collisionBox() {
    }

    @Override
    protected void collisionItem(Item item) {
        if(!(item instanceof HeartItem)){
            item.powerUp(this);
        }
        item.death();
    }
}
