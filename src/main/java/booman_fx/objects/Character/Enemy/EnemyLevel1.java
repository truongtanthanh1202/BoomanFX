package booman_fx.objects.Character.Enemy;

import booman_fx.engine.Sprite;
import booman_fx.game.App;
import booman_fx.game.Manager.EnemyLevelManager;
import booman_fx.objects.Character.Character;
import booman_fx.objects.Character.Player.Player;
import booman_fx.objects.Item.HeartItem;
import booman_fx.objects.Item.Item;

import static booman_fx.Enum.StatusCharacter.IMMORTAL;

public class EnemyLevel1 extends Enemy {
    public EnemyLevel1(int realX, int realY) {
        super(realX, realY);
        controller = new EnemyLevelManager(this);
    }

    @Override
    public void executeCollision() {
        super.executeCollision();
        for (Sprite sprite : App.gameAttribute.sprites()) {
            if (this.checkCollision(sprite) && sprite instanceof Player && ((Player) sprite).getStatus() != IMMORTAL) {
                Character newP = (Character) sprite;
                newP.handleDeath();
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
