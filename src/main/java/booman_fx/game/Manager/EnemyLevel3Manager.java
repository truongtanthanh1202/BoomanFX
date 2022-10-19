package booman_fx.game.Manager;

import booman_fx.objects.Character.Enemy;

public class EnemyLevel3Manager extends EnemyManager {
    public EnemyLevel3Manager(Enemy enemy) {
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
