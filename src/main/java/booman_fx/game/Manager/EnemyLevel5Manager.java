package booman_fx.game.Manager;

import booman_fx.objects.Character.Enemy.Enemy;

public class EnemyLevel5Manager extends EnemyManager {
    public EnemyLevel5Manager(Enemy enemy) {
        super(enemy);
    }

    @Override
    public void control() {
        if (enemy.checkCovered(enemy.getXInMap(), enemy.getYInMap())) {
            super.control();
            if (spritesMap.checkDanger(enemy.getXInMap(), enemy.getYInMap())) {
                if (!avoidBomb(2)) {
                    if (!avoidBomb(1)) {
                        avoidBomb(0);
                    }
                }
            } else {
                checkStoreBomb(true, true, 2);
                findShortestWay();
            }
            setMove();
        }
    }
}
