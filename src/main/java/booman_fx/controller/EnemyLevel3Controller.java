package booman_fx.controller;

import booman_fx.objects.Character.Enemy;

public class EnemyLevel3Controller extends EnemyController {
    public EnemyLevel3Controller(Enemy enemy) {
        super(enemy);
    }

    @Override
    public void control() {

        if (enemy.checkCovered(enemy.getXInMap(), enemy.getYInMap())) {
            super.control();
            checkStoreBomb(true, true, 0);
            if (spritesMap.checkDanger(enemy.getXInMap(), enemy.getYInMap())) {
                avoidBomb(0);
            } else {
                findShortestWay();
            }
            setMove();
        }
    }
}
