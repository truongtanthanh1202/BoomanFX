package booman_fx.controller;

import booman_fx.objects.Character.Enemy;

public class EnemyLevel4Controller extends EnemyController {
    public EnemyLevel4Controller(Enemy enemy) {
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
