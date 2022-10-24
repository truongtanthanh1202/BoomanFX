package booman_fx.objects.Character.Enemy;

import booman_fx.game.Manager.EnemyLevelManager;

public class EnemyLevel3 extends Enemy {
    public EnemyLevel3(int xInMap, int yInMap) {
        super(xInMap, yInMap);
        controller = new EnemyLevelManager(this);
    }
}
