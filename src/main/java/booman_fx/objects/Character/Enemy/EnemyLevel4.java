package booman_fx.objects.Character.Enemy;

import booman_fx.game.Manager.EnemyLevel4Manager;

public class EnemyLevel4 extends Enemy {
    public EnemyLevel4(int xInMap, int yInMap) {
        super(xInMap, yInMap);
        controller = new EnemyLevel4Manager(this);
    }
}
