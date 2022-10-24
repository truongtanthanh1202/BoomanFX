package booman_fx.objects.Character.Enemy;

import booman_fx.game.Manager.EnemyLevelManager;

public class EnemyLevel4 extends Enemy {
    public EnemyLevel4(int realX, int realY) {
        super(realX, realY);
        controller = new EnemyLevelManager(this);
    }
}
