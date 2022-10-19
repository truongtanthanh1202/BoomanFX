package booman_fx.game.Manager;

import booman_fx.objects.Character.Enemy;

public class EnemyLevel4Manager extends EnemyManager {
    public EnemyLevel4Manager(Enemy enemy) {
        super(enemy);
    }

    @Override
    public void control() {
        if (enemy.checkCovered(enemy.getXInMap(), enemy.getYInMap())) {
            super.control();
            if (spritesMap.checkDanger(enemy.getXInMap(), enemy.getYInMap())) {
                if (!avoidBomb(1)) {
                    avoidBomb(0);
                }
            } else {
                checkStoreBomb(true, true, 1);
                findShortestWay();
            }
            setMove();
        }
    }
}
