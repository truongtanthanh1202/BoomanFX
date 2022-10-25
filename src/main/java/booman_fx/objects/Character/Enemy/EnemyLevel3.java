package booman_fx.objects.Character.Enemy;

import booman_fx.game.Manager.EnemyLevelManager;

public class EnemyLevel3 extends Enemy {
    public EnemyLevel3(int realX, int realY) {
        super(realX, realY);
        enemyManager = new EnemyLevelManager(this);
    }
}
