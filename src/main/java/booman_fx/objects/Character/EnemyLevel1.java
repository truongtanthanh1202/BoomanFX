package booman_fx.objects.Character;

import booman_fx.Enum.StatusCharacter;
import booman_fx.game.Manager.EnemyLevel1Manager;
import booman_fx.engine.Sprite;
import booman_fx.game.App;
import booman_fx.objects.Item.HeartItem;
import booman_fx.objects.Item.Item;

import static booman_fx.Enum.StatusCharacter.IMMORTAL;

public class EnemyLevel1 extends Enemy {
    public EnemyLevel1(int xInMap, int yInMap) {
        super(xInMap, yInMap);
        controller = new EnemyLevel1Manager(this);
    }

    @Override
    public void executeCollision() {
        super.executeCollision();
        for (Sprite sprite : App.gameAttribute.sprites()) {
            if (this.checkCollision(sprite) && sprite instanceof Player && ((Player) sprite).getStatus() != IMMORTAL) {
                StatusCharacter.setStunned((Character) sprite);
            }
        }
    }

    @Override
    protected void collisionItem(Item item) {
        if (!(item instanceof HeartItem)) {
            item.powerUp(this);
        }
        item.handleDeath();
    }
}
